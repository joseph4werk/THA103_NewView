package com.tha103.newview.actcategory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;

public class ActCategoryJDBCDAO implements ActCategoryDAO_Interface{
	
	private static final String INSERT_STMT = "INSERT INTO ActCategory (actCategoryName) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT actCategoryID,actCategoryName FROM ActCategory order by actCategoryID";
	private static final String GET_ONE_STMT = "SELECT actCategoryID,actCategoryName  FROM ActCategory where actCategoryID = ?";
	private static final String DELETE = "DELETE FROM ActCategory where actCategoryID = ?";
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(ActCategoryVO actCategoryID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, actCategoryID.getActCategoryName());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	@Override
	public void delete(Integer ActCategoryVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

//			Class.forName(driver);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ActCategoryVO);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	@Override
	public List<ActCategoryVO> getAll() {
		// TODO Auto-generated method stub
		List<ActCategoryVO> list = new ArrayList<ActCategoryVO>();
		ActCategoryVO ActCategoryVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				ActCategoryVO = new ActCategoryVO();
				ActCategoryVO.setActCategoryID(rs.getInt("actCategoryID"));
				ActCategoryVO.setActCategoryName(rs.getString("actCategoryName"));
				
				list.add(ActCategoryVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list;

		
	}
	@Override
	public ActCategoryVO findByPrimaryKey(Integer actCategoryID) {
		// TODO Auto-generated method stub
		ActCategoryVO ActCategoryVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

//			Class.forName(driver);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actCategoryID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ActCategoryVO = new ActCategoryVO();
				ActCategoryVO.setActCategoryID(rs.getInt("actCategoryID"));
				ActCategoryVO.setActCategoryName(rs.getString("ActCategoryName"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return ActCategoryVO;
		
	}
	
	public static void main(String[] args) {
		ActCategoryJDBCDAO actCategoryJDBCDAO = new ActCategoryJDBCDAO();
		ActCategoryVO actCategoryVO = new ActCategoryVO();
		actCategoryVO.setActCategoryName("TEST CATEGORY");
		actCategoryJDBCDAO.insert(actCategoryVO);
	}


}
