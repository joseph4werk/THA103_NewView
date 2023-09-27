package com.tha103.newview.postmessage.model;

import java.sql.Date;
import java.sql.Timestamp;

public class PostMessageVO implements java.io.Serializable {
	private Integer postMessageID;
	private Integer postID;
	private Integer userID;
	private String mesContent;
	private Timestamp messageDate;

	public PostMessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostMessageVO(Integer postMessageID, Integer postID, Integer userID, String mesContent,Timestamp messageDate) {
		super();
		this.postMessageID = postMessageID;
		this.postID = postID;
		this.userID = userID;
		this.mesContent = mesContent;
		this.messageDate = messageDate;
	}

	public Integer getPostMessageID() {
		return postMessageID;
	}

	public void setPostMessageID(Integer postMessageID) {
		this.postMessageID = postMessageID;
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

	public String getMesContent() {
		return mesContent;
	}

	public void setMesContent(String mesContent) {
		this.mesContent = mesContent;
	}

	public Timestamp getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}

	@Override
	public String toString() {
		return "PostMessageVO [postMessageID=" + postMessageID + ", postID=" + postID + ", userID=" + userID
				+ ", mesContent=" + mesContent + ", messageDate=" + messageDate + "]";
	}

}
