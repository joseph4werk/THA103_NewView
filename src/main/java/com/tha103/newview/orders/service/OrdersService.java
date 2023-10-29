package com.tha103.newview.orders.service;

import java.util.List;

import com.tha103.newview.orders.model.OrdersVO;

public interface OrdersService {

	public int insertOrders(OrdersVO ordersVO);

	public int updateOrders(OrdersVO ordersVO);

	public int deleteOrders(Integer orderID);


	public OrdersVO getOneOrder(Integer orderID);

	List<OrdersVO> getAll();


	int deleteOrdersbyUserIDandPubID(Integer userID, Integer pubID);

	public Integer getOrderBy(int userID, int pubID);
	

}
