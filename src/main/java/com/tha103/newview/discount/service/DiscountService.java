package com.tha103.newview.discount.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.tha103.newview.discount.model.DiscountVO;

public interface DiscountService {

	public DiscountVO addDiscount(String discountContent, Integer disAmount, String discountCode,
			Date disStartDate, Date disFinishDate, Integer pubID, Integer adminID);
	
	public DiscountVO updateDiscount(Integer discountNO,String discountContent, Integer disAmount, String discountCode,
			Date disStartDate, Date disFinishDate, Integer pubID, Integer adminID);
	
	public void deleteDiscount(Integer discountNO);
	
	public DiscountVO getOneDiscount(Integer discountNO);
	
	public List<DiscountVO> getAll();
		
	public List<DiscountVO> getDiscountByPub(Integer pubID);

}
