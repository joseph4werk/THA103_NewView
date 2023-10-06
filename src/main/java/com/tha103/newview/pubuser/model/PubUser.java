package com.tha103.newview.pubuser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tha103.newview.publisher.model.Publisher;

@Entity
@Table(name="PubUser")
public class PubUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pubUserID" , updatable = false)
	private Integer pubUserID;
	
	/*
	@Column(name="pubID")
	private Integer pubID;
	*/
	
	@ManyToOne
	@JoinColumn(name="pubID" ,referencedColumnName ="pubID")
	private Publisher publisher;
	
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
		// TODO Auto-generated constructor stub
	}

	public Integer getPubUserID() {
		return pubUserID;
	}

	public void setPubUserID(Integer pubUserID) {
		this.pubUserID = pubUserID;
	}

/*  //沒有Association的get/setter
	public Integer getPubID() {
		return pubID;
	}

	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}
*/

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
	

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "PubUser [pubUserID=" + pubUserID + ", publisher=" + publisher + ", pubNickname=" + pubNickname
				+ ", pubAccount=" + pubAccount + ", pubPassword=" + pubPassword + ", pubAuthority=" + pubAuthority
				+ "]";
	}

	
	
}
