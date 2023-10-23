package com.tha103.newview.act.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.google.gson.Gson;
import com.tha103.newview.act.model.ActDAO;
import com.tha103.newview.act.model.ActDAOHibernateImpl;
import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.act.service.*;
import com.tha103.newview.actcategory.model.ActCategory;
import com.tha103.newview.actpic.model.ActPic;
import com.tha103.newview.actpic.model.ActPicDAO;
import com.tha103.newview.actpic.model.ActPicDAOHibernateImpl;
import com.tha103.newview.cityaddress.model.CityAddress;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.util.HibernateUtil;

@WebServlet("/act/act.do")
@MultipartConfig(maxFileSize = 1073741824)
public class TheActServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 對應到的service 實體
		ActService actService = new ActServiceImpl();
		ActPicService actPicService = new ActPicServiceImpl();

		ActDAO actDAO = new ActDAOHibernateImpl();
		ActPicDAO actPicDAO = new ActPicDAOHibernateImpl();
		ActUpdateService actUpdateService = new ActUpdateServiceImpl(actDAO, actPicDAO);

		// 接收的參數
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		resp.setContentType("application/json");

		if ("search".equals(action)) {

			/********** 收到請求參數_找圖片 **********/

			String actName = req.getParameter("search-product");
			System.out.println("收到請求參數" + actName);

			/********** 輸入錯誤處理 **********/

			if (actName != null && !actName.isEmpty()) {

				/********** 查詢圖片資料 **********/
				List<ActWithPicsDTO> actWithPicsList = actService.searchActsByName(actName);// 至資料庫找資料

				if (!actWithPicsList.isEmpty()) {
					req.setAttribute("actWithPicsList", actWithPicsList); // 資料庫取出的list物件,存入request集合
					req.getRequestDispatcher("/SearchNewFile.jsp").forward(req, resp);// 執行完畢後，轉交回送出修改的來源網頁
				} else {
					req.setAttribute("actNameNotFound", "notFound");
					req.getRequestDispatcher("NewActJSPTest.jsp").forward(req, resp);
				}

			}
			return;
		}

		if ("update".equals(action)) {

			/********** 收到請求參數 **********/
			String actId = req.getParameter("actId");
			System.out.println("servlet接收到的請求為：" + actId);

			/********** 輸入錯誤處理 **********/
			if (actId != null && !actId.isEmpty()) {

				/********** 查詢資料 **********/
				Integer actIdValue = Integer.parseInt(actId); // 參數轉為int
				ActWithPicsDTO actWithPicsDTO = actService.getActWithPicturesById(actIdValue); // 至資料庫找資料
				List<ActWithPicsDTO> actWithPicsList = Arrays.asList(actWithPicsDTO);

				if (actWithPicsDTO != null) {
					req.setAttribute("actWithPicsList", actWithPicsList); // 資料庫取出的list物件,存入request集合

//                    String url = "/Backstage/Allpage-publisher/activity/activity-list.jsp";
//                    RequestDispatcher successView = req.getRequestDispatcher(url);
//                    successView.forward(req, resp);
					req.getRequestDispatcher("/Backstage/Allpage-publisher/activity/activity-update.jsp").forward(req,
							resp); // 執行完畢後，轉交回送出修改的來源網頁
				}
			} else {
				req.setAttribute("actIdError", "null");
				req.getRequestDispatcher("ActJSP.jsp").forward(req, resp);
			}
		}

		if ("updateAct".equals(action)) {

			/********** 收到請求參數 **********/
			String actId = req.getParameter("actId");
			System.out.println("收到請求參數" + actId);

			/********** 輸入錯誤處理 **********/
			if (actId != null && !actId.isEmpty()) {

				/********** 查詢資料 **********/
				Integer actIdValue = Integer.parseInt(actId); // 參數轉為int
				ActWithPicsDTO actWithPicsDTO = actService.getActWithPicturesById(actIdValue); // 至資料庫找資料

				List<ActWithPicsDTO> actWithPicsList = Arrays.asList(actWithPicsDTO);

				if (actWithPicsDTO != null) {
					req.setAttribute("actWithPicsList", actWithPicsList); // 資料庫取出的list物件,存入request集合
					req.getRequestDispatcher("/update-act.jsp").forward(req, resp); // 執行完畢後，轉交回送出修改的來源網頁
				}
			} else {
				req.setAttribute("actIdError", "null");
				req.getRequestDispatcher("ActJSP.jsp").forward(req, resp);
			}
			return;
		}

		if ("getJsonData".equals(action)) {
			List<ActWithPicsDTO> actWithPicsList = new ArrayList<>();

			/********** 收到請求參數 **********/
			String actIdStr = req.getParameter("actID");
			Integer actId = null;

			/********** 輸入錯誤處理 **********/
			if (actIdStr != null) {
				actId = Integer.parseInt(actIdStr);

				/********** 查詢資料 **********/
				ActWithPicsDTO actWithPicsDTO = actService.getActWithPicsDTOById(actId);

				if (actWithPicsDTO != null) {
					actWithPicsList.add(actWithPicsDTO); // 至資料庫新增資料
					String json = new Gson().toJson(actWithPicsList); // 將資料轉至json

					resp.setContentType("application/json"); // 回應的型態為json格式
					resp.setCharacterEncoding("UTF-8");
					resp.getWriter().write(json); // 回應json資料
				} else {
					resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Activity not found");
				}
			}
		}

		if ("UP".equals(action)) {

			/********** 收到請求參數 **********/
			String toDelete = req.getParameter("toDelete");
			System.out.println(toDelete);

			/********** 輸入錯誤處理 **********/
			if (toDelete != null && !toDelete.isEmpty()) {

				/********** 先刪除圖片資料 **********/
				try {
					actPicService.deleteActPic(toDelete);

				} catch (NumberFormatException nfe) {
					nfe.printStackTrace();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			/********** 在放入新的資料 **********/
			actUpdateService.updateActAndImages(req, resp);
			resp.sendRedirect("ActJSP.jsp"); // 轉交資料
		}

		if ("delete".equals(action)) {

			/********** 收到請求參數 **********/
			String actIDelete = req.getParameter("actIDelete");
			// String actIDelete = req.getParameter("actIDelete");
			System.out.println("servlet收到刪除請求：" + actIDelete);

			/********** 輸入錯誤處理 **********/
			if (actIDelete != null) {
				try {

					/********** 查詢資料 **********/
					actService.delete(Integer.parseInt(actIDelete)); // 至資料庫刪除資料
					resp.setContentType("application/json; charset=UTF-8");

					resp.getWriter().println("Act ID " + actIDelete + " 成功"); // 回應刪除成功訊息

					String url = "/Backstage/Allpage-publisher/activity/activity-list.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, resp);

				} catch (Exception e) {
					e.printStackTrace();
					resp.getWriter().println("Act ID " + actIDelete + " 失敗"); // 回應刪除失敗訊息

				}
			} else {

				resp.getWriter().println("Act ID 不能為空"); // 回應其他錯誤訊息
			}
		}
		
		
		

		if ("addAct".equals(action)) {
			String actName = (req.getParameter("actName") != null && !req.getParameter("actName").isEmpty())
					? req.getParameter("actName")
					: "Default Act Name";
			System.out.println(actName);
			String actPriceStr = (req.getParameter("actPrice") != null
					&& !req.getParameter("actPrice").isEmpty()) ? req.getParameter("actPrice") : "1000";
			String actCategoryIDStr = (req.getParameter("actCategory") != null
					&& !req.getParameter("actCategory").isEmpty()) ? req.getParameter("actCategory") : "1";

			System.out.println(actCategoryIDStr);

			String publisherName = (req.getParameter("publisher") != null
					&& !req.getParameter("publisher").isEmpty()) ? req.getParameter("publisher") : "1";
			String actTimeStr = (req.getParameter("time") != null && !req.getParameter("time").isEmpty())
					? req.getParameter("time")
					: "12:00:00";
			String cityIDStr = (req.getParameter("cityName") != null && !req.getParameter("cityName").isEmpty())
					? req.getParameter("cityName")
					: "1";
			String actIntroduce = (req.getParameter("actIntroduce") != null
					&& !req.getParameter("actIntroduce").isEmpty()) ? req.getParameter("actIntroduce") : "未輸入";
			String actDateStr = (req.getParameter("actDate") != null && !req.getParameter("actDate").isEmpty())
					? req.getParameter("actDate")
					: "1970-01-01";
			String actTimeDate = (req.getParameter("actTime") != null && !req.getParameter("actTime").isEmpty())
					? req.getParameter("actTime")
					: "1970-01-01";
			String approvalConditionStr = (req.getParameter("approvalCondition") != null
					&& !req.getParameter("approvalCondition").isEmpty()) ? req.getParameter("approvalCondition")
							: "0";
			String actContent = (req.getParameter("actContent") != null
					&& !req.getParameter("actContent").isEmpty()) ? req.getParameter("actContent")
							: "Default Act Content";
			String actScope = (req.getParameter("actScope") != null && !req.getParameter("actScope").isEmpty())
					? req.getParameter("actScope")
					: "1";
			String actActAddress = (req.getParameter("actActAddress") != null
					&& !req.getParameter("actActAddress").isEmpty()) ? req.getParameter("actActAddress")
							: "Default actActAddress";
			String pubID = (req.getParameter("pubID") != null && !req.getParameter("pubID").isEmpty())
					? req.getParameter("pubID")
					: "1";
			Date actTime = null;
			if (actTimeStr != null && !actTimeStr.isEmpty()) {
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				try {
					actTime = timeFormat.parse(actTimeStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			Date actDate = null;
			if (actDateStr != null && !actDateStr.isEmpty()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				try {
					actDate = dateFormat.parse(actDateStr);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			ActVO act = new ActVO();
			ActCategory actC = new ActCategory();
			CityAddress city = new CityAddress();
			PublisherVO pub = new PublisherVO();
			act.setActID(act.getActID());
			city.setActAdressID(Integer.parseInt(cityIDStr));
			act.setActName(actName);
			act.setActPrice(Integer.parseInt(actPriceStr));
			actC.setActCategoryID(Integer.parseInt(actCategoryIDStr));
			act.setActCategory(actC);
			pub.setPubID(Integer.parseInt(pubID));
			act.setPublisherVOs(pub);
			act.setActTime(actTime);
			act.setTime(actTime);
			act.setActScope(Integer.parseInt(actScope));
			act.setActContent(actContent);
			act.setCityAddressID(city);

			act.setActIntroduce(actIntroduce);
			act.setActDate(actDate);
			act.setApprovalCondition(Integer.parseInt(approvalConditionStr));
			act.setCityAddress(actActAddress);

			// 獲取上傳的圖片
			Part filePart = req.getPart("actImage");
			System.out.println(filePart);
			;
			if (filePart != null) {
				InputStream fileContent = filePart.getInputStream();
				byte[] imageData = new byte[fileContent.available()];
				fileContent.read(imageData);
				ActPic actPic = new ActPic();
				actPic.setActID(act);
				actPic.setActPic(imageData);
				System.out.println("Received image data: " + imageData.length + " bytes");

				Set<ActPic> actPics = new HashSet<>();
				actPics.add(actPic);
				act.setActpics(actPics);
			} else {
				System.out.println("No image data received");
			}

			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			try {
				session.beginTransaction();
				String hql = "FROM ActVO WHERE actName = :actName";
				Query query = session.createQuery(hql);
				query.setParameter("actName", actName);
				List results = query.list();
				if (!results.isEmpty()) {
					System.out.println("處理同名活動");
					// 活動名稱存在
					req.getSession().setAttribute("actNameError", "actNameSame");
					resp.sendRedirect("ActJSP.jsp");

					return;
				}
				session.save(act);
				session.getTransaction().commit();
			} catch (Exception e) {
				if (session.getTransaction() != null) {
					session.getTransaction().rollback();
				}
				e.printStackTrace();
			} finally {
				// 關閉 Session
				session.close();
			}
			req.getRequestDispatcher("/Backstage/Allpage-publisher/activity/activity-list.jsp").forward(req,resp);
			//resp.sendRedirect("/Backstage/Allpage-publisher/activity/activity-list.jsp");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
