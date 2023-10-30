package com.tha103.newview.admin.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminLoginFilter implements Filter {
	
private FilterConfig config;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

	@Override
	public void destroy() {
		config = null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 設定編碼格式
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		// 取得 session
		HttpSession session = req.getSession();
		
		// 從 session 判斷 此管理者 是否登入過
		Object adminAccount = session.getAttribute("adminAccount");
		if(adminAccount == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/Backstage/Allpage-administrator/login/login_admin.jsp");
			System.out.println("重導至 後台管理者登入頁面");
			return; 
		}else {
			chain.doFilter(request, response);
			System.out.println("已登入");
		}
		
		
	}
}