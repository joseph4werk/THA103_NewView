package com.tha103.newview.mylike.model;

public class MyLikeVO implements java.io.Serializable{
//	private static final long serialVersionUID = 1L;
	private Integer myLikeID;
	private Integer userID;
	private Integer actID;
	
	public MyLikeVO() {
		super();
	}
	
	public MyLikeVO(Integer myLikeID, Integer userID, Integer actID) {
		super();
		this.myLikeID = myLikeID;
		this.userID = userID;
		this.actID = actID;
	}
	
	
	public Integer getMyLikeID() {
		return myLikeID;
	}
	public void setMyLikeID(Integer myLikeID) {
		this.myLikeID = myLikeID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	

	
	@Override
	public String toString() {
		return "MyLikeVO [myLIkeID=" + myLikeID + ", userID=" + userID + ", actID=" + actID+ "]";
	}
	
	
}