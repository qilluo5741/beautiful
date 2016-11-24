package com.xtc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xtc.dao.IfavoriteDao;
import com.xtc.entity.Park;
import com.xtc.entity.favorite;
import com.xtc.service.IfavoriteService;
@Service
@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class FavoriteServiceImpl implements IfavoriteService {
	@Autowired
	private IfavoriteDao dao;
	@Override
	public boolean insert(favorite favorite) {
		return dao.insert(favorite);
	}
	@Override
	public boolean deletefavorite(String parkid,String userId) {
		return dao.deletefavorite(parkid,userId);
	}
	@Override
	public List<favorite> getFavorite(String userId) {
		return dao.getFavorite(userId);
	}
	@Override
	public List<Park> getPark(List<favorite> list) {
		return dao.getPark(list);
	}
	@Override
	public List<Object> getfavorite(String userid) {
		return dao.getfavorite(userid);
	}
	@Override
	public favorite favor(String userId, String parkId) {
		return dao.favor(userId, parkId);
	}
	@Override
	public favorite getParkfavorite(String parkid, String userid) {
		return dao.getParkfavorite(parkid,userid);
	}
	@Override
	public List<Object> getUserfavorite(String userid) {
		return dao.getUserfavorite(userid);
	}
}
