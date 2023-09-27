package com.tha103.newview.pubuser.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;

public class PubUserJDBCDAO implements PubUserDAO_Interface {
	private static final String INSERT_STMT = "INSERT INTO pubuser(pubID,pubNickname,pubAccount,pubPassword,pubAuthority) VALUES(?,?,?,?,?)";
	private static final String UPDATE_STMT = "UPDATE pubuser SET pubID = ?,pubNickname = ?,pubAccount = ?,pubPassword = ?,pubAuthority = ? WHERE pubUserID = ?";
	private static final String DELETE_STMT = "DELETE FROM pubuser WHERE pubUserID = ?";
	private static final String FIND_BY_PK = "SELECT * FROM pubuser WHERE pubUserID = ?";
	private static final String GET_ALL = "SELECT * FROM pubuser";
	
	
	static {
		try {
			Class.forName(Util.DRIVER);
			
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	
	@Override
	public void insert(PubUserVO pubUserVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, pubUserVO.getPubID());
			pstmt.setString(2, pubUserVO.getPubNickname());
			pstmt.setString(3, pubUserVO.getPubAccount());
			pstmt.setString(4, pubUserVO.getPubPassword());
			pstmt.setInt(5, pubUserVO.getPubAuthority());

			
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
			
		} finally {
			closeResources(con, pstmt, null);

		}
	}



	@Override
	public void update(PubUserVO pubUserVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, pubUserVO.getPubID());
			pstmt.setString(2, pubUserVO.getPubNickname());
			pstmt.setString(3, pubUserVO.getPubAccount());
			pstmt.setString(4, pubUserVO.getPubPassword());
			pstmt.setInt(5, pubUserVO.getPubAuthority());
			pstmt.setInt(6, pubUserVO.getPubUserID());
			
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			se.printStackTrace();

		} finally {
			closeResources(con, pstmt, null);

		}
		
	}

	@Override
	public void delete(Integer pubUserID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, pubUserID);
			
			pstmt.executeUpdate();
			
		}catch (SQLException se) {
			se.printStackTrace();

		} finally {
			closeResources(con, pstmt, null);
		}
		
	}

	@Override
	public PubUserVO findByPK(Integer pubUserID) {
		// TODO Auto-generated method stub
		PubUserVO pubus = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);
			pstmt.setInt(1, pubUserID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pubus = new PubUserVO();
				pubus.setPubUserID(rs.getInt("pubUserID"));
				pubus.setPubID(rs.getInt("pubID"));
				pubus.setPubNickname(rs.getString("pubNickname"));
				pubus.setPubAccount(rs.getString("pubAccount"));
				pubus.setPubPassword(rs.getString("pubPassword"));
				pubus.setPubAuthority(rs.getInt("pubAuthority"));
				
			}
		}catch (SQLException se) {
			se.printStackTrace();

		} finally {
			closeResources(con, pstmt, rs);

		}
		return pubus;
	}

	@Override
	public List<PubUserVO> getAll() {
		// TODO Auto-generated method stub
		List<PubUserVO> pubusList = new ArrayList<>();
		PubUserVO pubus = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pubus = new PubUserVO();
				pubus.setPubUserID(rs.getInt("pubUserID"));
				pubus.setPubID(rs.getInt("pubID"));
				pubus.setPubNickname(rs.getString("pubNickname"));
				pubus.setPubAccount(rs.getString("pubAccount"));
				pubus.setPubPassword(rs.getString("pubPassword"));
				pubus.setPubAuthority(rs.getInt("pubAuthority"));
				
				pubusList.add(pubus);
			}
		}catch (SQLException se) {
			se.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return pubusList;
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
		PubUserJDBCDAO dao = new PubUserJDBCDAO();
		
		//新增---OK
//		PubUserVO pu = new PubUserVO();
//		pu.setPubID(1);
//		pu.setPubNickname("小白");
//		pu.setPubAccount("white");
//		pu.setPubPassword("991314");
//		pu.setPubAuthority(1);
//		dao.insert(pu);
//		System.out.println("新增成功");
		
		//修改---OK
//		PubUserVO pu2 = new PubUserVO();
//		pu2.setPubUserID(8);
//		pu2.setPubID(2);
//		pu2.setPubNickname("白白");
//		pu2.setPubAccount("happyyyy");
//		pu2.setPubPassword("22331000");
//		pu2.setPubAuthority(0);
//		dao.update(pu2);
//		System.out.println("修改成功");
		
		
		//刪除---OK
//		dao.delete(8);
//		System.out.println("刪除成功");
		
		
		//查詢單筆---OK
		PubUserVO pu3 = dao.findByPK(3);
		System.out.println(pu3.getPubID());
		System.out.println(pu3.getPubNickname());
		System.out.println(pu3.getPubAccount());
		System.out.println(pu3.getPubPassword());
		System.out.println(pu3.getPubAuthority());
		
		
//		//查詢多筆---OK
//		List<PubUserVO> list = dao.getAll();
//		for (PubUserVO pu4 : list) {
//			System.out.print(pu4.getPubUserID()+" ");
//			System.out.print(pu4.getPubID()+"");
//			System.out.print(pu4.getPubNickname()+" ");
//			System.out.print(pu4.getPubAccount()+" ");
//			System.out.print(pu4.getPubPassword()+" ");
//			System.out.print(pu4.getPubAuthority()+" ");
//			System.out.println();
//		}
		
		
		
	}
}
