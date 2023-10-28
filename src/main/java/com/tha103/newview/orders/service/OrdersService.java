package com.tha103.newview.orders.service;

import java.sql.Timestamp;
import java.util.List;
import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.orders.model.OrdersDAO;
import com.tha103.newview.orders.model.OrdersDAOImpl;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.newview.user.model.UserVO;

public class OrdersService {

	private OrdersDAO ordersDao;

	public OrdersService() {
		ordersDao = new OrdersDAOImpl();
	}
	
	public OrdersVO insertOrders(Integer userID, Integer ordTotal, Integer discount, Integer discountPrice, Timestamp ordTime, 
			Integer pubID, Integer ordType, Integer actQuantity, Integer discountNO) {
		
		OrdersVO ordersVO = new OrdersVO();
		
		UserVO userVO = new UserVO();
		userVO.setUserID(userID);
		ordersVO.setUserVO(userVO);	
		
		ordersVO.setOrdTotal(ordTotal);
		ordersVO.setDiscount(discount);
		ordersVO.setDiscountPrice(discountPrice);
		ordersVO.setOrdTime(ordTime);
		
		PublisherVO publisherVO = new PublisherVO();
		publisherVO.setPubID(pubID);
		ordersVO.setPublisherVO(publisherVO);
		
		ordersVO.setOrdType(ordType);
		ordersVO.setActQuantity(actQuantity);
		
		DiscountVO discountVO = new DiscountVO();
		discountVO.setDiscountNO(discountNO);
		ordersVO.setDiscountVO(discountVO);
			
		return ordersVO;
	}
	
	public OrdersVO updateOrders(Integer userID, Integer ordTotal, Integer discount, Integer discountPrice, Timestamp ordTime, 
			Integer pubID, Integer ordType, Integer actQuantity, Integer discountNO) {
		
		OrdersVO ordersVO = new OrdersVO();
		
		UserVO userVO = new UserVO();
		userVO.setUserID(userID);
		ordersVO.setUserVO(userVO);	
		
		ordersVO.setOrdTotal(ordTotal);
		ordersVO.setDiscount(discount);
		ordersVO.setDiscountPrice(discountPrice);
		ordersVO.setOrdTime(ordTime);
		
		PublisherVO publisherVO = new PublisherVO();
		publisherVO.setPubID(pubID);
		ordersVO.setPublisherVO(publisherVO);
		
		ordersVO.setOrdType(ordType);
		ordersVO.setActQuantity(actQuantity);
		
		DiscountVO discountVO = new DiscountVO();
		discountVO.setDiscountNO(discountNO);
		ordersVO.setDiscountVO(discountVO);
			
		return ordersVO;
		
	}
	
	public void deleteOrders(Integer orderID) {
		ordersDao.delete(orderID);
	}
	
	public OrdersVO getOneOrder(Integer orderID) {
		return ordersDao.findByPrimaryKey(orderID);
	}
	
	public List<OrdersVO> getAll() {
		return ordersDao.getAll();
	}
	
	
	
	//for Publisher Backstage get Order List
	public List<OrdersVO> getOrdersByPub(Integer pubID){
		//使用廠商ID取得Discount列表
		List<OrdersVO> discountList = ordersDao.getOrdersByPubID(pubID);
		return discountList;
	}

}
