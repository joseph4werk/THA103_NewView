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

//	這個DOA裡查詢可以用來排序的發文時間postDateTime、依照likeCount跟disLikeCount加總數字計算出的熱門度以及可以做分類的列表查詢，返回一个 List，其中包含满足查询条件的文章对象，但方法設計中，實際在前端運作時，用戶可以:
//		1. 使用篩選器: 可選新到舊，舊到新，最熱門，預設新到舊
//		2.可以關鍵字查詢: 只要輸入隨意的中文字就可以比對文章標題postHeader;輸入數字的話則UserID會被比對，預設不查詢可為空值
//		3. 有分類頁籤，總共有7大類，每類有不同categoryID ，預設全部

	
//	@Override
//	public List<PostVO> getByCompositeQuery(Map<String, String> query, String orderBy, int start, int pageSize) {
//	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//
//	    try {
//	        session.beginTransaction();
//
//	        CriteriaBuilder builder = session.getCriteriaBuilder();
//	        CriteriaQuery<PostVO> criteria = builder.createQuery(PostVO.class);
//	        Root<PostVO> root = criteria.from(PostVO.class);
//
//	        List<Predicate> predicates = new ArrayList<>();
//
//	        // 添加關鍵字搜索（匹配文章標题）
//	        if (query.containsKey("keyword") && !query.get("keyword").isEmpty()) {
//	            String keyword = "%" + query.get("keyword") + "%";
//	            predicates.add(builder.like(root.get("postHeader"), keyword));
//	        }
//
//	        // 添加分類篩選
//	        if (query.containsKey("categoryID") && !query.get("categoryID").isEmpty()) {
//	            int categoryID = Integer.parseInt(query.get("categoryID"));
//	            predicates.add(builder.equal(root.get("postCategoryID"), categoryID));
//	        }
//	        
//	        
//	        // 根據用戶篩選器選擇排序條件
//	        if ("newest".equals(orderBy)) {
//	            criteria.orderBy(builder.desc(root.get("postDateTime")));
//	        } else if ("oldest".equals(orderBy)) {
//	            criteria.orderBy(builder.asc(root.get("postDateTime")));
//	        } else if ("popular".equals(orderBy)) {
//	            criteria.orderBy(builder.desc(root.get("likeCount").add(root.get("disLikeCount"))));
//	        }
//
//	        criteria.where(builder.and(predicates.toArray(new Predicate[0])));
//
//	        TypedQuery<PostVO> typedQuery = session.createQuery(criteria).setFirstResult(start)
//	                .setMaxResults(pageSize);
//	        session.getTransaction().commit();
//	        return typedQuery.getResultList();
//
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        session.getTransaction().rollback();
//
//	    }
//	    return null;
//	}


	public int getTotalCountByCompositeQuery(Map<String, String> query) {
	    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
	    try {
	        session.beginTransaction();
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
	        Root<PostVO> root = criteria.from(PostVO.class);
	        
	        // 構建查詢條件
	        List<Predicate> predicates = new ArrayList<>();
	        if (query.containsKey("keyword") && !query.get("keyword").isEmpty()) {
	            String keyword = "%" + query.get("keyword") + "%";
	            predicates.add(builder.like(root.get("postHeader"), keyword));
	        }
	        if (query.containsKey("categoryID") && !query.get("categoryID").isEmpty()) {
	            int categoryID = Integer.parseInt(query.get("categoryID"));
	            predicates.add(builder.equal(root.get("postCategoryID"), categoryID));
	        }
	        
	        // 添加其他條件
	        
	        criteria.where(builder.and(predicates.toArray(new Predicate[0])));
	        
	        // 使用 count 函數獲取總數
	        criteria.select(builder.count(root));
	        
	        Long count = session.createQuery(criteria).getSingleResult();
	        session.getTransaction().commit();
	        
	        // 將 Long 型別轉換為 int 並返回
	        return count.intValue();
	    } catch (Exception e) {
	        session.getTransaction().rollback();
	        e.printStackTrace();
	        return 0;
	    }
	}

	@Override
	public List<PostVO> getByCompositeQuery(Map<String, String> query, String orderBy, int start, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

}
