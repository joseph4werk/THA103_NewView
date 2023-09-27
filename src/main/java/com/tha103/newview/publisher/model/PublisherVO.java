package com.tha103.newview.publisher.model;

public class PublisherVO {
	private Integer pubID;
	private String pubName;
	private String pubEmail;
	
	
	
	public PublisherVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PublisherVO(Integer pubID, String pubName, String pubEmail) {
		super();
		this.pubID = pubID;
		this.pubName = pubName;
		this.pubEmail = pubEmail;
	}


	public Integer getPubID() {
		return pubID;
	}
	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}
	public String getPubName() {
		return pubName;
	}
	public void setPubName(String pubName) {
		this.pubName = pubName;
	}
	public String getPubEmail() {
		return pubEmail;
	}
	public void setPubEmail(String pubEmail) {
		this.pubEmail = pubEmail;
	}


	@Override
	public String toString() {
		return "PublisherVO [pubID=" + pubID + ", pubName=" + pubName + ", pubEmail=" + pubEmail + "]";
	}
	
	
}
