package com.tha103.newview.user.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.tha103.newview.likes.model.LikesVO;
import com.tha103.newview.report.model.ReportVO;
import com.tha103.newview.usediscount.model.UseDiscountVO;

@Entity
@Table(name = "user")
//配合 HQL
//@NamedQuery(name = "")
public class UserVO {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	private Integer userID;

	@Expose
	@Column(name = "userName")
	private String userName;

	@Expose
	@Column(name = "userAccount")
	private String userAccount;

	@Expose
	@Column(name = "userPassword")
	private String userPassword;

	@Expose
	@Column(name = "userBirth")
	private Date userBirth;

	@Expose
	@Column(name = "userCell")
	private String userCell;

	@Expose
	@Column(name = "userEmail")
	private String userEmail;

	@Expose
	@Column(name = "userNickname")
	private String userNickname;

	@Expose
	@Column(name = "buyAuthority", columnDefinition = "tinyint")
	private Integer buyAuthority; // 0/1 = 啟用/未啟用

	@Expose
	@Column(name = "speakAuthority", columnDefinition = "tinyint")
	private Integer speakAuthority; // 0/1 = 啟用/未啟用

	@Expose
	@OneToMany(mappedBy = "userVO", cascade = CascadeType.ALL)
	private Set<UseDiscountVO> useDiscountVOs;

	@Expose
	@OneToMany(mappedBy = "userVO", cascade = CascadeType.ALL)
	private Set<ReportVO> reportVOs;

	@Expose
	@OneToMany(mappedBy = "userVO", cascade = CascadeType.ALL)
	private Set<LikesVO> likesVOs;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(Date userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserCell() {
		return userCell;
	}

	public void setUserCell(String userCell) {
		this.userCell = userCell;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public Integer getBuyAuthority() {
		return buyAuthority;
	}

	public void setBuyAuthority(Integer buyAuthority) {
		this.buyAuthority = buyAuthority;
	}

	public Integer getSpeakAuthority() {
		return speakAuthority;
	}

	public void setSpeakAuthority(Integer speakAuthority) {
		this.speakAuthority = speakAuthority;
	}

	public Set<UseDiscountVO> getUseDiscountVOs() {
		return useDiscountVOs;
	}

	public void setUseDiscountVOs(Set<UseDiscountVO> useDiscountVOs) {
		this.useDiscountVOs = useDiscountVOs;
	}

	public Set<ReportVO> getReportVOs() {
		return reportVOs;
	}

	public void setReportVOs(Set<ReportVO> reportVOs) {
		this.reportVOs = reportVOs;
	}

	public Set<LikesVO> getLikesVOs() {
		return likesVOs;
	}

	public void setLikesVOs(Set<LikesVO> likesVOs) {
		this.likesVOs = likesVOs;
	}

	public UserVO(Integer userID, String userName, String userAccount, String userPassword, Date userBirth,
			String userCell, String userEmail, String userNickname, Integer buyAuthority, Integer speakAuthority,
			Set<UseDiscountVO> useDiscountVOs, Set<ReportVO> reportVOs, Set<LikesVO> likesVOs) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userAccount = userAccount;
		this.userPassword = userPassword;
		this.userBirth = userBirth;
		this.userCell = userCell;
		this.userEmail = userEmail;
		this.userNickname = userNickname;
		this.buyAuthority = buyAuthority;
		this.speakAuthority = speakAuthority;
		this.useDiscountVOs = useDiscountVOs;
		this.reportVOs = reportVOs;
		this.likesVOs = likesVOs;
	}

	public UserVO() {
		super();
	}

	@Override
	public String toString() {
		return "UserVO [userID=" + userID + ", userName=" + userName + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", userBirth=" + userBirth + ", userCell=" + userCell
				+ ", userEmail=" + userEmail + ", userNickname=" + userNickname + ", buyAuthority=" + buyAuthority
				+ ", speakAuthority=" + speakAuthority + ", useDiscountVOs=" + useDiscountVOs + ", reportVOs="
				+ reportVOs + ", likesVOs=" + likesVOs + "]";
	}

//	public UserVO(String userAccount) {
//		super();
//		this.userAccount = userAccount;
//	}

}
