package com.tha103.newview.user.dto;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.actpic.model.ActPic;

public class HotActDTO {
	
	List<Integer> actID = new ArrayList<>();

	List<String> actName = new ArrayList<>();
	
	List<String> actPic = new ArrayList<>();
	
	public HotActDTO(ActVO actVO) {
		
		this.actID.add(actVO.getActID());
		this.actName.add(actVO.getActName());
		
		Set<ActPic> actpics = actVO.getActpics();
		Object[] objectArray = actpics.toArray();
		ActPic[] actPicsArray = new ActPic[objectArray.length];
		if(objectArray[0] instanceof ActPic) {
			actPicsArray[0] = (ActPic) objectArray[0];
			this.actPic.add(Base64.getEncoder().encodeToString(actPicsArray[0].getActPic()));
		}
	}
	
	public HotActDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "HomeDTO [actID=" + actID + ", actName=" + actName + ", actPic=" + actPic + "]";
	}
	
	

}
