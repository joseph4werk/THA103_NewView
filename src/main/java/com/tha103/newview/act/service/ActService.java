package com.tha103.newview.act.service;

import java.util.List;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.user.model.UserVO;

public interface ActService {
	void insert(ActVO act);
	void update(ActVO act);
    void delete(Integer ActID);
    ActVO findByPrimaryKey(Integer ActID);
    List<ActVO> getAll();
}
