package com.tha103.newview.cart.service;

import java.util.List;

import com.tha103.newview.cart.vo.Act;

public interface CartService {
	
	List<Act> findCartList(Integer userID);
	
	boolean removeCart(Integer userID, Integer actId);
	
	Integer disAmountValue(String discountCode, Integer userID);
}
