package com.tha103.newview.act.model;

import java.util.List;
import java.util.Set;

import com.tha103.newview.actpic.model.ActPic;



public interface ActDAO{
	
	  void insert(ActVO act);
	  void update(ActVO act);
      void delete(Integer ActID);
      ActVO findByPrimaryKey(Integer ActID);
      List<ActVO> getAll();      
      List<ActVO> getActPics();
}
