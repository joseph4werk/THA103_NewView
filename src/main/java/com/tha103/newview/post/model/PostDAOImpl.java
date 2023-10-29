package com.tha103.newview.post.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;

import com.tha103.util.HibernateUtil;
import com.tha103.util.Util;

public class PostDAOImpl implements PostDAO {

	@Override
	public int insert(PostVO postVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		int postID = 0;
		
		try {
			session.beginTransaction();
//			session.saveOrUpdate(postVO);
			postID = (int) session.save(postVO);
			session.getTransaction().commit();
			return postID;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(PostVO postVO) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			session.update(postVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer postID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			PostVO post = session.get(PostVO.class, postID);
			if (post != null) {
				session.delete(post);
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
	public PostVO findByPrimaryKey(Integer postID) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			PostVO post = session.get(PostVO.class, postID);
			session.getTransaction().commit();
			return post;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<PostVO> getAll() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();
			List<PostVO> list = session.createQuery("from PostVO", PostVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}



	@Override
	public List<PostVO> findByCategory(int postCategoryID) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	    try {
	        session.beginTransaction();
	        // 使用 HQL 查詢
	        List<PostVO> catelist = session.createQuery("FROM PostVO p WHERE p.postCategoryVO.postCategoryID = :postCategoryID", PostVO.class)
	                .setParameter("postCategoryID", postCategoryID)
	                .list();
	        session.getTransaction().commit();
	        return catelist;
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    return null;
	}

	
	
	@Override
	public int getAuthorIDByPostID(int postID) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

	    try {
	        session.beginTransaction();
	        // 使用 HQL 查詢
	        String hql = "SELECT p.userVO.userID FROM PostVO p WHERE p.postID = :postID";
	        int userID = (int) session.createQuery(hql)
	                .setParameter("postID", postID)
	                .uniqueResult();
	        session.getTransaction().commit();
	        return userID;
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    return 0; // 或者返回其他值
	}


	
	
	
	@Override
	public List<PostVO> getByCompositeQuery(Map<String, String> query, String orderBy, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
