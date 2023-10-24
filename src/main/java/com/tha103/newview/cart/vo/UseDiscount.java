package com.tha103.newview.cart.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "UseDiscount")
@Table(name = "useDiscount")
public class UseDiscount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer useDisID;
	private Integer discountNO;
	private Integer userID;
	private Integer ditUsed;

	public UseDiscount() {
	}

	public UseDiscount(Integer useDisID, Integer discountNO, Integer userID, Integer ditUsed) {
		this.useDisID = useDisID;
		this.discountNO = discountNO;
		this.userID = userID;
		this.ditUsed = ditUsed;
	}

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
}