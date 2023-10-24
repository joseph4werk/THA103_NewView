package com.tha103.newview.orders.service;

import java.util.List;

import com.tha103.newview.orders.model.Orders;

public interface OrdersService2 {
	
	List<Orders> findByUserID(Integer userID);
}
