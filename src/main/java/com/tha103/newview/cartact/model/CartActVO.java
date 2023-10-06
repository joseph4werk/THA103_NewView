package com.tha103.newview.cartact.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.user.model.UserVO;

@Entity
@Table(name = "cartact")
public class CartActVO implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartActID;
	@ManyToOne
	@JoinColumn(name = "userID" , referencedColumnName ="userID")
	private UserVO user;
	@ManyToOne
	@JoinColumn(name = "actID" , referencedColumnName ="actID")
	private ActVO act;
	private Integer cartQuantity;
	
	
	
	
	public CartActVO() {
		super();
		// TODO Auto-generated constructor stub
	}




	public CartActVO(Integer cartActID, UserVO user, ActVO act, Integer cartQuantity) {
		super();
		this.cartActID = cartActID;
		this.user = user;
		this.act = act;
		this.cartQuantity = cartQuantity;
	}




	public Integer getCartActID() {
		return cartActID;
	}




	public void setCartActID(Integer cartActID) {
		this.cartActID = cartActID;
	}




	public UserVO getUser() {
		return user;
	}




	public void setUser(UserVO user) {
		this.user = user;
	}




	public ActVO getAct() {
		return act;
	}




	public void setAct(ActVO act) {
		this.act = act;
	}




	public Integer getCartQuantity() {
		return cartQuantity;
	}




	public void setCartQuantity(Integer cartQuantity) {
		this.cartQuantity = cartQuantity;
	}




	@Override
	public String toString() {
		return "CartActVO [cartActID=" + cartActID + ", user=" + user + ", act=" + act + ", cartQuantity="
				+ cartQuantity + "]";
	}
	
	

	
	
	

}
