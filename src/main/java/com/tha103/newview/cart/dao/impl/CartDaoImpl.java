package com.tha103.newview.cart.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;

import com.tha103.newview.cart.dao.CartDao;
import com.tha103.newview.cart.vo.Act;
import com.tha103.util.HibernateUtil;

import redis.clients.jedis.Jedis;

public class CartDaoImpl implements CartDao {
	public static void main(String[] args) {
		CartDaoImpl CartDaoImpl = new CartDaoImpl();
		var v = CartDaoImpl.getSeat(2,2);

		System.out.println(v + "ABC");
	}

	@Override
	public String getSeat(Integer actID, Integer userID) {
		try (Jedis jedis = new Jedis("localhost", 6379)) {
//			int maxHashes = 10; // 哈希表的最大序号
			String prefixToMatch = String.valueOf(userID); // 要匹配的值的前缀
//
//			// 使用正则表达式匹配存在的哈希表
//			Pattern pattern = Pattern.compile("^seatData:(\\d+)$");
			String s = "";
//
//			// 使用 keys 命令获取匹配的哈希表名称
//			Set<String> hashNames = jedis.keys("seatData:*");
//
//			for (String hashName : hashNames) {
//				// 使用正则表达式匹配哈希表的序号
//				Matcher matcher = pattern.matcher(hashName);
//			
//				if (matcher.matches()) {
//					int hashIndex = Integer.parseInt(matcher.group(1));
//					if (hashIndex <= maxHashes) {
//						// 构造哈希表的名称
						String hashKey = "seatData:" + actID;

						// 使用 hgetAll 方法获取指定哈希表的所有键值对
						Map<String, String> seatData = jedis.hgetAll(hashKey);

						// 遍历键值对，并筛选值中左边以 "2" 开头的数据
						for (Map.Entry<String, String> entry : seatData.entrySet()) {
							String key = entry.getKey();
							String value = entry.getValue();
							if (value.startsWith(prefixToMatch)) {
								s += key + ",";
								System.out.println("Hash: " + hashKey + ", Key: " + key + ", Value: " + value);
							}
						}
					
				
			

			jedis.close(); // 关闭连接

			return s != null && !s.isEmpty() ? s : null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Act selectByActId(Integer actId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			var act = session.get(Act.class, actId);
			session.getTransaction().commit();
			return act;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public long deleteByActIDAndUserID(Integer actID, Integer userID) {
		try (Jedis jedis = new Jedis("localhost", 6379)) {
			return jedis.hdel("seatData:" + actID, String.valueOf(userID));
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Integer selectByDiscountCodeAndUserID(String discountCode, Integer userID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			StringBuilder hql = new StringBuilder(
					"SELECT t2.ditUsed FROM DiscountVO t1 JOIN UseDiscount t2 WITH t1.discountNO = t2.discountNO ")
					.append("WHERE t1.discountCode = :discountCode AND t2.userID = :userID");
			var vo = session.createQuery(hql.toString(), Integer.class).setParameter("discountCode", discountCode)
					.setParameter("userID", userID).uniqueResult();
			session.getTransaction().commit();
			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

//	public Integer findDisAmountByDiscountCodeAndUserID(String discountCode, Integer userID) {
//	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//	    try {
//	        session.beginTransaction();
//
//	        // 使用HQL查询
//	        String hql = "SELECT dis.disAmount FROM Discount dis WHERE dis.discountCode = :code AND dis.userID = :userId";
//	        Query<Integer> query = session.createQuery(hql, Integer.class);
//	        query.setParameter("code", discountCode);
//	        query.setParameter("userId", userID);
//
//	        Integer disAmount = query.uniqueResult();
//
//	        session.getTransaction().commit();
//	        return disAmount;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        session.getTransaction().rollback();
//	        return null;
//	    }
//	}

//	@Override
//	public long updateDitUsedByDiscountNOAndUserID(Integer discountNO, Integer userID) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			var disAmount = session.get(Discount.class, discountNO);
//			session.getTransaction().commit();
//			return act;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return null;
//		}
//}
}
