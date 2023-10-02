package com.tha103.newview.post.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class PostVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;

	@Column(name = "userID")
	private Integer userID;

	@Column(name = "postCategoryID")
	private Integer postCategoryID;

	@Column(name = "postHeader")
	private String postHeader;

	@Column(name = "postDateTime")
	private Timestamp postDateTime;

	@Column(name = "lastEditedTime")
	private Timestamp lastEditedTime;

	@Column(name = "postContent")
	private String postContent;

	@Column(name = "disLikeCount")
	private Integer disLikeCount;

	@Column(name = "likeCount")
	private Integer likeCount;

	@Column(name = "postStatus", columnDefinition = "tinyint")
	private Integer postStatus;

	public PostVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostVO(Integer postID, Integer userID, Integer postCategoryID, String postHeader, Timestamp postDateTime,
			Timestamp lastEditedTime, String postContent, Integer disLikeCount, Integer likeCount, Integer postStatus) {
		super();
		this.postID = postID;
		this.userID = userID;
		this.postCategoryID = postCategoryID;
		this.postHeader = postHeader;
		this.postDateTime = postDateTime;
		this.lastEditedTime = lastEditedTime;
		this.postContent = postContent;
		this.disLikeCount = disLikeCount;
		this.likeCount = likeCount;
		this.postStatus = postStatus;
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

	public Integer getPostCategoryID() {
		return postCategoryID;
	}

	public void setPostCategoryID(Integer postCategoryID) {
		this.postCategoryID = postCategoryID;
	}

	public String getPostHeader() {
		return postHeader;
	}

	public void setPostHeader(String postHeader) {
		this.postHeader = postHeader;
	}

	public Timestamp getPostDateTime() {
		return postDateTime;
	}

	public void setPostDateTime(Timestamp postDateTime) {
		this.postDateTime = postDateTime;
	}

	public Timestamp getLastEditedTime() {
		return lastEditedTime;
	}

	public void setLastEditedTime(Timestamp lastEditedTime) {
		this.lastEditedTime = lastEditedTime;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public Integer getDisLikeCount() {
		return disLikeCount;
	}

	public void setDisLikeCount(Integer disLikeCount) {
		this.disLikeCount = disLikeCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(Integer postStatus) {
		this.postStatus = postStatus;
	}

	@Override
	public String toString() {
		return "PostVO [postID=" + postID + ", userID=" + userID + ", postCategoryID=" + postCategoryID
				+ ", postHeader=" + postHeader + ", postDateTime=" + postDateTime + ", lastEditedTime=" + lastEditedTime
				+ ", postContent=" + postContent + ", disLikeCount=" + disLikeCount + ", likeCount=" + likeCount
				+ ", postStatus=" + postStatus + "]";
	}

}