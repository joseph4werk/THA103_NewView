package com.tha103.newview.pubuser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PubUser")
public class PubUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pubUserID")
	private Integer pubUserID;
	
	@Column(name="pubID")
	private Integer pubID;
	
	@Column(name="pubNickname")
	private String pubNickname;
	
	@Column(name="pubAccount")
	private String pubAccount;
	
	@Column(name="pubPassword")
	private String pubPassword;
	
	@Column(name="pubAuthority",columnDefinition = "tinyint")
	private byte pubAuthority;

	public PubUser() {
		super();
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

	public byte getPubAuthority() {
		return pubAuthority;
	}

	public void setPubAuthority(byte pubAuthority) {
		this.pubAuthority = pubAuthority;
	}

	@Override
	public String toString() {
		return "PubUserPOJO [pubUserID=" + pubUserID + ", pubID=" + pubID + ", pubNickname=" + pubNickname
				+ ", pubAccount=" + pubAccount + ", pubPassword=" + pubPassword + ", pubAuthority=" + pubAuthority
				+ "]";
	}
	
	
	
}
