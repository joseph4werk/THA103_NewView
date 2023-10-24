package com.tha103.newview.orderlist.service;

import java.util.List;

import com.tha103.newview.act.model.ActDAO;
import com.tha103.newview.orderlist.model.OrderListDAO;
import com.tha103.newview.orderlist.model.OrderListVO;

public class OrderListServiceImpl implements OrderListService{
	  private OrderListDAO orderListDAO;

	    public OrderListServiceImpl(OrderListDAO orderListDAO) {
	        this.orderListDAO = orderListDAO;
	    }
	@Override
	public int insert(OrderListVO orderListVO) {
		// TODO Auto-generated method stub
		return orderListDAO.insert(orderListVO);
	}

	@Override
	public int update(OrderListVO orderListVO) {
		// TODO Auto-generated method stub
		return  orderListDAO.update(orderListVO);
	}

	@Override
	public int delete(Integer orderListID) {
		// TODO Auto-generated method stub
		return orderListDAO.delete(orderListID);
	}

	@Override
	public OrderListVO findeByPrimaryKey(Integer orderListID) {
		// TODO Auto-generated method stub
		return orderListDAO.findeByPrimaryKey(orderListID);
	}

	@Override
	public List<OrderListVO> getAll() {
		// TODO Auto-generated method stub
		return orderListDAO.getAll();
	}

}
