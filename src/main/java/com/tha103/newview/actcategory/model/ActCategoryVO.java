package com.tha103.newview.actcategory.model;

public class ActCategoryVO implements java.io.Serializable{

	private Integer actCategoryID;
	private String actCategoryName;
	
	
	public ActCategoryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ActCategoryVO(Integer actCategoryID, String actCategoryName) {
		super();
		this.actCategoryID = actCategoryID;
		this.actCategoryName = actCategoryName;
	}


	public Integer getActCategoryID() {
		return actCategoryID;
	}
	public void setActCategoryID(Integer actCategoryID) {
		this.actCategoryID = actCategoryID;
	}
	public String getActCategoryName() {
		return actCategoryName;
	}
	public void setActCategoryName(String actCategoryName) {
		this.actCategoryName = actCategoryName;
	}


	@Override
	public String toString() {
		return "ActCategoryVO [actCategoryID=" + actCategoryID + ", actCategoryName=" + actCategoryName + "]";
	}
	
	
	
}
