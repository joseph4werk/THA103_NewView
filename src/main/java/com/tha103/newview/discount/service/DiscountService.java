package com.tha103.newview.discount.service;

import java.util.List;

import com.tha103.newview.discount.model.DiscountDAO;
import com.tha103.newview.discount.model.DiscountDAOImpl;
import com.tha103.newview.discount.model.DiscountVO;


public class DiscountService {

	private DiscountDAO discountDAO;
	
	public DiscountService() {
		discountDAO = new DiscountDAOImpl();
	}



}
