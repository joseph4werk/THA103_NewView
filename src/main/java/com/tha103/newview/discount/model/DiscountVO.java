package com.tha103.newview.discount.model;

import java.sql.Timestamp;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.tha103.newview.orderlist.model.OrderListVO;
import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.newview.usediscount.model.UseDiscountVO;
import com.tha103.newview.usediscount.model.AdminVO;


@Entity
@Table(name = "discount")
public class DiscountVO{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	@Column(name = "discountNO")
	private Integer discountNO;
	
	@Expose
	@Column(name = "discountContent")
	private String discountContent;
	
	@Expose
	@Column(name = "disAmount")
	private Integer disAmount;
	
	@Expose
	@Column(name = "discountCode")
	private String discountCode;
	
	@Expose
	@Column(name = "disStartDate")
	private Timestamp disStartDate;
	
	@Expose
	@Column(name = "disFinishDate")
	private Timestamp disFinishDate;
	
	@Expose
	@OneToMany(mappedBy = "discountVO")
	private Set<UseDiscountVO> useDiscountVOs;
	
	@Expose
	@OneToMany(mappedBy = "discountVO", cascade = CascadeType.ALL)
	private Set<OrderListVO> orderListVOs;
	
	@ManyToOne
	@JoinColumn(name = "pubID", referencedColumnName = "pubID")
	private PublisherVO publisherVO;
	
	@ManyToOne
	@JoinColumn(name = "adminID", referencedColumnName = "adminID")
	private AdminVO adminVO;
	
	public DiscountVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DiscountVO(Integer discountNO, PublisherVO publisherVO, AdminVO adminVO, String discountContent, Integer disAmount,
			String discountCode, Timestamp disStartDate, Timestamp disFinishDate) {
		super();
		this.discountNO = discountNO;
		this.publisherVO = publisherVO;
		this.adminVO = adminVO;
		this.discountContent = discountContent;
		this.disAmount = disAmount;
		this.discountCode = discountCode;
		this.disStartDate = disStartDate;
		this.disFinishDate = disFinishDate;
	}


	public Integer getDiscountNO() {
		return discountNO;
	}
	public void setDiscountNO(Integer discountNO) {
		this.discountNO = discountNO;
	}
	
	public PublisherVO getPublisher() {
		return publisherVO;
	}
	
	public void setPublisher(PublisherVO publisherVO) {
		this.publisherVO = publisherVO;
	}
	
	public AdminVO getAdmin() {
		return adminVO;
	}
	public void setAdmin(AdminVO adminVO) {
		this.adminVO = adminVO;
	}
	
	public String getDiscountContent() {
		return discountContent;
	}
	public void setDiscountContent(String discountContent) {
		this.discountContent = discountContent;
	}
	public Integer getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(Integer disAmount) {
		this.disAmount = disAmount;
	}
	public String getDiscountCode() {
		return discountCode;
	}
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	public Timestamp getDisStartDate() {
		return disStartDate;
	}
	public void setDisStartDate(Timestamp disStartDate) {
		this.disStartDate = disStartDate;
	}
	public Timestamp getDisFinishDate() {
		return disFinishDate;
	}
	public void setDisFinishDate(Timestamp disFinishDate) {
		this.disFinishDate = disFinishDate;
	}

	@Override
	public String toString() {
		return "DiscountVO [discountNO=" + discountNO + ", publisherVO=" + publisherVO + ", adminVO=" + adminVO
				+ ", discountContent=" + discountContent + ", disAmount=" + disAmount + ", discountCode=" + discountCode
				+ ", disStartDate=" + disStartDate + ", disFinishDate=" + disFinishDate + "]";
	}
	
	
	
}




