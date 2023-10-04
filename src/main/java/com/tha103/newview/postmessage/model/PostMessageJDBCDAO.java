//package com.tha103.newview.postmessage.model;
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
//
//
//
//
//public class PostMessageJDBCDAO implements PostMessageDAO_interface{
//	public static final String INSERT_STMT = "insert into postMessage ( postID, userID, mesContent,messageDate) values(?,?,?,?)";
//	public static final String FIND_BY_PK = "select * from postMessage where postMessageID = ?";
//	public static final String GET_ALL = "select * from postMessage order by postMessageID desc";
//	private static final String DELETE = "delete from postMessage where postMessageID = ?";
//	private static final String UPDATE = "update postMessage set postID=?, userID=?, mesContent=?,messageDate=? where postMessageID = ?";
//	private static Timestamp now = new Timestamp(System.currentTimeMillis());
//	
//	static {
//		try {
//			Class.forName(Util.DRIVER);
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		}
//	}
//	@Override
//	public void insert(PostMessageVO PostMessageVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			
//			pstmt.setInt(1, PostMessageVO.getPostID());
//			pstmt.setInt(2, PostMessageVO.getUserID());
//			pstmt.setString(3, PostMessageVO.getMesContent());
//			pstmt.setTimestamp(4, PostMessageVO.getMessageDate());
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
//	public void update(PostMessageVO PostMessageVO) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, PostMessageVO.getPostID());
//			pstmt.setInt(2, PostMessageVO.getUserID());
//			pstmt.setString(3, PostMessageVO.getMesContent());
//			pstmt.setTimestamp(4, PostMessageVO.getMessageDate());
//			pstmt.setInt(5, PostMessageVO.getPostMessageID());
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
//	public void delete(Integer postMessageID) {
//	
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(DELETE);
//			pstmt.setInt(1, postMessageID);
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//
//		
//	}
//
//	@Override
//	public PostMessageVO findByPrimaryKey(Integer postMessageID) {
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		PostMessageVO msg = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(FIND_BY_PK);
//			pstmt.setInt(1, postMessageID);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				msg = new PostMessageVO();
//				msg.setPostMessageID(rs.getInt("postMessageID"));
//				msg.setPostID(rs.getInt("postID"));
//				msg.setUserID(rs.getInt("userID"));
//				msg.setMesContent(rs.getString("mesContent"));
//				msg.setMessageDate(rs.getTimestamp("MessageDate"));
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return msg;
//		
//	}
//
//	@Override
//	public List<PostMessageVO> getAll() {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<PostMessageVO> msgList = new ArrayList<>();
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				
//				PostMessageVO msg = new PostMessageVO();
//
//				msg.setPostMessageID(rs.getInt("postMessageID"));
//				msg.setPostID(rs.getInt("postID"));
//				msg.setUserID(rs.getInt("userID"));
//				msg.setMesContent(rs.getString("mesContent"));
//				msg.setMessageDate(rs.getTimestamp("MessageDate"));
//
//				msgList.add(msg);
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return msgList;
//		
//	}
//	
//	
//	public static void main(String[] args) {
//
//		PostMessageDAO_interface dao = new PostMessageJDBCDAO();
//		
//		//Insert// OK
////		PostMessageVO msg = new PostMessageVO();
////		msg.setPostID(1);
////		msg.setUserID(2);
////		msg.setMesContent("great!");
////		msg.setMessageDate(now);
////		dao.insert(msg);
////		System.out.println("Success!");
//		
//		
////		//Update// OK
////		PostMessageVO msg2 = new PostMessageVO();
////		msg2.setPostMessageID(3);
////		msg2.setPostID(2);
////		msg2.setUserID(3);
////		msg2.setMesContent("good");
////		msg2.setMessageDate(now);
////		dao.update(msg2);
////
////		//Delete// OK
////		dao.delete(4);
////
////		//FindPK// OK
//		PostMessageVO msg1 = dao.findByPrimaryKey(1);
//		System.out.println(msg1);
//
//		//Find-All// OK
//		List<PostMessageVO> list = dao.getAll();
//		for (PostMessageVO lists : list) {
//			System.out.println(lists);
//		}
//	}
//	
//}
//
