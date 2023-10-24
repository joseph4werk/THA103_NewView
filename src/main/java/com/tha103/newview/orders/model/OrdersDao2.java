package com.tha103.newview.orders.model;

import java.util.List;

public interface OrdersDao2 {
	
	List<Orders> selectByUserID(Integer userID);
}