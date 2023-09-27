package com.tha103.newview.orderlist.model;

import java.util.List;

public interface OrderListDAO_Interface {
	
	public void insert(OrderListVO orderListVO);
	 public void update(OrderListVO orderListVO);
    public void delete(Integer orderListID);
    public OrderListVO findByPrimaryKey(Integer orderListID);
    public List<OrderListVO> getAll();
}
