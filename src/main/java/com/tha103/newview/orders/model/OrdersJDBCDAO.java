package com.tha103.newview.orders.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tha103.util.Util;



public class OrdersJDBCDAO implements OrdersDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO orders ( userID, ordTotal, discount, discountPrice, ordTime, pubID, ordType, actQuantity, discountNO) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
	private static final String GET_ALL_STMT = "SELECT * FROM orders ORDER BY orderID ";
	private static final String GET_ONE_STMT = "SELECT * FROM orders WHERE orderID = ? ";
	private static final String DELETE_STMT = "DELETE FROM orders WHERE orderID = ? ";
	private static final String UPDATE_STMT = "UPDATE orders SET userID=?, ordTotal=?, discount=?, discountPrice=?, ordTime=?, pubID=?, ordType=?, actQuantity=?, discountNO=? WHERE orderID = ? ";
	private static Timestamp now = new Timestamp(System.currentTimeMillis());

	static {
		try {
			Class.forName(Util.DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(OrdersVO OrdersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, OrdersVO.getUserID());
			pstmt.setInt(2, OrdersVO.getOrdTotal());
			pstmt.setInt(3, OrdersVO.getDiscount());
			pstmt.setInt(4, OrdersVO.getDiscountPrice());
			pstmt.setTimestamp(5, OrdersVO.getOrdTime());
			pstmt.setInt(6, OrdersVO.getPubID());
			pstmt.setInt(7, OrdersVO.getOrdType());
			pstmt.setInt(8, OrdersVO.getActQuantity());
			pstmt.setInt(9, OrdersVO.getDiscountNO());

			pstmt.executeUpdate();

			System.out.println("data inserted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}

	}

	@Override
	public void update(OrdersVO OrdersVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, OrdersVO.getUserID());
			pstmt.setInt(2, OrdersVO.getOrdTotal());
			pstmt.setInt(3, OrdersVO.getDiscount());
			pstmt.setInt(4, OrdersVO.getDiscountPrice());
			pstmt.setTimestamp(5, OrdersVO.getOrdTime());
			pstmt.setInt(6, OrdersVO.getPubID());
			pstmt.setInt(7, OrdersVO.getOrdType());
			pstmt.setInt(8, OrdersVO.getActQuantity());
			pstmt.setInt(9, OrdersVO.getDiscountNO());
			pstmt.setInt(10, OrdersVO.getOrderID());

			pstmt.executeUpdate();

			System.out.println("data updated");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}

	}

	@Override
	public void delete(Integer orderID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, orderID);

			pstmt.executeUpdate();

			System.out.println("data deleted");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, null);
		}

	}

	@Override
	public OrdersVO findByPrimaryKey(Integer orderID) {

		OrdersVO orders = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, orderID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				orders = new OrdersVO();
				orders.setOrderID(rs.getInt("orderID"));
				orders.setUserID(rs.getInt("userID"));
				orders.setOrdTotal(rs.getInt("ordTotal"));
				orders.setDiscount(rs.getInt("discount"));
				orders.setDiscountPrice(rs.getInt("discountPrice"));
				orders.setOrdTime(rs.getTimestamp("ordTime"));
				orders.setPubID(rs.getInt("pubID"));
				orders.setOrdType(rs.getInt("ordType"));
				orders.setActQuantity(rs.getInt("actQuantity"));
				orders.setDiscountNO(rs.getInt("discountNO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(con, pstmt, rs);
		}
		return orders;

	}

	@Override
	public List<OrdersVO> getAll() {

		List<OrdersVO> list = new ArrayList<>();
		OrdersVO orders = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(Util.URL, Util.USER, Util.PASSWORD);
			System.out.println("Connected...");
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				orders = new OrdersVO();
				orders.setOrderID(rs.getInt("orderID"));
				orders.setUserID(rs.getInt("userID"));
				orders.setOrdTotal(rs.getInt("ordTotal"));
				orders.setDiscount(rs.getInt("discount"));
				orders.setDiscountPrice(rs.getInt("discountPrice"));
				orders.setOrdTime(rs.getTimestamp("ordTime"));
				orders.setPubID(rs.getInt("pubID"));
				orders.setOrdType(rs.getInt("ordType"));
				orders.setActQuantity(rs.getInt("actQuantity"));
				orders.setDiscountNO(rs.getInt("discountNO"));
				list.add(orders);
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
		OrdersDAO_interface dao = new OrdersJDBCDAO();

		// insert//OK
		OrdersVO orders1 = new OrdersVO();
		
//		orders1.setUserID(1);
//		orders1.setOrdTotal(1000);
//		orders1.setDiscount(200);
//		orders1.setDiscountPrice(800);
//		orders1.setOrdTime(now);
//		orders1.setPubID(1);
//		orders1.setOrdType(1);
//		orders1.setActQuantity(1);
//		orders1.setDiscountNO(1);
//		
//		dao.insert(orders1);

		// update OK
//		OrdersVO orders2 = new OrdersVO();
//		orders2.setOrderID(3);
//		orders2.setUserID(1);
//		orders2.setOrdTotal(2000);
//		orders2.setDiscount(300);
//		orders2.setDiscountPrice(1700);
//		orders2.setOrdTime(now);
//		orders2.setPubID(1);
//		orders2.setOrdType(1);
//		orders2.setActQuantity(1);
//		orders2.setDiscountNO(1);
//		
//		dao.update(orders2);

		// delete OK
//		dao.delete(4);

		// find PK OK
//		OrdersVO orders3 = dao.findByPrimaryKey(1);
//		System.out.println(orders3);
//		System.out.println("Success");
		

		// find ALL OK
//		List<OrdersVO> list = dao.getAll();
//		for (OrdersVO lists : list) {
//			System.out.println(lists);
//		}
	}

}