package com.tha103.newview.post.model;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Session;
import com.tha103.util.HibernateUtil;

public class PostHibernate implements PostDAO_interface {

	@Override
	public int add(PostVO PostVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(PostVO);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(PostVO PostVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(PostVO);
			session.getTransaction().commit();
			return 1;
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
	public PostVO findByPK(Integer postID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			PostVO postcategory = session.get(PostVO.class, postID);
			session.getTransaction().commit();
			return postcategory;
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

	public static void main(String[] args) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		PostDAO_interface dao = new PostHibernate();

		// Insert
		PostVO post1 = new PostVO();
		post1.setUserID(2);
		post1.setPostCategoryID(2);
		post1.setPostHeader("Test Header");
		post1.setPostDateTime(new Timestamp(System.currentTimeMillis()));
		post1.setLastEditedTime(new Timestamp(System.currentTimeMillis()));
		post1.setPostContent("Test postContent");
		post1.setDisLikeCount(2);
		post1.setLikeCount(2);
		post1.setPostStatus(1);
		dao.add(post1);
		System.out.println("Success!");

		// Update
		PostVO post2 = new PostVO();
		post2.setPostID(6);
		post2.setUserID(2);
		post2.setPostCategoryID(2);
		post2.setPostHeader("Test Header2");
		post2.setPostDateTime(new Timestamp(System.currentTimeMillis()));
		post2.setLastEditedTime(new Timestamp(System.currentTimeMillis()));
		post2.setPostContent("Test postContent2");
		post2.setDisLikeCount(2);
		post2.setLikeCount(2);
		post2.setPostStatus(1);
		dao.update(post2);
		System.out.println("Success!");

		// Delete
		dao.delete(7);
		System.out.println("Success!");

		// FindByPK
		PostVO post3 = new PostHibernate().findByPK(1);
		System.out.println(post3);

		// ListAll
		List<PostVO> list = new PostHibernate().getAll();
		System.out.println(list);

	}
}
