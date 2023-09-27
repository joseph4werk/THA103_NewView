package com.tha103.newview.postcategory.model;

public class PostCategoryVO implements java.io.Serializable{
	
	private Integer postCategoryID;
	private String postCategoryName;
	
	public PostCategoryVO() {
		super();
		// TODO Auto-generated constructor stub
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
