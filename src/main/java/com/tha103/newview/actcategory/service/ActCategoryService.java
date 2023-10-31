package com.tha103.newview.actcategory.service;

import java.util.List;
import java.util.Set;

import com.tha103.newview.act.model.ActVO;
import com.tha103.newview.actcategory.model.ActCategory;
import com.tha103.newview.actcategory.model.ActCategoryDAO;
import com.tha103.newview.actcategory.model.ActCategoryDAOHibernateImpl;
import com.tha103.newview.pubuser.model.PubUserVO;

public class ActCategoryService {

	private ActCategoryDAO dao;

	public ActCategoryService() {
		dao = new ActCategoryDAOHibernateImpl();
	}

	public void addOneActCategory(String actCategoryName) {
		ActCategory actCategory = new ActCategory();

		actCategory.setActCategoryName(actCategoryName);

		dao.insert(actCategory);
		
		System.out.println("add categoryh success!");

	}

	public ActCategory addActCategory(Integer actCategoryID, String actCategoryName, Set<ActVO> acts) {

		ActCategory actCategory = new ActCategory();

		actCategory.setActCategoryID(actCategoryID);
		actCategory.setActCategoryName(actCategoryName);
		actCategory.setActs(acts);

		dao.insert(actCategory);

		return actCategory;
	}

	public ActCategory updateActCategory(Integer actCategoryID, String actCategoryName, Set<ActVO> acts) {

		ActCategory actCategory = new ActCategory();

		actCategory.setActCategoryID(actCategoryID);
		actCategory.setActCategoryName(actCategoryName);
		actCategory.setActs(acts);

		dao.update(actCategory);

		return actCategory;
	}

	public void deleteActCategory(Integer actCategoryID) {
		dao.delete(actCategoryID);
	}
	
	public ActCategory getOneActCategory(Integer actCategoryID) {
		return dao.findByPrimaryKey(actCategoryID);
	}

	public List<ActCategory> getAll() {
		return dao.getAll();
	}

}

//package com.tha103.newview.actcategory.service;
//
//import java.util.List;
//
//import com.tha103.newview.actcategory.model.ActCategory;
//
//public interface ActCategoryService {
//	
//	public int addActCategory(ActCategory ActCategory);
//	public int updateActCategory(ActCategory ActCategory);
//	public int deleteActCategory(ActCategory ActCategory);
//	public List<ActCategory> getAll();
//	
//}
