package com.tha103.newview.cityAdress.model;

import java.util.List;

import com.tha103.newview.act.model.Act;

public interface CityAdressDAO {

	void insert(CityAdress act);

	void update(CityAdress act);

	void delete(Integer ActID);

	CityAdress findByPrimaryKey(Integer ActID);

	List<CityAdress> getAll();
}
