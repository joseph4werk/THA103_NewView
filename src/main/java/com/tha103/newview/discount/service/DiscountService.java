package com.tha103.newview.discount.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import com.tha103.newview.admin.model.AdminVO;
import com.tha103.newview.discount.model.DiscountDAO;
import com.tha103.newview.discount.model.DiscountDAOImpl;
import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.newview.usediscount.model.UseDiscountVO;


public class DiscountService {

	private DiscountDAO discountDAO;
	
	
	
	public DiscountService() {
		discountDAO = new DiscountDAOImpl();
	}
	
	
	
	public DiscountVO addDiscount(String discountContent, Integer disAmount, String discountCode,
			Timestamp disStartDate, Timestamp disFinishDate,Integer pubID, Integer adminID) {
		
		DiscountVO discount = new DiscountVO();
		discount.setDiscountContent(discountContent);
		discount.setDisAmount(disAmount);
		discount.setDiscountCode(discountContent);
		discount.setDisStartDate(disStartDate);
		discount.setDisFinishDate(disFinishDate);

		PublisherVO pub = new PublisherVO();
		pub.setPubID(pubID);
		discount.setPublisherVO(pub);
		
		AdminVO admin = new AdminVO();
		admin.setAdminID(adminID);
		discount.setAdminVO(admin);
		
		discountDAO.insert(discount);
		return discount;
		
	}
	
	
	
	
	public DiscountVO updateDiscount(Integer discountNO, String discountContent, Integer disAmount, String discountCode,
			Timestamp disStartDate, Timestamp disFinishDate, Integer pubID, Integer adminID) {
		
		DiscountVO discount = new DiscountVO();
		discount.setDiscountNO(discountNO);
		discount.setDiscountContent(discountContent);
		discount.setDisAmount(disAmount);
		discount.setDiscountCode(discountContent);
		discount.setDisStartDate(disStartDate);
		discount.setDisFinishDate(disFinishDate);

		PublisherVO pub = new PublisherVO();
		pub.setPubID(pubID);
		discount.setPublisherVO(pub);
		
		AdminVO admin = new AdminVO();
		admin.setAdminID(adminID);
		discount.setAdminVO(admin);
		
		discountDAO.update(discount);
		return discount;
		
	}
	
	
	
	
	public  void deleteDiscount(Integer discountNO) {
		discountDAO.delete(discountNO);
	}
	
	
	
	
	public DiscountVO getDiscountByPK(Integer discountNO) {
		return discountDAO.findByPrimaryKey(discountNO);
	}
	
	
	
	//for Publisher Backstage get discount List
	public List<DiscountVO> getDiscountByPub(Integer pubID){
		List<DiscountVO> discountList = discountDAO.getDiscountByPubID(pubID);
		return discountList;
		
	}
}
