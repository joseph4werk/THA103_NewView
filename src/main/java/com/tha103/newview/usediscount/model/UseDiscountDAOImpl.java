package com.tha103.newview.usediscount.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.util.HibernateUtil;

public class UseDiscountDAOImpl implements UseDiscountDAO {

	@Override
	public int insert(UseDiscountVO useDiscountVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.update(useDiscountVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(UseDiscountVO useDiscountVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.update(useDiscountVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer useDisID) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			UseDiscountVO useDiscount = session.get(UseDiscountVO.class, useDisID);
			if (useDiscount != null) {
				session.delete(useDiscount);
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
	public UseDiscountVO findeByPrimaryKey(Integer useDisID) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			UseDiscountVO usediscount = session.get(UseDiscountVO.class, useDisID);
			session.getTransaction().commit();
			return usediscount;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<UseDiscountVO> getAll() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			List<UseDiscountVO> useDiscount = session.createQuery("from UseDiscountVO", UseDiscountVO.class).list();
			session.getTransaction().commit();
			return useDiscount;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
}
