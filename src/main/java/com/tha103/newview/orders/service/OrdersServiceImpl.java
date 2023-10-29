package com.tha103.newview.orders.service;

import java.util.List;

import com.tha103.newview.orders.model.OrdersDAO;
import com.tha103.newview.orders.model.OrdersDAOImpl;
import com.tha103.newview.orders.model.OrdersVO;


public class OrdersServiceImpl implements OrdersService {

	private OrdersDAO ordersDao;

	public OrdersServiceImpl() {
		ordersDao = new OrdersDAOImpl();
	}

	public int insertOrders(OrdersVO ordersVO) {

		return ordersDao.insert(ordersVO);
	}

	public int updateOrders(OrdersVO ordersVO) {

		return ordersDao.update(ordersVO);

	}

	public int deleteOrders(Integer orderID) {
		return ordersDao.delete(orderID);
	}

	public OrdersVO getOneOrder(Integer orderID) {
		return ordersDao.findByPrimaryKey(orderID);
	}

	public List<OrdersVO> getAll() {
		return ordersDao.getAll();
	}


	@Override
	public int deleteOrdersbyUserIDandPubID(Integer userID, Integer pubID) {
		// TODO Auto-generated method stub
		return ordersDao.deleteOrdersbyUserIDandPubID(userID,pubID);
	}

	@Override
	public Integer getOrderBy(int userID, int pubID) {
		// TODO Auto-generated method stub
		return ordersDao.getOrderBy(userID,pubID);
	}

	

	

}
