package com.tha103.newview.postcategory.model;

import java.util.List;
import org.hibernate.Session;
import com.tha103.util.HibernateUtil;

public class PostCategoryHibernate implements PostCategoryDAO_interface {
	
	@Override
	public int add(PostCategoryVO postCategoryVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(postCategoryVO);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(PostCategoryVO postCategoryVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(postCategoryVO);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int delete(Integer postCategoryID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PostCategoryVO postcategory = session.get(PostCategoryVO.class, postCategoryID);
			if (postcategory != null) {
				session.delete(postcategory);
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
	public PostCategoryVO findByPK(Integer postCategoryID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PostCategoryVO postcategory = session.get(PostCategoryVO.class, postCategoryID);
			session.getTransaction().commit();
			return postcategory;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<PostCategoryVO> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<PostCategoryVO> list = session.createQuery("from PostCategoryVO", PostCategoryVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PostCategoryDAO_interface dao = new PostCategoryHibernate();
		
		// Insert 
		PostCategoryVO category1 = new PostCategoryVO();
		category1.setPostCategoryName("TestA");
		dao.add(category1);
		System.out.println("Success!");
		
		// Update 
//		PostCategoryVO category2 = new PostCategoryVO();
//		category2.setPostCategoryID(10);
//		category2.setPostCategoryName("TestB");
//		dao.update(category2);
//		System.out.println("Success!");
//		
//		// Delete 
//		dao.delete(12);
//		System.out.println("Success!");
//		
//		// FindByPK
//		PostCategoryVO category4 = new PostCategoryHibernate().findByPK(1);
//		System.out.println(category4);
//		
//		// ListAll	
//		List<PostCategoryVO> list = new PostCategoryHibernate().getAll();
//		System.out.println(list);

	}
}
