package com.tha103.newview.publisher.model;

import java.util.List;

public interface PublisherDAO_Interface {
	public void insert(PublisherVO publisherVO);
	public void update(PublisherVO publisherVO);
	public void delete(Integer pubID);
	PublisherVO findByPK(Integer pubID);
	List<PublisherVO> getAll();
}
