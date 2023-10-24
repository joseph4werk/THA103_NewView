package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tha103.newview.user.model.UserVO;
import com.tha103.newview.user.service.UserService;
import com.tha103.newview.user.service.UserServiceImpl;

@WebServlet("/RetrievePassword")
public class RetrievePasswordController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();
		HashMap<String, String> data = new HashMap<>();
		String json = null;
		String hashPassword = null;

		// 取得前端傳遞 email 參數
		String email = req.getParameter("email");

		// 透過 email 查詢此使用者是否存在
		// 不存在回傳 false，程式中斷，回傳 status -> failed 告知前端
		UserService userSvc = new UserServiceImpl();
		if (!userSvc.checkUserAccountByEmail(email)) {
			data.put("status", "failed");
			json = gson.toJson(data);
			out.write(json);
			return;
		}

		// 比對成功，寄驗證信給前端傳來之 email + 驗證信 + 驗證碼 + 新密碼 (亂數8碼)，回傳 status -> success
		String verificationCode = getVerificationCode();
		String newPassword = getVerificationCode();
		sendMail(email, verificationCode, newPassword);

		// 取得 userVO，更新資料
		Integer userID = userSvc.getUserByEmail(email).getUserID();
		UserVO userVO = userSvc.getUserByPK(userID);

		/*************************** 不修改區塊 ***************************/
		// 取得 user 中資料者註冊資訊，取代原先資訊(不更新)
		userVO.setUserID(userVO.getUserID());
		userVO.setUserName(userVO.getUserName());
		userVO.setUserAccount(userVO.getUserAccount());
		userVO.setUserBirth(userVO.getUserBirth());
		userVO.setUserCell(userVO.getUserCell());
		userVO.setUserEmail(userVO.getUserEmail());
		userVO.setUserNickname(userVO.getUserNickname());
		userVO.setBuyAuthority(userVO.getBuyAuthority());
		userVO.setSpeakAuthority(userVO.getSpeakAuthority());
		/*************************** 不修改區塊 ***************************/

		// 加密 newPassword
		// 加密密碼 -> MD5
		try {
			// 創建 MD5 實體
			MessageDigest md = MessageDigest.getInstance("MD5");

			// 轉換原始密碼
			byte[] bytes = md.digest(newPassword.getBytes());

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

		// 更新 hashPawwrod 到資料庫中
		userVO.setUserPassword(hashPassword);

		// 更新使用者資訊
		userSvc.updateUser(userVO);
		System.out.println("密碼重設完成");

		// 比對成功，寄驗證信給前端傳來之 email + 驗證信 + 驗證碼 + 新密碼 (亂數8碼)，回傳 status -> success
		data.put("status", "success");
		json = gson.toJson(data);
		out.write(json);

	}

	public void sendMail(String to, String verificationCode, String newPassword) {

		try {
			// 設定使用SSL連線至 Gmail smtp Server
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			final String myGmail = "tibame.newview@gmail.com";
			final String myGmail_password = "yvkgfcljelqibmwz";
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(myGmail, myGmail_password);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myGmail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// 設定信中的主旨
			message.setSubject("NewView 忘記密碼驗證信");
			// 設定信中的內容
			message.setText(
					"NewView 會員您好，已將密碼重設如下：" + newPassword + "\n\n請輸入以下驗證碼以重新修改密碼，謝謝。\n\n" + "驗證碼：" + verificationCode);

			Transport.send(message);
			System.out.println("傳送成功!");
		} catch (MessagingException e) {
			System.out.println("傳送失敗!");
			e.printStackTrace();
		}
	}

	public String getVerificationCode() {
		String verficationCode = "";
		StringBuilder sb = new StringBuilder();

		// 產生驗證碼
		for (int i = 0; i < 8; i++) {
			switch ((int) (Math.random() * 3)) {
			case 0:
				verficationCode = sb.append(((int) (Math.random() * 10))).toString();
				break;
			case 1:
				verficationCode = sb.append((char) (int) (Math.random() * 26 + 65)).toString();
				break;
			case 2:
				verficationCode += sb.append(((char) (int) (Math.random() * 26 + 97))).toString();
				break;
			}
		}
		// 將驗證碼回傳，回傳後紀錄進 Session Attribute 以供後續驗證
		return verficationCode.substring(0, 8);
	}

}
