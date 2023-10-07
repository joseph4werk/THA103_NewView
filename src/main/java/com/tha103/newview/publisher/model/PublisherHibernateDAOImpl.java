package com.tha103.newview.publisher.model;

import java.util.List;
import org.hibernate.Session;
import com.tha103.util.HibernateUtil;

public class PublisherHibernateDAOImpl implements PublisherHibernateDAO{

	@Override
	public int add(PublisherVO publisher) {
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
	public int update(PublisherVO publisher) {
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
			PublisherVO pub = session.get(PublisherVO.class,pubID);
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
	public PublisherVO findByPK(Integer pubID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PublisherVO pub = session.get(PublisherVO.class,pubID);
			session.getTransaction().commit();
			return pub;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<PublisherVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<PublisherVO> list = session.createQuery("from Publisher",PublisherVO.class).list();
			session.getTransaction().rollback();
			return list;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

}
