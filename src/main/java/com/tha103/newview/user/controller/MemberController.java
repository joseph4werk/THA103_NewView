package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.newview.user.model.UserVO;
import com.tha103.newview.user.service.UserService;
import com.tha103.newview.user.service.UserServiceImpl;

@WebServlet("/MemberPage")
public class MemberController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		HashMap<String, Object> data = new HashMap<>();
		PrintWriter out = res.getWriter();
		
//		System.out.println("接到 ajax 請求");

		// 取得 session 中的 userID，載入以下資訊
		String userID = (String) req.getSession().getAttribute("userID");
		System.out.println("session中的userID: " + userID);

		// 透過 userID 查詢資料
		UserService userSvc = new UserServiceImpl();
		UserVO userVO = userSvc.getUserByPK(Integer.valueOf(userID));
		OrdersVO ordersVO = userSvc.getOrderByUserID(Integer.valueOf(userID));
//		System.out.println(userVO);

		// 取得會員資料
		String name = userVO.getUserName();
		String nickname = userVO.getUserNickname();
		String email = userVO.getUserEmail();
		String birthdate = userVO.getUserBirth().toString();
		String cellphone = userVO.getUserCell();

		// 取得訂單資料
		String pubName = ordersVO.getPublisherVO().getPubName();
		System.out.println("pubName: " + pubName);

		// 加入 map 中，使用 json 傳遞物件
		data.put("userID", userID);
		data.put("name", name);
		data.put("nickname", nickname);
		data.put("email", email);
		data.put("birthdate", birthdate);
		data.put("cellphone", cellphone);

//		System.out.println(data);

		// 使用 out.write() 傳遞 json 格式資料
		String json = gson.toJson(data);
		out.write(json);
		out.flush();

		System.out.println("成功回應 ajax");
	}
}
