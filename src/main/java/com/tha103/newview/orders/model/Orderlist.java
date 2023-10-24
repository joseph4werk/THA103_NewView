package com.tha103.newview.orders.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Orderlist implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderListID;
	private Integer orderID;
	private Integer actTotal;
	private byte[] qrCodeId;
	private Timestamp orderListTime;
	private String reviewContent;
	private Integer fiveStarReview;
	private Integer seatRows;
	private Integer seatColumns;
	private String vacancy;
	@ManyToOne
	@JoinColumn(name = "actID", insertable = false, updatable = false)
	private Act act;
	
	public Orderlist() {
	}

	public Orderlist(Integer orderListID, Integer orderID, Integer actTotal, byte[] qrCodeId, Timestamp orderListTime,
			String reviewContent, Integer fiveStarReview, Integer seatRows, Integer seatColumns, String vacancy,
			Act act) {
		this.orderListID = orderListID;
		this.orderID = orderID;
		this.actTotal = actTotal;
		this.qrCodeId = qrCodeId;
		this.orderListTime = orderListTime;
		this.reviewContent = reviewContent;
		this.fiveStarReview = fiveStarReview;
		this.seatRows = seatRows;
		this.seatColumns = seatColumns;
		this.vacancy = vacancy;
		this.act = act;
	}

	public Integer getOrderListID() {
		return orderListID;
	}

	public void setOrderListID(Integer orderListID) {
		this.orderListID = orderListID;
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public Integer getActTotal() {
		return actTotal;
	}

	public void setActTotal(Integer actTotal) {
		this.actTotal = actTotal;
	}

	public byte[] getQrCodeId() {
		return qrCodeId;
	}

	public void setQrCodeId(byte[] qrCodeId) {
		this.qrCodeId = qrCodeId;
	}

	public Timestamp getOrderListTime() {
		return orderListTime;
	}

	public void setOrderListTime(Timestamp orderListTime) {
		this.orderListTime = orderListTime;
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

	public Act getAct() {
		return act;
	}

	public void setAct(Act act) {
		this.act = act;
	}
}