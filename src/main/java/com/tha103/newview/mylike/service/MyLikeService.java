package com.tha103.newview.mylike.service;

import java.util.List;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.mylike.model.MyLikeDAO;
import com.tha103.newview.mylike.model.MyLikeDAOImpl;
import com.tha103.newview.mylike.model.MyLikeVO;
import com.tha103.newview.user.model.UserVO;


public class MyLikeService{

	private MyLikeDAO myLikeDao;
	
	public MyLikeService() {
		myLikeDao = new MyLikeDAOImpl();
	}
	
	public MyLikeVO insertMyLike(Integer userID, Integer actID) {

		MyLikeVO myLikeVO = new MyLikeVO();
		
		UserVO userVO = new UserVO();
		userVO.setUserID(userID);
		myLikeVO.setUser(userVO);
		
		ActVO actVO = new ActVO();
		actVO.setActID(actID);
		myLikeVO.setAct(actVO);

		myLikeDao.insert(myLikeVO);

		return myLikeVO;
	}
	
	public MyLikeVO updateMyLike(Integer userID, Integer actID) {

		MyLikeVO myLikeVO = new MyLikeVO();
		
		UserVO userVO = new UserVO();
		userVO.setUserID(userID);
		myLikeVO.setUser(userVO);
		
		ActVO actVO = new ActVO();
		actVO.setActID(actID);
		myLikeVO.setAct(actVO);

		myLikeDao.update(myLikeVO);

		return myLikeVO;
	}
	
	public void deleteMyLike(Integer myLikeID) {
		myLikeDao.delete(myLikeID);
	}
	
	
	public MyLikeVO getOneMyLike(Integer myLikeID) {
		return myLikeDao.findByPrimaryKey(myLikeID);
	}

	
	public List<MyLikeVO> getAll() {
		return myLikeDao.getAll();
	}
}
