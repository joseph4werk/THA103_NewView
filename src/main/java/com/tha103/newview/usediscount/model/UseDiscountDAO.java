package com.tha103.newview.usediscount.model;

import java.util.*;

public interface UseDiscountDAO {

	public void insert(UseDiscountVO useDiscountVO);
	public void update(UseDiscountVO useDiscountVO);
	public void delete(Integer useDisID);
	public UseDiscountVO findeByPrimaryKey(Integer useDisID);
	public List<UseDiscountVO> getAll();
//	 萬用複合查詢(傳入參數型態Map)(回傳List)
//	public List<UseDiscountVO> getAll(Map<String, String[]> map);
}
