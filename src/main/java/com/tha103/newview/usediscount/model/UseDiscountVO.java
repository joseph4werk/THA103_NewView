package com.tha103.newview.usediscount.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.user.model.UserVO;

@Entity
@Table(name = "useDiscount")
//配合 HQL
//@NamedQuery(name = "")
public class UseDiscountVO{
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "useDisID")
	private Integer useDisID;
	
	@Expose
	@Column(name = "discountNO")
	private Integer discountNO;
	
	@Expose
	@Column(name = "userID")
	private Integer userID;
	
	@Expose
	@Column(name = "ditUsed", columnDefinition = "TINYINT")
	private Integer ditUsed;
	
	@ManyToOne
	@JoinColumn(name = "discountNO", referencedColumnName = "discountNO")
	private DiscountVO discountVO;
	
	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private UserVO userVO;
	
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
	public DiscountVO getDiscountVO() {
		return discountVO;
	}
	public void setDiscountVO(DiscountVO discountVO) {
		this.discountVO = discountVO;
	}
	
	public UserVO getUserVO() {
		return userVO;
	}
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	
	public UseDiscountVO(Integer useDisID, Integer discountNO, Integer userID, Integer ditUsed, DiscountVO discountVO,
			UserVO userVO) {
		super();
		this.useDisID = useDisID;
		this.discountNO = discountNO;
		this.userID = userID;
		this.ditUsed = ditUsed;
		this.discountVO = discountVO;
		this.userVO = userVO;
	}
	public UseDiscountVO() {
		super();
	}
	@Override
	public String toString() {
		return "UseDiscountVO [useDisID=" + useDisID + ", discountNO=" + discountNO + ", userID=" + userID
				+ ", ditUsed=" + ditUsed + ", discountVO=" + discountVO + ", userVO=" + userVO + "]";
	}
}
