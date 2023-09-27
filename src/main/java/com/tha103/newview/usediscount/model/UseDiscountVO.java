package com.tha103.newview.usediscount.model;

import java.io.Serializable;

public class UseDiscountVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer useDisID;
	private Integer discountNO;
	private Integer userID;
	private Integer ditUsed;
	
	public Integer getUseDisID() {
		return useDisID;
	}
	public void setUseDisID(Integer useDisID) {
		this.useDisID = useDisID;
	}
	public Integer getDiscountNO() {
		return discountNO;
	}
	public void setDiscountNO(Integer discountNO) {
		this.discountNO = discountNO;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getDitUsed() {
		return ditUsed;
	}
	public void setDitUsed(Integer ditUsed) {
		this.ditUsed = ditUsed;
	}
	public UseDiscountVO(Integer useDisID, Integer discountNO, Integer userID, Integer ditUsed) {
		super();
		this.useDisID = useDisID;
		this.discountNO = discountNO;
		this.userID = userID;
		this.ditUsed = ditUsed;
	}
	public UseDiscountVO() {
		super();
	}
	@Override
	public String toString() {
		return "useDiscountVO [useDisID=" + useDisID + ", discountNO=" + discountNO + ", userID=" + userID
				+ ", ditUsed=" + ditUsed + "]";
	}
	
	
	

}
