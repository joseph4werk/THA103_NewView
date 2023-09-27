package com.tha103.newview.act.model;

import java.util.List;



public interface ActDAO_interface {
	
	 public void insert(ActVO actVO);
	 public void update(ActVO actVO);
     public void delete(Integer ActID);
     public ActVO findByPrimaryKey(Integer ActID);
     public List<ActVO> getAll();
     
}
