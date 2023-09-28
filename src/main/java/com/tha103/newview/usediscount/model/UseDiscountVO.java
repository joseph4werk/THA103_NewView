package com.tha103.newview.usediscount.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usediscount")
//配合 HQL
//@NamedQuery(name = "")
public class UseDiscountVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "useDisID")
	private Integer useDisID;
	
	@Column(name = "discountNO")
	private Integer discountNO;
	
	@Column(name = "userID")
	private Integer userID;
	
	@Column(name = "ditUsed", columnDefinition = "TINYINT")
	private Integer ditUsed;
	
	public Integer getUseDisID() {
		return useDisID;
	}
	public void setUseDisID(Integer useDisID) {
		this.useDisID = useDisID;
	}
	public Integer getDiscountNO() {
		return discountNO;
	}
	public void setDiscountNO(Integer discountNO) {
		this.discountNO = discountNO;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getDitUsed() {
		return ditUsed;
	}
	public void setDitUsed(Integer ditUsed) {
		this.ditUsed = ditUsed;
	}
	public UseDiscountVO(Integer useDisID, Integer discountNO, Integer userID, Integer ditUsed) {
		super();
		this.useDisID = useDisID;
		this.discountNO = discountNO;
		this.userID = userID;
		this.ditUsed = ditUsed;
	}
	public UseDiscountVO() {
		super();
	}
	@Override
	public String toString() {
		return "useDiscountVO [useDisID=" + useDisID + ", discountNO=" + discountNO + ", userID=" + userID
				+ ", ditUsed=" + ditUsed + "]";
	}
	
	
	

}
