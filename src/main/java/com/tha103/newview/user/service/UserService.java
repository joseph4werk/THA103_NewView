package com.tha103.newview.user.service;

import java.util.List;

import com.tha103.newview.user.model.UserVO;

public interface UserService {
	public boolean checkUserAccount(String account);
	public int addUser(UserVO userVO);
	public int updateUser(UserVO userVO);
	public int getUserByPK(int userID);
	public List<UserVO> getAll();
}
