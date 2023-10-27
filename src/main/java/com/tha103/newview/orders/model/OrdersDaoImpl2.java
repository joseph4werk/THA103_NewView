package com.tha103.newview.orders.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.util.HibernateUtil;

public class OrdersDaoImpl2 implements OrdersDao2 {

	@Override
	public List<OrdersVO> selectByUserID(Integer userID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			var list = session.createQuery("FROM OrdersVO WHERE userID = :userID", OrdersVO.class)
					.setParameter("userID", userID)
					.list();
//			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
}
