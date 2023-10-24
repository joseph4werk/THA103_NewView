package com.tha103.newview.websocketchat.service;

import java.util.Map;
import java.util.Set;

import javax.websocket.Session;

import seatModel.SeatInfo;

public interface RedisService {
	Set<SeatInfo> fetchSeatInfoFromRedis(Session userSession);
	String performRedisCancelOperations(String actName, String userName, String seatNumber);
	boolean isSeatSoldOut(String actID, String seatNumber);
	void storeSeatInfoInRedis(String actID, SeatInfo seatInfo);
	void deleteSeatDataFromRedis(String actID, String seatNumber, String userName);
	Map<String, String> markSeatsInRedis(String actID, String targetUserName);
	Map<String, String> getSeatDataFromRedis(String actID);	
	
}
