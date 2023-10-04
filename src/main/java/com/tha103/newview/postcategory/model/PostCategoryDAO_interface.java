package com.tha103.newview.postcategory.model;

import java.util.List;


public interface PostCategoryDAO_interface {
//	public void insert(PostCategoryVO postCategoryVO);
//	public void update(PostCategoryVO PostCategoryVO);
//	public PostCategoryVO findByPrimaryKey(Integer PostCategoryID);
//	public List<PostCategoryVO> getAll();
	
	int add(PostCategoryVO postCategoryVO);
	int update(PostCategoryVO postCategoryVO);
	int delete(Integer postCategoryID);
	PostCategoryVO findByPK(Integer postCategoryID);
	List<PostCategoryVO> getAll();
	
}
