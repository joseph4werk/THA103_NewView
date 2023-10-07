package com.tha103.newview.publisher.model;

import java.util.List;

public interface PublisherHibernateDAO {
	int add(PublisherVO publisher);
	int update(PublisherVO publisher);
	int delete(Integer pubID);
	PublisherVO findByPK(Integer pubID);
	List<PublisherVO> getAll();
	
}
