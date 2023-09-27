package com.tha103.newview.discount.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;

public class DiscountJDBCDAO implements DiscountDAO_interface {
	private static final String INSERT_STMT_P = "INSERT INTO discount (pubID, discountContent, disAmount, discountCode, disStartDate, disFinishDate) VALUES (?, ?, ?, ?, ?, ?) ";
	private static final String INSERT_STMT_A = "INSERT INTO discount (adminID, discountContent, disAmount, discountCode, disStartDate, disFinishDate) VALUES ( ?, ?, ?, ?, ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT * FROM discount ORDER BY discountNO ";
	private static final String GET_ONE_STMT = "SELECT * FROM discount WHERE discountNO = ? ";
	private static final String DELETE_STMT = "DELETE FROM discount WHERE discountNO = ? ";
	private static final String UPDATE_STMT_P = "UPDATE discount SET discountContent=?, disAmount=?, discountCode=?, disStartDate=?, disFinishDate=? WHERE discountNO = ? AND pubID=? ";
	private static final String UPDATE_STMT_A = "UPDATE discount SET discountContent=?, disAmount=?, discountCode=?, disStartDate=?, disFinishDate=? WHERE discountNO = ? AND adminID=? ";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(DiscountVO DiscountVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");

			if (DiscountVO.getAdminID() == null && DiscountVO.getPubID() != null) {
				pstmt = con.prepareStatement(INSERT_STMT_P);
				pstmt.setInt(1, DiscountVO.getPubID());
				pstmt.setString(2, DiscountVO.getDiscountContent());
				pstmt.setInt(3, DiscountVO.getDisAmount());
				pstmt.setString(4, DiscountVO.getDiscountCode());
				pstmt.setDate(5, DiscountVO.getDisStartDate());
				pstmt.setDate(6, DiscountVO.getDisFinishDate());
			} else if (DiscountVO.getPubID() == null && DiscountVO.getAdminID() != null) {
				pstmt = con.prepareStatement(INSERT_STMT_A);
				pstmt.setInt(1, DiscountVO.getAdminID());
				pstmt.setString(2, DiscountVO.getDiscountContent());
				pstmt.setInt(3, DiscountVO.getDisAmount());
				pstmt.setString(4, DiscountVO.getDiscountCode());
				pstmt.setDate(5, DiscountVO.getDisStartDate());
				pstmt.setDate(6, DiscountVO.getDisFinishDate());

			}
			pstmt.executeUpdate();

			System.out.println("data inserted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}

	}

	@Override
	public void update(DiscountVO DiscountVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");

			if (DiscountVO.getAdminID() == null && DiscountVO.getPubID() != null) {

				pstmt = con.prepareStatement(UPDATE_STMT_P);
				pstmt.setString(1, DiscountVO.getDiscountContent());
				pstmt.setInt(2, DiscountVO.getDisAmount());
				pstmt.setString(3, DiscountVO.getDiscountCode());
				pstmt.setDate(4, DiscountVO.getDisStartDate());
				pstmt.setDate(5, DiscountVO.getDisFinishDate());
				pstmt.setInt(6, DiscountVO.getDiscountNO());
				pstmt.setInt(7, DiscountVO.getPubID());
				pstmt.executeUpdate();
				System.out.println("data updated");

			} else if (DiscountVO.getPubID() == null && DiscountVO.getAdminID() != null) {
				pstmt = con.prepareStatement(UPDATE_STMT_A);
				pstmt.setString(1, DiscountVO.getDiscountContent());
				pstmt.setInt(2, DiscountVO.getDisAmount());
				pstmt.setString(3, DiscountVO.getDiscountCode());
				pstmt.setDate(4, DiscountVO.getDisStartDate());
				pstmt.setDate(5, DiscountVO.getDisFinishDate());
				pstmt.setInt(6, DiscountVO.getDiscountNO());
				pstmt.setInt(7, DiscountVO.getAdminID());
				pstmt.executeUpdate();
				System.out.println("data updated");

			} else if (DiscountVO.getPubID() == null && DiscountVO.getAdminID() == null) {
				System.out.println("查無此優惠無法修改");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}

	}

	@Override
	public void delete(Integer discountNO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, discountNO);

			pstmt.executeUpdate();

			System.out.println("data deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}

	}

	@Override
	public DiscountVO findByPrimaryKey(Integer discountNO) {
		// TODO Auto-generated method stub
		DiscountVO discount = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, discountNO);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				discount = new DiscountVO();
				discount.setDiscountNO(rs.getInt("discountNO"));
				discount.setPubID(rs.getInt("pubID"));
				discount.setAdminID(rs.getInt("adminID"));
				discount.setDiscountContent(rs.getString("discountContent"));
				discount.setDisAmount(rs.getInt("disAmount"));
				discount.setDiscountCode(rs.getString("discountCode"));

				discount.setDisStartDate(rs.getDate("disStartDate"));
				discount.setDisFinishDate(rs.getDate("disFinishDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return discount;

	}

	@Override
	public List<DiscountVO> getAll() {
		List<DiscountVO> list = new ArrayList<>();
		DiscountVO discount = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				discount = new DiscountVO();
				discount.setDiscountNO(rs.getInt("discountNO"));
				discount.setPubID(rs.getInt("pubID"));
				discount.setAdminID(rs.getInt("adminID"));
				discount.setDiscountContent(rs.getString("discountContent"));
				discount.setDisAmount(rs.getInt("disAmount"));
				discount.setDiscountCode(rs.getString("discountCode"));

				discount.setDisStartDate(rs.getDate("disStartDate"));
				discount.setDisFinishDate(rs.getDate("disFinishDate"));
				list.add(discount);
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

	public static void main(String[] args) {
		DiscountDAO_interface dao = new DiscountJDBCDAO();

		// insert// OK
//		DiscountVO discount1 = new DiscountVO();
//
//		discount1.setAdminID(1);
//		discount1.setDiscountContent("moon festival discount");
//		discount1.setDisAmount(100);
//		discount1.setDiscountCode("moon");
//		discount1.setDisStartDate(java.sql.Date.valueOf("2023-09-28"));
//		discount1.setDisFinishDate(java.sql.Date.valueOf("2023-09-29"));
//
//		dao.insert(discount1);

		// Update OK
//		DiscountVO discount2 = new DiscountVO();
//		discount2.setDiscountNO(1);
//		discount2.setPubID(1);
//		discount2.setAdminID(1);
//		discount2.setDiscountContent("national day discount");
//		discount2.setDisAmount(100000);
//		discount2.setDiscountCode("taiwan");
//		discount2.setDisStartDate(java.sql.Date.valueOf("2023-10-09"));
//		discount2.setDisFinishDate(java.sql.Date.valueOf("2023-10-10"));
////		
//		dao.update(discount2);

		// Delete OK
//		dao.delete(4);

		// findPK OK
		DiscountVO discount3 = dao.findByPrimaryKey(1);
		System.out.println(discount3);

		// getAll OK
		List<DiscountVO> list = dao.getAll();
		for (DiscountVO lists : list) {
			System.out.println(lists);
		}
	}
}
