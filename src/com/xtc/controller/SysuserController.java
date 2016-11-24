package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Park;
import com.xtc.entity.jpushinfo;
import com.xtc.entity.sysuser;
import com.xtc.service.IUserService;
import com.xtc.service.IjpushinfoService;
import com.xtc.service.SysuserService;
import com.xtc.util.RestDto;
import com.xtc.utils.HttpSender;
import com.xtc.utils.MD5Util;
/**
 * 享泊物业
 * @author Administrator
 *
 */
@Controller
@RequestMapping("sysuser")
public class SysuserController {
	@Autowired
	private IUserService userService;
	@Autowired
	private SysuserService service;
	@Autowired
	private IjpushinfoService jservice;
	  /**
	   * 创蓝短信
	   */
	  String url = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
	  String account = "vip-xbtc1";// 账号
	  String pswd = "Tch917428";// 密码
	  boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
	  String extno = null;// 扩展码
	/**
	 * 物业端登录
	 * @param mobile
	 * @param password
	 * @param regid
	 * sysuser/login.action?mobile=13818413839&password=123456&regid=9999999999
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> login(String mobile,String password,String regid){
    Map<String, Object> mapRtn = new HashMap<String, Object>();
    	try{
			String syspwd = MD5Util.encode(password);
			sysuser user=service.getsysuserInfo(mobile,syspwd);
			if(user!=null){
				String isadmin = user.getIs_admin();
				String userid = user.getId();
				String first=user.getFirst();
				if(regid==null || regid.equals("")){
					Map<String, Object> extra = new HashMap<String, Object>();
					extra.put("isadmin",isadmin);
					extra.put("first",first);
					mapRtn.put(RestDto.RESULT,extra);
				}else{
					String regisd=jservice.selectByregid(userid);
					if(regisd!=null){
						boolean list = this.jservice.updatejpushinfo(regid,userid);
						System.out.println(list+"修改regid");
						Map<String, Object> extra = new HashMap<String, Object>();
						extra.put("isadmin", isadmin);
						extra.put("first",first);
						mapRtn.put(RestDto.RESULT,extra);
					}else{
						jpushinfo jpush = new jpushinfo();
						jpush.setUserid(userid);
						jpush.setRegid(regid);
						boolean list = this.jservice.insert(jpush);
						System.out.println(list+"得到regid");
						Map<String, Object> extra = new HashMap<String, Object>();
						extra.put("isadmin", isadmin);
						extra.put("first",first);
						mapRtn.put(RestDto.RESULT,extra);
					}
				}
			}else{
				Map<String, Object> extra = new HashMap<String, Object>();
				extra.put("isadmin", null);
				extra.put("first",null);
				mapRtn.put(RestDto.RESULT,extra);
			}
		} catch (Exception e) {
			System.out.println("物业端登录");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * 第一次登录修改密码
	 * @param mobile
	 * @param password
	 * @return
	 */
	@RequestMapping(value="sendforget",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> sendforget(String mobile,String password){
    Map<String, Object> mapRtn = new HashMap<String, Object>();
    	try {
			sysuser sys =service.selectsysuserfirst(mobile);
			String first=sys.getFirst();
			int firsts=Integer.valueOf(first);
			int isfirst=firsts+1;
			String isfirsts=String.valueOf(isfirst);
			String userpsw = MD5Util.encode(password);//加密新的密码
			if(mobile!=null){
				 boolean register = service.updatesysuserpwd(isfirsts,userpsw,mobile);
				 mapRtn.put(RestDto.RESULT,register);//修改密码
			}
		} catch (Exception e) {
			System.out.println("第一次登录修改密码");
			mapRtn.put(RestDto.RESULT,false);//修改密码
		}
		return mapRtn;
	}
	  //找回密码发送短信
	  @RequestMapping(value="sendauthcode",method=RequestMethod.POST)
	  public @ResponseBody Map<String, Object> sendauthcode(String mobile){
		  Map<String, Object> mapRtn = new HashMap<String, Object>();
		  try {
			  String authCode = RandomStringUtils.randomNumeric(6);//得到短信验证码
			  String users=service.selectmobile(mobile);//判断手机号码是否存在
			  if(users!=null){
				  String msg = "您好，您的验证码是"+authCode+"，欢迎您的使用。";//短信内容
				  HttpSender.batchSend(url,account,pswd,mobile,msg,needstatus,extno);
				  System.out.println(authCode+"找回密码");
				  boolean userss=userService.updateauth_code(authCode,mobile);
				  mapRtn.put(RestDto.RESULT,userss);
			  }else{
				  System.out.println("手机号码不存在");
				  mapRtn.put(RestDto.RESULT,false);//手机号码不存在
			  }
		} catch (Exception e) {
			System.out.println("手机号码不存在1");
			mapRtn.put(RestDto.RESULT,false);//手机号码不存在
		}
		  return mapRtn;
	  }
	  /**
	   * 找回密码
	   * @param mobile
	   * @param password
	   * @param authCode
	   * @return
	   */
	@RequestMapping(value="forget",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> forget(String mobile,String password,String authCode){
	    Map<String, Object> mapRtn = new HashMap<String, Object>();
	    try {
	    	 String users=service.selectmobile(mobile);//判断手机号码是否存在
	    	if(users!=null){
	    		sysuser sys =service.selectsysuserfirst(mobile);
				String first=sys.getFirst();
				String authcode=sys.getAuth_code();
				int firsts=Integer.valueOf(first);
				int isfirst=firsts+1;
				String isfirsts=String.valueOf(isfirst);
				String userpsw = MD5Util.encode(password);//加密新的密码	
				if(authcode.equals(authCode)){
					   boolean register = service.updatesysuserpwd(isfirsts,userpsw,mobile);
					   System.out.println("修改密码成功");
					   mapRtn.put(RestDto.RESULT,register);//修改密码
				   }else{
					   System.out.println("验证码错误！");
					   mapRtn.put(RestDto.RESULT,false);//验证码错误
				   }
	    	}else{
	    		 mapRtn.put(RestDto.RESULT,false);//验证码错误
	    	}
		} catch (Exception e) {
			System.out.println("找回密码  forget");
			 mapRtn.put(RestDto.RESULT,false);//验证码错误
		}
		return mapRtn;
	  }
	/**
	 * 查询停车场
	 * @param name
	 * @return
	 */
	@RequestMapping(value="selectNameOrAddress",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> selectNameOrAddress(String name){
	    Map<String, Object> mapRtn = new HashMap<String, Object>();
	    try {
			List<Park> list= service.selectByParkName(name);
			if(null!=list){
				mapRtn.put(RestDto.RESULT,list);
			}
		} catch (Exception e) {
			System.out.println("查询停车场");
			 mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
}
