package com.tha103.newview.pubuser.model;

public class PubUserVO implements java.io.Serializable{
	private Integer pubUserID;
	private Integer pubID;
	private String pubNickname;
	private String pubAccount;
	private String pubPassword;
	private Integer pubAuthority;
	
	public PubUserVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public PubUserVO(Integer pubUserID, Integer pubID, String pubNickname, String pubAccount, String pubPassword,
			Integer pubAuthority) {
		super();
		this.pubUserID = pubUserID;
		this.pubID = pubID;
		this.pubNickname = pubNickname;
		this.pubAccount = pubAccount;
		this.pubPassword = pubPassword;
		this.pubAuthority = pubAuthority;
	}


	public Integer getPubUserID() {
		return pubUserID;
	}
	public void setPubUserID(Integer pubUserID) {
		this.pubUserID = pubUserID;
	}
	public Integer getPubID() {
		return pubID;
	}
	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}
	public String getPubNickname() {
		return pubNickname;
	}
	public void setPubNickname(String pubNickname) {
		this.pubNickname = pubNickname;
	}
	public String getPubAccount() {
		return pubAccount;
	}
	public void setPubAccount(String pubAccount) {
		this.pubAccount = pubAccount;
	}
	public String getPubPassword() {
		return pubPassword;
	}
	public void setPubPassword(String pubPassword) {
		this.pubPassword = pubPassword;
	}
	public Integer getPubAuthority() {
		return pubAuthority;
	}
	public void setPubAuthority(Integer pubAuthority) {
		this.pubAuthority = pubAuthority;
	}


	@Override
	public String toString() {
		return "PubUserVO [pubUserID=" + pubUserID + ", pubID=" + pubID + ", pubNickname=" + pubNickname
				+ ", pubAccount=" + pubAccount + ", pubPassword=" + pubPassword + ", pubAuthority=" + pubAuthority
				+ "]";
	}
	
	
	
}
