//package com.tha103.newview.orderlist.model;
//
//import java.io.FileInputStream;
//import java.io.IOException;
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
//public class OrderListJDBCDAO implements OrderListDAO_Interface {
//
//	private static final String INSERT_STMT = "INSERT INTO orderList (orderID,actID,actTotal, QRcodeID, OrderListTime, reviewContent, fiveStarReview,seatColumns, seatRows, vacancy) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
//	private static final String GET_ALL_STMT = "SELECT orderListID,orderID,actID, actTotal, QRcodeID, OrderListTime, reviewContent, fiveStarReview, seatRows, seatColumns, vacancy FROM orderList order by orderListID";
//	private static final String GET_ONE_STMT = "SELECT orderListID,orderID,actID, actTotal, QRcodeID, OrderListTime, reviewContent, fiveStarReview, seatRows, seatColumns, vacancy FROM orderList where orderListID = ?";
//	private static final String DELETE = "DELETE FROM orderList where orderListID = ?";
//	private static final String UPDATE = "UPDATE orderList set orderID=?,actID=?,actTotal=?, QRcodeID=?, OrderListTime=?,reviewContent=?, fiveStarReview=?, seatRows=?, seatColumns=?,vacancy=? where orderListID = ?";
//	private static Timestamp now = new Timestamp(System.currentTimeMillis());
//	static {
//		try {
//			Class.forName(Util.DRIVER);
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		}
//	}
//
//	@Override
//	public void insert(OrderListVO orderListID) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setInt(1, orderListID.getOrderID());
//			pstmt.setInt(2, orderListID.getActID());
//			pstmt.setInt(3, orderListID.getActTotal());
//			pstmt.setBytes(4, orderListID.getQRcodeID());
//			pstmt.setTimestamp(5, orderListID.getOrderListTime());
//			pstmt.setString(6, orderListID.getReviewContent());
//			pstmt.setInt(7, orderListID.getFiveStarReview());
//			pstmt.setInt(8, orderListID.getSeatColumns());
//			pstmt.setInt(9, orderListID.getSeatRows());
//			pstmt.setString(10, orderListID.getVacancy());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//
//	}
//
//	@Override
//	public void update(OrderListVO orderListID) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, orderListID.getOrderID());
//			pstmt.setInt(2, orderListID.getActID());
//			pstmt.setInt(3, orderListID.getActTotal());
//			pstmt.setBytes(4, orderListID.getQRcodeID());
//			pstmt.setTimestamp(5, orderListID.getOrderListTime());
//			pstmt.setString(6, orderListID.getReviewContent());
//			pstmt.setInt(7, orderListID.getFiveStarReview());
//			pstmt.setInt(8, orderListID.getSeatColumns());
//			pstmt.setInt(9, orderListID.getSeatRows());
//			pstmt.setString(10, orderListID.getVacancy());
//			pstmt.setInt(11, orderListID.getOrderListID());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//
//	}
//
//	@Override
//	public void delete(Integer orderListID) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, orderListID);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//
//	}
//
//	@Override
//	public OrderListVO findByPrimaryKey(Integer orderListID) {
//		// TODO Auto-generated method stub
//		OrderListVO orderListVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, orderListID);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				orderListVO = new OrderListVO();
//				orderListVO.setOrderListID(rs.getInt("orderListID"));
//				orderListVO.setOrderID(rs.getInt("orderID"));
//				orderListVO.setActID(rs.getInt("actID"));
//				orderListVO.setActTotal(rs.getInt("actTotal"));
//				orderListVO.setQRcodeID(rs.getBytes("QRcodeID"));
//				orderListVO.setOrderListTime(rs.getTimestamp("OrderListTime"));
//				orderListVO.setReviewContent(rs.getString("reviewContent"));
//				orderListVO.setFiveStarReview(rs.getInt("fiveStarReview"));
//				orderListVO.setSeatRows(rs.getInt("seatRows"));
//				orderListVO.setSeatColumns(rs.getInt("seatColumns"));
//				orderListVO.setVacancy(rs.getString("vacancy"));
//
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return orderListVO;
//
//	}
//
//	@Override
//	public List<OrderListVO> getAll() {
//		List<OrderListVO> list = new ArrayList<OrderListVO>();
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL_STMT);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				// empVO 也稱為 Domain objects
//				OrderListVO orderListVO = new OrderListVO();
//				orderListVO.setOrderListID(rs.getInt("orderListID"));
//				orderListVO.setOrderID(rs.getInt("orderID"));
//				orderListVO.setActID(rs.getInt("actID"));
//				orderListVO.setActTotal(rs.getInt("actTotal"));
//				orderListVO.setQRcodeID(rs.getBytes("QRcodeID"));
//				orderListVO.setOrderListTime(rs.getTimestamp("OrderListTime"));
//				orderListVO.setReviewContent(rs.getString("reviewContent"));
//				orderListVO.setFiveStarReview(rs.getInt("fiveStarReview"));
//				orderListVO.setSeatRows(rs.getInt("seatRows"));
//				orderListVO.setSeatColumns(rs.getInt("seatColumns"));
//				orderListVO.setVacancy(rs.getString("vacancy"));
//
//				list.add(orderListVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return list;
//
//	}
//
//	public byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//
//		byte[] buffer = fis.readAllBytes();
//		fis.close();
//		return buffer;
//	}
//
//	public static void main(String[] args) {
//		OrderListJDBCDAO orderListJDBCDAO = new OrderListJDBCDAO();
//
//		OrderListVO orderListVO = new OrderListVO();
//		
////		// insert
////		try {
////			orderListVO.setOrderID(2);
////			orderListVO.setActID(3);
////			orderListVO.setActTotal(1000);
////			orderListVO.setQRcodeID(orderListJDBCDAO.getPictureByteArray("C:/ok2.jpg"));
////			orderListVO.setOrderListTime(now);
////			orderListVO.setReviewContent("weeeeeeeee~");
////			orderListVO.setFiveStarReview(1);
////			orderListVO.setSeatRows(1);
////			orderListVO.setSeatColumns(1);
////			orderListVO.setVacancy("waoooooooooooo~");
////			System.out.println("成功");
////
//////			actPicVO.setActPic(actPicJDBCDAO.getPictureByteArray("C:/THA103_WebApp/eclipse_WTP_workspace1/THA103G5_All_ver9/src/main/java/com/newview/model/compic/image/ok2.jpg"));
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		orderListJDBCDAO.insert(orderListVO);
//		
////		// update
////		try {
////			orderListVO.setOrderListID(3);
////			orderListVO.setOrderID(1);
////			orderListVO.setActID(3);
////			orderListVO.setActTotal(1000);
////			orderListVO.setQRcodeID(orderListJDBCDAO.getPictureByteArray("C:/THA103_WebApp/eclipse_WTP_workspace1/THA103G5_All_ver9/src/main/java/com/newview/model/compic/image/ok2.jpg"));
////			orderListVO.setOrderListTime(now);
////			orderListVO.setReviewContent("wweeeeeeeee~");
////			orderListVO.setFiveStarReview(1);
////			orderListVO.setSeatRows(1);
////			orderListVO.setSeatColumns(1);
////			orderListVO.setVacancy("waoooooooooooo~");
////
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		orderListJDBCDAO.update(orderListVO);
//		
////		//delete
////		orderListJDBCDAO.delete(5);
//		
////		// findByPrimaryKey
////		orderListVO = orderListJDBCDAO.findByPrimaryKey(1);
////		System.out.print(orderListVO.getOrderID() + ",");
////		System.out.print(orderListVO.getActID() + ",");
////		System.out.print(orderListVO.getActTotal() + ",");
////		System.out.print(orderListVO.getQRcodeID() + ",");
////		System.out.print(orderListVO.getOrderListTime() + ",");
////		System.out.print(orderListVO.getReviewContent() + ",");
////		System.out.print(orderListVO.getFiveStarReview() + ",");
////		System.out.print(orderListVO.getSeatRows() + ",");
////		System.out.print(orderListVO.getSeatColumns() + ",");
////		System.out.print(orderListVO.getVacancy() + ",");
//		
////		// getAll
////		List<OrderListVO> list = orderListJDBCDAO.getAll();
////		for (OrderListVO oList : list) {
////			System.out.println(oList);
////		}
//
//	}
//}