package com.tha103.newview.discount.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.newview.admin.model.AdminVO;
import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.discount.service.DiscountService;
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

		DiscountService discountSvc = new DiscountService();

		if ("add".equals(action)) {
			// 收到請求

			try {
				String pubIDStr = req.getParameter("pubIDStr");
				Integer pubID = Integer.parseInt(pubIDStr);

				String adminIDStr = req.getParameter("adminIDStr");
				Integer adminID = Integer.parseInt(adminIDStr);

				String discountContent = req.getParameter("discountContent");
				String disAmountStr = req.getParameter("disAmount");
				Integer disAmount = Integer.parseInt(disAmountStr);

				String discountCode = req.getParameter("discountCode");

				String disStartDateStr = req.getParameter("disStartDateStr");
				Timestamp disStartDate = null;
				if (disStartDateStr != null && !(disStartDateStr).isEmpty()) {
					SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						Date parsedDate = timeFormat.parse(disStartDateStr); // 將字串轉為日期
						disStartDate = new Timestamp(parsedDate.getTime()); // 轉換Timestamp類型
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				String disFinishDateStr = req.getParameter("disStartDateStr");
				Timestamp disFinishDate = null;
				if (disFinishDateStr != null && !(disFinishDateStr).isEmpty()) {
					SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						Date parsedDate = timeFormat.parse(disFinishDateStr); // 將字串轉為日期
						disFinishDate = new Timestamp(parsedDate.getTime()); // 轉換Timestamp類型
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

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
				String pubIDStr = req.getParameter("pubIDStr");
				Integer pubID = Integer.parseInt(pubIDStr);

				String adminIDStr = req.getParameter("adminIDStr");
				Integer adminID = Integer.parseInt(adminIDStr);

				String discountNOStr = req.getParameter("discountNOStr");
				Integer discountNO = Integer.parseInt(discountNOStr);

				String discountContent = req.getParameter("discountContent");
				String disAmountStr = req.getParameter("disAmount");
				Integer disAmount = Integer.parseInt(disAmountStr);

				String discountCode = req.getParameter("discountCode");

				String disStartDateStr = req.getParameter("disStartDateStr");
				Timestamp disStartDate = null;
				if (disStartDateStr != null && !(disStartDateStr).isEmpty()) {
					SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						Date parsedDate = timeFormat.parse(disStartDateStr); // 將字串轉為日期
						disStartDate = new Timestamp(parsedDate.getTime()); // 轉換Timestamp類型
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				String disFinishDateStr = req.getParameter("disStartDateStr");
				Timestamp disFinishDate = null;
				if (disFinishDateStr != null && !(disFinishDateStr).isEmpty()) {
					SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						Date parsedDate = timeFormat.parse(disFinishDateStr); // 將字串轉為日期
						disFinishDate = new Timestamp(parsedDate.getTime()); // 轉換Timestamp類型
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				/********** Package data **********/
				DiscountVO discountVO = new DiscountVO();
				discountVO.setDiscountNO(discountNO);
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

				discountVO = discountSvc.updateDiscount(discountNO, discountContent, disAmount, discountContent,
						disStartDate, disFinishDate, pubID, adminID);

				String url = "/Backstage/Allpage-publisher/discount/discount-listAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, resp);

			} catch (Exception e) {
				e.printStackTrace();
				resp.sendRedirect("/Backstage/Allpage-publisher/pub-index.jsp"); // 回應路徑
			}
		}

		if ("delete".equals(action)) {
			String discountNO = req.getParameter("discountNO").trim(); // 收到請求
			try {
				discountSvc.deleteDiscount(Integer.parseInt(discountNO)); // 透過service至資料庫刪除資料
				System.out.println(discountNO + "刪除成功");
				resp.sendRedirect("/Backstage/Allpage-publisher/discount/discount-list"); // 回應路徑
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendRedirect("/Backstage/Allpage-publisher/pub-index.jsp"); // 回應路徑
			}
		}

		if ("getOneForDisplay".equals(action)) {
			String discountNO = req.getParameter("discountNO").trim(); // 收到請求
			try {
				discountSvc.getDiscountByPK(Integer.parseInt(discountNO)); // 透過service至資料庫刪除資料
				System.out.println(discountNO + "顯示成功");
				resp.sendRedirect("/Backstage/Allpage-publisher/discount/discount-listOne"); // 回應路徑
			} catch (Exception e) {
				e.printStackTrace();
				resp.sendRedirect("/Backstage/Allpage-publisher/pub-index.jsp"); // 回應路徑
			}
		}

	}
}