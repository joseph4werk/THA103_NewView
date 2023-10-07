package com.tha103.newview.user.model;

import java.util.List;

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
		boolean notExist = dao.checkUserAccount("test_a");
		System.out.println("userAccount: test_a is available = " + notExist);

	}
}
