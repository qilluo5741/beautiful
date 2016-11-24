package com.xtc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Park;
import com.xtc.entity.User;
import com.xtc.entity.favorite;
import com.xtc.entity.dto.favoriteDto;
import com.xtc.entity.dto.parkDto;
import com.xtc.service.IUserService;
import com.xtc.service.IfavoriteService;
import com.xtc.service.IparkService;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("favorite")
public class FavoriteController {
	@Autowired
	private IfavoriteService favservice;
	@Autowired
	private IUserService userService;
	@Autowired
	private IparkService parkservice;
	//添加收藏
	@RequestMapping("addfavorite")
	public @ResponseBody Map<String, Object> addfavorite(ModelMap map,String mobil,String name){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = userService.selectBymobil(mobil);
			String userid=userlist.get(0).getId();//根据手机号码获取用户id
			Park park=parkservice.selectByOneName(name);
			String parkId=park.getId();
			if(mobil!=null && name!=null)
			{   favorite favorite= new favorite();
				favorite.setUserId(userid);
				favorite.setParkId(parkId);
				favorite.setLocation_x("0.000000");
				favorite.setLocation_y("0.000000");
				boolean list = favservice.insert(favorite);
				mapRtn.put(RestDto.SUCCESS,list);
			}
		} catch (Exception e) {
			System.out.println("添加收藏");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
	/**
	 * 查询收藏记录
	 * @param mobil
	 * @return
	 */
	@RequestMapping("getfavorite")
	public @ResponseBody Map<String, Object> getfavorite(String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = userService.selectBymobil(mobil);
			String userid=userlist.get(0).getId();//根据手机号码获取用户id
			List<Object> flist = favservice.getfavorite(userid);
			List<parkDto> plist = new  ArrayList<parkDto>();
			for (int i = 0; i <flist.size(); i++) {
				Object [] o = (Object[]) flist.get(i);
				parkDto p = new parkDto();
				p.setId((String) o[0]);
				p.setName((String) o[1]);
				p.setAddress((String) o[2]);
				plist.add(p);
			}
			mapRtn.put(RestDto.SUCCESS,plist);
		} catch (Exception e) {
			System.out.println("异常处理完成！");
		}
		return mapRtn;
	}
	/**
	 * 删除收藏记录
	 * @param map
	 * @param mobile
	 * @param name
	 * @return
	 */
	@RequestMapping("delectfavorite")
	public @ResponseBody Map<String, Object> delectfavorite(ModelMap map,String mobile,String name){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		List<User> userlist = userService.selectBymobil(mobile);
		String userid=userlist.get(0).getId();//根据手机号码获取用户id
		Park park = parkservice.selectByOneName(name);
		String parkid=park.getId();
		if(name!=null){  
			boolean list = favservice.deletefavorite(parkid,userid);
			mapRtn.put(RestDto.SUCCESS,list);
		}
		return mapRtn;
	}
	/**
	 * 
	 * @param map
	 * @param mobile
	 * @param name
	 * @return
	 */
	@RequestMapping("selectfavorite")
	public @ResponseBody Map<String, Object> selectfavorite(ModelMap map,String mobile,String name){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = userService.selectBymobil(mobile);
			String userid=userlist.get(0).getId();//根据手机号码获取用户id
			Park park=parkservice.selectByOneName(name);
			String parkId=park.getId();
			favorite favor = favservice.favor(userid, parkId);
			if(null != favor){
				mapRtn.put(RestDto.SUCCESS,1);
			}
			else{
				mapRtn.put(RestDto.RESULT,0);
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
	
	/**
	 * 查询是否被收藏高德
	 */
	@RequestMapping("getparkfavorite")
	public @ResponseBody Map<String,Object> getparkfavorite(ModelMap map,String mobile,String parkid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		List<User> userlist = userService.selectBymobil(mobile);
		String userid=userlist.get(0).getId();//根据手机号码获取用户id
		favorite favo=favservice.getParkfavorite(parkid,userid);
		if(favo!=null){
			mapRtn.put(RestDto.RESULT,1);//已经收藏
		}else{
			mapRtn.put(RestDto.RESULT,0);//未收藏
		}
		return mapRtn;
	}
	/**
	 * 添加收藏以及取消收藏高德
	 */
	@RequestMapping("addordelfavorite")
	public @ResponseBody Map<String,Object> addordelfavorite(ModelMap map,String mobile,String parkid,String location_x,String location_y){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		List<User> userlist = userService.selectBymobil(mobile);
		String userid=userlist.get(0).getId();//根据手机号码获取用户id
		favorite favo=favservice.getParkfavorite(parkid,userid);
		if(favo!=null){
			boolean list = favservice.deletefavorite(parkid,userid);
			mapRtn.put(RestDto.RESULT,list);
		}else{
			favorite favorite= new favorite();
			favorite.setUserId(userid);
			favorite.setParkId(parkid);
			favorite.setLocation_x(location_x);
			favorite.setLocation_y(location_y);
			boolean list = favservice.insert(favorite);
			mapRtn.put(RestDto.RESULT,list);
		}
		return mapRtn;
	}
	/**
	 * 查询改用户的收藏记录
	 */
	@RequestMapping("getCollection")
	public @ResponseBody Map<String, Object> getCollection(String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = userService.selectBymobil(mobile);
			String userid=userlist.get(0).getId();//根据手机号码获取用户id
			List<Object> flist = favservice.getUserfavorite(userid);
			List<favoriteDto> plist = new  ArrayList<favoriteDto>();
			for (int i = 0; i <flist.size(); i++) {
				Object [] o = (Object[]) flist.get(i);
				favoriteDto p = new favoriteDto();
				p.setId((String) o[0]);
				p.setName((String) o[1]);
				p.setAddress((String) o[2]);
				p.setLocation_x((String) o[3]);
				p.setLocation_y((String) o[4]);
				plist.add(p);
			}
			mapRtn.put(RestDto.RESULT,plist);
		} catch (Exception e){
			System.out.println("异常处理完成！");
		}
		return mapRtn;
	}
}
