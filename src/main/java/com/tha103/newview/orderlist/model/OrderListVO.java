package com.tha103.newview.orderlist.model;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.orders.model.OrdersVO;
import com.tha103.newview.postmessage.model.PostMessageVO;

@Entity
@Table(name = "orderlist")
public class OrderListVO implements java.io.Serializable{	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderListID;
	private ActVO act;
	private Integer actTotal;
	private byte[] QRcodeID;
	private Timestamp OrderListTime ;
	private String reviewContent;
	private Integer fiveStarReview;
	private Integer seatRows;
	private Integer seatColumns;
	private String vacancy;
	
	@ManyToOne
	@JoinColumn(name = "orderID", referencedColumnName = "orderID")
	private OrdersVO orders;
	@ManyToOne
	@JoinColumn(name = "actID", referencedColumnName = "actID")
	@OneToMany(mappedBy = "orderList" , cascade = CascadeType.ALL)
	private Set<OrderListVO> OrderListVOs;
	
	
	
	public OrderListVO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public OrderListVO(Integer orderListID, ActVO act, Integer actTotal, byte[] qRcodeID, Timestamp orderListTime,
			String reviewContent, Integer fiveStarReview, Integer seatRows, Integer seatColumns, String vacancy,
			OrdersVO orders, Set<OrderListVO> orderListVOs) {
		super();
		this.orderListID = orderListID;
		this.act = act;
		this.actTotal = actTotal;
		QRcodeID = qRcodeID;
		OrderListTime = orderListTime;
		this.reviewContent = reviewContent;
		this.fiveStarReview = fiveStarReview;
		this.seatRows = seatRows;
		this.seatColumns = seatColumns;
		this.vacancy = vacancy;
		this.orders = orders;
		OrderListVOs = orderListVOs;
	}



	public Integer getOrderListID() {
		return orderListID;
	}



	public void setOrderListID(Integer orderListID) {
		this.orderListID = orderListID;
	}



	public ActVO getAct() {
		return act;
	}



	public void setAct(ActVO act) {
		this.act = act;
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



	public OrdersVO getOrders() {
		return orders;
	}



	public void setOrders(OrdersVO orders) {
		this.orders = orders;
	}



	public Set<OrderListVO> getOrderListVOs() {
		return OrderListVOs;
	}



	public void setOrderListVOs(Set<OrderListVO> orderListVOs) {
		OrderListVOs = orderListVOs;
	}



	@Override
	public String toString() {
		return "OrderListVO [orderListID=" + orderListID + ", act=" + act + ", actTotal=" + actTotal + ", QRcodeID="
				+ Arrays.toString(QRcodeID) + ", OrderListTime=" + OrderListTime + ", reviewContent=" + reviewContent
				+ ", fiveStarReview=" + fiveStarReview + ", seatRows=" + seatRows + ", seatColumns=" + seatColumns
				+ ", vacancy=" + vacancy + ", orders=" + orders + ", OrderListVOs=" + OrderListVOs + "]";
	}



	


	
	
	
	
	
}
