package com.tha103.newview.user.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.tha103.newview.actpic.model.ActPic;
import com.tha103.newview.orderlist.model.OrderListVO;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.util.HibernateUtil;

public class TestHibernateDAO {
	public static void main(String[] args) {

		UserDAO dao = new UserDAOImpl();

		// 新增
//		UserVO user1 = new UserVO();
//		user1.setUserName("abc");
//		user1.setUserAccount("b");
//		user1.setUserPassword("123");
//		user1.setUserBirth(java.sql.Date.valueOf("2023-09-07"));
//		user1.setUserCell("0987654321");
//		user1.setUserEmail("test@test.com");
//		user1.setUserNickname("c");
//		user1.setBuyAuthority(0);
//		user1.setSpeakAuthority(0);
//		
//		dao.insert(user1);

		// 修改
//		UserVO user2 = new UserVO();
//		user2.setUserID(1);
//		user2.setUserName("xxxx");
//		user2.setUserAccount("b");
//		user2.setUserPassword("123");
//		user2.setUserBirth(java.sql.Date.valueOf("2023-09-07"));
//		user2.setUserCell("0987654321");
//		user2.setUserEmail("test@test.com");
//		user2.setUserNickname("c");
//		user2.setBuyAuthority(0);
//		user2.setSpeakAuthority(0);
//		
//		dao.update(user2);

		// 刪除

//		dao.delete(3);

		// 查詢單筆
		UserVO user3 = dao.findByPrimaryKey(1);
		System.out.println(user3);

		// 查詢多筆
		List<UserVO> list = dao.getAll();
		for (UserVO lists : list) {
			System.out.println(lists);
		}

		// 使用 userAccount 查詢單筆
//		boolean notExist = dao.checkUserAccount("test_a");
//		System.out.println("userAccount: test_a is available = " + notExist);
		
		// 使用 userID 查詢 actPicID
		Integer userID = 1;
		
//		UserVO userVO = new UserVO();
//		OrdersVO ordersVO = new OrdersVO();
//		OrderListVO orderListVO = new OrderListVO();
//		ActVO actVO = new ActVO();
//		ActPic actPicVO = new ActPic();
//		
//		Integer orderID = ordersVO.getOrderID();
//		Integer orderListID = orderListVO.getOrderListID();
//		Integer actID = actVO.getActID();
//		Integer actPicID = actPicVO.getActPicID();
		
//		System.out.println(getOrderIdByUserID(userID));
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			
			System.out.println("====================");
			session.beginTransaction();
			
			List<Object[]> list2 = session.createNativeQuery("SELECT ap.ActPicID FROM user u "
					+ "JOIN orders o ON u.userid = :userID "
					+ "JOIN orderlist ol ON o.orderID = ol.orderID "
					+ "JOIN act a ON ol.actID = a.actID "
					+ "JOIN actpic ap ON a.actID = ap.actID; ")
					.setParameter("userID", 4)
					.list();
			
			
			System.out.println(list2);
			System.out.println("====================");
			
//		
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
		}
	
		
		
		
	}
	public static List<Integer> getActPicIDByUserID(Integer userID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			String sql = "SELECT ap.ActPicID " +
		             "FROM User u " +
		             "LEFT JOIN u.orders o " +
		             "LEFT JOIN o.orderList ol " +
		             "LEFT JOIN ol.act a " +
		             "LEFT JOIN a.actPic ap";

			Query query = session.createQuery(sql);
			List<Integer> actPicIds = query.list();
//			UserVO userVO = (UserVO) session.createQuery(sql).setParameter("userEmail", email).uniqueResult();
			session.getTransaction().commit();
			return actPicIds;

		} catch (Exception e) {
			session.getTransaction().rollback();

		}
		return null;
	}
	
	public static List<Integer> getOrderIdByUserID(Integer userID) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			String sql = "SELECT o.ordersID FROM User u LEFT JOIN u.orders o ";

			Query query = session.createQuery(sql);
			List<Integer> orderIDLists = query.list();
//			UserVO userVO = (UserVO) session.createQuery(sql).setParameter("userEmail", email).uniqueResult();
			session.getTransaction().commit();
			return orderIDLists;

		} catch (Exception e) {
			session.getTransaction().rollback();

		}
		return null;
	}
}
