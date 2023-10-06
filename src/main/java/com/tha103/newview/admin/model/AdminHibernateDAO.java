package com.tha103.newview.admin.model;

import java.util.List;

public interface AdminHibernateDAO {
	int update(Admin admin);
	List<Admin>getAll();
	
}


