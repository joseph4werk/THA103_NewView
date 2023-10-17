package com.tha103.newview.orderlist.model;

import java.util.List;


public interface OrderListDAO {
	public int insert(OrderListVO orderListVO);
	public int update(OrderListVO orderListVO);
	public int delete(Integer orderListID);
	public OrderListVO findeByPrimaryKey(Integer orderListID);
	public List<OrderListVO> getAll();
//	 萬用複合查詢(傳入參數型態Map)(回傳List)
//	public List<OrderListVO> getAll(Map<String, String[]> map);

}
