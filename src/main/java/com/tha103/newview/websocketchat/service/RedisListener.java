package com.tha103.newview.websocketchat.service;

import com.tha103.newview.user.jedis.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisListener {

    public static void start() {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedisPool().getResource();
            jedis.psubscribe(new KeyExpiredListener(), "__keyevent@1__:expired"); // 1 是 db 的 index 表示專門監聽該db1
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}

class KeyExpiredListener extends JedisPubSub {

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        System.out.println("Waiting for key expiration events...");
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("Key expired: " + message);

        String[] parts = message.split(":");

        if (parts.length == 4) {
            String cartName = parts[0];
            String userName = parts[1];
            String actID = parts[2];
            String seatNumber = parts[3];
            String value = userName + "," + actID + ",buy";
            System.out.println("seatData:" + actID + "," + seatNumber + "," + value);

            deleteSeatData(actID, seatNumber);
        } else {
            System.out.println("Invalid message format: " + message);
        }

        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedisPool().getResource();
            jedis.select(0); // 選擇 DB 0
            jedis.del(message); // 刪除對應的資料
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    // 删除方法
    private void deleteSeatData(String actID, String seatNumber) {
        Jedis jedis = null;
        try {
            jedis = JedisPoolUtil.getJedisPool().getResource();
            jedis.hdel("seatData:" + actID, seatNumber); 
            // 刪除座位資料
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
