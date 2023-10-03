package com.tha103.newview.user.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
//配合 HQL
//@NamedQuery(name = "")
public class UserVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userID")
	private Integer userID;

	@Column(name = "userName")
	private String userName;

	@Column(name = "userAccount")
	private String userAccount;

	@Column(name = "userPassword")
	private String userPassword;

	@Column(name = "userBirth")
	private Date userBirth;

	@Column(name = "userCell")
	private String userCell;

	@Column(name = "userEmail")
	private String userEmail;

	@Column(name = "userNickname")
	private String userNickname;

	@Column(name = "buyAuthority", columnDefinition = "tinyint")
	private Integer buyAuthority; // 0/1 = 啟用/未啟用

	@Column(name = "speakAuthority", columnDefinition = "tinyint")
	private Integer speakAuthority; // 0/1 = 啟用/未啟用

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

	public UserVO(Integer userID, String userName, String userAccount, String userPassword, Date userBirth,
			String userCell, String userEmail, String userNickname, Integer buyAuthority, Integer speakAuthority) {
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
	}

	public UserVO() {
		super();
	}

	@Override
	public String toString() {
		return "UserVO [userID=" + userID + ", userName=" + userName + ", userAccount=" + userAccount
				+ ", userPassword=" + userPassword + ", userBirth=" + userBirth + ", userCell=" + userCell
				+ ", userEmail=" + userEmail + ", userNickname=" + userNickname + ", buyAuthority=" + buyAuthority
				+ ", speakAuthority=" + speakAuthority + "]";
	}

	public UserVO(String userAccount) {
		super();
		this.userAccount = userAccount;
	}
	
	

}
