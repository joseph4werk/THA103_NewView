package com.tha103.newview.discount.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.newview.admin.model.AdminVO;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.util.HibernateUtil;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hibernate.Session;
//
//import com.tha103.newview.orders.model.OrdersHibernateVO;
//import com.tha103.util.HibernateUtil;
//import com.tha103.util.Util;

public class DiscountDAOImpl implements DiscountDAO {

	@Override
	public int insert(DiscountVO discountVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(discountVO);
//			Integer id = (Integer) session.save(discountVO);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return 1;

	}

	@Override
	public int update(DiscountVO discountVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(discountVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	@Override
	public int delete(Integer discountNO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			DiscountVO discountVO = session.get(DiscountVO.class, discountNO);
			if (discountVO != null) {
				session.delete(discountVO);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public DiscountVO findByPrimaryKey(Integer discountNO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			DiscountVO discountVO = session.get(DiscountVO.class, discountNO);
			session.getTransaction().commit();
			return discountVO;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;

	}

	@Override
	public List<DiscountVO> getAll() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<DiscountVO> list = session.createQuery("from DiscountVO", DiscountVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
		
	}


	public static void main(String[] args) {
		DiscountDAO dao = new DiscountDAOImpl();

		// insert// <test ok>
//		DiscountVO discount1 = new DiscountVO();
//
//		AdminVO admin1 = new AdminVO();
//		admin1.setAdminID(1);
//		discount1.setAdminVO(admin1);
		
//		discount1.setDiscountContent("moon festival discount");
//		discount1.setDisAmount(100);
//		discount1.setDiscountCode("moon");
//		discount1.setDisStartDate(java.sql.Timestamp.valueOf("2023-09-28 00:00:00"));
//		discount1.setDisFinishDate(java.sql.Timestamp.valueOf("2023-09-29 00:00:00"));
//
//		dao.insert(discount1);

		// Update
//		DiscountVO discount2 = new DiscountVO();
//		
//		discount2.setDiscountNO(4);
		
//		PublisherVO publisher1 = new PublisherVO();
//		publisher1.setPubID(4);
//		discount2.setPublisherVO(publisher1);
		
//		if (DiscountVO.getAdminID() == null && DiscountVO.getPubID() != null)
		
//		AdminVO admin2 = new AdminVO();
//		admin2.setAdminID(1);
//		discount2.setAdminVO(admin2);
//		
//		discount2.setDiscountContent("聖誕節優惠");
//		discount2.setDisAmount(300);
//		discount2.setDiscountCode("taiwan");
//		discount2.setDisStartDate(java.sql.Timestamp.valueOf("2023-12-24 00:00:00"));
//		discount2.setDisFinishDate(java.sql.Timestamp.valueOf("2023-12-25 00:00:00"));
//		
//		dao.update(discount2);

		// Delete <test ok>
//		dao.delete(4);

		// findPK <test ok>
//		DiscountVO discount3 = dao.findByPrimaryKey(1);
//		System.out.println(discount3);

		// getAll <test ok>
		List<DiscountVO> list = dao.getAll();
		for (DiscountVO lists : list) {
			System.out.println(lists);
		}
	}
}
