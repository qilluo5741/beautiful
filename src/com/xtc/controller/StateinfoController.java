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

import com.xtc.entity.Stateinfo;
import com.xtc.entity.User;
import com.xtc.service.IStateinfoService;
import com.xtc.service.IUserService;
import com.xtc.service.IparkService;
import com.xtc.util.HttpUtil;
import com.xtc.util.RestDto;
/**
 * 状态机修改
 * @author Administrator
 */
@Controller
@RequestMapping("stateinfo")
public class StateinfoController {
	@Autowired
	private IStateinfoService iss;
	@Autowired
	private IUserService us;
	@Autowired 
	private IparkService ps;
	/**
	 * 修改状态机
	 *同时修高德上的状态
	 * @param map
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("insert")
	public @ResponseBody Map<String, Object> insert(ModelMap map,String mobile,String statetype){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = us.selectBymobil(mobile);
			String userid=userlist.get(0).getId();
			String id=ps.selectgaodemoralid(userid);
			String username = userlist.get(0).getName();
			String parkid= userlist.get(0).getParkId();
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
			String statetime = dateFormat.format( now ); 
			Stateinfo info = new Stateinfo();
			info.setStatetime(statetime);
			info.setStatetype(statetype);
			info.setUsername(username);
			info.setParkid(parkid);
			boolean f = iss.insert(info);
			if (f) {
				boolean fl = ps.updateState(parkid,statetype);
				String key="14607db53fff73bc7ed6e611a6246fc1";
				String tableid="57563285afdf52393935af22";
				String httpUrl="http://yuntuapi.amap.com/datamanage/data/update";
				String httpArg="key="+key+"&tableid="+tableid+"&loctype=2&data={\"_id\":\""+id+"\",\"status\":\""+statetype+"\"}";
				System.out.println(HttpUtil.request_post(httpUrl, httpArg));
				mapRtn.put(RestDto.SUCCESS, fl);
			}
		} catch (Exception e) {
			System.out.println("修改状态机");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	//明显记录查询
	/**
	 * stateinfo/selectall.action?mobile
	 * @param mape
	 * @param mobile
	 * @return
	 */
	@RequestMapping("selectall")
	public @ResponseBody Map<String, Object> selectall(ModelMap mape,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{
			List<User> userlist = us.selectBymobil(mobile);
			String parkid =userlist.get(0).getParkId();
			System.out.println(parkid);
			List<Stateinfo> slist = iss.selectAll(parkid);
			System.out.println(slist);
			mapRtn.put(RestDto.RESULT,slist);
		}catch (Exception e) {
			System.out.println("明显记录查询'");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
}
