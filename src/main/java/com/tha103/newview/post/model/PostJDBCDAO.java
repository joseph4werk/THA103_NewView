//package com.tha103.newview.post.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.tha103.util.Util;
//
//public class PostJDBCDAO implements PostDAO_interface {
//	public static final String INSERT_STMT = "insert into post(userID, postCategoryID, postHeader,postDateTime,postContent,disLikeCount,likeCount,postStatus) values(?,?,?,?,?,?,?,?)";
//	public static final String FIND_BY_PK = "select * from post where postID = ?";
//	public static final String GET_ALL = "select * from post order by postID desc";
//	private static final String DELETE = "delete from post where postID = ?";
//	private static final String UPDATE = "update post set userID=?, postCategoryID=?, postHeader=?,lastEditedTime=?,postContent=?,disLikeCount=?,likeCount=?,postStatus=? where postID = ?";
//	private static Timestamp now = new Timestamp(System.currentTimeMillis());
//	
//	static {
//		try {
//			Class.forName(Util.DRIVER);
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		}
//	}
//
//	// ----------------------------------------Insert method----------------------------------------//
//	@Override
//	public void insert(PostVO PostVO) {
//		// TODO Auto-generated method stub
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, PostVO.getUserID());
//			pstmt.setInt(2, PostVO.getPostCategoryID());
//			pstmt.setString(3, PostVO.getPostHeader());
//			pstmt.setTimestamp(4, PostVO.getPostDateTime());
//			pstmt.setString(5, PostVO.getPostContent());
//			pstmt.setInt(6, PostVO.getDisLikeCount());
//			pstmt.setInt(7, PostVO.getLikeCount());
//			pstmt.setInt(8, PostVO.getPostStatus());
//
//			pstmt.executeUpdate();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//	}
//
//	// ----------------------------------------Update method----------------------------------------//
//	@Override
//	public void update(PostVO post) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, post.getUserID());
//			pstmt.setInt(2, post.getPostCategoryID());
//			pstmt.setString(3, post.getPostHeader());
//			pstmt.setTimestamp(4, post.getLastEditedTime());
//			pstmt.setString(5, post.getPostContent());
//			pstmt.setInt(6, post.getDisLikeCount());
//			pstmt.setInt(7, post.getLikeCount());
//			pstmt.setInt(8, post.getPostStatus());
//			pstmt.setInt(9, post.getPostID());
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
//	// ----------------------------------------Deletemethod----------------------------------------//
//	@Override
//	public void delete(Integer postID) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(DELETE);
//			pstmt.setInt(1, postID);
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
//	// ----------------------------------------FindPKmethod----------------------------------------//
//	@Override
//	public PostVO findByPrimaryKey(Integer postID) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		PostVO post = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(FIND_BY_PK);
//			pstmt.setInt(1, postID);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				post = new PostVO();
//				post.setPostID(rs.getInt("postID"));
//				post.setUserID(rs.getInt("userID"));
//				post.setPostCategoryID(rs.getInt("postCategoryID"));
//				post.setPostHeader(rs.getString("postHeader"));
//				post.setPostDateTime(rs.getTimestamp("postDateTime"));
//				post.setLastEditedTime(rs.getTimestamp("lastEditedTime"));
//				post.setPostContent(rs.getString("postContent"));
//				post.setDisLikeCount(rs.getInt("disLikeCount"));
//				post.setLikeCount(rs.getInt("likeCount"));
//				post.setPostStatus(rs.getInt("postStatus"));
//
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return post;
//
//	}
//
//	// ----------------------------------------GetAllmethod----------------------------------------//
//	@Override
//	public List<PostVO> getAll() {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<PostVO> postList = new ArrayList<>();
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				PostVO post = new PostVO();
//				post.setPostID(rs.getInt("postID"));
//				post.setUserID(rs.getInt("userID"));
//				post.setPostCategoryID(rs.getInt("postCategoryID"));
//				post.setPostHeader(rs.getString("postHeader"));
//				post.setPostDateTime(rs.getTimestamp("postDateTime"));
//				post.setLastEditedTime(rs.getTimestamp("lastEditedTime"));
//				post.setPostContent(rs.getString("postContent"));
//				post.setDisLikeCount(rs.getInt("disLikeCount"));
//				post.setLikeCount(rs.getInt("likeCount"));
//				post.setPostStatus(rs.getInt("postStatus"));
//
//				postList.add(post);
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return postList;
//
//	}
//
//	// ----------------------------------------Main method----------------------------------------//
//	public static void main(String[] args) {
//
//		PostDAO_interface dao = new PostJDBCDAO();
//
//		// insert// OK
////		PostVO post = new PostVO();
////		post.setUserID(2);
////		post.setPostCategoryID(1);
////		post.setPostHeader("Amazing Show");
////		post.setPostDateTime(now);
////		post.setPostContent("really fantastic!");
////		post.setDisLikeCount(10);
////		post.setLikeCount(50);
////		post.setPostStatus(1);
////		dao.insert(post);
////		System.out.println("Success!");
//
//		// update// OK
////		PostVO post2 = new PostVO();
////		post2.setPostID(2);
////		post2.setUserID(1);
////		post2.setPostCategoryID(3);
////		post2.setPostHeader("really funny");
////		post2.setLastEditedTime(now);
////		post2.setPostContent("funny Show");
////		post2.setDisLikeCount(2);
////		post2.setLikeCount(2);
////		post2.setPostStatus(1);
////
////		dao.update(post2);
////		System.out.println("Success!");
//
//		// delete// OK
////		dao.delete(5);
//		System.out.println("Success!");
//
//		// FindPK// OK
//		PostVO post1 = dao.findByPrimaryKey(1);
//		System.out.print(post1);
//
//		// Find-All// OK
//		List<PostVO> list = dao.getAll();
//		for (PostVO lists : list) {
//			System.out.println(lists);
//		}
//	}
//}
