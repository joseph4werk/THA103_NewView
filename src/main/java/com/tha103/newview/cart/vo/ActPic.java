package com.tha103.newview.cart.vo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "aPic")
@Table(name = "actpic")
public class ActPic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer actPicID;
	private byte[] actPic;
	
	public ActPic() {
	}

	public ActPic(Integer actPicID, byte[] actPic) {
		this.actPicID = actPicID;
		this.actPic = actPic;
	}

	public Integer getActPicID() {
		return actPicID;
	}

	public void setActPicID(Integer actPicID) {
		this.actPicID = actPicID;
	}

	public byte[] getActPic() {
		return actPic;
	}

	public void setActPic(byte[] actPic) {
		this.actPic = actPic;
	}
}