package com.tha103.newview.cart.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import redis.clients.jedis.Jedis;

public class TestJedisHash3 {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost", 6379);
		
//		jedis.hset("pen:1", "brand", "SKB");
//		jedis.hset("pen:1", "price", "10");
//		System.out.println("Pen 1's brand is: " + jedis.hget("pen:1", "brand"));
//		System.out.println("Pen 1's price is: " + jedis.hget("pen:1", "price"));
//		
		HashMap<String, String> data = new HashMap<>();
		
		data.put("1", "2,3,buy");
		data.put("1", "20,30,buy");
		data.put("2", "4,5,buy");
		data.put("2", "40,50,buy");
		jedis.hmset("seatData:1", data);
		
		data.put("1", "1,2,buy");
		data.put("1", "10,20,buy");
		data.put("2", "3,4,buy");
		data.put("2", "30,40,buy");
//		data.put("brand", "Pentel");
//		data.put("price", "50");
//		// 無須對應ORM設計，可自由為key增減自己需要的欄位與欄位值
//		data.put("color", "blue");
//		
		jedis.hmset("seatData:2", data);

//		List<String> penData = jedis.hmget("pen:2", "brand", "price", "color");
//		System.out.println("HMGET: ");
//		for (String str : penData)
//			System.out.println(str);
//		
//		Map<String, String> hAll = jedis.hgetAll("pen:2");
//		for (String str : hAll.keySet())
//			System.out.println(str + " = " + hAll.get(str));
		
		// 註：Hash沒有提供hincr(遞增)指令，只有hincrby指令可用
		
		jedis.close();
	}

}
