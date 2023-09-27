package com.tha103.newview.user.model;

import java.util.*;

public interface UserDAO {
	public void insert(UserVO userVO);
	public void update(UserVO userVO);
	public void delete(Integer userID);
	public  UserVO findByPrimaryKey(Integer userID);
	public List<UserVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳List)
//	public List<UserVO> getAll(Map<String, String[]> map);
}
