package com.tha103.newview.mylike.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;




public class MyLikeJDBCDAO implements MyLikeDAO_interface{
	
	private static final String INSERT_STMT = 
		"INSERT INTO myLike (userID, actID) VALUES (?, ?) ";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM myLike ORDER BY myLikeID ";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM myLike WHERE myLikeID = ? ";
	private static final String DELETE_STMT = 
		"DELETE FROM myLike WHERE myLikeID = ? ";
	private static final String UPDATE_STMT = 
		"UPDATE myLike SET userID=? , actID=? WHERE myLikeID = ? ";

		
		static {
			try {
				Class.forName(Util.DRIVER);
			} catch (ClassNotFoundException ce) {
				ce.printStackTrace();
			}
		}
		

	@Override
	public void insert(MyLikeVO MyLikeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, MyLikeVO.getUserID());
			pstmt.setInt(2, MyLikeVO.getActID());

			pstmt.executeUpdate();

			System.out.println("data inserted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
	}



	@Override
	public void update(MyLikeVO MyLikeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(UPDATE_STMT);

			
			pstmt.setInt(1, MyLikeVO.getUserID());
			pstmt.setInt(2, MyLikeVO.getActID());
			pstmt.setInt(3, MyLikeVO.getMyLikeID());
			pstmt.executeUpdate();

			System.out.println("data updated");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
		
	}

	@Override
	public void delete(Integer myLikeID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, myLikeID);

			pstmt.executeUpdate();

			System.out.println("data deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}
		
		
	}

	@Override
	public MyLikeVO findByPrimaryKey(Integer myLikeID) {
		
		MyLikeVO myLike = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, myLikeID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				myLike = new MyLikeVO();
				myLike.setMyLikeID(rs.getInt("myLikeID"));
				myLike.setUserID(rs.getInt("userID"));
				myLike.setActID(rs.getInt("actID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return myLike;
		
	}

	@Override
	public List<MyLikeVO> getAll() {
		
		List<MyLikeVO> list = new ArrayList<>();
		MyLikeVO myLike = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				myLike = new MyLikeVO();
				myLike.setMyLikeID(rs.getInt("myLikeID"));
				myLike.setUserID(rs.getInt("userID"));
				myLike.setActID(rs.getInt("actID"));
				list.add(myLike);
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
		MyLikeDAO_interface dao = new MyLikeJDBCDAO();

		//insert OK
//		MyLikeVO myLike1 = new MyLikeVO();
//		myLike1.setUserID(1);
//		myLike1.setActID(1);
//		dao.insert(myLike1);

		//update
		MyLikeVO myLike2 = new MyLikeVO();
		myLike2.setMyLikeID(1);
		myLike2.setUserID(2);
		myLike2.setActID(1);		
		dao.update(myLike2);

		// Delete OK
//		dao.delete(4);

		// Find PK OK
//		MyLikeVO myLike3 = dao.findByPrimaryKey(1);
//		System.out.println(myLike3);

		// FindALL OK
//		List<MyLikeVO> list = dao.getAll();
//		for (MyLikeVO lists : list) {
//			System.out.println(lists);
//		}
	}
	
}