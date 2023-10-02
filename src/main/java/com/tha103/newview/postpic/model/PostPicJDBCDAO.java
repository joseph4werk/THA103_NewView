//package com.tha103.newview.postpic.model;
//
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.imageio.ImageIO;
//
//import com.tha103.util.Util;
//
//public class PostPicJDBCDAO implements PostPicDAO_interface {
//	public static final String INSERT_STMT = "insert into postPic (postID, postPic) values(?,?)";
//	public static final String FIND_BY_PK = "select * from postPic where postPicID = ?";
//	public static final String GET_ALL = "select * from postPic order by postPicID desc";
//	private static final String DELETE = "delete from postPic where postPicID = ?";
//	private static final String UPDATE = "update postPic set postID=?, postPic=? where postPicID = ?";
//	static {
//		try {
//			Class.forName(Util.DRIVER);
//		} catch (ClassNotFoundException ce) {
//			ce.printStackTrace();
//		}
//	}
//
//	@Override
//	public void insert(PostPicVO PostPicVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, PostPicVO.getPostID());
//			pstmt.setBytes(2, PostPicVO.getPostPic());
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//	}
//
//	@Override
//	public void update(PostPicVO PostPicVO) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(UPDATE);
//
//			pstmt.setInt(1, PostPicVO.getPostID());
//			pstmt.setBytes(2, PostPicVO.getPostPic());
//			pstmt.setInt(3, PostPicVO.getPostPicID());
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//
//	}
//
//	@Override
//	public void delete(Integer postPicID) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(DELETE);
//			pstmt.setInt(1, postPicID);
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, null);
//		}
//
//	}
//
//	@Override
//	public PostPicVO findByPrimaryKey(Integer postPicID) {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		PostPicVO pic = null;
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(FIND_BY_PK);
//			pstmt.setInt(1, postPicID);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				pic = new PostPicVO();
//				pic.setPostPicID(postPicID);
//				pic.setPostID(rs.getInt("postID"));
//				pic.setPostPic(rs.getBytes("postPic"));
//
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return pic;
//
//	}
//
//	@Override
//	public List<PostPicVO> getAll() {
//
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<PostPicVO> picList = new ArrayList<>();
//
//		try {
//			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
//			pstmt = con.prepareStatement(GET_ALL);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//
//				PostPicVO pic = new PostPicVO();
//
//				pic.setPostPicID(rs.getInt("postPicID"));
//				pic.setPostID(rs.getInt("postID"));
//				pic.setPostPic(rs.getBytes("postPic"));
//				picList.add(pic);
//			}
//
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			Util.closeResource(con, pstmt, rs);
//		}
//
//		return picList;
//
//	}
//
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//
//	}
//
//	public byte[] gifToByteArray(String gif) throws IOException {
//		File gifFile = new File(gif);
//
//		BufferedImage image = ImageIO.read(gifFile);
//
//		if (image != null) {
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			ImageIO.write(image, "gif", baos);
//			return baos.toByteArray();
//		} else {
//			throw new IOException("failed");
//		}
//	}
//
//	public static void main(String args[]) throws IOException {
//
//		PostPicDAO_interface dao = new PostPicJDBCDAO();
//		PostPicJDBCDAO PostPicJDBCDAO = new PostPicJDBCDAO();
//
//		 //Insert// OK
////		PostPicVO pic = new PostPicVO();
////		pic.setPostID(01);
////		byte[] img = null;
////		try {
////			img = PostPicJDBCDAO.getPictureByteArray("C:/THA103_WebApp/eclipse_WTP_workspace1/THA103G5_All_ver9/src/main/java/com/newview/model/postpic/image/ok2.jpg");
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
////		pic.setPostPic(img);
////		dao.insert(pic);
//
////		//Update// OK
////		PostPicVO pic2 = new PostPicVO();
////		pic2.setPostPicID(1);
////		pic2.setPostID(2);
////		byte[] img2 = getPictureByteArray("C:/THA103_WebApp/eclipse_WTP_workspace1/THA103G5_All_ver9/src/main/java/com/newview/model/postpic/image/ok2.jpg");
////		pic2.setPostPic(img2);
////		dao.update(pic2);
//
//		//Delete// ok
////		dao.delete(4);
////
////		//FindPK// ok
//		PostPicVO pic3 = dao.findByPrimaryKey(1);
//		System.out.println(pic3);
////
////		//Find-All// ok
//		List<PostPicVO> list = dao.getAll();
//		for (PostPicVO lists : list) {
//			System.out.println(lists);
//		}
//	}
//}
//
