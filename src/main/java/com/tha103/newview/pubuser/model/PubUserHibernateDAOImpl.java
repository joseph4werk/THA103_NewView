package com.tha103.newview.pubuser.model;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.tha103.newview.pubuser.service.PubUserCompositeQuery;
import com.tha103.util.HibernateUtil;


public class PubUserHibernateDAOImpl implements PubUserHibernateDAO{
private static final String GET_ALL_STMT = "from PubUserVO order by pubUserID";
	
	@Override
	public void add(PubUserVO pubUserVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(pubUserVO);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(PubUserVO pubUserVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(pubUserVO);
			session.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
	}

	/*
	@Override
	public void delete(Integer pubUserID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PubUserVO pubUser = session.get(PubUserVO.class,pubUserID);
			if (pubUser != null) {
				session.delete(pubUser);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	*/
	
	public void delete(Integer pubUserID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
//			【此時多方(宜)可採用HQL刪除】
			Query query = session.createQuery("delete PubUserVO where pubUserID=?0");
			query.setParameter(0,pubUserID);
			System.out.println("刪除的筆數=" + query.executeUpdate());
			
//			【或此時多方(也)可採用去除關聯關係後，再刪除的方式】
//			PubUesrVO pubUserVO = new PubUesrVO();
//			pubUserVO.setPubUesrID(pubUserID);
//			session.delete(pubUserVO);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

	@Override
	public PubUserVO findByPK(Integer pubUserID) {
		PubUserVO pubUserVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			pubUserVO = (PubUserVO) session.get(PubUserVO.class,pubUserID);
			session.getTransaction().commit();
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			throw ex;
		}
		return pubUserVO;
	}
	
	//for login
	public PubUserVO findByAccount(String pubAccount) {
		//List<PubUserVO> pubUserVO = null;
		//PubUserVO pubUserVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		try {
			session.beginTransaction();
			String hql = "FROM PubUserVO WHERE pubAccount = :pubAccount";
			
			Query<PubUserVO> query = session.createQuery(hql, PubUserVO.class);			
			System.out.println(query);
			
			query.setParameter("pubAccount",pubAccount);
			System.out.println(pubAccount);
			
			PubUserVO pubuserVO = query.uniqueResult();
			session.getTransaction().commit();
			System.out.println("交易成功");
			System.out.println(pubuserVO);
			return pubuserVO;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			
			System.out.println("交易失敗");
			return null;
		}
		
	}
	
	/*
	@Override
	public List<PubUserVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<PubUserVO> list = session.createQuery("from PubUser",PubUserVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	*/
	
	public List<PubUserVO> getAll() {
		List<PubUserVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query<PubUserVO> query = session.createQuery(GET_ALL_STMT, PubUserVO.class);
			list = query.getResultList();
			session.getTransaction().commit();
		} catch (Exception ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<PubUserVO> getAllByCQ(Map<String, String[]> map) {
		List<PubUserVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			//取出邏輯另外存在
			list = PubUserCompositeQuery.getAllCQ(map);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;

	}

	

}
