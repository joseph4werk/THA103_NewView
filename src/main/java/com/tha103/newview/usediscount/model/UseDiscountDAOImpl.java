package com.tha103.newview.usediscount.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;


public class UseDiscountDAOImpl implements UseDiscountDAO {
//	CRUD SQL Statement
	private static final String INSERT_STMT = "INSERT INTO useDiscount (discountNO, userID, ditUsed) "
			+ "VALUES (?, ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT * FROM useDiscount ORDER BY useDisID ";
	private static final String GET_ONE_STMT = "SELECT * FROM useDiscount WHERE useDisID = ? ";
	private static final String DELETE_STMT = "DELETE FROM useDiscount WHERE useDisID = ? ";
	private static final String UPDATE_STMT = "UPDATE useDiscount SET discountNO = ?, userID = ?, ditUsed = ? WHERE useDisID = ? ";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(UseDiscountVO useDiscountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, useDiscountVO.getDiscountNO());
			pstmt.setInt(2, useDiscountVO.getUserID());
			pstmt.setInt(3, useDiscountVO.getDitUsed());

			pstmt.executeUpdate();

			System.out.println("date inserted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(UseDiscountVO useDiscountVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, useDiscountVO.getDiscountNO());
			pstmt.setInt(2, useDiscountVO.getUserID());
			pstmt.setInt(3, useDiscountVO.getDitUsed());
			pstmt.setInt(4,useDiscountVO.getUseDisID());

			pstmt.executeUpdate();

			System.out.println("date updated");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer useDisID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, useDisID);

			pstmt.executeUpdate();

			System.out.println("data deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public UseDiscountVO findeByPrimaryKey(Integer useDisID) {

		UseDiscountVO use = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, useDisID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				use = new UseDiscountVO();
				use.setUseDisID(rs.getInt("useDisID"));
				use.setDiscountNO(rs.getInt("discountNO"));
				use.setUserID(rs.getInt("userID"));
				use.setDitUsed(rs.getInt("ditUsed"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}

		return use;
	}

	@Override
	public List<UseDiscountVO> getAll() {
		List<UseDiscountVO> list = new ArrayList<>();
		UseDiscountVO use = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				use = new UseDiscountVO();
				use.setUseDisID(rs.getInt("useDisID"));
				use.setDiscountNO(rs.getInt("discountNO"));
				use.setUserID(rs.getInt("userID"));
				use.setDitUsed(rs.getInt("ditUsed"));
				list.add(use);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return list;
	}

	private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
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
}
