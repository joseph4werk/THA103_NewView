package com.tha103.newview.act.model;
import java.util.List;

import org.hibernate.Session;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tha103.util.HibernateUtil;


public class ActDAOHibernateImpl implements ActDAO {

	@Override
	public void insert(Act act) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(act);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void update(Act act) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(act);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void delete(Integer ActID) {
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Act act = session.get(Act.class, ActID);
			if (act != null) {
				session.delete(act);
			}
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public Act findByPrimaryKey(Integer actID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Act act = null;
		try {
			session.beginTransaction();
			 act = session.get(Act.class, actID);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return act;
	}

	@Override
	public List<Act> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Act> list = session.createQuery("from Act", Act.class).list();
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			String json = gson.toJson(list);
			
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	
	}

	

}
