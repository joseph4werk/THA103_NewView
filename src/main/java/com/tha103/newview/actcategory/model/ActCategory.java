package com.tha103.newview.actcategory.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import com.tha103.newview.act.model.Act;

@Entity
@Table(name = "actCategory")
// 配合 TestHQLWithParameter.java
@NamedQuery(name = "getAllActCategory", query = "from ActCategory where actCategoryID > :actCategoryID order by actCategoryID desc")
public class ActCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actCategoryID", updatable = false)
	private Integer actCategoryID;

	@Column(name = "actCategoryName")
	private String actCategoryName;

	
	
	public ActCategory(Integer actCategoryID, String actCategoryName) {
		super();
		this.actCategoryID = actCategoryID;
		this.actCategoryName = actCategoryName;
	}

	
	
	@Override
	public String toString() {
		return "ActCategory [ActCategoryID=" + actCategoryID + ", actCategoryName=" + actCategoryName + "]";
	}

	public Integer getActCategoryID() {
		return actCategoryID;
	}

	public String getActCategoryName() {
		return actCategoryName;
	}

	public void setActCategoryID(Integer actCategoryID) {
		this.actCategoryID = actCategoryID;
	}

	public void setActCategoryName(String actCategoryName) {
		this.actCategoryName = actCategoryName;
	}
	
	public ActCategory() {
       
    }
	
}
