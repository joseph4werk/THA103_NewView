package com.tha103.newview.pubuser.service;

import java.util.List;

import com.tha103.newview.publisher.model.PublisherVO;
import com.tha103.newview.pubuser.model.*;

public class PubUserService {

	private PubUserHibernateDAO dao;

	public PubUserService() {
		dao = new PubUserHibernateDAOImpl();
	}

	public PubUserVO addPubUser(String pubNickname, String pubAccount, String pubPassword, byte pubAuthority,
			Integer pubID) {

		PubUserVO pubuserVO = new PubUserVO();
		pubuserVO.setPubNickname(pubNickname);
		pubuserVO.setPubAccount(pubAccount);
		pubuserVO.setPubPassword(pubPassword);
		pubuserVO.setPubAuthority(pubAuthority);

		// coz Association Publisher create PublisherVO object to set pubID
		PublisherVO pubVO = new PublisherVO();
		pubVO.setPubID(pubID);
		// to PublisherVO to set PublisherVO
		pubuserVO.setPublisherVO(pubVO);

		dao.add(pubuserVO);
		return pubuserVO;
	}

	public PubUserVO updatePubUser(String pubNickname, String pubAccount, String pubPassword, byte pubAuthority,
			Integer pubID) {

		PubUserVO pubuserVO = new PubUserVO();
		pubuserVO.setPubNickname(pubNickname);
		pubuserVO.setPubAccount(pubAccount);
		pubuserVO.setPubPassword(pubPassword);
		pubuserVO.setPubAuthority(pubAuthority);
		// coz Association Publisher create PublisherVO object to set pubID
		PublisherVO pubVO = new PublisherVO();
		pubVO.setPubID(pubID);
		// to PublisherVO to set PublisherVO
		pubuserVO.setPublisherVO(pubVO);

		dao.update(pubuserVO);
		return pubuserVO;
	}
	
	
	public boolean authenticate(String pubAccount, String pubPassword) {
		PubUserVO userlogin = dao.findByAccount(pubAccount);
				
		if(userlogin != null && userlogin.getPubPassword().equals(pubPassword)) {
			System.out.println("比對成功");
			return true;
		}else {
			System.out.println("比對失敗");
			return false;
		}
		
	}

	public void deletePubUser(Integer pubUserID) {
		dao.delete(pubUserID);
	}

	public PubUserVO getOnePubUser(Integer pubUserID) {
		return dao.findByPK(pubUserID);
	}

	public List<PubUserVO> getAll() {
		return dao.getAll();
	}

//	public List<PubUserVO> getAll(Map<String, String[]> map) {
//	return dao.getAll(map);
//	}
	
}
