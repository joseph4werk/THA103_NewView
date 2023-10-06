package com.tha103.newview.publisher.model;

import java.util.List;

public interface PublisherHibernateDAO {
	int add(Publisher publisher);
	int update(Publisher publisher);
	int delete(Integer pubID);
	Publisher findByPK(Integer pubID);
	List<Publisher> getAll();
	
}
