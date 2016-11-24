package com.xtc.service;

import java.util.List;

import com.xtc.entity.Advertisement;

public interface IAdvertisementService {
	public List<Advertisement> listALL();
	public boolean insert(Advertisement advert);
}
