package com.tha103.newview.admin.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.util.HibernateUtil;

public class AdminHibernateDAOImpl implements AdminHibernateDAO{
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	@Override
	public int update(AdminVO admin) {
		try {
			session.beginTransaction();
			session.update(admin);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}
	

	@Override
	public List<AdminVO> getAll() {
		try {
			session.beginTransaction();
			List<AdminVO> list = session.createQuery("from Admin",AdminVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}
	

}
