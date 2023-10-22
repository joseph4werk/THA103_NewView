package com.tha103.newview.admin.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("adminLogin".equals(action)) { // 來自login_admin.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*********1.接收請求參數 - 輸入格式的錯誤處理*********/
			String strAccount = req.getParameter("adminAccount");
			String strPassword = req.getParameter("adminPassword");
			
			if (strAccount == null || (strAccount.trim()).length() == 0) {
				errorMsgs.add("請輸入帳號");
			}
			if (strPassword == null || (strPassword.trim()).length() == 0) {
				errorMsgs.add("請輸入密碼");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/Backstage/Allpage-administrator/login/login_admin.html");
				failureView.forward(req, res);
				return; //程式中斷
			}
		}
	}

	
}