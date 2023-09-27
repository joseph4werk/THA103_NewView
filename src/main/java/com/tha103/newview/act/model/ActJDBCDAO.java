package com.tha103.newview.act.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;



public class ActJDBCDAO implements ActDAO_interface {
	 

	private static final String INSERT_STMT = "INSERT INTO Act (actName,actPrice,actCategoryID,pubID,actTime,cityAddressID,actScope,actIntroduce,actContent,time,actDate,approvalCondition,cityAddress) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT actID,actName,actPrice,actCategoryID,pubID,actTime,cityAddressID,actScope,actIntroduce,actContent,time,actDate,approvalCondition,cityAddress FROM Act order by ActID";
	private static final String GET_ONE_STMT = "SELECT actID,actName,actPrice,actCategoryID,pubID,actTime,cityAddressID,actScope,actIntroduce,actContent,time,actDate,approvalCondition,cityAddress FROM Act where ActID = ?";
	private static final String DELETE = "DELETE FROM Act where ActID = ?";
	private static final String UPDATE = 
	"UPDATE Act set actName=?, actPrice=? ,actCategoryID=? ,pubID=? , actTime=?,cityAddressID,actScope=?, actIntroduce=?, time=?,actDate=?,approvalCondition=?,cityAddress=? where actID = ?";
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(ActVO ActID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, ActID.getActName());
			pstmt.setInt(2, ActID.getActPrice());
			pstmt.setInt(3,ActID.getActCategoryID());
			pstmt.setInt(4, ActID.getPubID());
			pstmt.setDate(5, ActID.getActTime());
			pstmt.setInt(6, ActID.getCityAddressID());
			pstmt.setInt(7, ActID.getActScope());
			pstmt.setString(8, ActID.getActIntroduce());
			pstmt.setString(9, ActID.getActContent());
			pstmt.setTime(10, ActID.getTime());
			pstmt.setDate(11, ActID.getActDate());
			pstmt.setInt(12, ActID.getApprovalCondition());
			pstmt.setString(13, ActID.getCityAddress());
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
	public void delete(Integer ActID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ActID);

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
	public List<ActVO> getAll() {
		// TODO Auto-generated method stub
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO ActVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				ActVO = new ActVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActPrice(rs.getInt("actPrice"));
				ActVO.setActCategoryID(rs.getInt("actCategoryID"));
				ActVO.setPubID(rs.getInt("pubID"));
				ActVO.setActTime(rs.getDate("actTime"));
				ActVO.setActScope(rs.getInt("actScope"));
				ActVO.setActIntroduce(rs.getString("actIntroduce"));
				ActVO.setActContent(rs.getString("actContent"));
			
				ActVO.setTime(rs.getTime("time"));
				ActVO.setActDate(rs.getDate("actDate"));
				ActVO.setApprovalCondition(rs.getInt("approvalCondition"));
				ActVO.setCityAddress(rs.getString("cityAddress"));
				ActVO.setPubID(rs.getInt("pubID"));
				ActVO.setCityAddressID(rs.getInt("cityAddressID"));
				list.add(ActVO); // Store the row in the list
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
	public ActVO findByPrimaryKey(Integer ActID) {
		// TODO Auto-generated method stub
		ActVO ActVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ActID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ActVO = new ActVO();
				ActVO.setActID(rs.getInt("actID"));
				ActVO.setActName(rs.getString("actName"));
				ActVO.setActPrice(rs.getInt("actPrice"));
				ActVO.setActCategoryID(rs.getInt("actCategoryID"));
				ActVO.setPubID(rs.getInt("pubID"));
				ActVO.setActTime(rs.getDate("actTime"));
				ActVO.setActScope(rs.getInt("actScope"));
				ActVO.setActIntroduce(rs.getString("actIntroduce"));
				ActVO.setActContent(rs.getString("actContent"));
			
				ActVO.setTime(rs.getTime("time"));
				ActVO.setActDate(rs.getDate("actDate"));
				ActVO.setApprovalCondition(rs.getInt("approvalCondition"));
				ActVO.setCityAddress(rs.getString("cityAddress"));
				ActVO.setPubID(rs.getInt("pubID"));
				ActVO.setCityAddressID(rs.getInt("cityAddressID"));
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

		return ActVO;
	}
	@Override
	public void update(ActVO actVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, actVO.getActName());
			pstmt.setInt(2, actVO.getActPrice());
			pstmt.setInt(3,actVO.getActCategoryID());
			pstmt.setInt(4, actVO.getPubID());
			pstmt.setDate(5, actVO.getActTime());
			pstmt.setInt(6, actVO.getCityAddressID());
			pstmt.setInt(7, actVO.getActScope());
			pstmt.setString(8, actVO.getActIntroduce());
			pstmt.setString(9, actVO.getActContent());
			pstmt.setTime(10, actVO.getTime());
			pstmt.setDate(11, actVO.getActDate());
			pstmt.setInt(12, actVO.getApprovalCondition());
			pstmt.setString(13, actVO.getCityAddress());
			pstmt.setInt(14, actVO.getActID());
			
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	public static void main(String[] args) {
		ActJDBCDAO actJDBCDAO = new ActJDBCDAO();
		
		ActVO actVO = new ActVO();
		
		actVO.setActName("ACT TEST");
		actVO.setActPrice(1000);
		actVO.setActCategoryID(1);
		actVO.setPubID(1);
		actVO.setActTime(java.sql.Date.valueOf("1990-10-13"));
		actVO.setCityAddressID(1);
		actVO.setActScope(400);
		actVO.setActIntroduce("actIntroduce");
		actVO.setActContent("actContent");
		actVO.setTime(java.sql.Time.valueOf("10:0:0"));
		actVO.setActDate(java.sql.Date.valueOf("1990-12-13"));
		actVO.setApprovalCondition(-1);
		actVO.setCityAddress("THE CONCERT");
		actJDBCDAO.insert(actVO);
	}

	

}
