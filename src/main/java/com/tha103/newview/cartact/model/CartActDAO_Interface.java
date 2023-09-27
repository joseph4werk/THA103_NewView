package com.tha103.newview.cartact.model;

import java.util.List;

public interface CartActDAO_Interface {
   public void insert(CartActVO cartActVO);
   public void update(CartActVO cartActVO);
   public void delete(Integer cartActID);
   public CartActVO findByPrimaryKey(Integer cartActID);
   public List<CartActVO> getAll();
	
}
