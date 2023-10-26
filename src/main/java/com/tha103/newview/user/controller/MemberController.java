package com.tha103.newview.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tha103.newview.act.service.ActPicService;
import com.tha103.newview.act.service.ActPicServiceImpl;
import com.tha103.newview.actpic.model.ActPic;
import com.tha103.newview.actpic.model.ActPicDAO;
import com.tha103.newview.actpic.model.ActPicDAOHibernateImpl;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.newview.user.dto.MyLikeActDTO;
import com.tha103.newview.user.dto.OrderDTO;
import com.tha103.newview.user.jedis.JedisPoolUtil;
import com.tha103.newview.user.model.UserVO;
import com.tha103.newview.user.service.UserService;
import com.tha103.newview.user.service.UserServiceImpl;

import redis.clients.jedis.Jedis;

@WebServlet("/MemberPage")
public class MemberController extends HttpServlet {

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

		// 取得 session 中的 userID，載入以下資訊
		String userID = (String) req.getSession().getAttribute("userID");
		System.out.println("session中的userID: " + userID);

		// 透過 userID 查詢資料
		UserService userSvc = new UserServiceImpl();
		UserVO userVO = userSvc.getUserByPK(Integer.valueOf(userID));
		OrdersVO ordersVO = userSvc.getOrderByUserID(Integer.valueOf(userID));
		System.out.println(userVO);


		// 回傳 status -> hasNoOrders '預設'沒訂單
		data.put("status", "hasNoOrders");
		if (ordersVO != null) {
			OrderDTO orderDTO = new OrderDTO(Integer.valueOf(userID));
			data.put("orders", orderDTO);
			
//			// 取得活動名稱
//			List<Object[]> listActName = userSvc.getActNameByUserID(Integer.valueOf(userID));
//
//			// 取得廠商名稱
//			List<Object[]> listPubName = userSvc.getPublisherNameByUserID(Integer.valueOf(userID));
//
//			// 取得活動圖片ID
//			List<Object[]> listActPicID = userSvc.getActPicIDByUserID(Integer.valueOf(userID));
//			ActPicDAO actPicDAO = new ActPicDAOHibernateImpl();
//			ActPic actPicVO = actPicDAO.findByPrimaryKey(1);
//			byte[] actPic = actPicVO.getActPic();
//
//			// 將活動、廠商名稱、活動圖片 ID 回傳給前端
//			data.put("activity", listActName.get(0));
//			data.put("publisher", listPubName.get(0));
//			// 傳遞 base64 圖片給前端渲染
//			data.put("ordersPic", Base64.getEncoder().encodeToString(actPic));
//
//			// 回傳 status -> hasOrders，'覆蓋'原先無訂單的 status
			data.put("status", "hasOrders");
		}
		
		// 取得我的最愛資料
		MyLikeActDTO myLikeActDTO = new MyLikeActDTO(Integer.valueOf(userID));
		data.put("mylike", myLikeActDTO);

		// 檢查啟用狀態 -> 從 redis 取得驗證碼
		Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
		jedis.select(15);
		String activate = jedis.get("UserAccount:" + userVO.getUserName()) == null ? "已啟用" : "未啟用";
		jedis.close();

		// 取得會員資料
		String name = userVO.getUserName();
		String nickname = userVO.getUserNickname();
		String email = userVO.getUserEmail();
		String birthdate = userVO.getUserBirth().toString();
		String cellphone = userVO.getUserCell();

		// 加入 map 中，使用 json 傳遞物件
		data.put("userID", userID);
		data.put("name", name);
		data.put("nickname", nickname);
		data.put("email", email);
		data.put("birthdate", birthdate);
		data.put("cellphone", cellphone);
		data.put("activate", activate);

		System.out.println(data);

		// 使用 out.write() 傳遞 json 格式資料
		String json = gson.toJson(data);
		out.write(json);
		out.flush();

		System.out.println("成功回應 ajax");
	}
}
