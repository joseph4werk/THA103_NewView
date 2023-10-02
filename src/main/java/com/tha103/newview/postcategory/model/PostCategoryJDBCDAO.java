//package com.tha103.newview.postcategory.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import com.tha103.util.Util;
//
//public class PostCategoryJDBCDAO implements PostCategoryDAO_interface {
//	public static final String INSERT_STMT = "insert into postCategory (postCategoryName) values(?)";
//	public static final String FIND_BY_PK = "select * from postCategory where postCategoryID = ?";
//	public static final String GET_ALL = "select * from postCategory order by postCategoryID desc";
//	private static final String UPDATE = "update postCategory set postCategoryName=? where postCategoryID = ?";
//
//	static {
//		try {
//			Class.forName(Util.DRIVER);
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		}
//	}
//
//	@Override
//	public void insert(PostCategoryVO postCategoryVO) {
//	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setString(1, postCategoryVO.getPostCategoryName());
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//
//	}
//
//	@Override
//	public void update(PostCategoryVO PostCategoryVO) {
//	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			
//			pstmt.setString(1, PostCategoryVO.getPostCategoryName());
//			pstmt.setInt(2, PostCategoryVO.getPostCategoryID());
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//
//	}
//
//	@Override
//	public PostCategoryVO findByPrimaryKey(Integer PostCategoryID) {
//	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		PostCategoryVO category = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(FIND_BY_PK);
//			pstmt.setInt(1, PostCategoryID);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				category = new PostCategoryVO();
//				category.setPostCategoryID(rs.getInt("postCategoryID"));
//				category.setPostCategoryName(rs.getString("postCategoryName"));
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return category;
//
//	}
//
//	@Override
//	public List<PostCategoryVO> getAll() {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<PostCategoryVO> categoryList = new ArrayList<>();
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				PostCategoryVO category = new PostCategoryVO();
//
//				category.setPostCategoryID(rs.getInt("postCategoryID"));
//				category.setPostCategoryName(rs.getString("postCategoryName"));
//
//				categoryList.add(category);
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return categoryList;
//
//	}
//
//	public static void main(String[] args) {
//
//		PostCategoryDAO_interface dao = new PostCategoryJDBCDAO();
//
//		//insert// OK
//		PostCategoryVO category = new PostCategoryVO();
////		category.setPostCategoryName("TEST");
////		dao.insert(category);
//
//		//update// OK
////		PostCategoryVO category2 = new PostCategoryVO();
////		category2.setPostCategoryID(8);
////		category2.setPostCategoryName("exhibition");
////		dao.update(category2);
//
//		//FindbyPK// OK
//		PostCategoryVO category3 = dao.findByPrimaryKey(1);
//		System.out.print(category3);
//
//		//find-All// OK
//		List<PostCategoryVO> list = dao.getAll();
//		for (PostCategoryVO lists : list) {
//			System.out.println(lists);
//		}
//	}
//}
