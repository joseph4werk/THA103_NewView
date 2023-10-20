package com.tha103.newview.pubuser.service;

import java.util.*;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tha103.newview.pubuser.model.PubUserVO;
import com.tha103.util.HibernateUtil;

public class PubUserCompositeQuery {
	
	public static Predicate getPredicateForDB(CriteriaBuilder builder, Root<PubUserVO> root, String columnName, String value) {
		
		//使用Predicate構建查詢條件的介面
		//Root表示查詢的型別
		Predicate predicate = null;
		
		
		if("pubUserID".equals(columnName)) {
			// 用於Integer
			predicate = builder.equal(root.get(columnName), new Integer(value));
		}else if ("pubNickname".equals(columnName)) {
			// 用於varchar
			predicate = builder.like(root.get(columnName), "%" + value + "%");
		}else if ("pubAuthority".equals(columnName)) {
			// 用於tinyint
			byte byteValue = Byte.parseByte(value);
			predicate = builder.equal(root.get(columnName), byteValue);
		}
		
		return predicate;
		
	}
	
	
	
	
	public static List<PubUserVO> getAllCQ(Map<String, String[]> map){
		
		//創建的 Session 不會自動參與容器管理的事務
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<PubUserVO> list = null;
		
		try {
			// 【●創建 CriteriaBuilder】用於建立查詢條件
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// 【●創建 CriteriaQuery】//指定了查詢的返回型別
			CriteriaQuery<PubUserVO> criteriaQuery = builder.createQuery(PubUserVO.class);
			// 【●創建 Root】//查詢的根實體類別
			Root<PubUserVO> root = criteriaQuery.from(PubUserVO.class);
			
			List<Predicate> predicateList = new ArrayList<Predicate>();
			
			Set<String> keys = map.keySet();
			int count = 0;
			for (String key : keys) {
				String value = map.get(key)[0];
				if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
					count++;
					predicateList.add(getPredicateForDB(builder, root, key, value));
					System.out.println("有送出查詢資料的欄位數count = " + count);
					
				}
				
			}
			System.out.println("predicateList.size()="+predicateList.size());
			criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
			criteriaQuery.orderBy(builder.asc(root.get("empno")));
			// 【●最後完成創建 javax.persistence.Query●】
			Query query = session.createQuery(criteriaQuery); //javax.persistence.Query; //Hibernate 5 開始 取代原 org.hibernate.Query 介面
			list = query.getResultList();
			
			tx.commit();
		} catch (Exception ex) {
			if (tx != null)
				tx.rollback();
			throw ex; // System.out.println(ex.getMessage());
			
		}finally {
			session.close();
			// HibernateUtil.getSessionFactory().close();
		}
		
		return list;
		
	}
}
