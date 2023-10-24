package com.tha103.newview.orderlist.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.tha103.newview.act.controller.ActWithPicsDTO;
import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.act.service.ActServiceImpl;
import com.tha103.newview.orderlist.model.OrderListDAO;
import com.tha103.newview.orderlist.model.OrderListDAOImpl;
import com.tha103.newview.orderlist.model.OrderListVO;

@WebServlet("/SeatOrderList")
public class OrderListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderListDAO orderListDAO = new OrderListDAOImpl();
		int seatDataCount = 0;
		int scope = 0;
		Integer Total = null;
		String actIDStr = request.getParameter("actID");
		String seatNumberStr = request.getParameter("seatNumber");
		String targetUserName = request.getParameter("targetUserName");
		String modificationCountStr = request.getParameter("modificationCount");
		Integer modificationCount = Integer.parseInt(modificationCountStr);
		Integer actID = Integer.parseInt(actIDStr);
		Map<String, String> seatData = new HashMap<>();
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();
			if (paramName.startsWith("seatNumber_")) {
				String seatNumber = paramName.replace("seatNumber_", "");
				System.out.println("Received seatNumber: " + seatNumber);
				String seatInfo = request.getParameter("seatData_" + seatNumber);
				seatData.put(seatNumber, seatInfo);
				seatDataCount++;
			}
		}

		Timestamp lastEditedTime = new Timestamp(System.currentTimeMillis());

		System.out.println("Received actID: " + actID);
		System.out.println("Received targetUserName: " + targetUserName);
		System.out.println("Received modificationCount: " + modificationCount);
		System.out.println("Received lastEditedTime: " + lastEditedTime);

		if (actID != null && !actIDStr.trim().isEmpty()) {
			ActServiceImpl actService = new ActServiceImpl();
			ActWithPicsDTO actData = actService.getActWithPicturesById(actID);
			scope = actData.getActScope();
			if (actData != null) {
				Total = (int) (actData.getActPrice());
				System.out.println("Act Price Total: " + Total);
			} else {
				System.out.println("No data found for actID: " + actID);
			}
		} else {
			System.out.println("Invalid actID received.");
		}

		for (Map.Entry<String, String> entry : seatData.entrySet()) {
			String seatNumber = entry.getKey();
			String seatInfo = entry.getValue();
			System.out.println("Received seatNumber: " + seatNumber);
			System.out.println("Received seatInfo: " + seatInfo);
		}

		String youtubeLink = "https://youtu.be/dQw4w9WgXcQ?si=3NVOtjDf3Lf9LSPW";
		byte[] qrCodeImage = generateQRCode(youtubeLink);

		for (Map.Entry<String, String> entry : seatData.entrySet()) {
			String seatNumber = entry.getKey();
			String seatInfo = entry.getValue();
			System.out.println("Received seatNumber: " + seatNumber);
			Integer seatNumberInt = Integer.parseInt(seatNumber);
			System.out.println("Received seatInfo: " + seatInfo);
			double rowIndex1 = 0.0;
			Integer seatIndex1 = 0;
			switch (scope) {
			case 1:
				rowIndex1 = Math.ceil(seatNumberInt / 20);
				seatIndex1 = seatNumberInt % 10 == 0 ? 10 : seatNumberInt % 10;
			case 2:
				rowIndex1 = Math.ceil(seatNumberInt / 20);
				seatIndex1 = seatNumberInt % 20 == 0 ? 20 : seatNumberInt % 20;
			case 3:
				rowIndex1 = Math.ceil(seatNumberInt / 30);
				seatIndex1 = seatNumberInt % 30 == 0 ? 30 : seatNumberInt % 30;
			}
			System.out.println("Received rowIndex1: " + rowIndex1);
			System.out.println("Received seatIndex1: " + seatIndex1);
			ActVO act = new ActVO();
			act.setActID(actID);
			OrderListVO orderListVO = new OrderListVO();
			orderListVO.setActTotal(Total);
			orderListVO.setOrderListTime(lastEditedTime);
			orderListVO.setQRcodeID(qrCodeImage);

			orderListVO.setActVO(act);
			orderListVO.setSeatRows(((int) rowIndex1) + 1);
			orderListVO.setSeatColumns(seatIndex1);
			orderListVO.setVacancy(seatNumber+",未使用");

			orderListDAO.insert(orderListVO);
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
