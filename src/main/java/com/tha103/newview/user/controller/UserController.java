package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.newview.user.model.UserVO;
import com.tha103.newview.user.service.UserService;
import com.tha103.newview.user.service.UserServiceImpl;

@WebServlet("/SignUp")
public class UserController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		/*************************** 1.接收請求參數 **********************/
		String signUpItem = "";
		String name = req.getParameter("name");
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String birthdate = req.getParameter("birthdate");
		String cellphone = req.getParameter("cellphone");
		String email = req.getParameter("email");
		String nickname = req.getParameter("nickname");

		/*************************** 2.開始查詢資料 **********************/

		UserService userSvc = new UserServiceImpl();
		UserVO userVO = new UserVO();
		System.out.println(account);

		if (userSvc.checkUserAccount(account)) {
			System.out.println("資料庫查無userAccount: " + account + "的使用者");

			// 包裝資料為 UserVO，呼叫 addUser 方法
			userVO.setUserName(name);
			userVO.setUserAccount(account);
			userVO.setUserPassword(password);
			userVO.setUserBirth(Date.valueOf(birthdate));
			userVO.setUserCell(cellphone);
			userVO.setUserEmail(email);
			userVO.setUserNickname(nickname);
			userVO.setBuyAuthority(0);
			userVO.setSpeakAuthority(0);
			System.out.println(userVO);

			int addUser = userSvc.addUser(userVO);
			if (addUser == 1) {
				System.out.println(userVO);
				System.out.println("userAccount: " + account + " 新增成功");
				out.println("userName: " + name + ",\n" + "userAccount: " + account + ",\n" + "新增成功");
			} else {
				System.out.println("新增失敗");
				out.println("新增失敗");
			}
		} else {
			System.out.println("使用者已存在");
			out.println("使用者已存在");
		}

		/***************************************************************/

//		if (name.equals("a")) {
//			out.println("name: " + name);
//			out.println("account: " + account);
//			out.println("password: " + password);
//			out.println("birthdate: " + birthdate);
//			out.println("cellphone: " + cellphone);
//			out.println("email: " + email);
//			out.println("nickname: " + nickname);
//
//			out.println("connected!");
//			System.out.println("ajax POST method success, Got data!" + "\n" + "User:" + name);
//			System.out.println("email: " + email);
//
//		} else {
//			System.out.println(name);
//			System.out.println("param value verify failed, but ajax POST success!");
//		}
	}
}