package com.tha103.newview.postpic.model;

import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postpic")
public class PostPicVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer postPicID;
	
	@Column(name = "postID")
	private Integer postID;
	
	@Column(name = "postpic", columnDefinition = "longblob")
	private byte[] postPic;
	
	
	public PostPicVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PostPicVO(Integer postPicID, Integer postID, byte[] postPic) {
		super();
		this.postPicID = postPicID;
		this.postID = postID;
		this.postPic = postPic;
	}

	public Integer getPostPicID() {
		return postPicID;
	}
	public void setPostPicID(Integer postPicID) {
		this.postPicID = postPicID;
	}
	public Integer getPostID() {
		return postID;
	}
	public void setPostID(Integer postID) {
		this.postID = postID;
	}
	public byte[] getPostPic() {
		return postPic;
	}
	public void setPostPic(byte[] postPic) {
		this.postPic = postPic;
	}
	
	
	@Override
	public String toString() {
		return "PostPicVO [postPicID=" + postPicID + ", postID=" + postID + ", postPic=" + Arrays.toString(postPic)
				+ "]";
	}
	
	
	

}
