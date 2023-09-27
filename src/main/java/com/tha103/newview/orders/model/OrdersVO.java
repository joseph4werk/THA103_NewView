package com.tha103.newview.orders.model;

import java.sql.Date;
import java.sql.Timestamp;

public class OrdersVO implements java.io.Serializable{
	private Integer orderID;
	private Integer userID;
	private Integer ordTotal;
	private Integer discount;
	private Integer discountPrice;
	private Timestamp ordTime;
	private Integer pubID;
	private Integer ordType;   // 0 = not use, 1 = used, 2 = cancelled order
	private Integer actQuantity;
	private Integer discountNO;
	
	public OrdersVO() {
		super();
	}
	
	public OrdersVO(Integer orderID, Integer userID, Integer ordTotal, Integer discount, Integer discountPrice, Timestamp ordTime, Integer pubID, Integer ordType, Integer actQuantity, Integer discountNO) {
		super();
		this.orderID = orderID;
		this.userID = userID;
		this.ordTotal = ordTotal;
		this.discount = discount;
		this.discountPrice = discountPrice;
		this.ordTime = ordTime;
		this.pubID = pubID;
		this.ordType = ordType;  
		this.actQuantity = actQuantity;
		this.discountNO = discountNO;
	}
	
	
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public Integer getOrdTotal() {
		return ordTotal;
	}
	public void setOrdTotal(Integer ordTotal) {
		this.ordTotal = ordTotal;
	}
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public Integer getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(Integer discountPrice) {
		this.discountPrice = discountPrice;
	}
	public Timestamp getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(Timestamp ordTime) {
		this.ordTime = ordTime;
	}
	public Integer getPubID() {
		return pubID;
	}
	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}
	public Integer getOrdType() {
		return ordType;
	}
	public void setOrdType(Integer ordType) {
		this.ordType = ordType;
	}
	public Integer getActQuantity() {
		return actQuantity;
	}
	public void setActQuantity(Integer actQuantity) {
		this.actQuantity = actQuantity;
	}
	public Integer getDiscountNO() {
		return discountNO;
	}
	public void setDiscountNO(Integer discountNO) {
		this.discountNO = discountNO;
	}
	

	
	@Override
	public String toString() {
		return "OrdersVO [orderID=" + orderID + ", userID=" + userID + ", ordTotal=" + ordTotal
				+ ", discount=" + discount + ", discountPrice=" + discountPrice + ", ordTime=" + ordTime
				+ ", pubID=" + pubID + ", ordType=" + ordType + ", actQuantity=" + actQuantity
				+ ", discountNO=" + discountNO + "]";
	}
	
	
}