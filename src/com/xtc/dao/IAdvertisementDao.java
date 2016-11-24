package com.xtc.dao;

import java.util.List;
import com.xtc.entity.Advertisement;

public interface IAdvertisementDao {
	public List<Advertisement> listALL();
	public boolean insert(Advertisement advert);
}
