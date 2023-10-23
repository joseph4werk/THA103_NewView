package com.tha103.newview.pubuser.controller;

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

public class PubuserLoginFilter implements Filter{
	
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
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		
		Object pubAccount = session.getAttribute("pubAccount");
		if(pubAccount == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/Backstage/Allpage-publisher/login/login.jsp");
			System.out.println("需登入帳密，登入後台");
			return;
		}else {
			chain.doFilter(request, response);
			System.out.println("已登入");
		}
		
		
	}
	
	
	
}
