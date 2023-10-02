package com.tha103.newview.actpic.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.tha103.newview.act.model.Act;
import com.tha103.newview.actcategory.model.ActCategory;

@Entity
@Table(name = "actpic")
// 配合 TestHQLWithParameter.java
@NamedQuery(name = "getAllActPics", query = "from ActPic where ActPicID > :ActPicID order by ActPicID desc")
public class ActPic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actPicID", updatable = false)
	private Integer actPicID;

	@Column(name = "actID")
	private Integer actID;

	@Column(name = "actPic", columnDefinition = "LONGBLOB")
	private byte[] actPic;

	@ManyToOne
	@JoinColumn(name = "actID", insertable = false, updatable = false)
	private Act act;

	public ActPic(Integer actPicID, Integer actID, byte[] actPic) {
		super();
		this.actPicID = actPicID;
		this.actID = actID;
		this.actPic = actPic;
	}

	public ActPic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getActPicID() {
		return actPicID;
	}

	public Integer getActID() {
		return actID;
	}

	public byte[] getActPic() {
		return actPic;
	}

	public void setActPicID(Integer actPicID) {
		this.actPicID = actPicID;
	}

	public void setActID(Integer actID) {
		this.actID = actID;
	}

	public void setActPic(byte[] actPic) {
		this.actPic = actPic;
	}

}
