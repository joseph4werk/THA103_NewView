package com.tha103.newview.publisher.model;

import java.util.List;
import org.hibernate.Session;
import com.tha103.util.HibernateUtil;

public class PublisherHibernateDAOImpl implements PublisherHibernateDAO{

	@Override
	public int add(Publisher publisher) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(publisher);
			session.getTransaction().commit();
			return id;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Publisher publisher) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(publisher);
			session.getTransaction().commit();
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer pubID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Publisher pub = session.get(Publisher.class,pubID);
			if(pub != null) {
				session.delete(pub);
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
	public Publisher findByPK(Integer pubID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Publisher pub = session.get(Publisher.class,pubID);
			session.getTransaction().commit();
			return pub;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Publisher> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Publisher> list = session.createQuery("from Publisher",Publisher.class).list();
			session.getTransaction().rollback();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
