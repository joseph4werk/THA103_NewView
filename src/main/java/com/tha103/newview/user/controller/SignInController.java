package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
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
		Gson gson = new Gson();
		HashMap<String, String> data = new HashMap<>();

//		測試 url 連線是否正確
//		out.println("hi");

		/*************************** 1.接收請求參數 **********************/
		// 取得 json 物件資料
		String signInItem = "";
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		
		// 加密接收到的密碼 -> 使用 MD5 加密
		try {
			// 創建 MD5 實體
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			// 轉換原始密碼
			byte[] bytes = md.digest(password.getBytes());
			
			// 將 byte[] 轉為 16 進制 String
			StringBuilder sb = new StringBuilder();
			for(byte b: bytes) {
				sb.append(String.format("%02x", b));
			}
			
			// MD5 加密後的 Password
			password = sb.toString();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		System.out.println("account: " + account);
		System.out.println("password: " + password);
		out.println(account + "1");
		out.println(password);

		/*************************** 2.開始查詢資料 **********************/
		UserService userSvc = new UserServiceImpl();
		UserVO userVO = new UserVO();

		if (!userSvc.checkUserAccount(account, password)) {
			out.println("帳號/密碼無效！");
			out.println("請重新輸入帳號密碼。");
		} else {
			System.out.println("帳號密碼比對成功！");

			out.println("歡迎: " + account + "登入！");
			System.out.println("歡迎: " + account + "登入！");

			HttpSession session = req.getSession();
			session.setAttribute("account", account);

			data.put("account", account);

			/*************************** 3.查看有無來源網頁 ******************/
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					data.put("location", location);
					res.sendRedirect(location);
					session.removeAttribute("location");
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 如果無來源網頁，導向首頁
			res.sendRedirect(req.getContextPath() + "/home-03.html");
			
			
			
//		if(userSvc.getUserByAccount(account)==null) {
//			out.println("帳號不存在，請重新輸入！");
//			return;
//		}
//		
//		if (userSvc.checkUserAccount(account)
//				&& (userSvc.getUserByAccount(account).getUserPassword()).equals(password)) {
//			out.println("歡迎: " + account + "登入！");
//			System.out.println("歡迎: " + account + "登入！");
//
//			HttpSession session = req.getSession();
//			session.setAttribute("account", account);
//
//			try {
//				String location = (String) session.getAttribute("location");
//				if (location != null) {
//					data.put("location", location);
//					res.sendRedirect(location);
//					session.removeAttribute("location");
//					return;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			// 如果無來源網頁，導向首頁
//			res.sendRedirect(req.getContextPath() + "/home-03.html");
//
//		} else if (!userSvc.checkUserAccount(account)) {
//			out.println("帳號不存在，請重新輸入！");
//		} else if (!(userSvc.getUserByAccount(account).getUserPassword()).equals(password)) {
//			out.println("密碼錯誤，請重新輸入！");
//		}
		}
	}
}
