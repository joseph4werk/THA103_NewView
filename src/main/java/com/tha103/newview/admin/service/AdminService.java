package com.tha103.newview.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tha103.newview.admin.model.AdminVO;
import com.tha103.newview.pubuser.model.PubUserVO;
import com.tha103.newview.admin.model.*;

public class AdminService {

	private AdminHibernateDAO dao;

	public AdminService() {
		dao = new AdminHibernateDAOImpl();
	}
	
	//登入驗證
	public boolean authenticate(String adminAccount, String adminPassword) {
		AdminVO adminLogin = dao.findByAccount(adminAccount);
				
		if(adminLogin != null && adminLogin.getAdminPassword().equals(adminPassword)) {
			System.out.println("比對成功");
			return true;
		}else {
			System.out.println("比對失敗");
			return false;
		}
		
	}

	public AdminVO addAdmin(String adminName, String adminAccount, String adminPassword, 
			String adminEmail) {

		AdminVO adminVO = new AdminVO();
		adminVO.setAdminName(adminName);
		adminVO.setAdminAccount(adminAccount);
		adminVO.setAdminPassword(adminPassword);
		adminVO.setAdminEmail(adminEmail);

		dao.add(adminVO);
		return adminVO;
	}

	public AdminVO updateAdmin(String adminName, String adminAccount, String adminPassword,
			String adminEmail) {

		AdminVO adminVO = new AdminVO();
		adminVO.setAdminName(adminName);
		adminVO.setAdminAccount(adminAccount);
		adminVO.setAdminPassword(adminPassword);
		adminVO.setAdminEmail(adminEmail);

		dao.update(adminVO);
		return adminVO;
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


	
	public List<PubUserVO> getByCompositeQuery(Map<String, String[]> map) {
		
		Map<String, String> query = new HashMap<>();
		// Map.Entry即代表一組key-value
		Set<Map.Entry<String, String[]>> entry = map.entrySet();
		
		for (Map.Entry<String, String[]> row : entry) {
			String key = row.getKey();
			// 因為請求參數裡包含了action，做個去除動作
			if ("action".equals(key)) {
				continue;
			}
			// 若是value為空即代表沒有查詢條件，做個去除動作
			String value = row.getValue()[0]; //getValue拿到一個String陣列，接著[0]取得第一個元素檢查
			if ( value == null ||value.isEmpty() ) {
				continue;
			}
			query.put(key, value);
		}
		System.out.println(query);
		
		return dao.getAllByCQ(query);
	}
	
}
