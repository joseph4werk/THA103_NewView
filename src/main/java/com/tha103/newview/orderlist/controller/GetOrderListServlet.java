package com.tha103.newview.orderlist.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.newview.orderlist.model.OrderListVO;
import com.tha103.newview.orderlist.service.OrderListService;
import com.tha103.newview.orderlist.service.OrderListServiceImpl;
import com.tha103.newview.user.model.UserVO;



@WebServlet("/GetOrderListServlet")
public class GetOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
		
		
		OrderListService ordSvc = new OrderListServiceImpl();
		OrderListVO orderVO = new OrderListVO();
		UserVO userVO = new UserVO();
		
		
		Map<String, Object> OrderInfo = new HashMap<>();
		
		//基本資訊確認
		OrderInfo.put("userID", userVO.getUserID());
		OrderInfo.put("userName", userVO.getUserName());
		OrderInfo.put("userBirth", userVO.getUserBirth());
		OrderInfo.put("userCell", userVO.getUserCell());
		OrderInfo.put("userEmail", userVO.getUserEmail());
		
		//訂單明細確認
		OrderInfo.put("userID", userVO.getUserID());
		OrderInfo.put("userID", userVO.getUserID());
		OrderInfo.put("userID", userVO.getUserID());
		
		
	}
	
}