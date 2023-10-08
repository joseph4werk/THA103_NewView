package com.tha103.newview.act.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.tha103.newview.act.*;
import com.tha103.newview.actcategory.model.ActCategory;
import com.tha103.newview.cityAdress.model.CityAddress;

public class TestHiberateDAO {
	public static void main(String[] args) throws Exception {
		ActDAO dao = new ActDAOHibernateImpl();

//		// 新增
//		Act act = new Act();
//		CityAddress city = new CityAddress();
//		ActCategory actCategory = new ActCategory();
//		act.setActName("新增");
//		act.setActPrice(12345);
//		actCategory.setActCategoryID(2);
//		act.setActCategory(actCategory);
//		act.setPubID(1);
//		act.setActTime(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-08"));
//		city.setActAdressID(1);
//		act.setCityAddressID(city);;
//		act.setActScope(1);
//		act.setActIntroduce("有改到");
//		act.setActContent("有改到");
//		act.setTime(new SimpleDateFormat("HH:mm:ss").parse("12:10:00"));
//		act.setActDate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-07"));		
//		act.setApprovalCondition(true);
//		act.setCityAddress("有改到");
//		dao.insert(act);
//
//		// 修改
//		Act act = new Act();
//		CityAddress city = new CityAddress();
//		ActCategory actCategory = new ActCategory();
//		act.setActID(3);
//		act.setActName("David Jr.");
//		act.setActPrice(12345);
//		actCategory.setActCategoryID(1);
//		act.setActCategory(actCategory);
//		act.setPubID(2);
//		act.setActTime(new SimpleDateFormat("yyyy-MM-dd").parse("2018-08-08"));
//		city.setActAdressID(1);
//		act.setCityAddressID(city);
//		act.setActScope(1);
//		act.setActIntroduce("有改到");
//		act.setActContent("有改到");
//		act.setTime(new SimpleDateFormat("HH:mm:ss").parse("12:10:00"));
//		act.setActDate(new SimpleDateFormat("yyyy-MM-dd").parse("2016-08-07"));		
//		act.setApprovalCondition(true);
//		act.setCityAddress("有改到");
//		dao.update(act);
//
//		// 刪除
//		dao.delete(6);
//
//		// 查詢單筆
/*    	Act act = dao.findByPrimaryKey(4);
		System.out.print(act.getActName() + ",");
		System.out.print(act.getActPrice() + ",");
		System.out.print(act.getActCategoryID() + ",");
		System.out.print(act.getPubID() + ",");
		System.out.print(act.getActTime() + ",");
		System.out.print(act.getCityAddressID() +",");
		System.out.print(act.getActScope() + ",");
		System.out.print(act.getActIntroduce() + ",");
		System.out.print(act.getTime() + ",");
		System.out.print(act.getActDate() + ",");
		System.out.print(act.getApprovalCondition() + ",");
		System.out.print(act.getCityAddress() + ",");
//		System.out.println("---------------------");
*/
		// 查詢多筆
//		
		List<ActVO> list = dao.getAll();
		
		for (ActVO act : list) {
			System.out.print(act.getActID() + ",");
			System.out.print(act.getActName() + ",");
			System.out.print(act.getActPrice() + ",");
			System.out.print(act.getActCategoryID().getActCategoryName() + ",");
			System.out.print(act.getPublisherVOs().getPubName() + ",");
			System.out.print(act.getActTime() + ",");
			System.out.print(act.getCityAddressID().getCityName() +",");
			System.out.print(act.getActScope() + ",");
			System.out.print(act.getActIntroduce() + ",");
			System.out.print(act.getTime() + ",");
			System.out.print(act.getActDate() + ",");
			System.out.print(act.getApprovalCondition() + ",");
			System.out.print(act.getCityAddress() + ",");
			System.out.println();
		}
	}
}
