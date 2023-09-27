package com.tha103.newview.likes.model;

import java.util.*;

public interface LikesDAO {
	public void insert(LikesVO likeVO);
	public void update(LikesVO likeVO);
	public void delete(Integer likeID);
	public LikesVO findByPrimaryKey(Integer likeID);
	public List<LikesVO> getAll();

//	 萬用複合查詢(傳入參數型態Map)(回傳List)
//	public List<UseDiscountVO> getAll(Map<String, String[]> map);
}
