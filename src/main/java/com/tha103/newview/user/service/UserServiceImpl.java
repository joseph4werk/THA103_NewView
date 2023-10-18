package com.tha103.newview.user.service;

import java.util.List;

import com.tha103.newview.user.model.UserDAO;
import com.tha103.newview.user.model.UserDAOImpl;
import com.tha103.newview.user.model.UserVO;

public class UserServiceImpl implements UserService {

	private UserDAO userdao;

	public UserServiceImpl() {
		userdao = new UserDAOImpl();
	}

	@Override
	public boolean checkUserAccount(String account) {
		return userdao.checkUserAccount(account);
	}

	@Override
	public boolean checkUserAccount(String account, String password) {
		return userdao.checkUserAccount(account, password);
	}

	@Override
	public int addUser(UserVO userVO) {
		return userdao.insert(userVO);
	}

	@Override
	public int updateUser(UserVO userVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserVO getUserByPK(int userID) {
		return userdao.findByPrimaryKey(userID);
	}

	@Override
	public List<UserVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserVO getUserByAccount(String account) {
		return userdao.getUserByAccount(account);
	}
}
