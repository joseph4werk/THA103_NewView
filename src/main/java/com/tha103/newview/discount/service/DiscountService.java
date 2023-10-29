package com.tha103.newview.discount.service;

import java.sql.Timestamp;
import java.util.List;

import com.tha103.newview.admin.model.AdminVO;
import com.tha103.newview.discount.model.DiscountDAO;
import com.tha103.newview.discount.model.DiscountDAOImpl;
import com.tha103.newview.discount.model.DiscountVO;
import com.tha103.newview.publisher.model.PublisherVO;

public class DiscountService {

	private DiscountDAO discountDAO;

	public DiscountService() {
		discountDAO = new DiscountDAOImpl();
	}

	public DiscountVO addDiscount(String discountContent, Integer disAmount, String discountCode,
			Timestamp disStartDate, Timestamp disFinishDate, Integer pubID, Integer adminID) {

		DiscountVO discountVO = new DiscountVO();
		discountVO.setDiscountContent(discountContent);
		discountVO.setDisAmount(disAmount);
		discountVO.setDiscountCode(discountCode);
		discountVO.setDisStartDate(disStartDate);
		discountVO.setDisFinishDate(disFinishDate);

		// coz Association Publisher create PublisherVO object to set pubID
		PublisherVO publisherVO = new PublisherVO();
		publisherVO.setPubID(pubID);
		// to PublisherVO to set PublisherVO
		discountVO.setPublisherVO(publisherVO);

		// coz Association Admin create AdminVO object to set adminID
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminID(adminID);
		// to AdminVO to set AdminVO
		discountVO.setAdminVO(adminVO);

		discountDAO.insert(discountVO);
		return discountVO;
	}

	public DiscountVO updateDiscount(String discountContent, Integer disAmount, String discountCode,
			Timestamp disStartDate, Timestamp disFinishDate, Integer pubID, Integer adminID) {

		DiscountVO discountVO = new DiscountVO();
		discountVO.setDiscountContent(discountContent);
		discountVO.setDisAmount(disAmount);
		discountVO.setDiscountCode(discountCode);
		discountVO.setDisStartDate(disStartDate);
		discountVO.setDisFinishDate(disFinishDate);

		// coz Association Publisher create PublisherVO object to set pubID
		PublisherVO publisherVO = new PublisherVO();
		publisherVO.setPubID(pubID);
		// to PublisherVO to set PublisherVO
		discountVO.setPublisherVO(publisherVO);

		// coz Association Admin create AdminVO object to set adminID
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminID(adminID);
		// to AdminVO to set AdminVO
		discountVO.setAdminVO(adminVO);

		discountDAO.update(discountVO);
		return discountVO;
	}

	public DiscountVO getOneDiscount(Integer discountNO) {
		return discountDAO.findByPrimaryKey(discountNO);
	}

	public List<DiscountVO> getAll() {
		return discountDAO.getAll();
	}

	public DiscountVO updateDiscount(Integer discountNO, String discountContent, Integer disAmount, String discountCode,
			Timestamp disStartDate, Timestamp disFinishDate, Integer pubID, Integer adminID) {

		DiscountVO discount = new DiscountVO();
		discount.setDiscountNO(discountNO);
		discount.setDiscountContent(discountContent);
		discount.setDisAmount(disAmount);
		discount.setDiscountCode(discountContent);
		discount.setDisStartDate(disStartDate);
		discount.setDisFinishDate(disFinishDate);

		PublisherVO pub = new PublisherVO();
		pub.setPubID(pubID);
		discount.setPublisherVO(pub);

		AdminVO admin = new AdminVO();
		admin.setAdminID(adminID);
		discount.setAdminVO(admin);

		discountDAO.update(discount);
		return discount;

	}

	public void deleteDiscount(Integer discountNO) {
		discountDAO.delete(discountNO);
	}

	public DiscountVO getDiscountByPK(Integer discountNO) {
		return discountDAO.findByPrimaryKey(discountNO);
	}

	// for Publisher Backstage get discount List
	public List<DiscountVO> getDiscountByPub(Integer pubID) {
		List<DiscountVO> discountList = discountDAO.getDiscountByPubID(pubID);
		return discountList;

	}

}
