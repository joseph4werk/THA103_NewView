package com.tha103.newview.pubuser.model;

import java.util.List;
import java.util.Map;

public interface PubUserHibernateDAO {
	
	int add(PubUser pubUser);
	
	int update(PubUser pubUser);
	
	int delete(Integer pubUserID);
	
	PubUser findByPK(Integer pubUserID);
	
	List<PubUser> getAll();
	
	/*
	List<PubUser> getByCompositeQuery(Map<String,String>map);

	List<PubUser> getAll(int cueerntPage);

	long getTotal();
	*/
	
}
