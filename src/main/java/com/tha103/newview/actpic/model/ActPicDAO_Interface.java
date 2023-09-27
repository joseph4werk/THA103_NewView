package com.tha103.newview.actpic.model;

import java.util.List;

public interface ActPicDAO_Interface {
	public void insert(ActPicVO actPicVO);
    
    public void delete(Integer actPicID);
    public ActPicVO findByPrimaryKey(Integer actPicID);
    public List<ActPicVO> getAll();
}
