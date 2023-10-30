package com.tha103.newview.orderlist.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tha103.newview.act.model.ActDAO;
import com.tha103.newview.act.model.ActDAOHibernateImpl;
import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.act.service.ActPicService;
import com.tha103.newview.act.service.ActPicServiceImpl;
import com.tha103.newview.act.service.ActService;
import com.tha103.newview.act.service.ActServiceImpl;
import com.tha103.newview.act.service.ActUpdateService;
import com.tha103.newview.act.service.ActUpdateServiceImpl;
import com.tha103.newview.actpic.model.ActPicDAO;
import com.tha103.newview.actpic.model.ActPicDAOHibernateImpl;
import com.tha103.newview.orderlist.model.OrderListDAO;
import com.tha103.newview.orderlist.model.OrderListDAOImpl;
import com.tha103.newview.orderlist.model.OrderListVO;
import com.tha103.newview.orderlist.service.OrderListService;
import com.tha103.newview.orderlist.service.OrderListServiceImpl;

@WebServlet("/SeatOrderList")
public class OrderListController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private OrderListService orderListService;
	private ActService actService;
	private ActPicService actPicService;
	private ActUpdateService actUpdateService;

	@Override
	public void init() throws ServletException {
		super.init();
		OrderListDAO orderListDAO = new OrderListDAOImpl();
		this.orderListService = new OrderListServiceImpl();
		ActDAO actDAO = new ActDAOHibernateImpl();
		ActPicDAO actPicDAO = new ActPicDAOHibernateImpl();
		actService = new ActServiceImpl(actDAO);
		actPicService = new ActPicServiceImpl(actPicDAO);
		actUpdateService = new ActUpdateServiceImpl(actDAO, actPicDAO);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		int seatDataCount = 0;
		int scope = 0;
		Integer Total = null;
		String actID = null;
		String seatNumber =null;
		ActVO act = null;
		// 訂單購買接值
		String jsonCartData = request.getAttribute("cartData").toString();
		Gson gson = new Gson();
		JsonArray cartJsonArray = gson.fromJson(jsonCartData, JsonArray.class);
		for (JsonElement element : cartJsonArray) {
			JsonObject cartItem = element.getAsJsonObject();
			for (Map.Entry<String, JsonElement> entry : cartItem.entrySet()) {
				String key = entry.getKey();
				JsonElement value = entry.getValue();

				String cartItemInfo = value.getAsString();
				System.out.println("Key: " + key + ", Value: " + cartItemInfo);
				String[] parts = cartItemInfo.split(",");
				 actID = parts[1];
				 seatNumber = parts[0];
				double rowIndex1 = 0.0;
				Integer seatIndex1 = 0;
				Integer seatNumberInt = Integer.parseInt(seatNumber);
				act = actService.findByPrimaryKey(Integer.parseInt(actID));
				Integer scopeIn = act.getActScope();
				System.out.println(scopeIn);
				switch (scopeIn) {
				case 1:
					rowIndex1 = Math.ceil(seatNumberInt / 10);
					seatIndex1 = seatNumberInt % 10 == 0 ? 10 : seatNumberInt % 10;
					break;
				case 2:
					rowIndex1 = Math.ceil(seatNumberInt / 20);
					seatIndex1 = seatNumberInt % 20 == 0 ? 20 : seatNumberInt % 20;
					break;
				case 3:
					rowIndex1 = Math.ceil(seatNumberInt / 30);
					seatIndex1 = seatNumberInt % 30 == 0 ? 30 : seatNumberInt % 30;
					break;
				}
				String youtubeLink = "https://youtu.be/dQw4w9WgXcQ?si=3NVOtjDf3Lf9LSPW";
				byte[] qrCodeImage = generateQRCode(youtubeLink);
				Timestamp lastEditedTime = new Timestamp(System.currentTimeMillis());
				OrderListVO orderList = new OrderListVO();
				orderList.setOrderListTime(lastEditedTime);
				orderList.setActTotal(act.getActPrice());
				orderList.setActVO(act);
				orderList.setQRcodeID(qrCodeImage);
				orderList.setSeatRowsColumns("第 " + ((int)rowIndex1+1) + "排, 第 " + seatIndex1 + " 列");
				orderList.setType(0);

				orderListService.insert(orderList);
				//如果沒有印出訊息
				if (act != null) {
					System.out.println("Found related act: " + act.getActName());
				} else {
					System.out.println("Act with ID " + actID + " not found.");
				}
			}
		}
	}

	protected byte[] generateQRCode(String youtubeLink) throws IOException {
		try {
			// 設置qrcode
			int width = 300;
			int height = 300;
			String format = "png";

			// 生成QR码
			BitMatrix bitMatrix = new MultiFormatWriter().encode(youtubeLink, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, format, baos);

			// 返回改好的byte
			return baos.toByteArray();
		} catch (WriterException e) {
			e.printStackTrace();
			return null;
		}
	}
}
