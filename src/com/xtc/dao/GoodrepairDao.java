package com.xtc.dao;

import java.util.List;

import com.xtc.entity.Goodrepair;


public interface GoodrepairDao {
	public List<Goodrepair> selectGoodrepairAll(String goodproperid);
}
