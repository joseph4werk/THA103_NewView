package com.tha103.newview.actpic.model;

import java.util.Arrays;

public class ActPicVO implements java.io.Serializable{
	
	private Integer actPicID;
	private Integer actID; 
	private byte[] actPic;
	
	
	
	public ActPicVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ActPicVO(Integer actPicID, Integer actID, byte[] actPic) {
		super();
		this.actPicID = actPicID;
		this.actID = actID;
		this.actPic = actPic;
	}



	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Integer getActPicID() {
		return actPicID;
	}
	public void setActPicID(Integer actPicID) {
		this.actPicID = actPicID;
	}
	public byte[] getActPic() {
		return actPic;
	}
	public void setActPic(byte[] actPic) {
		this.actPic = actPic;
	}



	@Override
	public String toString() {
		return "ActPicVO [actPicID=" + actPicID + ", actID=" + actID + ", actPic=" + Arrays.toString(actPic) + "]";
	}
	
	

}
