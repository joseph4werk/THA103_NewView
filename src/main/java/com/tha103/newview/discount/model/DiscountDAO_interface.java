package com.tha103.newview.discount.model;

import java.util.List;

public interface DiscountDAO_interface {
	public void insert(DiscountVO DiscountVO);
    public void update(DiscountVO DiscountVO);
    public void delete(Integer discountNO);
    public DiscountVO findByPrimaryKey(Integer discountNO);
    public List<DiscountVO> getAll();
    
//	萬用複合查詢(傳入參數型態Map)(回傳List)
//  public List<DiscountVO> getAll(Map<String, String[]> map);
}
