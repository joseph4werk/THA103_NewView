package com.tha103.newview.discount.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.newview.admin.model.AdminVO;
import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.discount.service.*;
import com.tha103.newview.publisher.model.PublisherVO;

@WebServlet("/pub/discount.do")
public class DiscountByPubServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		DiscountService discountSvc = new DiscountServiceImpl();

		if ("add".equals(action)) {
			// 收到請求 

			try {
				String discountNOStr = req.getParameter("discountNOStr");
				Integer discountNO = Integer.parseInt(discountNOStr);
				System.out.println("servlet收到編號" + discountNO + "更新請求");
				
				String pubIDStr = req.getParameter("pubIDStr");
				Integer pubID = Integer.parseInt(pubIDStr);
				System.out.println("pubID" + pubID);
				

				String adminIDStr = (req.getParameter("adminIDStr") 
						!= null && !req.getParameter("adminIDStr").isEmpty()) ? req.getParameter("adminIDStr") : "1";
				Integer adminID = Integer.parseInt(adminIDStr);
				System.out.println("adminID" + adminID);

				String discountContent = req.getParameter("discountContent");
				System.out.println("discountContent" + discountContent);
				
				String disAmountStr = req.getParameter("disAmount");
				Integer disAmount = Integer.parseInt(disAmountStr);
				System.out.println("disAmount" + disAmount);

				String discountCode = req.getParameter("discountCode");
				System.out.println("discountCode" + discountCode);
				
				String disStartDateStr = req.getParameter("disStartDate"); // 使用正確的變數名
				System.out.println("disStartDateStr: " + disStartDateStr);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Timestamp disStartDate = null;
				try {
					Date parsedDate = dateFormat.parse(disStartDateStr);//轉為日期對象
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//定義目標格式
					String formattedDateTime = targetFormat.format(parsedDate);
					disStartDate = Timestamp.valueOf(formattedDateTime);
				} catch (Exception e) {
					System.out.println("優惠起始日發生例外");
					e.printStackTrace();
				}
				System.out.println("disStartDate: " + disStartDate);
				
				
				
				String disFinishDateStr = req.getParameter("disFinishDate");
				System.out.println("disFinishDateStr" + disFinishDateStr);
				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
				Timestamp disFinishDate = null;
				try {
					Date parsedDate = dateFormat2.parse(disFinishDateStr);//轉為日期對象
					SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//定義目標格式
					String formattedDateTime = targetFormat.format(parsedDate);
					disFinishDate = Timestamp.valueOf(formattedDateTime);
				} catch (Exception e) {
					System.out.println("優惠結束日發生例外");
					e.printStackTrace();
				}
				System.out.println("disFinishDate" + disFinishDate);


				/********** Package data **********/
				DiscountVO discountVO = new DiscountVO();
				discountVO.setDiscountContent(discountContent);
				discountVO.setDisAmount(disAmount);
				discountVO.setDiscountCode(discountContent);
				discountVO.setDisStartDate(disStartDate);
				discountVO.setDisFinishDate(disFinishDate);

				PublisherVO pub = new PublisherVO();
				pub.setPubID(pubID);
				discountVO.setPublisherVO(pub);

				AdminVO admin = new AdminVO();
				admin.setAdminID(adminID);
				discountVO.setAdminVO(admin);

				/********** 新增資料 **********/

				discountVO = discountSvc.addDiscount(discountContent, disAmount, discountContent, disStartDate,
						disFinishDate, pubID, adminID);

				String url = "/Backstage/Allpage-publisher/discount/discount-listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
				resp.sendRedirect("/Backstage/Allpage-publisher/pub-index.jsp"); // 回應路徑
			}
		}

		if ("update".equals(action)) {

			try {
				String discountNOStr = req.getParameter("discountNOStr");
				Integer discountNO = Integer.parseInt(discountNOStr);
				System.out.println("servlet收到編號" + discountNO + "更新請求");
				

				String discountContent = req.getParameter("discountContent");
				System.out.println("discountContent" + discountContent);
				
				String disAmountStr = req.getParameter("disAmount");
				Integer disAmount = Integer.parseInt(disAmountStr);
				System.out.println("disAmount" + disAmount);

				String discountCode = req.getParameter("discountCode");
				System.out.println("discountCode" + discountCode);

				String disStartDateStr = req.getParameter("disStartDate"); // 使用正確的變數名
				System.out.println("disStartDateStr: " + disStartDateStr);
				
				//這個是用date可以成功的
				Date disStartDate = null;
				if (disStartDateStr != null && !disStartDateStr.isEmpty()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						disStartDate = dateFormat.parse(disStartDateStr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("disStartDate" + disStartDate);

//				DateFormat startdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//				Date startdate = startdf.parse(disStartDateStr);
//				long starttime = startdate.getTime();
//				Timestamp disStartDate = new Timestamp(starttime);
//				System.out.println("disStartDate: " + disStartDate);

				
//				Date targetFormat = null;
//				Timestamp disStartDate = null;
//				if (disStartDateStr != null && !(disStartDateStr).isEmpty()) {
//					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//					try {
//						targetFormat = dateFormat.parse(disStartDateStr); 
//						disStartDate = new Timestamp(date.getTime()); // 轉換Timestamp類型
//					} catch (Exception e) {
//						System.out.println("disStartDateStr錯誤訊息");
//						e.printStackTrace();
//						
//					}
//				}
//				System.out.println(disStartDate);

				//這個是用date可以成功的
				String disFinishDateStr = req.getParameter("disFinishDate");
				System.out.println("disFinishDateStr" + disFinishDateStr);
				
				Date disFinishDate = null;
				if (disFinishDateStr != null && !disFinishDateStr.isEmpty()) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						disFinishDate = dateFormat.parse(disFinishDateStr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				System.out.println("disFinishDate" + disFinishDate);
				
				
//			    DateFormat finishdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//			    Date finishdate = finishdf.parse(disStartDateStr);
//			    long finishtime = finishdate.getTime();
//			    Timestamp disFinishDate = new Timestamp(finishtime);
//			    System.out.println("disFinishDate" + disFinishDate);
			    
			    
			    
//				Timestamp disFinishDate = null;
//				if (disFinishDateStr != null && !(disFinishDateStr).isEmpty()) {
//					SimpleDateFormat timeFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
//					try {
//						Date date = timeFormat.parse(disFinishDateStr); 
//						disFinishDate = new Timestamp(date.getTime()); // 轉換Timestamp類型
//					} catch (Exception e) {
//						System.out.println("disFinishDateStr錯誤訊息");
//						e.printStackTrace();
//					}
//				}
//				System.out.println("disFinishDateStr" + disFinishDateStr);
				

				String pubIDStr = req.getParameter("pubIDStr") ;
				Integer pubID = Integer.parseInt(pubIDStr);
				System.out.println("pubID" + pubID);

				String adminIDStr = (req.getParameter("adminIDStr") 
						!= null && !req.getParameter("adminIDStr").isEmpty()) ? req.getParameter("adminIDStr") : "1";
				Integer adminID = Integer.parseInt(adminIDStr);
				System.out.println("adminID" + adminID);
				
				System.out.println("接收完請求了");
				/********** Package data **********/
				DiscountVO discountVO = new DiscountVO();
				discountVO.setDiscountNO(discountNO);
				discountVO.setDiscountContent(discountContent);
				discountVO.setDisAmount(disAmount);
				discountVO.setDiscountCode(discountCode);
//				discountVO.setDisStartDate(disStartDate);
//				discountVO.setDisFinishDate(disFinishDate);

				PublisherVO pub = new PublisherVO();
				pub.setPubID(pubID);
				discountVO.setPublisherVO(pub);

				AdminVO admin = new AdminVO();
				admin.setAdminID(adminID);
				discountVO.setAdminVO(admin);

				/********** 新增資料 **********/
				System.out.println("準備新增資料");

//				discountVO = discountSvc.updateDiscount(discountNO,discountContent, disAmount, discountCode,
//						disStartDate, disFinishDate, pubID, adminID);
//				System.out.println("要新增的資料為");
//				System.out.println(discountVO);

				String url = "/Backstage/Allpage-publisher/discount/discount-list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
				resp.sendRedirect("/Backstage/Allpage-publisher/pub-index.jsp"); // 回應路徑
			}
			
		}


		if ("delete".equals(action)) {
			String discountNO = req.getParameter("discountNO").trim(); // 收到請求
			System.out.println("servlet收到刪除請求" + discountNO);
			try {
				discountSvc.deleteDiscount(Integer.parseInt(discountNO)); // 透過service至資料庫刪除資料
				System.out.println(discountNO + "刪除成功");
				resp.sendRedirect("/Backstage/Allpage-publisher/discount/discount-list"); // 回應路徑
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendRedirect("/Backstage/Allpage-publisher/pub-index.jsp"); // 回應路徑
			}
		}

		if ("getOneForUpdate".equals(action)) {
			String discountNO = req.getParameter("discountNO").trim(); // 收到請求
			System.out.println("servlet收到顯示請求" + discountNO);
			try {
				
				DiscountVO discountVO = discountSvc.getOneDiscount(Integer.parseInt(discountNO)); // 透過service至資料庫找到資料
				System.out.println(discountVO);
				System.out.println("成功找到資料");
				
				req.setAttribute("discountVO", discountVO); // 資料庫取出的pubuserVO物件,存入req
				String url = "/Backstage/Allpage-publisher/discount/discount-update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, resp);
				
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendRedirect("/Backstage/Allpage-publisher/pub-index.jsp"); // 回應路徑
			}
		}

	}
}