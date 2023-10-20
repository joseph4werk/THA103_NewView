package com.tha103.newview.websocketchat.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
 
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ScanResult;
import seatModel.Message;
import seatModel.SeatInfo;
import seatModel.SeatInfoCancel;

@ServerEndpoint("/WebSocketChatWeb/{userName}")
public class SeatMiddle {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private static final Set<SeatInfo> seatInfoList = Collections.synchronizedSet(new HashSet<>());
	private static Map<String, String> userIdToUserNameMap = new ConcurrentHashMap<>(); // 測試階段
	private static final ExecutorService executorService = Executors.newCachedThreadPool();

	// 方法區
	

	private void sendRedisDataToClients(Session userSession) {
	    try (Jedis jedis = new Jedis("localhost")) {
	        Set<String> keys = jedis.keys("seatData:*");
	        Gson gson = new Gson();
	        Long currentDB = jedis.getDB();
	        
	        // 陣列收集
	        Set<SeatInfo> allSeatInfo = new HashSet<>();
	        
	        for (String key : keys) {
	            Map<String, String> data = jedis.hgetAll(key);
	            
	            for (Map.Entry<String, String> entry : data.entrySet()) {
	                String keyName = entry.getKey();
	                String value = entry.getValue();
	                
	                SeatInfo seatInfo = new SeatInfo("pk: " + currentDB, keyName, value);
	                String dataSplit = seatInfo.getSeatType();
	                String[] parts = dataSplit.split(",");
	                
	                if (parts[1] != "soldOut") {
	                    seatInfo = new SeatInfo(parts[0], seatInfo.getSeatNumber(), parts[1]);
	                    allSeatInfo.add(seatInfo);
	                }
	            }
	        }
	        
	        // 將所有座位信息打包
	        String jsonString = gson.toJson(allSeatInfo);
	        
	        //將JSON消息送去WebSocket
	        userSession.getAsyncRemote().sendText(jsonString);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



//	
	private void broadcastUserStatus(String userName, String userStatus) {
		for (Session session : connectedSessions) {
			if (session.isOpen()) {
				synchronized (session) {
					try {
						JsonObject json = new JsonObject();
						json.addProperty("type", "userStatus");
						json.addProperty("userName", userName);
						json.addProperty("userStatus", userStatus);
						session.getBasicRemote().sendText(json.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private void handleCancelOperation(Message message) {
		String userName = message.getUserName();
		String seatNumber = message.getSeatNumber();
		String seatType = message.getType();
		String actName = message.getActID();
		Jedis jedis = null;

		try {

			jedis = new Jedis("localhost");

			jedis.hdel("seatData:" + actName, seatNumber, userName + "," + seatType);
		} finally {
			jedis.close();
		}
	}

	private synchronized void handleBuyOperation(Message message) {
	    String userName = message.getUserName();
	    String seatNumber = message.getSeatNumber();
	    String seatType = message.getType();
	    String actID = message.getActID();
	    Jedis jedis = null;
	    try { 
	        jedis = new Jedis("localhost");

	        jedis.hset("seatData:" + actID, seatNumber, userName + "," + seatType);
	        jedis.expire(seatNumber, 1800);
	    } finally {
	        jedis.close();
	    }
	}

//	private synchronized void handleDisconnectOperation(String userName,String actID) {
//	    Jedis jedis = null;
//	    try {
//	        jedis = new Jedis("localhost");
//
//	        jedis.hdel("seatData:" + actID, userName);
//	    } finally {
//	        jedis.close();
//	    }
//	}
	private String extractUserNameFromSession(Session userSession) {

		Map<String, String> pathParameters = userSession.getPathParameters();
		return pathParameters.get("userName");
	}

	private synchronized void removeSeatSelectionsForUser(String userName, Session excludedSession) {
	    Iterator<SeatInfo> iterator = seatInfoList.iterator();
	    while (iterator.hasNext()) {
	        SeatInfo info = iterator.next();
	        if (info.getUserName().equals(userName)) {
	            iterator.remove(); 
	        }
	    }

	    broadcastSeatInfoToAllExcept(userName, excludedSession);
	}


	// broadcastSeatInfoToAll 方法排除特定用户
	private void broadcastSeatInfoToAllExcept(String excludedUserName, Session excludedSession) {
		Gson gson = new Gson();
		String seatInfoJson = gson.toJson(seatInfoList);

		for (Session session : connectedSessions) {
			if (session.isOpen() && !session.equals(excludedSession)) {
				session.getAsyncRemote().sendText(seatInfoJson);
			}
		}
	}

	private void broadcastSeatInfoToAll() {
		Gson gson = new Gson();
		String seatInfoJson = gson.toJson(seatInfoList);
		for (Session session : connectedSessions) {
			if (session.isOpen()) {
				synchronized (session) {
					session.getAsyncRemote().sendText(seatInfoJson);
				}
			}
		}
	}

	public static void deleteSeatsByUserName(String actID, String targetUserName) {
	    try (Jedis jedis = new Jedis("localhost")) {
	        String cursor = "0";
	        Pipeline pipeline = jedis.pipelined();

	        do {
	            ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan("seatData:" + actID, cursor);
	            List<Map.Entry<String, String>> entries = scanResult.getResult();

	            for (Map.Entry<String, String> entry : entries) {
	                String seatNumber = entry.getKey();
	                String seatInfo = entry.getValue();

	                String[] seatInfoParts = seatInfo.split(",");
	                String userName = seatInfoParts[0];
	                String seatType = seatInfoParts[1];

	                if (userName.equals(targetUserName) && !seatType.equals("soldOut")) {
	                   
	                    pipeline.hdel("seatData:" + actID, seatNumber);
	                    System.out.println("Deleted seat: " + seatNumber + " for user: " + userName);
	                }
	            }

	            cursor = scanResult.getStringCursor();
	        } while (!"0".equals(cursor));

	       
	        pipeline.sync();
	    }
	}


	public static void markSeatsAsSoldOutByUserName(String actID, String targetUserName) {
		Jedis jedis = null;
		try {
			jedis = new Jedis("localhost");

			Map<String, String> seatsData = jedis.hgetAll("seatData:" + actID);

			for (Map.Entry<String, String> entry : seatsData.entrySet()) {
				String seatNumber = entry.getKey();
				String seatInfo = entry.getValue();

				String[] seatInfoParts = seatInfo.split(",");
				String userName = seatInfoParts[0];
				String seatType = seatInfoParts[1];

				if (userName.equals(targetUserName) && !seatType.equals("soldOut")) {
					String newSeatInfo = userName + ",soldOut";
					jedis.hset("seatData:" + actID, seatNumber, newSeatInfo);
					System.out.println("Marked seat: " + seatNumber + " as soldOut for user: " + userName);
				}
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	// 方法區
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		 synchronized (connectedSessions) {
		        connectedSessions.add(userSession);
		    }
		connectedSessions.add(userSession);
		sendRedisDataToClients(userSession);
		userIdToUserNameMap.put(userSession.getId(), userName);
		 synchronized (userIdToUserNameMap) {
		        userIdToUserNameMap.put(userSession.getId(), userName);
		    }
		// String text = String.format("Session ID = %s, connected; userName = %s",
		// userSession.getId(), userName);
		// System.out.println(text);
		//
		 sendRedisDataToClients(userSession);
		// broadcastUserStatus(userName, "Online");
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
	    executorService.submit(() -> {
	        try {
	            handleMessage(userSession, message, 100);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    });
	}

	private void handleMessage(Session userSession, String message, long delayMillis) {
	    Gson gson = new Gson();

	  
	    Set<Session> sessionsToSend = new HashSet<>();

	    synchronized (connectedSessions) {
	        for (Session session : connectedSessions) {
	            if (session.isOpen() && !session.equals(userSession)) {
	                sessionsToSend.add(session);
	            }
	        }
	    }

	    
	    synchronized (sessionsToSend) {
	        sessionsToSend.removeIf(session -> connectedSessions.contains(session));
	    }

	    for (Session session : sessionsToSend) {
	        try {
	            Thread.sleep(delayMillis);
	            session.getAsyncRemote().sendText(message);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    try {
	        Message messageObject = gson.fromJson(message, Message.class);
	        SeatInfoCancel SeatInfoCancelObject = gson.fromJson(message, SeatInfoCancel.class);
	        SeatInfo SeatInfoObject = gson.fromJson(message, SeatInfo.class);

	        String senderActID = messageObject.getActID();
	        String messageType = messageObject.getType();
	        String SeatInfoType = SeatInfoObject.getSeatType();
	        String SeatInfoCancelType = SeatInfoCancelObject.getUserType();
//	        System.err.println(messageType);
//	        System.err.println(SeatInfoType);
	        System.err.println(SeatInfoCancelType);

	        if (("cancel".equals(messageType)) && (!"disconnect".equals(SeatInfoCancelType))) {
	            System.err.println("取消");
	            handleCancelOperation(messageObject);
	        } else if (("buy".equals(messageType)) && (!"disconnect".equals(SeatInfoCancelType))) {
	            System.err.println("買");
	            handleBuyOperation(messageObject);

	        } else if ("disconnect".equals(SeatInfoCancelType) && (!"soldOut".equals(messageType))) {
	            System.err.println("斷開連結 斷開鎖鏈 斷開一切的牽連");
	            deleteSeatsByUserName(messageObject.getActID(), messageObject.getUserName());

	        } else if ("soldOut".equals(messageType)) {
	            System.err.println("先來先贏 慢來拚輸贏");
	            markSeatsAsSoldOutByUserName(messageObject.getActID(), messageObject.getUserName());
	        }

	        for (Session session : connectedSessions) {
	            if (session.isOpen() && !session.equals(userSession)) {
	                session.getAsyncRemote().sendText(message);
	            }
	        }

	        System.out.println("Message received from " + senderActID + ": " + message);
	    } catch (JsonSyntaxException e) {
	        System.err.println("Failed to parse JSON message: " + message);
	    }
	}


	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
	    synchronized (connectedSessions) {
	        connectedSessions.remove(userSession);
	    }
	    
	    String userName = extractUserNameFromSession(userSession);
	    
	    if (!isUserSeatSoldOut(userName)) {
	        broadcastSeatInfoToAllExcept(userName, userSession);
	    }
	    
	   
	    
	    String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
	            userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
	    System.out.println(text);
	}

	
	private boolean isUserSeatSoldOut(String userName) {
	    synchronized (seatInfoList) {
	        return seatInfoList.stream().anyMatch(info -> userName.equals(info.getUserName()) && "soldOut".equals(info.getSeatType()));
	    }
	}


	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
