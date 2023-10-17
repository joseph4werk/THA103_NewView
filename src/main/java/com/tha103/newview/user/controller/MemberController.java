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
import com.tha103.newview.user.model.UserVO;
import com.tha103.newview.user.service.UserService;
import com.tha103.newview.user.service.UserServiceImpl;

@WebServlet("/MemberPage")
public class MemberController extends HttpServlet{

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
		
//		String userID = req.getParameter("userID");
		System.out.println("接到 ajax 請求");
		
		String userID = (String) req.getSession().getAttribute("userID");
		System.out.println("session中的userID: " + userID);
		
		UserService userSvc = new UserServiceImpl();
		UserVO userVO = userSvc.getUserByPK(Integer.valueOf(userID));
//		System.out.println(userVO);
		
		String name = userVO.getUserName();
		String nickname = userVO.getUserNickname();
		String email = userVO.getUserEmail();
		String birthdate = userVO.getUserBirth().toString();
		String cellphone = userVO.getUserCell();

		data.put("userID", userID);
		data.put("name", name);
		data.put("nickname", nickname);
		data.put("email", email);
		data.put("birthdate", birthdate);
		data.put("cellphone", cellphone);
		
//		System.out.println(data);
		
		String json = gson.toJson(data);
		out.write(json);
		out.flush();
		

		System.out.println("成功回應 ajax");
		
		// 接收 ajax Json 傳入物件參數
//		String name = req.getParameter("name");
//		String nickname = req.getParameter("nickname");
//		
//		System.out.println(name);
//		System.out.println(nickname);
//		
//		
//		
//		out.write(json);
//		
//		out.write("接收ajax\n");
//		out.write("name: " + name + "\n");
//		out.write("nickname: " + nickname + "\n");
//		
	}
	
}
