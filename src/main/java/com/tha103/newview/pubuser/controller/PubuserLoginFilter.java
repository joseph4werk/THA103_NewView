package com.tha103.newview.pubuser.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns = 
//{ "/Backstage/Allpage-publisher/activity/*",
//"/Backstage/Allpage-publisher/discount/*",
//"/Backstage/Allpage-publisher/order/*",
//"/Backstage/Allpage-publisher/pages/*",
//"/Backstage/Allpage-publisher/user/*"})
public class PubuserLoginFilter implements Filter{
	
	private FilterConfig config;
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}



	@Override
	//FilterChain 執行順序的物件
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		System.out.println("Filter接收到" + session + "的session");
		
		// 【從 session 判斷此user是否登入過】
		Object pubAccount = session.getAttribute("pubAccount");
		System.out.println("Filter判斷是否需要登入中...");
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
	
	
	@Override
	public void destroy() {
		config = null;
	}
	
	
	
}
