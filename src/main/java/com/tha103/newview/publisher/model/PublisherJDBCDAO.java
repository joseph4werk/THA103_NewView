package com.tha103.newview.publisher.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;


public class PublisherJDBCDAO implements PublisherDAO_Interface{
	private static final String INSERT_STMT = "INSERT INTO publisher(pubName,pubEmail) VALUES(?,?)";
	private static final String UPDATE_STMT = "UPDATE publisher SET pubName = ?,pubEmail = ? WHERE pubID = ?";
	private static final String DELETE_STMT = "DELETE FROM publisher WHERE pubID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM publisher WHERE pubID = ?";
	private static final String GET_ALL = "SELECT * FROM publisher";
	
	static {
		try {
			Class.forName(Util.DRIVER);

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void insert(PublisherVO publisherVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, publisherVO.getPubName());
			pstmt.setString(2, publisherVO.getPubEmail());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();

		} finally {
			closeResources(con, pstmt, null);

		}
		
	}

	@Override
	public void update(PublisherVO publisherVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setString(1, publisherVO.getPubName());
			pstmt.setString(2, publisherVO.getPubEmail());
			pstmt.setInt(3, publisherVO.getPubID());
			

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();

		} finally {
			closeResources(con, pstmt, null);

		}
		
	}

	@Override
	public void delete(Integer pubID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, pubID);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();

		} finally {
			closeResources(con, pstmt, null);
		}
		
	}

	@Override
	public PublisherVO findByPK(Integer pubID) {
		// TODO Auto-generated method stub
		PublisherVO pub = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, pubID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pub = new PublisherVO();
				pub.setPubID(rs.getInt("pubID"));
				pub.setPubName(rs.getString("pubName"));
				pub.setPubEmail(rs.getString("pubEmail"));

			}
		} catch (SQLException se) {
			se.printStackTrace();

		} finally {
			closeResources(con, pstmt, rs);

		}
		return pub;
		
	}

	@Override
	public List<PublisherVO> getAll() {
		// TODO Auto-generated method stub
		List<PublisherVO> pubList = new ArrayList<>();
		PublisherVO pub = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pub = new PublisherVO();
				pub.setPubID(rs.getInt("pubID"));
				pub.setPubName(rs.getString("pubName"));
				pub.setPubEmail(rs.getString("pubEmail"));
				pubList.add(pub);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return pubList;
		
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
		PublisherJDBCDAO dao = new PublisherJDBCDAO();
		
		//新增---OK
//		PublisherVO pub = new PublisherVO();
//		pub.setPubName("笑點有限娛樂公司");
//		pub.setPubEmail("noidear@gmail.com");
//		dao.insert(pub);
//		System.out.println("新增成功");
		
		//修改---OK
//		PublisherVO pub2 = new PublisherVO();
//		pub2.setPubID(2);
//		pub2.setPubName("奇奇妙妙工作坊");
//		pub2.setPubEmail("mouse@gmail.com");
//		dao.update(pub2);
//		System.out.println("修改成功");
		
		//刪除---OK
//		dao.delete(6);
//		System.out.println("刪除成功");
		
		//查詢單筆---OK
//		PublisherVO pu4 = dao.findByPK(1);
//		System.out.println(pu4.getPubName());
//		System.out.println(pu4.getPubEmail());
		
		//查詢多筆---OK
//		List<PublisherVO> list = dao.getAll();
//		for(PublisherVO pu4 : list) {
//			System.out.print(pu4.getPubID());
//			System.out.print(pu4.getPubName());
//			System.out.print(pu4.getPubEmail());
//			System.out.println();
//		}
		
		
		
	}
	

}
