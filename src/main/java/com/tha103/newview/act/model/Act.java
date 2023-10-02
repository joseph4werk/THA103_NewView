package com.tha103.newview.act.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tha103.newview.actcategory.model.ActCategory;
import com.tha103.newview.actpic.model.ActPic;

@Entity
@Table(name = "act")
// 配合 TestHQLWithParameter.java
@NamedQuery(name = "getAllActs", query = "from Act where ActID > :ActID order by ActID desc")
public class Act {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actID", updatable = false)
	private Integer actID;

	@Column(name = "actName")
	private String actName;

	@Column(name = "actPrice")
	private Integer actPrice;

//	@Temporal 
//	1.用來指定型態為java.util.Date, java.util.Calendar的mapping.
//	2.TemporalType, 有DATE(mapping到java.sql.Date)、TIME(mapping到java.sql.Time)、TIMESTAMP(mapping到java.sql.Timestamp)(預設值)
//	3.如果型態本來就是java.sql.Date, java.sql.Time, java.sql.Timestamp, 就不用@Temporal 
//	4.這範例有 import java.util.Date; --> 是故意用 java.util.Date 做展示，所以一定要加 @Temporal
	@Temporal(TemporalType.DATE)
	@Column(name = "actTime")
	private Date actTime;

	@Column(name = "actScope")
	private Integer actScope;

	@Column(name = "actIntroduce")
	private String actIntroduce;

	@Column(columnDefinition = "text")
	private String actContent;

	@Temporal(TemporalType.TIME)
	@Column(name = "time")
	private Date time;

	@Temporal(TemporalType.DATE)
	@Column(name = "actDate")
	private Date actDate;

	// 搭配TestHQLQueryProperty.java

	@Column(columnDefinition = "TINYINT(1)")
	private boolean approvalCondition;

	@Column(name = "cityAddress")
	private String cityAddress;

	
	@JoinColumn(name = "actCategoryID")
	private Integer actCategoryID;
	
	@Column(name = "pubID")
	private Integer pubID;

	@Column(name = "cityAddressID")
	private Integer cityAddressID;

	
	
	@OneToMany(mappedBy = "act")
    private List<ActPic> actPics;
//以上為資料設定  以下為方法

	@Override
	public String toString() {
		return "Act [actID=" + actID + ", actName=" + actName + ", actPrice=" + actPrice + ", actTime=" + actTime
				+ ", actScope=" + actScope + ", actIntroduce=" + actIntroduce + ", actContent=" + actContent + ", time="
				+ time + ", actDate=" + actDate + ", approvalCondition=" + approvalCondition + ", cityAddress="
				+ cityAddress + ", actCategoryID=" + actCategoryID + ", pubID=" + pubID + ", cityAddressID="
				+ cityAddressID + "]";
	}

	public Act() {
	}

	public Act(Integer actID, String actName, Integer actPrice, Date actTime, Integer actScope, String actIntroduce,
			String actContent, Date time, Date actDate, Boolean approvalCondition, String cityAddress,
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

	public Integer getActID() {
		return actID;
	}

	public String getActName() {
		return actName;
	}

	public Integer getActPrice() {
		return actPrice;
	}

	public Date getActTime() {
		return actTime;
	}

	public Integer getActScope() {
		return actScope;
	}

	public String getActIntroduce() {
		return actIntroduce;
	}

	public String getActContent() {
		return actContent;
	}

	public Date getTime() {
		return time;
	}

	public Date getActDate() {
		return actDate;
	}

	public Boolean getApprovalCondition() {
		return approvalCondition;
	}

	public String getCityAddress() {
		return cityAddress;
	}

	public Integer getActCategoryID() {
		return actCategoryID;
	}

	public Integer getPubID() {
		return pubID;
	}

	public Integer getCityAddressID() {
		return cityAddressID;
	}

	public void setActID(Integer actID) {
		this.actID = actID;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public void setActPrice(Integer actPrice) {
		this.actPrice = actPrice;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	public void setActScope(Integer actScope) {
		this.actScope = actScope;
	}

	public void setActIntroduce(String actIntroduce) {
		this.actIntroduce = actIntroduce;
	}

	public void setActContent(String actContent) {
		this.actContent = actContent;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	public void setApprovalCondition(Boolean approvalCondition) {
		this.approvalCondition = approvalCondition;
	}

	public void setCityAddress(String cityAddress) {
		this.cityAddress = cityAddress;
	}

	public void setActCategoryID(Integer actCategoryID) {
		this.actCategoryID = actCategoryID;
	}

	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}

	public void setCityAddressID(Integer cityAddressID) {
		this.cityAddressID = cityAddressID;
	}

}
