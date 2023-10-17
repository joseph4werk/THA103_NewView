package com.tha103.newview.orderlist.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.util.HibernateUtil;

public class OrderListDAOImpl implements OrderListDAO {

	@Override
	public int insert(OrderListVO orderListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.update(orderListVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(OrderListVO orderListVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.update(orderListVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer orderListID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			OrderListVO orderList = session.get(OrderListVO.class, orderListID);
			if (orderList != null) {
				session.delete(orderList);
			}
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public OrderListVO findeByPrimaryKey(Integer orderListID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			OrderListVO orderList = session.get(OrderListVO.class, orderListID);
			session.getTransaction().commit();
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<OrderListVO> getAll() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			List<OrderListVO> orderList = session.createQuery("from OrderListVO", OrderListVO.class).list();
			session.getTransaction().commit();
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
}
