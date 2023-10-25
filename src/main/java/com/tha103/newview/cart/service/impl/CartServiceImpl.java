package com.tha103.newview.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tha103.newview.cart.dao.CartDao;
import com.tha103.newview.cart.dao.impl.CartDaoImpl;
import com.tha103.newview.cart.service.CartService;
import com.tha103.newview.cart.vo.Act;
import com.tha103.newview.usediscount.model.UseDiscountVO;

public class CartServiceImpl implements CartService {
	private CartDao dao;

	public CartServiceImpl() {
		dao = new CartDaoImpl();
	}

	@Override
	public List<Act> findCartList(Integer userID) {
		var list = new ArrayList<Act>();
		for (int actID = 1; actID <= 5; actID++) {
			var seatsStr = dao.getSeat(actID,userID);
			System.out.println(seatsStr+"AAA");
			if (seatsStr != null) {
				var act = dao.selectByActId(actID);
				act.setSeatsStr(seatsStr);
				list.add(act);
//				var a = list.getActName();
			}
		}
		return list;
	}

	@Override
	public boolean removeCart(Integer actID, Integer userID) {
		String s = dao.getSeat(actID, userID);
		String[] numbers = s.split(",");
		for (String number : numbers) {
		    System.out.println(number);
		    Integer seatNumber = Integer.parseInt(number);
		    dao.deleteByActIDAndUserID(actID, seatNumber);
		    
		}
		return dao.deleteByActIDAndUserID(actID, userID) > 0;
	}

	public static void main(String[] args) {
		CartServiceImpl dao = new CartServiceImpl();
//		System.out.println(dao.disAmountValue("humor50", 2));
		System.out.println(dao.findCartList(2)+"654");
	}

	public Integer disAmountValue(String discountCode, Integer userID) {
		return dao.selectByDiscountCodeAndUserID(discountCode, userID);
	}
}
