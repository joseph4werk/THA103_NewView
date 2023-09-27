package com.tha103.newview.actpic.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.tha103.util.Util;

public class ActPicJDBCDAO implements ActPicDAO_Interface{
	
	private static final String INSERT_STMT = "INSERT INTO ActPic (actID,actPic) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT actPicID,actID,actPic FROM ActPic order by actPicID";
	private static final String GET_ONE_STMT = "SELECT actPicID,actID,actPic  FROM ActPic where actPicID = ?";
	private static final String DELETE = "DELETE FROM ActPic where actPicID = ?";
	
	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void insert(ActPicVO actPicID) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, actPicID.getActID());
			pstmt.setBytes(2, actPicID.getActPic());
			
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
	public void delete(Integer ActPicVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ActPicVO);

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
	public ActPicVO findByPrimaryKey(Integer actPicID) {
		// TODO Auto-generated method stub
		ActPicVO ActPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, actPicID);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				ActPicVO = new ActPicVO();
				ActPicVO.setActPicID(rs.getInt("actPicID"));
				ActPicVO.setActID(rs.getInt("actID"));
				ActPicVO.setActPic(rs.getBytes("actPic"));
				
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

		return ActPicVO;
		
		
	}

	@Override
	public List<ActPicVO> getAll() {
		// TODO Auto-generated method stub
		List<ActPicVO> list = new ArrayList<ActPicVO>();
		ActPicVO ActPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				ActPicVO = new ActPicVO();
				ActPicVO.setActPicID(rs.getInt("actPicID"));
				ActPicVO.setActPic(rs.getBytes("actPic"));
				
				list.add(ActPicVO); // Store the row in the list
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

	public byte[] gifToByteArray(String gif) throws IOException {
	    File gifFile = new File(gif);
	    
	    BufferedImage image = ImageIO.read(gifFile);
	    
	    if (image != null) {
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "gif", baos);
	        return baos.toByteArray();
	    } else {
	        throw new IOException("Can not read .gif");
	    }
	}
	

	public  byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);

		byte[] buffer = fis.readAllBytes();
		fis.close();
		return buffer;
	}
	
	public static void main(String[] args) {
		
		
		ActPicJDBCDAO actPicJDBCDAO = new ActPicJDBCDAO();
		ActPicVO actPicVO = new ActPicVO();
		actPicVO.setActID(1);
		try {
			
//			actPicVO.setActPic(actPicJDBCDAO.gifToByteArray("image/ok.gif"));
			actPicVO.setActPic(actPicJDBCDAO.getPictureByteArray("C:/THA103_WebApp/eclipse_WTP_workspace1/THA103G5_All_ver9/src/main/java/com/newview/model/actpic/image/ok2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		actPicJDBCDAO.insert(actPicVO);
	}

}
