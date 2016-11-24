package com.xtc.dao;


import java.util.List;

import com.xtc.entity.Park;
import com.xtc.entity.favorite;

public interface IfavoriteDao {
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
	
	public List<Object> getUserfavorite(String userid);
}
