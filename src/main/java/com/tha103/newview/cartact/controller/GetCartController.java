package com.tha103.newview.cartact.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tha103.newview.act.service.ActService;
import com.tha103.newview.act.service.ActServiceImpl;
import com.tha103.newview.actpic.model.ActPic;
import com.tha103.newview.user.jedis.JedisPoolUtil;

import redis.clients.jedis.Jedis;

@WebServlet("/getcart")
public class GetCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
	}
	// 1. 獲取用戶訊息(這裡有問題，直接購買會有user session，但如果先加購物車後登入?但我覺得只要選座位那邊就開始下filter就沒這問題)
//		HttpSession session = req.getSession();
//		String userIDString = (String) req.getSession().getAttribute("userID");
//		Integer userID = Integer.parseInt(userIDString);

	public static void main(String[] args) {
		// 2. 連接 Redis
		Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
		jedis.select(0); // 選擇 Redis db(0)

		// 3.藉由獲取RedisHash，裁切資料後得到actID、userID、座位狀態，以及座位行列資訊，其中座位行列要先透過ActVO得到Scope資料，才能知道座位數量，進一步轉換為行列資訊
		// 3-1. 先得到所有Hash資料
		Set<String> keys = jedis.keys("*");
		for (String key : keys) {
			System.out.println(key);

			// 3-2. 再得到單一Hash資訊
			Map<String, String> hashData = jedis.hgetAll(key);
			for (Map.Entry<String, String> entry : hashData.entrySet()) {
				String SeatDatakey = entry.getKey();
				String SeatDatavalue = entry.getValue();

				// 3-3. 再裁切取值
				String[] Seatkey = SeatDatakey.split(",");
				int seatNumber = Integer.parseInt(Seatkey[0]); // 使用 parseInt 來轉換
				String[] Seatvalue = SeatDatavalue.split(",");
				int cartUID = Integer.parseInt(Seatvalue[0]); // 使用 parseInt 來轉換
				int actID = Integer.parseInt(Seatvalue[1]); // 使用 parseInt 來轉換
				String seatStatus = Seatvalue[2];

				// 4. 開始處理座位資料
				// 4-1. 先獲取Scope資料，呼叫ActService
				ActService actService = new ActServiceImpl();
				int scope = actService.findByPrimaryKey(actID).getActScope();

				// 4-2. 使用TurnSeatNumberToRowColumn方法將座位編號轉換座位行列
				String rowColumn = TurnSeatNumberToRowColumn(seatNumber, scope);

				// 5. 取得其他購物車資訊(活動名稱、活動單價、廠商ID、廠商名稱等)
				// 5-1. 基本資料處理
				Integer pubID = actService.findByPrimaryKey(actID).getPublisherVO().getPubID();
				String pubName = actService.findByPrimaryKey(actID).getPublisherVO().getPubName();
				String actName = actService.findByPrimaryKey(actID).getActName();
				Integer actPrice = actService.findByPrimaryKey(actID).getActPrice();
				Integer actCount = SeatDatakey.length() - 1;
				Integer actPriceTotal = actCount * actPrice;

				// 5-2. 圖片處理
				List<byte[]> actPicsList = new ArrayList<>();
				Set<ActPic> actPics = actService.findByPrimaryKey(actID).getActpics();
				for (ActPic actPic : actPics) {
					if (actPic.getActPic() != null) {
						byte[] actPicData = actPic.getActPic();
						actPicsList.add(actPicData);
					}
				}

				// 5-4. 打印測試
				System.out.println("pubID: " + pubID + "," + "pubName: " + pubName + "actID: " + actID + "," + "scope: "
						+ scope + "," + "actPics: " + actPicsList + "," + "actName: " + actName + "," + "rowColumn: "
						+ rowColumn + "," + "actPrice: " + actPrice + "," + "actCount: " + actCount + ","
						+ "actPriceTotal: " + actPriceTotal);

				// 6. 回傳Json資料到購物車
//				if (userID != null && userID.equals(cartUID)) {
				Map<String, Object> jsonData = new HashMap<>();
				jsonData.put("pubID", pubID);
				jsonData.put("pubName", pubName);
				jsonData.put("actID", actID);
				jsonData.put("scope", scope);
				jsonData.put("actPics", actPicsList);
				jsonData.put("actName", actName);
				jsonData.put("rowColumn", rowColumn);
				jsonData.put("actPrice", actPrice);
				jsonData.put("actCount", actCount);
				jsonData.put("actPriceTotal", actPriceTotal);

				System.out.println(jsonData);

				Gson gson = new Gson();
				String json = gson.toJson(jsonData);
//					res.getWriter().write(json);
//				}
			}

		}

	}

	// 方法用於轉換座位編號為行列，需要提供 scope 參數
	public static String TurnSeatNumberToRowColumn(int seatNumber, int scope) {

		int rowIndex = 0;
		int seatIndex = 0;

		switch (scope) {
		case 1:
			rowIndex = (int) Math.ceil((double) seatNumber / 20);
			seatIndex = seatNumber % 20 == 0 ? 20 : seatNumber % 20;
			break;
		case 2:
			rowIndex = (int) Math.ceil((double) seatNumber / 20);
			seatIndex = seatNumber % 20 == 0 ? 20 : seatNumber % 20;
			break;
		case 3:
			rowIndex = (int) Math.ceil((double) seatNumber / 30);
			seatIndex = seatNumber % 30 == 0 ? 30 : seatNumber % 30;
			break;
		}

		return "第 " + rowIndex + "排" + "," + "第 " + seatIndex + "列";
	}
}
