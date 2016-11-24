package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.xtc.entity.orderpark;
import com.xtc.service.IUserService;
import com.xtc.service.IorderparkService;
import com.xtc.service.IparkService;
import com.xtc.util.RestDto;
/**
 * 需求内容
 * @author Administrator
 */
@Controller
@RequestMapping("orderpark")
public class OrderparkContrller {
	@Autowired
	private IorderparkService orservice;
	@Autowired
	private IUserService userService;
	@Autowired
	private IparkService parkService;
	@RequestMapping("addorderpark")
	public @ResponseBody Map<String, Object> addorderpark(ModelMap map,String mobile,String name,String starttime,String endtime){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = userService.selectBymobil(mobile);//根据手机号码获取用户名字
			String usname=userlist.get(0).getName();//用户名字
			//传过来一个停车场名字
			Park park = parkService.selectByOneName(name);
			String uid=park.getUserid();
			User user=userService.selectOneByuid(uid);
			String sename=user.getName();//保安名字
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String ordertime = dateFormat.format(now);//订单时间
			orderpark orderpark=new orderpark();
			orderpark.setSename(sename);
			orderpark.setUsname(usname);
			orderpark.setOrdertime(ordertime);
			orderpark.setStarttime(starttime);
			orderpark.setEndtime(endtime);
			boolean list = orservice.insert(orderpark);
			mapRtn.put(RestDto.SUCCESS,list);
		} catch (Exception e) {
			System.out.println("addorderpark");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
	//保安留言
	@RequestMapping("updateorderpark")
	public @ResponseBody Map<String, Object> updateorderpark(ModelMap map,String evaluate,String pinId){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		if(evaluate!=null){
			boolean orderpark = orservice.updateorderpark(evaluate,pinId);
			mapRtn.put(RestDto.SUCCESS,orderpark);
		}
		return mapRtn;
	}
	@RequestMapping("updateorderparkr")
	public @ResponseBody Map<String, Object> updateorderparkr(ModelMap map,String evaluatr,String pinId){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		if(evaluatr!=null){
			boolean orderpark = orservice.updateorderparkr(evaluatr,pinId);
			mapRtn.put(RestDto.SUCCESS,orderpark);
		}
		return mapRtn;
	}
	//订单记录
	@RequestMapping("selectorderpark")
	public @ResponseBody Map<String, Object> selectorderpark(ModelMap map,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = userService.selectBymobil(mobile);
			String usname=userlist.get(0).getName();
			List<orderpark> orderpark=orservice.selectBymobile(usname);
			mapRtn.put(RestDto.SUCCESS,orderpark);
		} catch (Exception e) {
			System.out.println("selectorderpark");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
}
