package com.tha103.newview.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report")
//配合 HQL
//@NamedQuery(name = "")
public class ReportVO{
	@Id
	@Column(name = "reportID")
	private Integer reportID;
	
	@Column(name = "userID")
	private Integer userID;
	
	@Column(name = "postID")
	private Integer postID;
	
	@Column(name = "reportContent")
	private String reportContent;
	
	@Column(name = "reportStatus", columnDefinition = "TINYINT")
	private Integer reportStatus;
	
	public Integer getReportID() {
		return reportID;
	}
	public void setReportID(Integer reportID) {
		this.reportID = reportID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getPostID() {
		return postID;
	}
	public void setPostID(Integer postID) {
		this.postID = postID;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public Integer getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public ReportVO(Integer reportID, Integer userID, Integer postID, String reportContent, Integer reportStatus) {
		super();
		this.reportID = reportID;
		this.userID = userID;
		this.postID = postID;
		this.reportContent = reportContent;
		this.reportStatus = reportStatus;
	}
	public ReportVO() {
		super();
	}
	@Override
	public String toString() {
		return "ReportVO [reportID=" + reportID + ", userID=" + userID + ", postID=" + postID + ", reportContent="
				+ reportContent + ", reportStatus=" + reportStatus + "]";
	}
	
	
	

}
