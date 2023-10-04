package com.tha103.newview.report.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.tha103.newview.post.model.PostVO;
import com.tha103.newview.user.model.UserVO;

@Entity
@Table(name = "report")
//配合 HQL
//@NamedQuery(name = "")
public class ReportVO {
	@Expose
	@Id
	@Column(name = "reportID")
	private Integer reportID;

	@Expose
	@Column(name = "userID")
	private Integer userID;

	@Expose
	@Column(name = "postID")
	private Integer postID;

	@Expose
	@Column(name = "reportContent")
	private String reportContent;

	@Expose
	@Column(name = "reportStatus", columnDefinition = "TINYINT")
	private Integer reportStatus;

	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private UserVO userVO;

	@ManyToOne
	@JoinColumn(name = "postID", referencedColumnName = "postID")
	private PostVO postVO;

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

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public PostVO getPostVO() {
		return postVO;
	}

	public void setPostVO(PostVO postVO) {
		this.postVO = postVO;
	}

	public ReportVO(Integer reportID, Integer userID, Integer postID, String reportContent, Integer reportStatus,
			UserVO userVO, PostVO postVO) {
		super();
		this.reportID = reportID;
		this.userID = userID;
		this.postID = postID;
		this.reportContent = reportContent;
		this.reportStatus = reportStatus;
		this.userVO = userVO;
		this.postVO = postVO;
	}

	public ReportVO() {
		super();
	}

	@Override
	public String toString() {
		return "ReportVO [reportID=" + reportID + ", userID=" + userID + ", postID=" + postID + ", reportContent="
				+ reportContent + ", reportStatus=" + reportStatus + ", userVO=" + userVO + ", postVO=" + postVO + "]";
	}

}
