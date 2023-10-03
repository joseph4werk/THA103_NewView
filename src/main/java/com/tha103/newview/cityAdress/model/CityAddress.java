package com.tha103.newview.cityAdress.model;

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
import com.tha103.newview.act.model.Act;

@Entity
@Table(name = "city")
public class CityAddress {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cityAddressID", updatable = false)
	private Integer cityAddressID;
	@Expose
	@Column(name = "cityName")
	private String cityName;
	@Expose
	@OneToMany(mappedBy = "city", cascade=CascadeType.ALL)
	private Set<Act> acts;
	
	public Integer getActAdressID() {
		return cityAddressID;
	}
	public void setActAdressID(Integer actAdressID) {
		this.cityAddressID = actAdressID;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Set<Act> getActs() {
		return acts;
	}
	public void setActs(Set<Act> acts) {
		this.acts = acts;
	}
	@Override
	public String toString() {
		return "cityadress [actAdressID=" + cityAddressID + ", cityName=" + cityName + ", acts=" + acts + "]";
	}
	public CityAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CityAddress(Integer actAddressID, String cityName, Set<Act> acts) {
		super();
		this.cityAddressID = actAddressID;
		this.cityName = cityName;
		this.acts = acts;
	}
	
	
	
	
}
