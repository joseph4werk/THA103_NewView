package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
import javax.servlet.http.HttpSession;

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
		HttpSession session = req.getSession();

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
		String hashPassword = null;

		if (!userSvc.checkUserAccount(account)) {
			// call sendMail 方法，順便將產生的驗證碼記錄進 sessionAttribute
			String verificationCode = getVerificationCode();
			sendMail(email, verificationCode);
			session.setAttribute("verificationCode", verificationCode);

//			System.out.println("usercontroller's verificationCode: " + verificationCode);

			// 將接到的參數存入 session 以提供下之程式(驗證碼)使用
			session.setAttribute("name", name);
			session.setAttribute("newAccount", account);
			session.setAttribute("password", password);
			session.setAttribute("birthdate", birthdate);
			session.setAttribute("cellphone", cellphone);
			session.setAttribute("email", email);
			session.setAttribute("nickname", nickname);

		} else {
			System.out.println("使用者已存在");
			out.println("使用者已存在");
		}

//		if (!userSvc.checkUserAccount(account)) {
//			System.out.println("資料庫查無userAccount: " + account + "的使用者");
//			
//			// 比對 Session 中的驗證碼是否相同
//			
//
//			// 包裝資料為 UserVO，呼叫 addUser 方法
//			userVO.setUserName(name);
//			userVO.setUserAccount(account);
//			
//			// 新增註冊用戶 account 至 Session 中
//			session.setAttribute("newAccount", account);
//
//			// 加密密碼 -> MD5
//			try {
//				// 創建 MD5 實體
//				MessageDigest md = MessageDigest.getInstance("MD5");
//
//				// 轉換原始密碼
//				byte[] bytes = md.digest(password.getBytes());
//
//				// 將 byte[] 轉為 16 進制 String
//				StringBuilder sb = new StringBuilder();
//				for (byte b : bytes) {
//					sb.append(String.format("%02x", b));
//				}
//
//				// MD5 加密後的 Password
//				hashPassword = sb.toString();
//
//			} catch (NoSuchAlgorithmException e) {
//				e.printStackTrace();
//			}
//
//			// 存入 hashPassword 進資料庫
//			userVO.setUserPassword(hashPassword);
//			userVO.setUserBirth(Date.valueOf(birthdate));
//			userVO.setUserCell(cellphone);
//			userVO.setUserEmail(email);
//			userVO.setUserNickname(nickname);
//			userVO.setBuyAuthority(0);
//			userVO.setSpeakAuthority(0);
//			System.out.println(userVO);
//
//			int addUser = userSvc.addUser(userVO);
//			if (addUser != 0) {
//				System.out.println(userVO);
//				System.out.println("userAccount: " + account + " 新增成功");
//				out.println("userName: " + name + ",\n" + "userAccount: " + account + ",\n" + "新增成功");
//				out.print(addUser);
//			} else {
//				System.out.println("新增失敗");
//				out.println("新增失敗");
//			}
//		} else {
//			System.out.println("使用者已存在");
//			out.println("使用者已存在");
//		}
//
//		System.out.println("原始密碼: " + password);
//		System.out.println("MD5密碼: " + hashPassword);

	}

	public void sendMail(String to, String verificationCode) {

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
			message.setSubject("NewView 註冊驗證碼");
			// 設定信中的內容
			message.setText("您好，歡迎您註冊成為 NewView 會員，請輸入以下驗證碼以完成會員註冊，謝謝。\n\n" + "驗證碼：\n" + verificationCode);

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
		return verficationCode;
	}
}