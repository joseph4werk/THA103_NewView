package com.tha103.newview.discount.model;

import java.util.List;

public interface DiscountDAO {
	
	public int insert(DiscountVO DiscountVO);
    public int update(DiscountVO DiscountVO);
    public int delete(Integer discountNO);
    public DiscountVO findByPrimaryKey(Integer discountNO);
    public List<DiscountVO> getAll();
    
//	萬用複合查詢(傳入參數型態Map)(回傳List)
//  public List<DiscountVO> getAll(Map<String, String[]> map);
}
