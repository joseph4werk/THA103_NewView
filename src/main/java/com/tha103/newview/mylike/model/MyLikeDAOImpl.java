package com.tha103.newview.mylike.model;

import java.util.List;

import org.hibernate.Session;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.user.model.UserVO;
import com.tha103.util.HibernateUtil;

public class MyLikeDAOImpl implements MyLikeDAO{	

	@Override
	public int insert(MyLikeVO myLikeVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(myLikeVO);
//			Integer id = (Integer) session.save(myLikeVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return 1;
	}



	@Override
	public int update(MyLikeVO myLikeVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(myLikeVO);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}

	@Override
	public int delete(Integer myLikeID) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MyLikeVO myLike = session.get(MyLikeVO.class, myLikeID);
			if (myLike != null) {
				session.delete(myLike);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}			
		return -1;
	}

	@Override
	public MyLikeVO findByPrimaryKey(Integer myLikeID) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			MyLikeVO myLike = session.get(MyLikeVO.class, myLikeID);
			session.getTransaction().commit();
			return myLike;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
		
	}

	@Override
	public List<MyLikeVO> getAll() {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<MyLikeVO> list = session.createQuery("from MyLikeVO", MyLikeVO.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
		
	}

	
	public static void main(String[] args) {
		MyLikeDAO dao = new MyLikeDAOImpl();

		//insert <test ok>
//		MyLikeVO myLike1 = new MyLikeVO();
//		UserVO user1 = new UserVO();
//		user1.setUserID(1);
//		myLike1.setUser(user1);
//		ActVO act1 = new ActVO();
//		act1.setActID(3);
//		myLike1.setAct(act1);
//		dao.insert(myLike1);

		//update <test ok>
		MyLikeVO myLike2 = new MyLikeVO();
		myLike2.setMyLikeID(3);
		UserVO user2 = new UserVO();
		user2.setUserID(1);
		myLike2.setUser(user2);
		ActVO act2 = new ActVO();
		act2.setActID(2);
		myLike2.setAct(act2);		
		dao.update(myLike2);

		// Delete <test ok>
//		dao.delete(4);

		// Find PK <test ok>
//		MyLikeVO myLike3 = dao.findByPrimaryKey(1);
//		System.out.println(myLike3);

		// FindALL <test ok>
//		List<MyLikeVO> list = dao.getAll();
//		for (MyLikeVO lists : list) {
//			System.out.println(lists);
//		}
	}
	
}