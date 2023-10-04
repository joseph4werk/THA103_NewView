package com.tha103.newview.act.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import idv.david.emp.service.EmpServiceImpl;

public class ActServlet extends HttpServlet{
	private ActServlet actServlet;
	
	
	@Override
	public void init() throws ServletException {
		actService = new Act();
	}
}
