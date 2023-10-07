package com.tha103.newview.postcategory.service;

import java.util.List;

import com.tha103.newview.postcategory.model.PostCategoryDAO_interface;
import com.tha103.newview.postcategory.model.PostCategoryHibernate;
import com.tha103.newview.postcategory.model.PostCategoryVO;

public class PostCategoryService {

	private PostCategoryDAO_interface dao;

	public PostCategoryService() {
		dao = new PostCategoryHibernate();
	}

	public PostCategoryVO addPostCategory(Integer postCategoryID,String postCategoryName) {

		PostCategoryVO PostCategoryVO = new PostCategoryVO();

		PostCategoryVO.setPostCategoryID(postCategoryID);
		PostCategoryVO.setPostCategoryName(postCategoryName);
		
		dao.add(PostCategoryVO);

		return PostCategoryVO;
	}

	public PostCategoryVO updatePostCategory(Integer postCategoryID,String postCategoryName) {

		PostCategoryVO PostCategoryVO = new PostCategoryVO();

		PostCategoryVO.setPostCategoryID(postCategoryID);
		PostCategoryVO.setPostCategoryName(postCategoryName);
		
		dao.update(PostCategoryVO);

		return PostCategoryVO;
	}

	public void deletePostCategory(Integer postCategoryID) {
		dao.delete(postCategoryID);
	}

	public PostCategoryVO getOnePostCategory(Integer postCategoryID) {
		return dao.findByPK(postCategoryID);
	}

	public List<PostCategoryVO> getAll() {
		return dao.getAll();
	}
}


