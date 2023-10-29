package com.tha103.newview.user.dto;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.actpic.model.ActPic;
import com.tha103.newview.mylike.model.MyLikeVO;
import com.tha103.newview.user.model.UserVO;
import com.tha103.newview.user.service.UserServiceImpl;

public class MyLikeActDTO {

	List<String> actName = new ArrayList<>();
	List<String> actIntroduce = new ArrayList<>();
	List<String> actPic = new ArrayList<>();

	public MyLikeActDTO(Integer userID) {
		UserVO userVO = new UserServiceImpl().getUserByPK(userID);
		Set<MyLikeVO> myLikeVOs = userVO.getMyLikeVOs();
		for (MyLikeVO mylikeVO : myLikeVOs) {
			ActVO actVO = mylikeVO.getAct();
			this.actName.add(actVO.getActName());
			this.actIntroduce.add(actVO.getActIntroduce());

			Set<ActPic> actpics = actVO.getActpics();
			Object[] objectArray = actpics.toArray();
			ActPic[] actPicsArray = new ActPic[objectArray.length];
			if(objectArray[0] instanceof ActPic) {
				actPicsArray[0] = (ActPic) objectArray[0];
				this.actPic.add(Base64.getEncoder().encodeToString(actPicsArray[0].getActPic()));
			}
		}
	}

	public MyLikeActDTO() {
	}

	@Override
	public String toString() {
		return "MyLikeActDTO [actName=" + actName + ", actIntroduce=" + actIntroduce + ", actPic=" + actPic + "]";
	}

}
