package com.tha103.newview.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;

public class UserDAOImpl implements UserDAO {
//	CRUD SQL Statement
	private static final String INSERT_STMT = "INSERT INTO user (userName, userAccount, userPassword, userBirth, userCell, userEmail, userNickname, buyAuthority, speakAuthority) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT * FROM user ORDER BY userID ";
	private static final String GET_ONE_STMT = "SELECT * FROM user WHERE userID = ? ";
	private static final String DELETE_STMT = "DELETE FROM user WHERE userID = ? ";
	private static final String UPDATE_STMT = "UPDATE user SET userName = ?, userAccount = ?, userPassword = ?, userBirth = ?, userCell = ?, userEmail = ?, userNickname = ?, buyAuthority = ?, speakAuthority = ? WHERE userID = ? ";

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(UserVO userVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, userVO.getUserName());
			pstmt.setString(2, userVO.getUserAccount());
			pstmt.setString(3, userVO.getUserPassword());
			pstmt.setDate(4, userVO.getUserBirth());
			pstmt.setString(5, userVO.getUserCell());
			pstmt.setString(6, userVO.getUserEmail());
			pstmt.setString(7, userVO.getUserNickname());
			pstmt.setInt(8, userVO.getBuyAuthority());
			pstmt.setInt(9, userVO.getSpeakAuthority());

			pstmt.executeUpdate();

			System.out.println("date inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void update(UserVO userVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, userVO.getUserName());
			pstmt.setString(2, userVO.getUserAccount());
			pstmt.setString(3, userVO.getUserPassword());
			pstmt.setDate(4, userVO.getUserBirth());
			pstmt.setString(5, userVO.getUserCell());
			pstmt.setString(6, userVO.getUserEmail());
			pstmt.setString(7, userVO.getUserNickname());
			pstmt.setInt(8, userVO.getBuyAuthority());
			pstmt.setInt(9, userVO.getSpeakAuthority());
			pstmt.setInt(10, userVO.getUserID());
			
			pstmt.executeUpdate();

			System.out.println("data updated");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}

	@Override
	public void delete(Integer userID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(Util.DRIVER);
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, userID);
			
			pstmt.executeUpdate();

			System.out.println("data deleted");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public UserVO findByPrimaryKey(Integer userID) {

		UserVO user = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new UserVO();
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setUserAccount(rs.getString("userAccount"));
				user.setUserBirth(rs.getDate("userBirth"));
				user.setUserCell(rs.getString("userCell"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserNickname(rs.getString("userNickname"));
				user.setBuyAuthority(rs.getInt("buyAuthority"));
				user.setSpeakAuthority(rs.getInt("speakAuthority"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}

		return user;
	}

	@Override
	public List<UserVO> getAll() {
		List<UserVO> list = new ArrayList<>();
		UserVO user = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				user = new UserVO();
				user.setUserID(rs.getInt("userID"));
				user.setUserName(rs.getString("userName"));
				user.setUserAccount(rs.getString("userAccount"));
				user.setUserBirth(rs.getDate("userBirth"));
				user.setUserCell(rs.getString("userCell"));
				user.setUserEmail(rs.getString("userEmail"));
				user.setUserNickname(rs.getString("userNickname"));
				user.setBuyAuthority(rs.getInt("buyAuthority"));
				user.setSpeakAuthority(rs.getInt("speakAuthority"));
				list.add(user);
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
