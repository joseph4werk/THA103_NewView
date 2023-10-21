package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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

@WebServlet("/UpdateUserInfo")
public class UpdateUserInfoController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		HashMap<String, String> data = new HashMap<>();
		HttpSession session = req.getSession();
		Gson gson = new Gson();

		// 從 session 取出 userID (已套用濾器 -> session 中有存放 userID)
		String userID = (String) session.getAttribute("userID");
		System.out.println(userID);

		// 取得資料庫資料
		UserService userSvc = new UserServiceImpl();
//		UserVO user = userSvc.getUserByPK(Integer.valueOf(userID));

		// 開始修改資料庫資料
//		String name = user.getUserName();
//		String nickname = user.getUserNickname();
//		String email = user.getUserEmail();
//		Date birthdate = user.getUserBirth();
//		String cellphone = user.getUserCell();
		UserVO user = new UserVO();
		user.setUserID(Integer.valueOf(userID));
		user.setUserName(req.getParameter("name"));
		// 帳號不修改
		user.setUserAccount((userSvc.getUserByPK(Integer.valueOf(userID)).getUserAccount()));
		// 密碼不修改
		user.setUserPassword((userSvc.getUserByPK(Integer.valueOf(userID)).getUserPassword()));
		user.setUserBirth(Date.valueOf(req.getParameter("birthdate")));
		user.setUserCell(req.getParameter("cellphone"));
		user.setUserEmail(req.getParameter("email"));
		user.setUserNickname(req.getParameter("nickname"));
		// 權限不修改
		user.setBuyAuthority((userSvc.getUserByPK(Integer.valueOf(userID)).getBuyAuthority()));
		user.setSpeakAuthority((userSvc.getUserByPK(Integer.valueOf(userID)).getSpeakAuthority()));

		// 取得 update 回傳值 (-1為失敗)
		int updateStatus = userSvc.updateUser(user);
		System.out.println("updateStatus: " + updateStatus);

		// 回傳 status 參數 (-1 為更新失敗)
		String status = updateStatus == -1 ? "failed" : "success";
		data.put("status", status);
		String json = gson.toJson(data);
		out.write(json);
	}
}
