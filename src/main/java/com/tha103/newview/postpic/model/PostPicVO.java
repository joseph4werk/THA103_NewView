package com.tha103.newview.postpic.model;

import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tha103.newview.post.model.PostVO;

@Entity
@Table(name = "postpic")
public class PostPicVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer postPicID;
	
	@ManyToOne
	@JoinColumn(name = "postID" , referencedColumnName ="postID")
	private PostVO post;
	
	@Column(name = "postpic", columnDefinition = "longblob")
	private byte[] postPic;
	
	
	public PostPicVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PostPicVO(Integer postPicID, byte[] postPic) {
		super();
		this.postPicID = postPicID;
		this.postPic = postPic;
	}

	public Integer getPostPicID() {
		return postPicID;
	}
	public void setPostPicID(Integer postPicID) {
		this.postPicID = postPicID;
	}

	
	public PostVO getPost() {
		return post;
	}

	public void setPost(PostVO post) {
		this.post = post;
	}

	public byte[] getPostPic() {
		return postPic;
	}
	public void setPostPic(byte[] postPic) {
		this.postPic = postPic;
	}
	
	
	@Override
	public String toString() {
		return "PostPicVO [postPicID=" + postPicID + ", postVO=" + post + ", postPic=" + Arrays.toString(postPic)
				+ "]";
	}
	
	
	

}
