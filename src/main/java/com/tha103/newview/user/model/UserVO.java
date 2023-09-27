package com.tha103.newview.user.model;

import java.sql.Date;

public class UserVO{
	
//	private static final long serialVersionUID = 1L;
	private Integer userID;
	private String userName;
	private String userAccount;
	private String userPassword;
	private Date userBirth;
	private String userCell;
	private String userEmail;
	private String userNickname;
	private Integer buyAuthority;  // 0/1 = 啟用/未啟用
	private Integer speakAuthority;  // 0/1 = 啟用/未啟用
	
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
	
	
}
