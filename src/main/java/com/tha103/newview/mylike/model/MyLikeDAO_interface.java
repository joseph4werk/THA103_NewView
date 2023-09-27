package com.tha103.newview.mylike.model;

import java.util.List;

public interface MyLikeDAO_interface {
	
          public void insert(MyLikeVO MyLikeVO);
          public void update(MyLikeVO MyLikeVO);
          public void delete(Integer myLikeID);
          public MyLikeVO findByPrimaryKey(Integer myLikeID);
          public List<MyLikeVO> getAll();
          
//     	  萬用複合查詢(傳入參數型態Map)(回傳List)
//        public List<MyLikeVO> getAll(Map<String, String[]> map);
    
}