package com.tha103.newview.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="administrator")
public class Admin {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="adminID" , updatable = false)
	private Integer adminID;
	
	@Expose
	@Column(name="adminName")
	private String adminName;
	
	@Expose
	@Column(name="adminAccount")
	private String adminAccount;
	
	@Expose
	@Column(name="adminPassword")
	private String adminPassword;
	
	@Expose
	@Column(name="adminEmail")
	private String adminEmail;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getAdminID() {
		return adminID;
	}

	public void setAdminID(Integer adminID) {
		this.adminID = adminID;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminAccount() {
		return adminAccount;
	}

	public void setAdminAccount(String adminAccount) {
		this.adminAccount = adminAccount;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	@Override
	public String toString() {
		return "Admin [adminID=" + adminID + ", adminName=" + adminName + ", adminAccount=" + adminAccount
				+ ", adminPassword=" + adminPassword + ", adminEmail=" + adminEmail + "]";
	}
	
	
	
}
