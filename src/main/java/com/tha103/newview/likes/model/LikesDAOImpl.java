package com.tha103.newview.likes.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;

public class LikesDAOImpl implements LikesDAO {
//	CRUD SQL Statement
	private static final String INSERT_STMT = "INSERT INTO likes (postID, userID, likeOrNot) " + "VALUES (?, ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT * FROM likes ORDER BY likeID ";
	private static final String GET_ONE_STMT = "SELECT * FROM likes WHERE likeID = ? ";
	private static final String DELETE_STMT = "DELETE FROM likes WHERE likeID = ? ";
	private static final String UPDATE_STMT = "UPDATE likes SET postID = ?, userID = ?, likeOrNot = ? WHERE likeID = ? ";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(LikesVO likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, likeVO.getPostID());
			pstmt.setInt(2, likeVO.getUserID());
			pstmt.setInt(3, likeVO.getLikeOrNot());

			pstmt.executeUpdate();

			System.out.println("data inserted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(LikesVO likeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, likeVO.getLikeID());
			pstmt.setInt(2, likeVO.getUserID());
			pstmt.setInt(3, likeVO.getLikeOrNot());
			pstmt.setInt(4, likeVO.getLikeID());

			pstmt.executeUpdate();

			System.out.println("data updated");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer likeID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, likeID);

			pstmt.executeUpdate();

			System.out.println("data deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public LikesVO findByPrimaryKey(Integer likeID) {

		LikesVO like = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, likeID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				like = new LikesVO();
				like.setLikeID(rs.getInt("likeID"));
				like.setPostID(rs.getInt("postID"));
				like.setUserID(rs.getInt("userID"));
				like.setLikeOrNot(rs.getInt("likeOrNot"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return like;
	}

	@Override
	public List<LikesVO> getAll() {
		List<LikesVO> list = new ArrayList<>();
		LikesVO like = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				like = new LikesVO();
				like.setLikeID(rs.getInt("likeID"));
				like.setPostID(rs.getInt("postID"));
				like.setUserID(rs.getInt("userID"));
				like.setLikeOrNot(rs.getInt("likeOrNot"));
				list.add(like);
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
