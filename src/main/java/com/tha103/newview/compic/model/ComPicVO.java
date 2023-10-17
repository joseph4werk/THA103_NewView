package com.tha103.newview.compic.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tha103.newview.orderlist.model.OrderListVO;

@Entity
@Table(name = "compic")
public class ComPicVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer comPicID;
	
	
	
	@Column(name = "comPic", columnDefinition = "longblob")
	private byte[] comPic;
	
	@ManyToOne
	@JoinColumn(name = "orderListID" , referencedColumnName ="orderListID")
	private OrderListVO orderList;

	public ComPicVO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ComPicVO(Integer comPicID, OrderListVO orderList, byte[] comPic) {
		super();
		this.comPicID = comPicID;
		this.orderList = orderList;
		this.comPic = comPic;
	}


	public Integer getComPicID() {
		return comPicID;
	}


	public void setComPicID(Integer comPicID) {
		this.comPicID = comPicID;
	}


	public OrderListVO getOrderList() {
		return orderList;
	}


	public void setOrderList(OrderListVO orderList) {
		this.orderList = orderList;
	}


	public byte[] getComPic() {
		return comPic;
	}


	public void setComPic(byte[] comPic) {
		this.comPic = comPic;
	}


	@Override
	public String toString() {
		return "ComPicVO [comPicID=" + comPicID + ", orderList=" + orderList + ", comPic=" + Arrays.toString(comPic)
				+ "]";
	}



	

	
	
	
}