package com.tha103.newview.discount.model;

import java.sql.Date;

public class DiscountVO implements java.io.Serializable{
	
	private Integer discountNO;
	private Integer pubID;
	private Integer adminID;
	private String discountContent;
	private Integer disAmount;
	private String discountCode;
	private Date disStartDate;
	private Date disFinishDate;
	
	public DiscountVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DiscountVO(Integer discountNO, Integer pubID, Integer adminID, String discountContent, Integer disAmount,
			String discountCode, Date disStartDate, Date disFinishDate) {
		super();
		this.discountNO = discountNO;
		this.pubID = pubID;
		this.adminID = adminID;
		this.discountContent = discountContent;
		this.disAmount = disAmount;
		this.discountCode = discountCode;
		this.disStartDate = disStartDate;
		this.disFinishDate = disFinishDate;
	}


	public Integer getDiscountNO() {
		return discountNO;
	}
	public void setDiscountNO(Integer discountNO) {
		this.discountNO = discountNO;
	}
	public Integer getPubID() {
		return pubID;
	}
	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}
	public Integer getAdminID() {
		return adminID;
	}
	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}
	public String getDiscountContent() {
		return discountContent;
	}
	public void setDiscountContent(String discountContent) {
		this.discountContent = discountContent;
	}
	public Integer getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(Integer disAmount) {
		this.disAmount = disAmount;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public Date getDisStartDate() {
		return disStartDate;
	}
	public void setDisStartDate(Date disStartDate) {
		this.disStartDate = disStartDate;
	}
	public Date getDisFinishDate() {
		return disFinishDate;
	}
	public void setDisFinishDate(Date disFinishDate) {
		this.disFinishDate = disFinishDate;
	}

	@Override
	public String toString() {
		return "DiscountVO [discountNO=" + discountNO + ", pubID=" + pubID + ", adminID=" + adminID
				+ ", discountContent=" + discountContent + ", disAmount=" + disAmount + ", discountCode=" + discountCode
				+ ", disStartDate=" + disStartDate + ", disFinishDate=" + disFinishDate + "]";
	}
	
	
	
}
