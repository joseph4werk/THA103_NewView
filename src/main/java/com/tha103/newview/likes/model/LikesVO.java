package com.tha103.newview.likes.model;

import java.io.Serializable;

public class LikesVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer likeID;
	private Integer postID;
	private Integer userID;
	private Integer likeOrNot;
	
	public LikesVO() {
		super();
	}
	
	public LikesVO(Integer likeID, Integer postID, Integer userID, Integer likeOrNot) {
		super();
		this.likeID = likeID;
		this.postID = postID;
		this.userID = userID;
		this.likeOrNot = likeOrNot;
	}
	
	public Integer getLikeID() {
		return likeID;
	}
	public void setLikeID(Integer likeID) {
		this.likeID = likeID;
	}
	public Integer getPostID() {
		return postID;
	}
	public void setPostID(Integer postID) {
		this.postID = postID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getLikeOrNot() {
		return likeOrNot;
	}
	public void setLikeOrNot(Integer likeOrNot) {
		this.likeOrNot = likeOrNot;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "LikeVO [likeID=" + likeID + ", postID=" + postID + ", userID=" + userID + ", likeOrNot=" + likeOrNot
				+ "]";
	}
	
}
