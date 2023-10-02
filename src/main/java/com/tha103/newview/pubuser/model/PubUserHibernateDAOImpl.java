package com.tha103.newview.pubuser.model;

import java.util.List;
import org.hibernate.Session;
import com.tha103.util.HibernateUtil;

public class PubUserHibernateDAOImpl implements PubUserHibernateDAO{

	@Override
	public int add(PubUser pubuser) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(pubuser);
			session.getTransaction().commit();
			return id;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(PubUser pubUser) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(pubUser);
			session.getTransaction().commit();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer pubUserID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PubUser pubuser = session.get(PubUser.class,pubUserID);
			if(pubuser != null) {
				session.delete(pubuser);
			}
			session.getTransaction().commit();
			return 1;
			
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public PubUser finkByPK(Integer pubUserID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PubUser pubuser = session.get(PubUser.class,pubUserID);
			session.getTransaction().commit();
			return pubuser;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<PubUser> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<PubUser> list = session.createQuery("from PubUser",PubUser.class).list();
			session.getTransaction().commit();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
