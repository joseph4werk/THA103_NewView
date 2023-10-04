package com.tha103.newview.postmessage.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tha103.newview.post.model.PostVO;
import com.tha103.newview.user.model.UserVO;

@Entity
@Table(name = "postmessage")
public class PostMessageVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postMessageID;

	@ManyToOne
	@JoinColumn(name = "postID", referencedColumnName = "postID")
	private PostVO post;

	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private UserVO user;

	@Column(name = "mesContent")
	private String mesContent;

	@Column(name = "messageDate")
	private Timestamp messageDate;

	public PostMessageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostMessageVO(Integer postMessageID,  String mesContent,
			Timestamp messageDate) {
		super();
		this.postMessageID = postMessageID;
		this.mesContent = mesContent;
		this.messageDate = messageDate;
	}

	public Integer getPostMessageID() {
		return postMessageID;
	}

	public void setPostMessageID(Integer postMessageID) {
		this.postMessageID = postMessageID;
	}


	public PostVO getPost() {
		return post;
	}

	public void setPost(PostVO post) {
		this.post = post;
	}

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
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
		return "PostMessageVO [postMessageID=" + postMessageID + ", postVO=" + post + ", userVO=" + user
				+ ", mesContent=" + mesContent + ", messageDate=" + messageDate + "]";
	}

}
