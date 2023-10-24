package com.tha103.newview.websocketchat.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

@ServerEndpoint("/WebSocketChatWeb/{userName}/{actID}")
public class SeatMiddle {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	private static final Set<SeatInfo> seatInfoList = Collections.synchronizedSet(new HashSet<>());
	private static Map<String, String> userIdToUserNameMap = new ConcurrentHashMap<>(); // 測試階段
	private static final ExecutorService executorService = Executors.newCachedThreadPool(); 
	private static final Map<String, Session> actIDToSessionMap = new ConcurrentHashMap<>();
	private static Map<String, List<SeatInfo>> seatInfoMap = new ConcurrentHashMap<>();
	// 方法區
	private String getActIDFromSession(Session userSession) {
	    //獲得用戶actID
	    return (String) userSession.getUserProperties().get("actID");
	}

	private void sendRedisDataToClients(Session userSession) {
	    try (Jedis jedis = new Jedis("localhost")) {
	        Set<String> keys = jedis.keys("seatData:*");
	        Gson gson = new Gson();
	        Long currentDB = jedis.getDB();
	        
	        // 陣列收集
	        Set<SeatInfo> allSeatInfo = new HashSet<>();
	     
	        String actID = (String) userSession.getUserProperties().get("actID");
	       
	        for (String key : keys) {
	            Map<String, String> data = jedis.hgetAll(key);

	            for (Map.Entry<String, String> entry : data.entrySet()) {
	                String keyName = entry.getKey();
	                String value = entry.getValue();
	                
	               
	                // 解析座位信息的 actID
	                String[] parts = value.split(",");
	                String seatActID = parts[1];
	             
	                if (seatActID.equals(actID) &&  !parts[0].isEmpty()) {
	                
	                    SeatInfo seatInfo = new SeatInfo(parts[0], keyName,parts[1]+","+parts[2],parts[1]);
	                    allSeatInfo.add(seatInfo);
	                }
	            }
	        }
	        
	        // 所有座位信息打包成 JSON
	        String jsonString = gson.toJson(allSeatInfo);
	        
	        //  JSON 消息送到 WebSocket
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

	private void handleCancelOperation(Message message, Session userSession) {
	    String userName = message.getUserName();
	    String seatNumber = message.getSeatNumber();
	    String seatType = message.getType();
	    String actName = message.getActID();

	    Jedis jedis = null;
//	    System.out.println("Canceled " + seatNumber + " for user: " + userName + "  type  " + seatType + "  Name  " + actName);
	    try {
	        jedis = new Jedis("localhost");

	        //  Redis 中得到座位信息
	        String existingSeatInfo = jedis.hget("seatData:" + actName, seatNumber);

	        if (existingSeatInfo != null && existingSeatInfo.endsWith(userName + "," + actName + "," + "buy")) {
	            // 更新消息中的 "buy" 為 "cancel"
	            String updatedSeatInfoInRedis = existingSeatInfo.replace("buy", "cancel");
//	            System.out.println("red   " + updatedSeatInfoInRedis);
	            updateSeatInfoInList(actName, seatNumber, userName + "," + actName + "," + "cancel");
	            if (updatedSeatInfoInRedis != null && updatedSeatInfoInRedis.endsWith(userName + "," + actName + "," + "cancel")) {
	               
	                sendSeatInfoToActIDUsers(actName, userSession);

	                // Redis 刪除
//	                System.out.println(actName + "," + userName + "," + seatType);
	                jedis.hdel("seatData:" + actName, seatNumber, actName + "," + userName + "," + seatType);

	                // 在 seatInfos 中更新座位信息
	                

//	                System.out.println("Seat info successfully updated and deleted from Redis: " + updatedSeatInfoInRedis);
	            } else {
	                System.out.println("Failed to update seat info.");
	            }
	        }
	    } finally {
	        jedis.close();
	    }
	}


	private void updateSeatInfoInList(String actID, String seatNumber, String updatedSeatInfo) {
	    List<SeatInfo> seatInfos = seatInfoMap.get(actID);
	    if (seatInfos != null) {
	        for (SeatInfo seatInfo : seatInfos) {
	            if (seatInfo.getSeatNumber().equals(seatNumber)) {
	                // 更新座位信息中的 "buy" 为 "cancel"
	                seatInfo.setSeatType(actID+",cancel");
	                break; // 找到後停止
	            }
	        }
	    }
	}





	// seatInfoMap删除座位
	private void removeSeatInfoFromMap(String actID, String seatNumber, String seatInfo) {
	    List<SeatInfo> seatInfos = seatInfoMap.get(actID);
//	    System.out.println(seatInfo + "   op");
	    if (seatInfos != null) {
	        Iterator<SeatInfo> iterator = seatInfos.iterator();
	        while (iterator.hasNext()) {
	            SeatInfo info = iterator.next();
//	            System.out.println(info.getSeatType()+"刪除");
	            if (info.getSeatNumber().equals(seatNumber)) {
	                iterator.remove();
	            
	            }
	        }

	      
	        int seatInfosLength = seatInfos.size();
//	        System.out.println("seatInfos ：" + seatInfosLength);
	    }
	}


	
	private void handleBuyOperation(Message message, Session userSession) {
	    String userName = message.getUserName();
	    String seatNumber = message.getSeatNumber();
	    String seatType = message.getType();
//	    System.out.println(seatType);
	    String actID = message.getActID();
	    Jedis jedis = null;
	    try {
	        jedis = new Jedis("localhost");

	        String existingSeatInfo = jedis.hget("seatData:" + actID, seatNumber);
	        if (existingSeatInfo != null && existingSeatInfo.endsWith(",soldOut")) {
	            return;
	        }
	        String[] seatTypeParts = seatType.split(",");
	        SeatInfo seatInfo = new SeatInfo(userName, seatNumber, actID + "," + seatType, actID);
	        storeSeatInfoInRedis(actID, seatInfo);
	        storeSeatInfoInMap(actID, seatInfo, userSession); // Session 方法
//	        System.out.println(actID + "," + userName + "," + seatType + "," + seatNumber);
	        jedis.expire("seatData:" + actID, 1800);

	        String updatedSeatInfoMessage = buildUpdatedSeatInfoMessage(actID);
	        sendMessageToActID(actID, updatedSeatInfoMessage);
	    } finally {
	        if (jedis != null) {
	            jedis.close();
	        }
	    }
	}

	/*新增功能為  向 相同actID  發送消息*/
	private void sendSeatInfoToActIDUsers(String actID, Session userSession) {
	    List<SeatInfo> seatInfos = seatInfoMap.get(actID);
	    if (seatInfos != null) {
	        Gson gson = new Gson();
	        String seatInfoJson = gson.toJson(seatInfos);
	        
	        for (Session session : connectedSessions) {
	            String userActID = (String) session.getUserProperties().get("actID");
	            if (actID.equals(userActID) && session.isOpen() && !session.equals(userSession)) {
	                session.getAsyncRemote().sendText(seatInfoJson);
	            }
	        }
	    }
	}


		// Redis儲存
		private void storeSeatInfoInRedis(String actID, SeatInfo seatInfo) {
		    Jedis jedis = new Jedis("localhost");
		    try {
		        if (!jedis.exists("seatData:" + actID)) {
		            jedis.hset("seatData:" + actID, seatInfo.getSeatNumber(), seatInfo.getUserName() + "," + seatInfo.getSeatType());
		        } else {
		            jedis.hset("seatData:" + actID, seatInfo.getSeatNumber(), seatInfo.getUserName() + "," + seatInfo.getSeatType());
		        }
		    } finally {
		        jedis.close();
		    }
		}
	
		// map儲存
		private void storeSeatInfoInMap(String actID, SeatInfo seatInfo, Session userSession) {
		    seatInfoMap.computeIfAbsent(actID, k -> new ArrayList<>()).add(seatInfo);

		    //  actID 的用户，排除自己
		    sendSeatInfoToActIDUsers(actID, userSession);
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


	private void broadcastSeatInfoToAllExcept(String excludedUserName, Session excludedSession) {
	    Gson gson = new Gson();
	    
	   
	    List<SeatInfo> filteredSeatInfoList = new ArrayList<>();
	    
	    String userActID = (String) excludedSession.getUserProperties().get("actID"); //得到自己的actID
	    
	    for (SeatInfo seatInfo : seatInfoList) {
	        String seatUserActID = seatInfo.getActID();
	        
	        
	        if (!excludedUserName.equals(seatInfo.getUserName()) && userActID.equals(seatUserActID)) {
	            filteredSeatInfoList.add(seatInfo);
	        }
	    }
	    
	    String seatInfoJson = gson.toJson(filteredSeatInfoList);

	    for (Session session : connectedSessions) {
	        if (session.isOpen() && !session.equals(excludedSession)) {
	            session.getAsyncRemote().sendText(seatInfoJson);
	        }
	    }
	}

	private void deleteSeatsByUserNameALL(String actID, String userName,Session session ) {
	    Jedis jedis = null;
	    try {
	        jedis = new Jedis("localhost");

	        // 離線刪除 所有相關
	        Map<String, String> seatData = jedis.hgetAll("seatData:" + actID);

	        for (Map.Entry<String, String> entry : seatData.entrySet()) {
	            String seatNumber = entry.getKey();
	            String seatInfo = entry.getValue();
	            String[] seatInfoParts = seatInfo.split(",");
	            

	            if (seatInfoParts.length >= 3 && seatInfoParts[0].equals(userName) && !seatInfoParts[2].equals("soldOut")) {
	               
	                updateSeatInfoInList(actID, seatNumber, userName + "," + actID + "," + "cancel");
	                // 更新座位信息 "cancel"，只在程式內部更改 seatInfo 的值
	                seatInfo = seatInfo.replace("buy", "cancel");
	                
	                
	                // 删除 Redis 中的座位信息，不存回 Redis
	                jedis.hdel("seatData:" + actID, seatNumber);
	            }
	        }

	      
	        jedis.del("seatData:" + actID + ":userName:" + userName);
	       
	        // 推送更新后的座位信息
	        sendSeatInfoToActIDUsers(actID,session);
	    } finally {
	        if (jedis != null) {
	            jedis.close();
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
//	                    System.out.println("Deleted seat: " + seatNumber + " for user: " + userName);
	                }
	            }

	            cursor = scanResult.getStringCursor();
	        } while (!"0".equals(cursor));

	       
	        pipeline.sync();
	    }
	}


	
	public static void markSeatsAsSoldOutByUserName(String actID, String targetUserName) {
	    Jedis jedis = null;
	    JsonObject soldOutSeatsJson = new JsonObject();
	    int modificationCount = 0;
	    Map<String, String> modifiedSeatsData = new HashMap<>(); //儲存已修改數據

	    try {
	        jedis = new Jedis("localhost");
	        Map<String, String> seatsData = jedis.hgetAll("seatData:" + actID);

	        for (Map.Entry<String, String> entry : seatsData.entrySet()) {
	            String seatNumber = entry.getKey();
	            String seatInfo = entry.getValue();

	            String[] seatInfoParts = seatInfo.split(",");
	            String actName = seatInfoParts[1];
	            String userName = seatInfoParts[0];
	            String seatType = seatInfoParts[2];

	            if (userName.equals(targetUserName) && !seatType.equals("soldOut")) {
	                String newSeatInfo = userName + "," + actName + ",soldOut";
	                jedis.hset("seatData:" + actID, seatNumber, newSeatInfo);
	                modifiedSeatsData.put(seatNumber, newSeatInfo);
	                modificationCount++;
	            }
	        }
	    } finally {
	        if (jedis != null) {
	            jedis.close();
	        }
	    }

	    System.out.println("Modification Count: " + modificationCount);

	    if (modificationCount > 0) {
	        // 只傳遞一次modificationCount
	        sendMarkedDataToServlet(actID, targetUserName, modifiedSeatsData, modificationCount);
	    }

	    System.out.println(soldOutSeatsJson.toString());
	}

	private static void sendMarkedDataToServlet(String actID, String targetUserName, Map<String, String> modifiedSeatsData, int modificationCount) {
	    try {
	        String servletUrl = "http://localhost:8080/com.tha103.newview/SeatOrderList"; 
	        URL url = new URL(servletUrl);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setDoOutput(true);
	        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

	        StringBuilder postDataBuilder = new StringBuilder();
	        postDataBuilder.append("actID=").append(URLEncoder.encode(actID, "UTF-8"));
	        postDataBuilder.append("&targetUserName=").append(URLEncoder.encode(targetUserName, "UTF-8"));
	        
	        // 添加修改後的數據
	        for (Map.Entry<String, String> entry : modifiedSeatsData.entrySet()) {
	            String seatNumber = entry.getKey();
	            String seatData = entry.getValue();
	            postDataBuilder.append("&seatNumber=").append(URLEncoder.encode(seatNumber, "UTF-8"));
	            postDataBuilder.append("&seatData=").append(URLEncoder.encode(seatData, "UTF-8"));
	        }
	        
	        postDataBuilder.append("&modificationCount=").append(modificationCount); 

	        OutputStream os = conn.getOutputStream();
	        os.write(postDataBuilder.toString().getBytes("UTF-8"));
	        os.flush();
	        os.close();

	       
	        int responseCode = conn.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	        
	        }

	        conn.disconnect();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}



	
	private void sendMessageToActID(String actID, String message) {
	    List<Session> sessions = (List<Session>) actIDToSessionMap.get(actID);
	   
	    if (sessions != null) {
	        Gson gson = new Gson();
	        String seatInfoJson = gson.toJson(message);

	        for (Session session : sessions) {
	        	
	            if (session.isOpen()) {
	                session.getAsyncRemote().sendText(seatInfoJson);
	               
	            }
	        }
	    }
	}

	 
	  private void sendNotificationToActID(String actID, String notificationMessage) {
	        String message = buildNotificationMessage(notificationMessage);
	        sendMessageToActID(actID, message);
	    }
	  
	  private String buildNotificationMessage(String notificationMessage) {
	        JsonObject json = new JsonObject();
	        json.addProperty("type", "notification");
	        json.addProperty("message", notificationMessage);
	        return json.toString();
	    }

	  private String buildUpdatedSeatInfoMessage(String actID) {
		    
		    List<SeatInfo> seatInfos = seatInfoMap.get(actID);
		    Gson gson = new Gson();
		    String seatInfoJson = gson.toJson(seatInfos);
		    
		    
		    JsonObject json = new JsonObject();
		    json.addProperty("type", "seatInfoUpdate");
		    json.addProperty("actID", actID);
		    json.addProperty("seatInfo", seatInfoJson);
		    
		    return json.toString();
		}
	  
	// 方法區
	  @OnOpen
	  public void onOpen(@PathParam("userName") String userName, @PathParam("actID") String actID, Session userSession) throws IOException {
	      synchronized (connectedSessions) {
	          connectedSessions.add(userSession);
	      }
	      
	      userIdToUserNameMap.put(userSession.getId(), userName);
	      userSession.getUserProperties().put("actID", actID);

//	      System.out.println("WebSocket connected - userName: " + userName + ", actID: " + actID);

	      sendRedisDataToClients(userSession);
	  }

	  @OnMessage
	  public void onMessage(Session userSession, String message) {
	      executorService.submit(() -> {
	          try {
	              
//	              System.out.println("Received message: " + message);
	              
	              handleMessage(userSession, message, 100);
	          } catch (Exception e) {
	              e.printStackTrace();
	          }
	      });
	  }


	  private void handleMessage(Session userSession, String message, long delayMillis) {
		    Gson gson = new Gson();

		    try {
		        Message messageObject = gson.fromJson(message, Message.class);
		        String senderActID = messageObject.getActID();
		        String messageType = messageObject.getType();
		        
		        
		      
		        String userActID = (String) userSession.getUserProperties().get("actID");
		        System.err.println( userActID);
		       
		        if (senderActID.equals(userActID)) {
		            if ("cancel".equals(messageType)) {
		                handleCancelOperation(messageObject, userSession);
		            } else if ("buy".equals(messageType)) {
		                handleBuyOperation(messageObject, userSession);
		            } else if ("disconnect".equals(messageType)) {
		            	 System.err.println("disconnect");
		            	 deleteSeatsByUserNameALL(senderActID, messageObject.getUserName(),userSession);
		            } else if ("soldOut".equals(messageType)) {
		                markSeatsAsSoldOutByUserName(senderActID, messageObject.getUserName());
		            } 
		        }
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
