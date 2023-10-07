package com.tha103.newview.cartact.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.user.model.UserVO;

@Entity
@Table(name = "cartact")
public class CartActVO implements java.io.Serializable {
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartActID")
	private Integer cartActID;

	@Expose
	@Column(name = "userID")
	private Integer userID;

	@Expose
	@Column(name = "actID")
	private Integer actID;

	@Expose
	@Column(name = "cartQuantity")
	private Integer cartQuantity;

	@ManyToOne
	@JoinColumn(name = "userID", referencedColumnName = "userID")
	private UserVO userVO;

	@ManyToOne
	@JoinColumn(name = "actID", referencedColumnName = "actID")
	private ActVO actVO;

	public CartActVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartActVO(Integer cartActID, Integer userID, Integer actID, Integer cartQuantity, UserVO userVO,
			ActVO actVO) {
		super();
		this.cartActID = cartActID;
		this.userID = userID;
		this.actID = actID;
		this.cartQuantity = cartQuantity;
		this.userVO = userVO;
		this.actVO = actVO;
	}

	public Integer getCartActID() {
		return cartActID;
	}

	public void setCartActID(Integer cartActID) {
		this.cartActID = cartActID;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Integer getActID() {
		return actID;
	}

	public void setActID(Integer actID) {
		this.actID = actID;
	}

	public Integer getCartQuantity() {
		return cartQuantity;
	}

	public void setCartQuantity(Integer cartQuantity) {
		this.cartQuantity = cartQuantity;
	}

	public UserVO getUserVO() {
		return userVO;
	}

	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}

	public ActVO getActVO() {
		return actVO;
	}

	public void setActVO(ActVO actVO) {
		this.actVO = actVO;
	}

	@Override
	public String toString() {
		return "CartActVO [cartActID=" + cartActID + ", userID=" + userID + ", actID=" + actID + ", cartQuantity="
				+ cartQuantity + ", userVO=" + userVO + ", actVO=" + actVO + "]";
	}

}
