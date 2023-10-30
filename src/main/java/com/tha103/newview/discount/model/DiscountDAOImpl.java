package com.tha103.newview.discount.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.tha103.util.HibernateUtil;


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

	// for Order & OrderList By Lin
	@Override
	public DiscountVO getDisAmountBy(String discountCode) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		DiscountVO discountVO = null;

		try {
			transaction = session.beginTransaction();
			Query<DiscountVO> query = session.createQuery("FROM DiscountVO WHERE discountCode = :code",
					DiscountVO.class);
			query.setParameter("code", discountCode);
			discountVO = query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}

		return discountVO;
	}

	// for Publisher Backstage get discount List
	public List<DiscountVO> getDiscountByPubID(Integer pubID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			String hql = "FROM DiscountVO AS d WHERE d.publisherVO.pubID = :pubID";
			Query<DiscountVO> query = session.createQuery(hql, DiscountVO.class);
			query.setParameter("pubID", pubID);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
