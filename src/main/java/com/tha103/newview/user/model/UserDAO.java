package com.tha103.newview.user.model;

import java.util.*;

import com.tha103.newview.orders.model.OrdersVO;

public interface UserDAO {
	boolean checkUserAccount(String account);
	boolean checkUserAccount(String account, String password);
	public int insert(UserVO userVO);
	public int update(UserVO userVO);
	public int delete(Integer userID);
	public UserVO findByPrimaryKey(Integer userID);
	public UserVO getUserByAccount(String account);
	public List<UserVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳List)
//	public List<UserVO> getAll(Map<String, String[]> map);
	public OrdersVO getOrderByUserID(Integer userID);
}
