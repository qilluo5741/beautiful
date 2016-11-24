package com.xtc.controller;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.TestInfo;
import com.xtc.entity.User;
import com.xtc.service.ITestService;
import com.xtc.service.IUserService;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("add")
public class TestController  {
	@Autowired
	private ITestService service;
	@Autowired 
	private IUserService us;
	@RequestMapping("test")
	public String login(ModelMap mp){
		TestInfo test=new TestInfo("","zhngsan");
		service.addTest(test);
		return "index";
	}
	@RequestMapping("select")
	public String seletct(ModelMap mp){
		List<User> list = us.selectBymobil("138********");
		System.out.println(list.size());
		return "sss";
	}
	@RequestMapping(value="",method=RequestMethod.POST)
	public 	@ResponseBody Map<String, Object> test(@PathVariable String urlName){
		System.out.println("Œ“ «£∫"+urlName);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("1","hello");
		map.put("2","hello");
		map.put("3","hello");
		return map;
	}

}
