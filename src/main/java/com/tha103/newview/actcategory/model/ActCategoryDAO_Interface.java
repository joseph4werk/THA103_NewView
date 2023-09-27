package com.tha103.newview.actcategory.model;

import java.util.List;

public interface ActCategoryDAO_Interface {
	public void insert(ActCategoryVO actCategoryVO);
    public void delete(Integer ActVO);
    public ActCategoryVO findByPrimaryKey(Integer actCategoryID);
    public List<ActCategoryVO> getAll();
}
