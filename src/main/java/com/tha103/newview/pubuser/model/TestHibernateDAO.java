package com.tha103.newview.pubuser.model;

import java.util.List;

public class TestHibernateDAO {
	
	public static void main(String[] args) {
		PubUserHibernateDAO dao = new PubUserHibernateDAOImpl();
		
		//test add ok
//		PubUser pu = new PubUser();
//		pu.setPubID(1);
//		pu.setPubNickname("Hiber");
//		pu.setPubAccount("Hibernate");
//		pu.setPubPassword("33142");
//		pu.setPubAuthority((byte) 1);
//		dao.add(pu);
//		System.out.println("Add PubUser success!");
		
		
		//test update ok
//		PubUser pu1 = new PubUser();
//		pu1.setPubUserID(1);
//		pu1.setPubID(1);
//		pu1.setPubNickname("QQ");
//		pu1.setPubAccount("DD9999");
//		pu1.setPubPassword("33joij42");
//		pu1.setPubAuthority((byte) 1);
//		dao.update(pu1);
//		System.out.println("update PubUser success!!");
		
		
		//test delete ok
//		dao.delete(5);
//		System.out.println("delete success!!!");
		
		
		//test findByPK ok
//		PubUser pu2 = dao.finkByPK(3);
//		System.out.println(pu2.getPubUserID());
//		System.out.println(pu2.getPubID());
//		System.out.println(pu2.getPubNickname());
//		System.out.println(pu2.getPubAccount());
//		System.out.println(pu2.getPubPassword());
//		System.out.println(pu2.getPubAuthority());
//		System.out.println("===========");
		
		
		//test findAll
		List<PubUser> list = dao.getAll();
		for(PubUser pu3 : list) {
			System.out.print(pu3.getPubUserID()+",");
			System.out.print(pu3.getPubID()+",");
			System.out.print(pu3.getPubNickname()+",");
			System.out.print(pu3.getPubAccount()+",");
			System.out.print(pu3.getPubPassword()+",");
			System.out.print(pu3.getPubAuthority()+",");
			System.out.println(" ");
		}
		
		
	}

}
