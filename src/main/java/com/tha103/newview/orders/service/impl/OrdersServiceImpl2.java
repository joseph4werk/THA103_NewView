package com.tha103.newview.orders.service.impl;

import java.util.List;

import com.tha103.newview.orders.model.OrdersDao2;
import com.tha103.newview.orders.model.OrdersDaoImpl2;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.newview.orders.service.OrdersService2;

public class OrdersServiceImpl2 implements OrdersService2 {
	private OrdersDao2 dao;
	
	public OrdersServiceImpl2() {
		dao = new OrdersDaoImpl2();
	}

	@Override
	public List<OrdersVO> findByUserID(Integer userID) {
		return dao.selectByUserID(userID);
	}
}
