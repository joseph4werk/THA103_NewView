package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.google.gson.Gson;
import com.tha103.newview.user.jedis.JedisPoolUtil;
import com.tha103.newview.user.service.UserService;
import com.tha103.newview.user.service.UserServiceImpl;

import redis.clients.jedis.Jedis;

@WebServlet("/SignUp")
public class SignUpController extends HttpServlet {

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
		String json = null;

		/*************************** 1.接收請求參數 **********************/
		String name = req.getParameter("name");
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String birthdate = req.getParameter("birthdate");
		String cellphone = req.getParameter("cellphone");
		String email = req.getParameter("email");
		String nickname = req.getParameter("nickname");

		/*************************** 2.開始查詢資料 **********************/

		UserService userSvc = new UserServiceImpl();
		System.out.println(account);

		if (!userSvc.checkUserAccount(account)) {
			// call sendMail 方法，產生驗證碼
			String verificationCode = getVerificationCode();
			// 開新的 Thread 寄 mail (不然很慢==)
			new Thread(() -> sendMail(email, verificationCode)).start();

			// 使用 Jedis 將 userAccount 當 key 存入驗證碼的資訊
			Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
			jedis.select(15);
			jedis.set("UserAccount:" + account, verificationCode);
			jedis.expire("UserAccount:" + account, 600);
			jedis.close();

			System.out.println("將資料存進 redis 15 DB");
			System.out.println("usercontroller's verificationCode: " + verificationCode);

			// 將接到的參數存入 session 以提供下之程式(驗證碼)使用
			session.setAttribute("name", name);
			session.setAttribute("newAccount", account);
			session.setAttribute("password", password);
			session.setAttribute("birthdate", birthdate);
			session.setAttribute("cellphone", cellphone);
			session.setAttribute("email", email);
			session.setAttribute("nickname", nickname);
			
			json = gson.toJson("success");
			out.write(json);
			return;
			
		} else {
			System.out.println("使用者已存在");
			out.println("使用者已存在");
			json = gson.toJson("failed");
			out.write(json);
			return;
		}
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
			message.setText("您好，歡迎您註冊成為 NewView 會員，請輸入以下驗證碼以完成會員註冊，謝謝。\n\n" + "驗證碼：\n[" + verificationCode + "]");

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