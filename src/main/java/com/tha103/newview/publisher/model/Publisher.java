package com.tha103.newview.publisher.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.tha103.newview.pubuser.model.PubUser;

@Entity
@Table(name="Publisher")
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pubID" , updatable = false)
	private Integer pubID;
	
	@Column(name="pubName")
	private String pubName;
	
	@Column(name="pubEmail")
	private String pubEmail;

//FOR Association
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
	@OrderBy("pubUserID")
	private Set<PubUser> pubUsers;


	public Publisher() {
		super();
		// TODO Auto-generated constructor stub
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
	
//Association的get/setter
	public Set<PubUser> getPubusers() {
		return pubUsers;
	}

	public void setPubusers(Set<PubUser> pubusers) {
		this.pubUsers = pubusers;
	}
//Association的get/setter

	@Override
	public String toString() {
		return "Publisher [pubID=" + pubID + ", pubName=" + pubName + ", pubEmail=" + pubEmail + "]";
	}
	
	
	
}
