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
 * ����ҵ
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
	   * ��������
	   */
	  String url = "http://222.73.117.158/msg/HttpBatchSendSM";// Ӧ�õ�ַ
	  String account = "vip-xbtc1";// �˺�
	  String pswd = "Tch917428";// ����
	  boolean needstatus = true;// �Ƿ���Ҫ״̬���棬��Ҫtrue������Ҫfalse
	  String extno = null;// ��չ��
	/**
	 * ��ҵ�˵�¼
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
						System.out.println(list+"�޸�regid");
						Map<String, Object> extra = new HashMap<String, Object>();
						extra.put("isadmin", isadmin);
						extra.put("first",first);
						mapRtn.put(RestDto.RESULT,extra);
					}else{
						jpushinfo jpush = new jpushinfo();
						jpush.setUserid(userid);
						jpush.setRegid(regid);
						boolean list = this.jservice.insert(jpush);
						System.out.println(list+"�õ�regid");
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
			System.out.println("��ҵ�˵�¼");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * ��һ�ε�¼�޸�����
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
			String userpsw = MD5Util.encode(password);//�����µ�����
			if(mobile!=null){
				 boolean register = service.updatesysuserpwd(isfirsts,userpsw,mobile);
				 mapRtn.put(RestDto.RESULT,register);//�޸�����
			}
		} catch (Exception e) {
			System.out.println("��һ�ε�¼�޸�����");
			mapRtn.put(RestDto.RESULT,false);//�޸�����
		}
		return mapRtn;
	}
	  //�һ����뷢�Ͷ���
	  @RequestMapping(value="sendauthcode",method=RequestMethod.POST)
	  public @ResponseBody Map<String, Object> sendauthcode(String mobile){
		  Map<String, Object> mapRtn = new HashMap<String, Object>();
		  try {
			  String authCode = RandomStringUtils.randomNumeric(6);//�õ�������֤��
			  String users=service.selectmobile(mobile);//�ж��ֻ������Ƿ����
			  if(users!=null){
				  String msg = "���ã�������֤����"+authCode+"����ӭ����ʹ�á�";//��������
				  HttpSender.batchSend(url,account,pswd,mobile,msg,needstatus,extno);
				  System.out.println(authCode+"�һ�����");
				  boolean userss=userService.updateauth_code(authCode,mobile);
				  mapRtn.put(RestDto.RESULT,userss);
			  }else{
				  System.out.println("�ֻ����벻����");
				  mapRtn.put(RestDto.RESULT,false);//�ֻ����벻����
			  }
		} catch (Exception e) {
			System.out.println("�ֻ����벻����1");
			mapRtn.put(RestDto.RESULT,false);//�ֻ����벻����
		}
		  return mapRtn;
	  }
	  /**
	   * �һ�����
	   * @param mobile
	   * @param password
	   * @param authCode
	   * @return
	   */
	@RequestMapping(value="forget",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> forget(String mobile,String password,String authCode){
	    Map<String, Object> mapRtn = new HashMap<String, Object>();
	    try {
	    	 String users=service.selectmobile(mobile);//�ж��ֻ������Ƿ����
	    	if(users!=null){
	    		sysuser sys =service.selectsysuserfirst(mobile);
				String first=sys.getFirst();
				String authcode=sys.getAuth_code();
				int firsts=Integer.valueOf(first);
				int isfirst=firsts+1;
				String isfirsts=String.valueOf(isfirst);
				String userpsw = MD5Util.encode(password);//�����µ�����	
				if(authcode.equals(authCode)){
					   boolean register = service.updatesysuserpwd(isfirsts,userpsw,mobile);
					   System.out.println("�޸�����ɹ�");
					   mapRtn.put(RestDto.RESULT,register);//�޸�����
				   }else{
					   System.out.println("��֤�����");
					   mapRtn.put(RestDto.RESULT,false);//��֤�����
				   }
	    	}else{
	    		 mapRtn.put(RestDto.RESULT,false);//��֤�����
	    	}
		} catch (Exception e) {
			System.out.println("�һ�����  forget");
			 mapRtn.put(RestDto.RESULT,false);//��֤�����
		}
		return mapRtn;
	  }
	/**
	 * ��ѯͣ����
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
			System.out.println("��ѯͣ����");
			 mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
}
