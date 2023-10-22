package com.tha103.newview.cart.dao.impl;

import org.hibernate.Session;

import com.tha103.newview.cart.dao.CartDao;
import com.tha103.newview.cart.vo.Act;
import com.tha103.newview.usediscount.model.UseDiscountVO;
import com.tha103.util.HibernateUtil;

import redis.clients.jedis.Jedis;

public class CartDaoImpl implements CartDao {

	@Override
	public String getSeat(Integer actID, Integer userID) {
		try (Jedis jedis = new Jedis("localhost", 6379)) {
			var list = jedis.hmget("seatData:" + actID, String.valueOf(userID));
			return list != null && !list.isEmpty() ? list.get(0) : null;
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
			StringBuilder hql = new StringBuilder("SELECT t2.ditUsed FROM DiscountVO t1 JOIN UseDiscount t2 WITH t1.discountNO = t2.discountNO ")
					.append("WHERE t1.discountCode = :discountCode AND t2.userID = :userID");
			var vo = session.createQuery(hql.toString(), Integer.class)
					.setParameter("discountCode", discountCode)
					.setParameter("userID", userID)
					.uniqueResult();
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
