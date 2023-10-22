package com.tha103.newview.cart.dao;


import com.tha103.newview.cart.vo.Act;

public interface CartDao {
	
	long deleteByActIDAndUserID(Integer actID, Integer userID);
	
	String getSeat(Integer actID, Integer userID);

	Act selectByActId(Integer actId);
	
	Integer selectByDiscountCodeAndUserID(String discountCode, Integer userID);
//	Integer findDisAmountByDiscountCodeAndUserID(String discountCode, Integer userID);
}
