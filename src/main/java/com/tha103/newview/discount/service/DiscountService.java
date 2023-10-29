package com.tha103.newview.discount.service;

import java.sql.Timestamp;
import java.util.List;

import com.tha103.newview.admin.model.AdminVO;
import com.tha103.newview.discount.model.DiscountDAO;
import com.tha103.newview.discount.model.DiscountDAOImpl;
import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.newview.pubuser.model.PubUserVO;


public class DiscountService {
	
	private DiscountDAO discountdao;	
	
	public DiscountService() {
		discountdao = new DiscountDAOImpl();
	}
		
	
	
	public DiscountVO addDiscount(String discountContent, Integer disAmount, String discountCode, Timestamp disStartDate, Timestamp disFinishDate, Integer pubID, Integer adminID) {
		
		DiscountVO discountVO = new DiscountVO();
		discountVO.setDiscountContent(discountContent);
		discountVO.setDisAmount(disAmount);
		discountVO.setDiscountCode(discountCode);
		discountVO.setDisStartDate(disStartDate);
		discountVO.setDisFinishDate(disFinishDate);
		
		// coz Association Publisher create PublisherVO object to set pubID
		PublisherVO publisherVO = new PublisherVO();
		publisherVO.setPubID(pubID);
		// to PublisherVO to set PublisherVO
		discountVO.setPublisherVO(publisherVO);
		
		// coz Association Admin create AdminVO object to set adminID
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminID(adminID);
		// to AdminVO to set AdminVO
		discountVO.setAdminVO(adminVO);		
		
		discountdao.insert(discountVO);
		return discountVO;
	}

	
	public DiscountVO updateDiscount(String discountContent, Integer disAmount, String discountCode, Timestamp disStartDate, Timestamp disFinishDate, Integer pubID, Integer adminID) {
		
		DiscountVO discountVO = new DiscountVO();
		discountVO.setDiscountContent(discountContent);
		discountVO.setDisAmount(disAmount);
		discountVO.setDiscountCode(discountCode);
		discountVO.setDisStartDate(disStartDate);
		discountVO.setDisFinishDate(disFinishDate);
		
		// coz Association Publisher create PublisherVO object to set pubID
		PublisherVO publisherVO = new PublisherVO();
		publisherVO.setPubID(pubID);
		// to PublisherVO to set PublisherVO
		discountVO.setPublisherVO(publisherVO);
		
		// coz Association Admin create AdminVO object to set adminID
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminID(adminID);
		// to AdminVO to set AdminVO
		discountVO.setAdminVO(adminVO);		
		
		discountdao.update(discountVO);
		return discountVO;
	}
	
	public void deleteDiscount(Integer discountNO) {
		discountdao.delete(discountNO);
	}
	
	public DiscountVO getOneDiscount(Integer discountNO) {
		return discountdao.findByPrimaryKey(discountNO);
	}

	public List<DiscountVO> getAll() {
		return discountdao.getAll();
	}
	

}
