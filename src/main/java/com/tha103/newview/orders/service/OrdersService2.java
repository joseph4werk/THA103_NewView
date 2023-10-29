package com.tha103.newview.orders.service;

import java.util.List;

import com.tha103.newview.orders.model.OrdersVO;

public interface OrdersService2 {
	
	List<OrdersVO> findByUserID(Integer userID);
}
