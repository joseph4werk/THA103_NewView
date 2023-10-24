package com.tha103.newview.cart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tha103.newview.cart.service.CartService;
import com.tha103.newview.cart.service.impl.CartServiceImpl;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd").create();
	private CartService service;

	@Override
	public void init() throws ServletException {
		service = new CartServiceImpl();
	}
//public static void main(String[] args) {
//	CartController CartController = new CartController();
//	CartController.doGet(null, null);
//}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer userID = 0;
		Integer uid = (Integer) req.getSession().getAttribute("userID");
		if (uid != null) {
			userID = uid;
		}else {userID = 1;}
		System.out.println(uid);
		var cartList = service.findCartList(userID);
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().write(gson.toJson(cartList));
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer userID = 0;
		Integer uid = (Integer) req.getSession().getAttribute("userID");
		if (uid != null) {
			userID = uid;
		}else {userID = 1;}
		var actID = Integer.parseInt(req.getParameter("actID"));
		var result = service.removeCart(actID, userID);

		JsonObject respBody = new JsonObject();
		respBody.addProperty("result", result);
		resp.getWriter().write(respBody.toString());
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		var userID = req.getSession().getAttribute("userID");
		Integer userID = 0;
		Integer uid = (Integer) req.getSession().getAttribute("userID");
		if (uid != null) {
			userID = uid;
		}else {userID = 1;}
		var reqBody = gson.fromJson(req.getReader(), JsonObject.class);
		String discountCode = reqBody.get("discountCode").getAsString();
		var value = service.disAmountValue(discountCode, userID);
		var respBody = new JsonObject();
		respBody.addProperty("result", value);
		resp.setContentType("application/json; charset=utf-8");
		resp.getWriter().write(gson.toJson(respBody));
	}

//	protected void doput(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////		var userID = req.getSession().getAttribute("userID");
//		var userID = 2;
//		String inputString = req.getParameter("inputString");
//		var result = service.removeCart(userID, inputString);
//		
//		
//		JsonObject respBody = new JsonObject();
//		respBody.addProperty("result", result);
//		resp.getWriter().write(respBody.toString());
//	}

}
