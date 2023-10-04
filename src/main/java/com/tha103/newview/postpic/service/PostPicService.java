//package com.tha103.newview.postpic.service;
//
//import java.util.List;
//
//import com.tha103.newview.postpic.model.PostPicDAO_interface;
//import com.tha103.newview.postpic.model.PostPicHibernate;
//import com.tha103.newview.postpic.model.PostPicVO;
//
//public class PostPicService {
//
//	private PostPicDAO_interface dao;
//
//	public PostPicService() {
//		dao = new PostPicHibernate();
//	}
//
//	public PostPicVO addPostPic(Integer postPicID, Integer postID, byte[] postPic) {
//
//		PostPicVO PostPicVO = new PostPicVO();
//
//		PostPicVO.setPostPicID(postPicID);
//		PostPicVO.setPostID(postID);
//		PostPicVO.setPostPic(postPic);
//		
//		dao.add(PostPicVO);
//
//		return PostPicVO;
//	}
//
//	public PostPicVO updatePostPic(Integer postPicID, Integer postID, byte[] postPic) {
//
//		PostPicVO PostPicVO = new PostPicVO();
//
//		PostPicVO.setPostPicID(postPicID);
//		PostPicVO.setPostID(postID);
//		PostPicVO.setPostPic(postPic);
//
//		dao.update(PostPicVO);
//
//		return PostPicVO;
//	}
//
//	public void deletePostPic(Integer postPicID) {
//		dao.delete(postPicID);
//	}
//
//	public PostPicVO getOnePostMessage(Integer postPicID) {
//		return dao.findByPK(postPicID);
//	}
//
//	public List<PostPicVO> getAll() {
//		return dao.getAll();
//	}
//}
