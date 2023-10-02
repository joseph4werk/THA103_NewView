package com.tha103.newview.post.model;

import java.util.List;

public interface PostDAO_interface {

//	public void insert(PostVO PostVO);
//	public void update(PostVO PostVO);
//	public void delete(Integer postID);
//	public PostVO findByPrimaryKey(Integer postID);
//	public List<PostVO> getAll();

	int add(PostVO PostVO);
	int update(PostVO PostVO);
	int delete(Integer PostID);
	PostVO findByPK(Integer PostID);
	List<PostVO> getAll();
}
