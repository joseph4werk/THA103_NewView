package com.tha103.newview.pubuser.model;

import java.util.List;

public interface PubUserHibernateDAO {
	int add(PubUser pubUser);
	int update(PubUser pubUser);
	int delete(Integer pubUserID);
	PubUser finkByPK(Integer pubUserID);
	List<PubUser> getAll();
	
}
