//package com.tha103.newview.post.controller;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Paths;
//import java.util.LinkedList;
//import java.util.List;
//import com.tha103.newview.post.model.*;
//import com.tha103.newview.postcategory.model.*;
//import com.tha103.newview.postpic.model.*;
//
////@WebServlet("/FourmServlet")
//public class FourmServlet extends HttpServlet {
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//
//		req.setCharacterEncoding("UTF-8");
//		String action = req.getParameter("action");
//		
//		
//		//-----------------------來自fourm_confirm.jsp的請求-----------------------------//
//		if ("insert".equals(action)) { 
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			String userID = req.getParameter("userID");
//			
//			if (userID == null || userID.trim().length() == 0) {
//				errorMsgs.add("請先登入會員");
//			} 
//
//			String postHeader = req.getParameter("posttitle").trim();
//			if (postHeader == null || postHeader.trim().length() == 0) {
//				errorMsgs.add("標題請勿空白");
//			}
//
//			Integer postCategoryID = Integer.valueOf( req.getParameter("postcategory"));
//			if (postCategoryID == null ) {
//				errorMsgs.add("請選擇類別");
//			}
//
//			String postContent = req.getParameter("postcontent").trim();
//			if (postContent == null || postContent.trim().length() == 0) {
//				errorMsgs.add("內容請勿空白");
//			}
//			
//			// 獲取上傳的文件
//			Part filePart = req.getPart("postpic");
//			if (filePart != null) {
//			    // 獲取文件名稱
//			    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//			    // 獲取IO
//			    InputStream fileContent = filePart.getInputStream();
//			    // 保存文件到Server
//			}
//
//			
//
//			PostVO postVO = new PostVO();
//			PostCategoryVO postCategoryVO = new PostCategoryVO();
//			PostPicVO postpicVO = new PostPicVO();
//			postVO.setPostHeader(postHeader) ;
//			postCategoryVO.setPostCategoryID(postCategoryID);
//			postVO.setPostContent(postContent);
//			postpicVO.setPostPic(postpic);
//	
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("postVO", postVO); // 含有輸入格式錯誤的postVO物件,也存入req
//				req.setAttribute("postCategoryVO", postCategoryVO);
//				RequestDispatcher failureView = req.getRequestDispatcher("/fourm_confirm.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 2.開始新增資料 ***************************************/
//			PostService postSvc = new PostService();
//			PostVO = postSvc.addPost(userID, postCategoryID, postHeader, postContent);
//
//			PostPicService postPicSvc = new PostPicService();
//			byte[] postPicData = ...; // 从上传的文件中获取数据
//			PostPicVO postPicVO = postPicSvc.addPostPic(postPicID, .getPostID(), postPicData);
//
//
//			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
////			String url = "/fourm_confirm.html";
////			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
////			successView.forward(req, res);
//			
//			// 在Servlet中添加以下代碼並顯示彈窗
//			String redirectURL = "/fourm_home.html"; // 重定向的URL
//			String script = "<script>alert('感謝您的確認!文章已提交!即將回到Newview論壇首頁'); window.location.href = '" + redirectURL + "';</script>";
//			res.setContentType("text/html;charset=UTF-8");
//			res.getWriter().write(script);
//
//		}
//		//-----------------------來自fourm_confirm.jsp的請求完成-----------------------------//
//
//	
//		//-----------------------來自fourm_home.jsp的請求開始-----------------------------//
//	
//	
//	
//	
//	
//	
//	
//	
//	}
//
//}
//
