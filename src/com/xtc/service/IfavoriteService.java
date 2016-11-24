package com.xtc.service;

import java.util.List;

import com.xtc.entity.Park;
import com.xtc.entity.favorite;

public interface IfavoriteService {
	public boolean insert(favorite favorite);
	public boolean deletefavorite(String parkid,String userId);
	public List<favorite> getFavorite(String userId);
	public List<Park> getPark(List<favorite> list);
	//收藏记录
	public List<Object> getfavorite(String userid);
	public favorite favor(String userId,String parkId);
	/**
	 * 查询停车场
	 * @param parkid
	 * @param mobile
	 * @return
	 */
	public favorite getParkfavorite(String parkid,String userid);
	/**
	 * 查询收藏记录
	 * @param userid
	 * @return
	 */
	public List<Object> getUserfavorite(String userid);
}
