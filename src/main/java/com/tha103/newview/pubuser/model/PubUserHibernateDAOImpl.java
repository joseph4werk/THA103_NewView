package com.tha103.newview.pubuser.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tha103.util.HibernateUtil;


public class PubUserHibernateDAOImpl implements PubUserHibernateDAO{
	
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	@Override
	public int add(PubUserVO pubuser) {
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(pubuser);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(PubUserVO pubuser) {
		try {
			session.beginTransaction();
			session.update(pubuser);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer pubUserID) {
		try {
			session.beginTransaction();
			PubUserVO pubUser = session.get(PubUserVO.class,pubUserID);
			if (pubUser != null) {
				session.delete(pubUser);
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
	public PubUserVO findByPK(Integer pubUserID) {
		try {
			session.beginTransaction();
			PubUserVO pubUser = session.get(PubUserVO.class,pubUserID);
			session.getTransaction().commit();
			return pubUser;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<PubUserVO> getAll() {
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
	
	
	
/*

	@Override
	public List<PubUser> getByCompositeQuery(Map<String, String> map) {
		if(map.size() == 0)
		return getAll();
		
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<PubUser> criteria = builder.createQuery(PubUser.class);
		Root<PubUser> root = criteria.from(PubUser.class);
		
		List<Predicate> predicates = new ArrayList<>();

		for (Map.Entry<String, String> row : map.entrySet()) {
			if ("pubNickname".equals(row.getKey())) {
				predicates.add(builder.like(root.get("pubNickname"), "%" + row.getValue() + "%"));
			}
			
			if ("pubAccount".equals(row.getKey())) {
				predicates.add(builder.like(root.get("pubAccount"), "%" + row.getValue() + "%"));
			}

		}

		criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
		criteria.orderBy(builder.asc(root.get("pubUserID")));
		TypedQuery<PubUser> query = getSession().createQuery(criteria);

		return query.getResultList();
	}
*/
/*
	@Override
	public List<PubUser> getAll(int cueerntPage) {
		return null;
		
		int first = (currentPage - 1) * PUB_USER_PAGE_MAX_RESULT;
		return getSession().createQuery("from PubUser",PubUser.class)
				.setFirstResult(first)
				.setMaxResults(PUB_USER_PAGE_MAX_RESULT)
				.list();
		
	}
*/
/*
	@Override
	public long getTotal() {
		return getSession().createQuery("select count(*) from PubUser", Long.class).uniqueResult();
	}
	
*/	



}
