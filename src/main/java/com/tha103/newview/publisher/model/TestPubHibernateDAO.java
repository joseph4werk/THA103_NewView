package com.tha103.newview.publisher.model;

import java.util.List;

public class TestPubHibernateDAO {
	public static void main(String[] args) {
		PublisherHibernateDAO dao = new PublisherHibernateDAOImpl();
		
		//test add OK
//		Publisher pub = new Publisher();
//		pub.setPubName("哈哈大笑娛樂公司");
//		pub.setPubEmail("hahahaha@gmail.com");
//		dao.add(pub);
//		System.out.println("Add Publisher success!");
		
		//test update
//		Publisher pub1 = new Publisher();
//		pub1.setPubID(7);
//		pub1.setPubName("別說不好笑喜劇工作坊");
//		pub1.setPubEmail("imfunny@gmail.com");
//		dao.update(pub1);
//		System.out.println("update Publisher success!");
		
		//test delete
//		Publisher pub2 = new Publisher();
//		dao.delete(7);
//		System.out.println("delete Publisher success!");
		
		//test findByPK
//		Publisher pub3 = dao.findByPK(6);
//		System.out.print(pub3.getPubID());
//		System.out.print(pub3.getPubName());
//		System.out.print(pub3.getPubEmail());
		
		//test gatAll
		List<Publisher> list = dao.getAll();
		for(Publisher pub4 : list) {
			System.out.print(pub4.getPubID()+",");
			System.out.print(pub4.getPubName()+",");
			System.out.print(pub4.getPubEmail()+",");
			System.out.println(" ");
		}

	}

}
