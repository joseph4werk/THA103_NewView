package com.tha103.newview.pubuser.model;

import org.hibernate.Session;

import com.tha103.newview.publisher.model.Publisher;
import com.tha103.util.HibernateUtil;

public class TestPubAndPubuser {
	
	public static void main(String[] args) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			// ==查詢== 從廠商使用者帶出所屬的廠商資料(一筆)
			PubUser pubuser = session.get(PubUser.class,2); 
			System.out.println(pubuser.getPublisher().getPubName());
			
			System.out.println("==========");
			
			
			// ==查詢== 從部門帶出關聯的員工們資料(多筆)
			Publisher publisher = session.get(Publisher.class,2);
			for(PubUser pu : publisher.getPubusers()) {
				System.out.println(pu.getPubUserID() + "" +pu.getPubNickname());
			}
			
			
			//==新增== 建立要新增的部門資料
			
			
			
			
			session.getTransaction().commit();
			
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}finally {
			HibernateUtil.shutdown();
		}
	}

}
