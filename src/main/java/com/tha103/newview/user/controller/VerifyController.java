package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tha103.newview.user.model.UserVO;
import com.tha103.newview.user.service.UserService;
import com.tha103.newview.user.service.UserServiceImpl;

@WebServlet("/Verify")
public class VerifyController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();

		UserService userSvc = new UserServiceImpl();
		UserVO userVO = new UserVO();

		// 從 session 取得驗證碼
		String verificationCode = (String) session.getAttribute("verificationCode");

		
		// 取得前端傳遞之驗證碼
		String verificationCodeFromWeb = req.getParameter("verificationCode");
//		System.out.println("verifyController's verificationCode: " + verificationCode);
//		System.out.println("verificationCodeFromWeb: " + verificationCodeFromWeb);
		
		// 取得前端傳來之birthdate
		String birthdate = (String) session.getAttribute("birthdate");

		// 取得 session 中資料者註冊資訊
		userVO.setUserName((String) session.getAttribute("name"));
		userVO.setUserAccount((String) session.getAttribute("newAccount"));
		userVO.setUserPassword((String) session.getAttribute("password"));
		userVO.setUserBirth(Date.valueOf(birthdate));
		userVO.setUserCell((String) session.getAttribute("cellphone"));
		userVO.setUserEmail((String) session.getAttribute("email"));
		userVO.setUserNickname((String) session.getAttribute("nickname"));
		userVO.setBuyAuthority(1);
		userVO.setSpeakAuthority(1);
		

		int addUser = userSvc.addUser(userVO);
//		if (addUser != 0) {
//			System.out.println(userVO);
//			System.out.println("userAccount: " + session.getAttribute("name") + " 新增成功");
//			out.println("userName: " + session.getAttribute("name") + ",\n" + "userAccount: "
//					+ session.getAttribute("newAccount") + ",\n" + "新增成功");
//			out.print(addUser);
//		} else {
//			System.out.println("新增失敗");
//			out.println("新增失敗");
//		}

//		if (verificationCodeFromWeb.equals(verificationCode)) {
//
//		}
	}

}
