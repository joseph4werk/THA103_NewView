package com.tha103.newview.orders.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tha103.newview.orders.service.OrdersService2;
import com.tha103.newview.orders.service.impl.OrdersServiceImpl2;
import com.tha103.util.HibernateUtil;

@WebServlet("/orders")
public class OrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new GsonBuilder()
			.setDateFormat("yyyy/MM/dd")
			.excludeFieldsWithoutExposeAnnotation()
			.create();
	private OrdersService2 service;

	@Override
	public void init() throws ServletException {
		service = new OrdersServiceImpl2();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		var userID = req.getSession().getAttribute("userID");
		var userID = 2;
		var list = service.findByUserID(userID);
		
//		if (list != null && !list.isEmpty()) {
//			list.forEach(vo -> {
//				vo.getOrderListVOs().forEach(ol -> {
//					ol.setOrdersVO(null);
//				});
//			});
//		}
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().write(gson.toJson(list));
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	}
}
