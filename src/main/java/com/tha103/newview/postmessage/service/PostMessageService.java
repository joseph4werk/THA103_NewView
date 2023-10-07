//package com.tha103.newview.postmessage.service;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//import com.tha103.newview.postmessage.model.PostMessageDAO_interface;
//import com.tha103.newview.postmessage.model.PostMessageHibernate;
//import com.tha103.newview.postmessage.model.PostMessageVO;
//
//public class PostMessageService {
//
//	private PostMessageDAO_interface dao;
//
//	public PostMessageService() {
//		dao = new PostMessageHibernate();
//	}
//
//	public PostMessageVO addPostMessage(Integer postMessageID, Integer postID, Integer userID, String mesContent,
//			Timestamp messageDate) {
//
//		PostMessageVO msg = new PostMessageVO();
//
//		msg.setPostMessageID(postMessageID);
//		msg.setPostID(postID);
//		msg.setUserID(userID);
//		msg.setMesContent(mesContent);
//		msg.setMessageDate(messageDate);
//
//		dao.add(msg);
//
//		return msg;
//	}
//
//	public PostMessageVO updatePostMessage(Integer postMessageID, Integer postID, Integer userID, String mesContent,
//			Timestamp messageDate) {
//
//		PostMessageVO PostMessageVO = new PostMessageVO();
//
//		PostMessageVO.setPostMessageID(postMessageID);
//		PostMessageVO.setPostID(postID);
//		PostMessageVO.setUserID(userID);
//		PostMessageVO.setMesContent(mesContent);
//		PostMessageVO.setMessageDate(messageDate);
//		
//		dao.update(PostMessageVO);
//
//		return PostMessageVO;
//	}
//
//	public void deletePostMessage(Integer postMessageID) {
//		dao.delete(postMessageID);
//	}
//
//	public PostMessageVO getOnePostMessage(Integer postMessageID) {
//		return dao.findByPK(postMessageID);
//	}
//
//	public List<PostMessageVO> getAll() {
//		return dao.getAll();
//	}
//}
