package com.tha103.newview.post.model;

import java.util.List;
import java.util.Map;


public interface PostDAO {

	int insert(PostVO entity);

	int update(PostVO entity);
	
	int delete(Integer id);
	 
	PostVO getById(Integer id);
	
	List<PostVO> getAll();
	
	List<PostVO> getByCompositeQuery(Map<String, String> map);
	
	List<PostVO> getAll(int currentPage);
	
	long getTotal();
	

	
}
