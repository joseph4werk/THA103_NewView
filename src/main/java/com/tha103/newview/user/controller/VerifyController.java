package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import redis.clients.jedis.Jedis;

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
		Gson gson = new Gson();
		String hashPassword = null;
		HashMap<String, String> data = new HashMap<>();

		UserService userSvc = new UserServiceImpl();
		UserVO userVO = new UserVO();


		// 取得前端傳遞之驗證碼
		String verificationCodeFromWeb = req.getParameter("verificationCode");
		
		// 從 redis 取得驗證碼
		Jedis jedis = new Jedis("localhost");
		jedis.select(15);
		String verificationCodeFromRedis = jedis.get("UserAccount:" + (String) session.getAttribute("newAccount"));
		System.out.println("verificationCodeFromWeb: " + verificationCodeFromRedis);
		
		
		// 取得前端傳來之birthdate
		String birthdate = (String) session.getAttribute("birthdate");

		// 取得 session 中資料者註冊資訊，並進資料庫新增
		userVO.setUserName((String) session.getAttribute("name"));
		userVO.setUserAccount((String) session.getAttribute("newAccount"));

		// 加密 password
		// 取得 session password
		String password = (String) session.getAttribute("password");
		// 加密密碼 -> MD5
		try {
			// 創建 MD5 實體
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 轉換原始密碼
			byte[] bytes = md.digest(password.getBytes());

			// 將 byte[] 轉為 16 進制 String
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				sb.append(String.format("%02x", b));
			}

			// MD5 加密後的 Password
			hashPassword = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 存入 hashPawwrod 進資料庫
		userVO.setUserPassword(hashPassword);
		userVO.setUserBirth(Date.valueOf(birthdate));
		userVO.setUserCell((String) session.getAttribute("cellphone"));
		userVO.setUserEmail((String) session.getAttribute("email"));
		userVO.setUserNickname((String) session.getAttribute("nickname"));
		userVO.setBuyAuthority(1);
		userVO.setSpeakAuthority(1);



		// 比對驗證碼
		if (!verificationCodeFromRedis.equals(verificationCodeFromWeb)) {
			// 錯誤時回傳錯誤狀態 + 前端渲染提示訊息 + 重導向
			String redirectPath = req.getRequestURI() + "/registration-verification.html";
			data.put("status", "failed");
			data.put("location", redirectPath);
			String json = gson.toJson(data);
			out.write(json);
		}else {
			// 新增 user，取得新 user 回傳之用戶編號 PK
			int addUser = userSvc.addUser(userVO);
			if (addUser != 0) {
				System.out.println(userVO);
				System.out.println("userAccount: " + (String) session.getAttribute("newAccount") + " 新增成功");
				// 成功驗證，刪除 redis 中儲存的驗證碼
				jedis.del("UserAccount:" + (String) session.getAttribute("newAccount"));
			} else {
				System.out.println("新增失敗");
			}
			
			// 印出加密前後密碼(供參)
			System.out.println("原始密碼: " + password);
			System.out.println("MD5密碼: " + hashPassword);
			
			// 將成功訊息放進 json 物件中 status 供前端識別
			data.put("status", "succeed");
			String json = gson.toJson(data);
			out.write(json);
		}
	}
}
