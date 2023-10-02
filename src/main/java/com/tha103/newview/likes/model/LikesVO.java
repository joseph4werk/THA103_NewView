package com.tha103.newview.likes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
//配合 HQL
//@NamedQuery(name = "")
public class LikesVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "likeID")
	private Integer likeID;
	
	@Column(name = "postID")
	private Integer postID;
	
	@Column(name = "userID")
	private Integer userID;
	
	@Column(name = "likeOrNot", columnDefinition = "TINYINT")
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

	@Override
	public String toString() {
		return "LikeVO [likeID=" + likeID + ", postID=" + postID + ", userID=" + userID + ", likeOrNot=" + likeOrNot
				+ "]";
	}

}
