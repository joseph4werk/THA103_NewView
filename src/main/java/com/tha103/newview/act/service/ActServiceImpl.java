package com.tha103.newview.act.service;

import java.util.List;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.act.model.ActDAO;
import com.tha103.newview.act.model.ActDAOHibernateImpl;
import com.tha103.util.HibernateUtil;

public class ActServiceImpl implements ActService{
	
	private ActDAO dao;
	
	public ActServiceImpl() {
		dao = new ActDAOHibernateImpl(HibernateUtil.getSessionFactory());
	}
	
	@Override
	public void insert(ActVO act) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ActVO act) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer ActID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ActVO findByPrimaryKey(Integer ActID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActVO> getAll() {
		// TODO Auto-generated method stub
		
		return dao.getAll();
	}
	
	
}
