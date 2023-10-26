package com.tha103.newview.orders.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;

import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.newview.user.model.UserVO;
import com.tha103.util.HibernateUtil;

public class OrdersDAOImpl implements OrdersDAO {

	private static Timestamp now = new Timestamp(System.currentTimeMillis());

	@Override
	public int insert(OrdersVO ordersVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(ordersVO);
//				Integer id = (Integer) session.save(ordersVO);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return 1;

	}

	@Override
	public int update(OrdersVO ordersVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(ordersVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	@Override
	public int delete(Integer orderID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			OrdersVO ordersVO = session.get(OrdersVO.class, orderID);
			if (ordersVO != null) {
				session.delete(ordersVO);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	@Override
	public OrdersVO findByPrimaryKey(Integer orderID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			OrdersVO ordersHibernateVO = session.get(OrdersVO.class, orderID);
			session.getTransaction().commit();
			return ordersHibernateVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;

	}

	@Override
	public List<OrdersVO> getAll() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<OrdersVO> list = session.createQuery("from OrdersVO", OrdersVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;

	}

}
