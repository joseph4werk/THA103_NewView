package com.tha103.newview.likes.model;

import java.util.List;

public class TestHibernateDAO {
	public static void main(String[] args) {
		LikesDAO dao = new LikesDAOImpl();

		// 新增
//		LikesVO like1 = new LikesVO();
//		like1.setPostID(1);
//		like1.setUserID(1);
//		like1.setLikeOrNot(1);
//		
//		dao.insert(like1);

		// 修改
//		LikesVO like2 = new LikesVO();
//		like2.setLikeID(1);
//		like2.setPostID(1);
//		like2.setUserID(1);
//		like2.setLikeOrNot(1);
//		
//		dao.update(like2);

		// 刪除
//		dao.delete(2);

		// 查詢單筆
		LikesVO like3 = dao.findByPrimaryKey(1);
		System.out.println(like3);

		// 查詢多筆
		List<LikesVO> list = dao.getAll();
		for (LikesVO lists : list) {
			System.out.println(lists);
		}
	}
}
