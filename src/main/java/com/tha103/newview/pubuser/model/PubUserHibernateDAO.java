package com.tha103.newview.pubuser.model;

import java.util.List;
import java.util.Map;

public interface PubUserHibernateDAO {
	
	int add(PubUserVO pubUser);
	
	int update(PubUserVO pubUser);
	
	int delete(Integer pubUserID);
	
	PubUserVO findByPK(Integer pubUserID);
	
	List<PubUserVO> getAll();
	
	/*
	List<PubUser> getByCompositeQuery(Map<String,String>map);

	List<PubUser> getAll(int cueerntPage);

	long getTotal();
	*/
	
}
