//
//package com.tha103.newview.cartact.model;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.tha103.util.Util;
//
//public class CartActJDBCDAO implements CartActDAO_Interface {
//
//	private static final String INSERT_STMT = "INSERT INTO CartAct (userID,actID,cartQuantity) VALUES (?,?,?)";
//	private static final String GET_ALL_STMT = "SELECT * FROM cartAct order by cartActID";
//	private static final String GET_ONE_STMT = "SELECT cartActID,userID,actID,cartQuantity FROM cartAct where cartActID = ?";
//	private static final String DELETE = "DELETE FROM CartAct where cartActID = ?";
//	private static final String UPDATE = "UPDATE cartAct set userID=?,actID=?,cartQuantity=? where cartActID = ?";
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
//	public void insert(CartActVO cartActID) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
//			pstmt.setInt(1, cartActID.getUserID());
//			pstmt.setInt(2, cartActID.getActID());
//			pstmt.setInt(3, cartActID.getCartQuantity());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void update(CartActVO cartActVO) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, cartActVO.getUserID());
//			pstmt.setInt(2, cartActVO.getActID());
//			pstmt.setInt(3, cartActVO.getCartQuantity());
//			pstmt.setInt(4, cartActVO.getCartActID());
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public void delete(Integer cartActID) {
//		// TODO Auto-generated method stub
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(DELETE);
//
//			pstmt.setInt(1, cartActID);
//
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//
//	@Override
//	public CartActVO findByPrimaryKey(Integer cartActID) {
//		CartActVO cartActVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setInt(1, cartActID);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				cartActVO = new CartActVO();
//				cartActVO.setCartActID(cartActID);
//				cartActVO.setUserID(rs.getInt("userID"));
//				cartActVO.setActID(rs.getInt("actID"));
//				cartActVO.setCartQuantity(rs.getInt("cartQuantity"));
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
//		return cartActVO;
//
//	}
//
//	@Override
//	public List<CartActVO> getAll() {
//		List<CartActVO> list = new ArrayList<CartActVO>();
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
//				CartActVO cartActVO = new CartActVO();
//				cartActVO.setCartActID(rs.getInt("cartActID"));
//				cartActVO.setUserID(rs.getInt("userID"));
//				cartActVO.setActID(rs.getInt("actID"));
//				cartActVO.setCartQuantity(rs.getInt("cartQuantity"));
//
//				list.add(cartActVO); // Store the row in the list
//			}
//
//			// Handle any driver errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//		return list;
//
//	}
//
//	public static void main(String[] args) {
//		CartActJDBCDAO cartActJDBCDAO = new CartActJDBCDAO();
//		CartActVO cartActVO = new CartActVO();
//
////		//insert OK
////		cartActVO.setUserID(1);
////		cartActVO.setActID(4);
////		cartActVO.setCartQuantity(3000);
////		cartActJDBCDAO.insert(cartActVO);
//
////		//update OK
////		cartActVO.setCartActID(1);
////		cartActVO.setUserID(1);
////		cartActVO.setActID(4);
////		cartActVO.setCartQuantity(400);
////		cartActJDBCDAO.update(cartActVO);
//
////		//Delete OK
////		cartActJDBCDAO.delete(4);
//
//		//Find PK
//		cartActVO = cartActJDBCDAO.findByPrimaryKey(1);
//		System.out.print(cartActVO.getCartActID() + ",");
//		System.out.print(cartActVO.getUserID() + ",");
//		System.out.print(cartActVO.getActID() + ",");
//		System.out.print(cartActVO.getCartQuantity() + ",");
//
//		//All OK
//		List<CartActVO> list = cartActJDBCDAO.getAll();
//		for (CartActVO cList : list) {
//			System.out.println(cList);
//		}
//	}
//
//}