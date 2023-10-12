package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.newview.user.model.UserVO;
import com.tha103.newview.user.service.UserService;
import com.tha103.newview.user.service.UserServiceImpl;

@WebServlet("/SignIn")
public class SignInController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

//		測試 url 連線是否正確
//		out.println("hi");

		/*************************** 1.接收請求參數 **********************/
		// 取得 json 物件資料
		String signInItem = "";
		String account = req.getParameter("account");
		String password = req.getParameter("password");

		System.out.println(account);
		System.out.println(password);
		out.println(account+"1");
		out.println(password);
		
		/*************************** 2.開始查詢資料 **********************/

		UserService userSvc = new UserServiceImpl();
		UserVO userVO = new UserVO();
		System.out.println(account);

		if (userSvc.checkUserAccount(account, password)) {
			System.out.println("帳號密碼比對成功！");

			out.println("歡迎: " + account + "登入^^");
			System.out.println("歡迎: " + account + "登入^^");
		}
	}
}
