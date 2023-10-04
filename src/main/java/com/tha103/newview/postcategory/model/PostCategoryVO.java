package com.tha103.newview.postcategory.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "postcategory")
public class PostCategoryVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer postCategoryID;
	
	@Column(name = "postCategoryName")
	private String postCategoryName;
	
	@OneToMany(mappedBy = "postCategory" , cascade = CascadeType.ALL)
	private Set<PostCategoryVO> post;
	
	public PostCategoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Set<PostCategoryVO> getPost() {
		return post;
	}

	public void setPost(Set<PostCategoryVO> post) {
		this.post = post;
	}

	public PostCategoryVO(Integer postCategoryID, String postCategoryName) {
		super();
		this.postCategoryID = postCategoryID;
		this.postCategoryName = postCategoryName;
	}
	

	public Integer getPostCategoryID() {
		return postCategoryID;
	}
	public void setPostCategoryID(Integer postCategoryID) {
		this.postCategoryID = postCategoryID;
	}
	public String getPostCategoryName() {
		return postCategoryName;
	}
	public void setPostCategoryName(String postCategoryName) {
		this.postCategoryName = postCategoryName;
	}

	@Override
	public String toString() {
		return "PostCategoryVO [postCategoryID=" + postCategoryID + ", postCategoryName=" + postCategoryName + "]";
	}
	
	
}
