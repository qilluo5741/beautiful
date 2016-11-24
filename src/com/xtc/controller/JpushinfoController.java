package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.User;
import com.xtc.entity.jpushinfo;
import com.xtc.service.IUserService;
import com.xtc.service.IjpushinfoService;
import com.xtc.util.RestDto;
/**
 * 给用户单独传regid
 * @author Administrator
 */
@Controller
@RequestMapping("jpush")
public class JpushinfoController {
	@Autowired
	private IjpushinfoService service;
	@Autowired
	private IUserService uService;
	/**
	 * 传入regid
	 * @param map
	 * @param mobile
	 * @param regid
	 * @return
	 */
	@RequestMapping("addregid")
	public @ResponseBody Map<String, Object> getByUserid(ModelMap map,String mobile,String regid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		if(regid==null){
			mapRtn.put(RestDto.RESULT,null);
		}else{
			List<User> userlist = uService.selectBymobil(mobile);
			String userid = userlist.get(0).getId();
			String regisd=service.selectByregid(userid);
			if(regisd!=null){
				boolean list = this.service.updatejpushinfo(regid,userid);
				System.out.println(list+"修改regid");
				mapRtn.put(RestDto.RESULT,list);
			}else{
				jpushinfo jpush = new jpushinfo();
				jpush.setUserid(userid);
				jpush.setRegid(regid);
				boolean list = this.service.insert(jpush);
				System.out.println(list+"得到regid");
				mapRtn.put(RestDto.RESULT,list);
			}
		}
		return mapRtn;
	}
}
