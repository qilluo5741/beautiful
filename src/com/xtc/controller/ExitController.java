package com.xtc.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("exit")
public class ExitController {
	@Autowired
	private HttpSession session;
	
	@RequestMapping("Exit")
	public String queryExitAll(ModelMap map){
		session.invalidate();//删除整个会话
		return "login";
	}
}
