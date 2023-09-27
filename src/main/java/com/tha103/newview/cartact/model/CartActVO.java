package com.tha103.newview.cartact.model;

public class CartActVO implements java.io.Serializable{
	
	private Integer cartActID;
	private Integer userID;
	private Integer actID;
	private Integer cartQuantity;
	
	
	
	
	public CartActVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public CartActVO(Integer cartActID, Integer userID, Integer actID, Integer cartQuantity) {
		super();
		this.cartActID = cartActID;
		this.userID = userID;
		this.actID = actID;
		this.cartQuantity = cartQuantity;
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
	public Integer getCartActID() {
		return cartActID;
	}
	public void setCartActID(Integer cartActID) {
		this.cartActID = cartActID;
	}
	public Integer getCartQuantity() {
		return cartQuantity;
	}
	public void setCartQuantity(Integer cartQuantity) {
		this.cartQuantity = cartQuantity;
	}



	@Override
	public String toString() {
		return "CartActVO [cartActID=" + cartActID + ", userID=" + userID + ", actID=" + actID + ", cartQuantity="
				+ cartQuantity + "]";
	}
	
	
	

}
