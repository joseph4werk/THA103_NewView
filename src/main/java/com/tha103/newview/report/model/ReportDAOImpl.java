package com.tha103.newview.report.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;

public class ReportDAOImpl implements ReportDAO {
//	CRUD SQL Statement
	private static final String INSERT_STMT = "INSERT INTO report (userID, postID, reportContent, reportStatus) "
			+ "VALUES (?, ?, ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT * FROM report ORDER BY reportID ";
	private static final String GET_ONE_STMT = "SELECT * FROM report WHERE reportID = ? ";
	private static final String DELETE_STMT = "DELETE FROM report WHERE reportID = ? ";
	private static final String UPDATE_STMT = "UPDATE report SET userID = ?, postID = ?, reportContent = ?, reportStatus = ? WHERE reportID = ? ";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, reportVO.getUserID());
			pstmt.setInt(2, reportVO.getPostID());
			pstmt.setString(3, reportVO.getReportContent());
			pstmt.setInt(4, reportVO.getReportStatus());

			pstmt.executeUpdate();

			System.out.println("data inserted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, reportVO.getUserID());
			pstmt.setInt(2, reportVO.getPostID());
			pstmt.setString(3, reportVO.getReportContent());
			pstmt.setInt(4, reportVO.getReportStatus());
			pstmt.setInt(5, reportVO.getReportID());

			pstmt.executeUpdate();

			System.out.println("date updated");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer reportID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, reportID);

			pstmt.executeUpdate();

			System.out.println("data deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public ReportVO findeByPrimaryKey(Integer reportID) {

		ReportVO report = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, reportID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				report = new ReportVO();
				report.setReportID(rs.getInt("reportID"));
				report.setUserID(rs.getInt("userID"));
				report.setPostID(rs.getInt("postID"));
				report.setReportContent(rs.getString("reportContent"));
				report.setReportStatus(rs.getInt("reportStatus"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return report;
	}

	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<>();
		ReportVO report = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				report = new ReportVO();
				report.setReportID(rs.getInt("reportID"));
				report.setUserID(rs.getInt("userID"));
				report.setPostID(rs.getInt("postID"));
				report.setReportContent(rs.getString("reportContent"));
				report.setReportStatus(rs.getInt("reportStatus"));
				list.add(report);
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
