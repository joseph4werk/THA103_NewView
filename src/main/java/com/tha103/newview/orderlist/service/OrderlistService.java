package com.tha103.newview.orderlist.service;

import java.util.List;
import com.tha103.newview.orderlist.model.OrderListVO;


public interface OrderlistService {

	public int insertOrderlist(OrderListVO orderListVO);

	public int updateOrderlist(OrderListVO orderListVO);

	public int deleteOrderlist(Integer orderID);

	public OrderListVO getOneOrderList(Integer orderListID);

	List<OrderListVO> getAll();

}
