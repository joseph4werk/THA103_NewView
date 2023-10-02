package com.tha103.newview.publisher.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Publisher")
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pubID")
	private Integer pubID;
	
	@Column(name="pubName")
	private String pubName;
	
	@Column(name="pubEmail")
	private String pubEmail;

	public Publisher() {
		super();
	}

	public Integer getPubID() {
		return pubID;
	}

	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}

	public String getPubName() {
		return pubName;
	}

	public void setPubName(String pubName) {
		this.pubName = pubName;
	}

	public String getPubEmail() {
		return pubEmail;
	}

	public void setPubEmail(String pubEmail) {
		this.pubEmail = pubEmail;
	}
	
	
	
}
