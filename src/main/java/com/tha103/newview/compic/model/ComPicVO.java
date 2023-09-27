package com.tha103.newview.compic.model;

import java.util.Arrays;

public class ComPicVO implements java.io.Serializable{
	private Integer comPicID;
	private Integer orderListID;
	private byte[] comPic;
	

	public ComPicVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ComPicVO(Integer comPicID, Integer orderListID, byte[] comPic) {
		super();
		this.comPicID = comPicID;
		this.orderListID = orderListID;
		this.comPic = comPic;
	}


	public Integer getOrderListID() {
		return orderListID;
	}
	public void setOrderListID(Integer orderListID) {
		this.orderListID = orderListID;
	}
	public Integer getComPicID() {
		return comPicID;
	}
	public void setComPicID(Integer comPicID) {
		this.comPicID = comPicID;
	}
	public byte[] getComPic() {
		return comPic;
	}
	public void setComPic(byte[] comPic) {
		this.comPic = comPic;
	}


	@Override
	public String toString() {
		return "ComPicVO [comPicID=" + comPicID + ", orderListID=" + orderListID + ", comPic=" + Arrays.toString(comPic)
				+ "]";
	}
	
	
	
}
