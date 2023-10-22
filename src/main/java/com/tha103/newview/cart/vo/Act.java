package com.tha103.newview.cart.vo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Act {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer actID;
	private String actName;
	private Integer actPrice;
	private Date actTime;
	private Integer actScope;
	private String actIntroduce;
	private String actContent;
	private Date time;
	private Date actDate;
	private Boolean approvalCondition;
	private String cityAddress;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "actID", referencedColumnName = "actID")
	private List<ActPic> actPics;
	
	@Transient
	private String seatsStr;
	
	public Act() {
	}

	public Act(Integer actID, String actName, Integer actPrice, Date actTime, Integer actScope, String actIntroduce,
			String actContent, Date time, Date actDate, Boolean approvalCondition, String cityAddress,
			List<ActPic> actPics, String seatsStr) {
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
		this.actPics = actPics;
		this.seatsStr = seatsStr;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Date getActDate() {
		return actDate;
	}

	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	public Boolean getApprovalCondition() {
		return approvalCondition;
	}

	public void setApprovalCondition(Boolean approvalCondition) {
		this.approvalCondition = approvalCondition;
	}

	public String getCityAddress() {
		return cityAddress;
	}

	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}

	public List<ActPic> getActPics() {
		return actPics;
	}

	public void setActPics(List<ActPic> actPics) {
		this.actPics = actPics;
	}

	public String getSeatsStr() {
		return seatsStr;
	}

	public void setSeatsStr(String seatsStr) {
		this.seatsStr = seatsStr;
	}

}
