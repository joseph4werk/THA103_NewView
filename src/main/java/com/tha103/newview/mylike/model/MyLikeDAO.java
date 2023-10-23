package com.tha103.newview.mylike.model;

import java.util.List;

public interface MyLikeDAO {
	
	public int insert(MyLikeVO MyLikeVO);
    public int update(MyLikeVO MyLikeVO);
    public int delete(Integer myLikeID);
    public MyLikeVO findByPrimaryKey(Integer myLikeID);
    public List<MyLikeVO> getAll();
		
//  萬用複合查詢(傳入參數型態Map)(回傳List)
//  public List<MyLikeVO> getAll(Map<String, String[]> map);
    
}