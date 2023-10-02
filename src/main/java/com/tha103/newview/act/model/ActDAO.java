package com.tha103.newview.act.model;

import java.util.List;



public interface ActDAO{
	
	  void insert(Act act);
	  void update(Act act);
      void delete(Integer ActID);
      Act findByPrimaryKey(Integer ActID);
      List<Act> getAll();
     
}
