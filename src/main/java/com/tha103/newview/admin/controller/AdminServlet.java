package com.tha103.newview.admin.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tha103.newview.admin.service.AdminService;

@WebServlet("/admin/admin.do")
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
			
			/************************* 接收請求參數 **************************/
			String adminAccount = req.getParameter("adminAccount").trim();
			String adminPassword = req.getParameter("adminPassword").trim();

			System.out.println(adminAccount);
			System.out.println(adminPassword);
			
			/************************* 開始查詢資料 **************************/
			AdminService adminSvc = new AdminService();
			System.out.println(adminSvc);

			boolean loginSuccessful = adminSvc.authenticate(adminAccount, adminPassword);
			System.err.println(loginSuccessful);
			
			/************************* 回傳資料路徑 **************************/
			if (!loginSuccessful) {
				System.out.println("登入失敗");
				res.sendRedirect(req.getContextPath() + "/Backstage/Allpage-administrator/login/login_admin.jsp");
				return;
			} else {
				System.out.println("登入成功");
				HttpSession session = req.getSession();
				String se = session.toString();
				System.out.println(se);
				session.setAttribute("adminAccount", adminAccount);

				try {
					String location = (String) session.getAttribute("location");
					if (location != null) {
						session.removeAttribute("location"); // *看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
						res.sendRedirect(location);
						return;
					}
				} catch (Exception ignored) {
				}
				res.sendRedirect(req.getContextPath() + "/Backstage/Allpage-administrator/admin-index.jsp");
				// *(-->如無來源網頁:則重導至後台首頁)
			}
			
		}
	}

	
}