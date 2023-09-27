package com.tha103.newview.orderlist.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class OrderListVO implements java.io.Serializable{
	
	private Integer orderListID;
	private Integer actTotal;
	private byte[] QRcodeID;
	private Timestamp OrderListTime ;
	private String reviewContent;
	private Integer fiveStarReview;
	private Integer seatRows;
	private Integer seatColumns;
	private String vacancy;
	private Integer orderID;
	private Integer actID;
	
	
	
	public OrderListVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public OrderListVO(Integer orderListID, Integer actTotal, byte[] qRcodeID, Timestamp orderListTime,
			String reviewContent, Integer fiveStarReview, Integer seatRows, Integer seatColumns, String vacancy,
			Integer orderID, Integer actID) {
		super();
		this.orderListID = orderListID;
		this.actTotal = actTotal;
		QRcodeID = qRcodeID;
		OrderListTime = orderListTime;
		this.reviewContent = reviewContent;
		this.fiveStarReview = fiveStarReview;
		this.seatRows = seatRows;
		this.seatColumns = seatColumns;
		this.vacancy = vacancy;
		this.orderID = orderID;
		this.actID = actID;
	}



	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getActID() {
		return actID;
	}
	public void setActID(Integer actID) {
		this.actID = actID;
	}
	public Integer getOrderListID() {
		return orderListID;
	}
	public void setOrderListID(Integer orderListID) {
		this.orderListID = orderListID;
	}
	public Integer getActTotal() {
		return actTotal;
	}
	public void setActTotal(Integer actTotal) {
		this.actTotal = actTotal;
	}
	public byte[] getQRcodeID() {
		return QRcodeID;
	}
	public void setQRcodeID(byte[] qRcodeID) {
		QRcodeID = qRcodeID;
	}
	public Timestamp getOrderListTime() {
		return OrderListTime;
	}
	public void setOrderListTime(Timestamp orderListTime) {
		OrderListTime = orderListTime;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public Integer getFiveStarReview() {
		return fiveStarReview;
	}
	public void setFiveStarReview(Integer fiveStarReview) {
		this.fiveStarReview = fiveStarReview;
	}
	
	public Integer getSeatRows() {
		return seatRows;
	}
	public void setSeatRows(Integer seatRows) {
		this.seatRows = seatRows;
	}
	public Integer getSeatColumns() {
		return seatColumns;
	}
	public void setSeatColumns(Integer seatColumns) {
		this.seatColumns = seatColumns;
	}
	public String getVacancy() {
		return vacancy;
	}
	public void setVacancy(String vacancy) {
		this.vacancy = vacancy;
	}



	@Override
	public String toString() {
		return "OrderListVO [orderListID=" + orderListID + ", actTotal=" + actTotal + ", QRcodeID="
				+ Arrays.toString(QRcodeID) + ", OrderListTime=" + OrderListTime + ", reviewContent=" + reviewContent
				+ ", fiveStarReview=" + fiveStarReview + ", seatRows=" + seatRows + ", seatColumns=" + seatColumns
				+ ", vacancy=" + vacancy + ", orderID=" + orderID + ", actID=" + actID + "]";
	}
	
	
	
}
