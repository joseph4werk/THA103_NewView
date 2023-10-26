package com.tha103.newview.user.dto;

public class OrderDTO {
	String publisher;
	
	String activity;
	
	Integer actPicID;
	
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Integer getActPicID() {
		return actPicID;
	}
	public void setActPicID(Integer actPicID) {
		this.actPicID = actPicID;
	}
	public OrderDTO(String publisher, String activity, Integer actPicID) {
		super();
		this.publisher = publisher;
		this.activity = activity;
		this.actPicID = actPicID;
	}
	@Override
	public String toString() {
		return "OrderDTO [publisher=" + publisher + ", activity=" + activity + ", actPicID=" + actPicID + "]";
	}
	

}
