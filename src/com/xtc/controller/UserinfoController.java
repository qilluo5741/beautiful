package com.xtc.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.Userinfo;
import com.xtc.service.IUserinfoService;
import com.xtc.utils.MD5Util;
/**
 * webµÇÂ¼
 * @author Administrator
 */
@Controller
@RequestMapping("userinfo")
public class UserinfoController {
	@Autowired
	private IUserinfoService us;
	@Autowired
	private HttpSession session;
	/**
	 * µÇÂ¼
	 * @param map
	 * @return
	 */
	@RequestMapping("login")
	public @ResponseBody String login(ModelMap model,String userName,String userPwd){
		try {
			String password = MD5Util.encode(userPwd);
			Userinfo user = us.login(userName,password);
			if(null != user){
				session.setAttribute("user",user);
				return "1";
			}else{
				return "0";
			}
		} catch (Exception e) {
			return "4";
		}
	}
}
