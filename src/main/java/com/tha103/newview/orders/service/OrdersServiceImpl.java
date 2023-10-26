package com.tha103.newview.orders.service;

import java.sql.Timestamp;
import java.util.List;
import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.orders.model.OrdersDAO;
import com.tha103.newview.orders.model.OrdersDAOImpl;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.newview.user.model.UserVO;

public class OrdersServiceImpl {

	private OrdersDAO ordersDao;

	public OrdersServiceImpl() {
		ordersDao = new OrdersDAOImpl();
	}

	public void insertOrders(OrdersVO ordersVO) {

		ordersDao.insert(ordersVO);
	}

	public void updateOrders(OrdersVO ordersVO) {

		ordersDao.update(ordersVO);

	}

	public void deleteOrders(Integer orderID) {
		ordersDao.delete(orderID);
	}

	public OrdersVO getOneOrder(Integer orderID) {
		return ordersDao.findByPrimaryKey(orderID);
	}

	public List<OrdersVO> getAll() {
		return ordersDao.getAll();
	}

}
