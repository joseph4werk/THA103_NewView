package com.tha103.newview.act.model;

import java.sql.Date;
import java.sql.Time;


public class ActVO implements java.io.Serializable{
	private Integer actID;  //PK
	private String actName;
	private Integer actPrice;
	private Date actTime;
	private Integer actScope;
	private String actIntroduce;
	private String actContent;
	private Time time;
	private Date actDate;
	private Integer approvalCondition;
	private String cityAddress;
	private Integer actCategoryID;
	private Integer pubID;
	private Integer cityAddressID;
	
	
	
	public ActVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ActVO(Integer actID, String actName, Integer actPrice, Date actTime, Integer actScope, String actIntroduce,
			String actContent, Time time, Date actDate, Integer approvalCondition, String cityAddress,
			Integer actCategoryID, Integer pubID, Integer cityAddressID) {
		super();
		this.actID = actID;
		this.actName = actName;
		this.actPrice = actPrice;
		this.actTime = actTime;
		this.actScope = actScope;
		this.actIntroduce = actIntroduce;
		this.actContent = actContent;
		this.time = time;
		this.actDate = actDate;
		this.approvalCondition = approvalCondition;
		this.cityAddress = cityAddress;
		this.actCategoryID = actCategoryID;
		this.pubID = pubID;
		this.cityAddressID = cityAddressID;
	}


	public Integer getCityAddressID() {
		return cityAddressID;
	}
	public void setCityAddressID(Integer cityAddressID) {
		this.cityAddressID = cityAddressID;
	}
	public Integer getPubID() {
		return pubID;
	}
	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}
	public Integer getActCategoryID() {
		return actCategoryID;
	}
	public void setActCategoryID(Integer actCategoryID) {
		this.actCategoryID = actCategoryID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public Integer getActPrice() {
		return actPrice;
	}
	public void setActPrice(Integer actPrice) {
		this.actPrice = actPrice;
	}
	public Date getActTime() {
		return actTime;
	}
	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}
	public Integer getActScope() {
		return actScope;
	}
	public void setActScope(Integer actScope) {
		this.actScope = actScope;
	}
	public String getActIntroduce() {
		return actIntroduce;
	}
	public void setActIntroduce(String actIntroduce) {
		this.actIntroduce = actIntroduce;
	}
	public String getActContent() {
		return actContent;
	}
	public void setActContent(String actContent) {
		this.actContent = actContent;
	}
	
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Date getActDate() {
		return actDate;
	}
	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}
	public Integer getApprovalCondition() {
		return approvalCondition;
	}
	public void setApprovalCondition(Integer approvalCondition) {
		this.approvalCondition = approvalCondition;
	}
	public String getCityAddress() {
		return cityAddress;
	}
	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}


	@Override
	public String toString() {
		return "ActVO [actID=" + actID + ", actName=" + actName + ", actPrice=" + actPrice + ", actTime=" + actTime
				+ ", actScope=" + actScope + ", actIntroduce=" + actIntroduce + ", actContent=" + actContent + ", time="
				+ time + ", actDate=" + actDate + ", approvalCondition=" + approvalCondition + ", cityAddress="
				+ cityAddress + ", actCategoryID=" + actCategoryID + ", pubID=" + pubID + ", cityAddressID="
				+ cityAddressID + "]";
	}
	
	
	
	
	
}
