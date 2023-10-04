//package com.tha103.newview.post.service;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//import com.tha103.newview.post.model.PostDAO_interface;
//import com.tha103.newview.post.model.PostHibernate;
//import com.tha103.newview.post.model.PostVO;
//
//public class PostService {
//
//	private PostDAO_interface dao;
//
//	public PostService() {
//		dao = new PostHibernate();
//	}
//
//	public PostVO addPost(Integer postID, Integer userID, String postHeader,Timestamp postDateTime,
//			Timestamp lastEditedTime, String postContent, Integer disLikeCount,Integer likeCount, Integer postStatus) {
//
//		PostVO postVO = new PostVO();
//		
//		postVO.setPostID(postID);
//		postVO.setUserID(userID);
//		postVO.setPostHeader(postHeader);
//		postVO.setPostDateTime(postDateTime);
//		postVO.setLastEditedTime(lastEditedTime);
//		postVO.setPostContent(postContent);
//		postVO.setDisLikeCount(disLikeCount);
//		postVO.setLikeCount(likeCount);
//		postVO.setPostStatus(postStatus);
//		
//		dao.add(postVO);
//
//		return postVO;
//	}
//
//	public PostVO updatePost(Integer postID, Integer userID, String postHeader,Timestamp postDateTime,
//			Timestamp lastEditedTime, String postContent, Integer disLikeCount,Integer likeCount, Integer postStatus) {
//
//		PostVO PostVO = new PostVO();
//
//		PostVO.setPostID(postID);
//		PostVO.setUserID(userID);
//		PostVO.setPostHeader(postHeader);
//		PostVO.setPostDateTime(postDateTime);
//		PostVO.setLastEditedTime(lastEditedTime);
//		PostVO.setPostContent(postContent);
//		PostVO.setDisLikeCount(disLikeCount);
//		PostVO.setLikeCount(likeCount);
//		PostVO.setPostStatus(postStatus);
//		dao.update(PostVO);
//
//		return PostVO;
//	}
//
//	public void deletePost(Integer postID) {
//		dao.delete(postID);
//	}
//
//	public PostVO getOnePost(Integer postID) {
//		return dao.findByPK(postID);
//	}
//
//	public List<PostVO> getAll() {
//		return dao.getAll();
//	}
//}
//
