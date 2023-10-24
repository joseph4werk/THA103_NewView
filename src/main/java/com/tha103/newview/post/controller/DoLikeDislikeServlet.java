package com.tha103.newview.post.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tha103.newview.post.model.PostVO;
import com.tha103.newview.post.service.PostService;
import com.tha103.newview.post.service.PostServiceImpl;

@WebServlet("/DoLikeDislikeServlet")
public class DoLikeDislikeServlet extends HttpServlet {
	private static final long serialVersionUID = 4941248261576452585L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("application/json; charset=UTF-8");
		String action = req.getParameter("action");
		int postID = Integer.parseInt(req.getParameter("postID").trim());
		int likeCount = Integer.parseInt(req.getParameter("likeCount").trim());
		int dislikeCount = Integer.parseInt(req.getParameter("dislikeCount").trim());

		System.out.println(action + "," + postID + "," + likeCount+ "," + dislikeCount);
		
		PostService postSvc = new PostServiceImpl();
		PostVO PostVOs = postSvc.getPostByPK(postID);

		if (action != null) {

			if (action.equals("like")) {
				PostVOs.setLikeCount(likeCount);

			} else if (action.equals("dislike")) {
				PostVOs.setDisLikeCount(dislikeCount);

			}

			postSvc.updatePost(PostVOs);
			PrintWriter out = res.getWriter();
			out.print("{ \"likeCount\": " + likeCount + ", \"dislikeCount\": " + dislikeCount + " }");
			out.flush();
		}
	}
}
