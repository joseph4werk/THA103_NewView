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

	public static void main(String[] args) {
		OrdersDAO dao = new OrdersDAOImpl();

		// insert// <test ok>
//		OrdersVO orders1 = new OrdersVO();
//		
//		UserVO user1 = new UserVO();
//		user1.setUserID(2);
//		
//		orders1.setUserVO(user1);
//		orders1.setOrdTotal(1000);
//		orders1.setDiscount(200);
//		orders1.setDiscountPrice(800);
//		orders1.setOrdTime(now);
//		
//		PublisherVO publisher1 = new PublisherVO();
//		publisher1.setPubID(1);
//		
//		orders1.setPublisherVO(publisher1);
//		orders1.setOrdType(1);
//		orders1.setActQuantity(1);
//		
//		DiscountVO discount1 = new DiscountVO();
//		discount1.setDiscountNO(2);
//		
//		orders1.setDiscountVO(discount1);
//		
//		dao.insert(orders1);

		// update <test ok>
//		OrdersVO orders2 = new OrdersVO();
//		
//		orders2.setOrderID(4);
//		
//		UserVO user2 = new UserVO();
//		user2.setUserID(3);
//		orders2.setUserVO(user2);
//		
//		orders2.setOrdTotal(2000);
//		orders2.setDiscount(300);
//		orders2.setDiscountPrice(1700);
//		orders2.setOrdTime(now);
//		
//		PublisherVO publisher2 = new PublisherVO();
//		publisher2.setPubID(1);
//		orders2.setPublisherVO(publisher2);
//
//		orders2.setOrdType(1);
//		orders2.setActQuantity(1);
//		
//		DiscountVO discount2 = new DiscountVO();
//		discount2.setDiscountNO(3);
//		orders2.setDiscountVO(discount2);
//		
//		dao.update(orders2);

		// delete <test ok>
		dao.delete(4);

		// find PK <test ok>
//		OrdersVO orders3 = dao.findByPrimaryKey(1);
//		System.out.println(orders3);
//		System.out.println("Success");
		

		// find ALL <test ok>
//		List<OrdersVO> list = dao.getAll();
//		for (OrdersVO lists : list) {
//			System.out.println(lists);
//		}
	}
}
