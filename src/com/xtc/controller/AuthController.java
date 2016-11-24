package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.Account;
import com.xtc.entity.User;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.jpushinfo;
import com.xtc.entity.sysuser;
import com.xtc.service.IAccountService;
import com.xtc.service.IUserService;
import com.xtc.service.IjpushinfoService;
import com.xtc.service.SysuserService;
import com.xtc.util.CreateInviteCode;
import com.xtc.util.RestDto;
import com.xtc.utils.HttpSender;
import com.xtc.utils.MD5Util;
/**
 * �û���¼�Լ�ע��
 * @author Administrator
 *
 */
@Controller
public class AuthController {
	  @Autowired
	  private SysuserService sservice;
	  @Autowired
	  private IUserService userService;
	  @Autowired
	  private IAccountService accservice;
	  @Autowired
	  private IjpushinfoService service;
	  @Autowired
	  private HttpServletRequest request;
	  /**
	   * ��������
	   */
  	  String url = "http://222.73.117.158/msg/HttpBatchSendSM";// Ӧ�õ�ַ
	  String account = "vip-xbtc1";// �˺�
	  String pswd = "Tch917428";// ����
	  boolean needstatus = true;// �Ƿ���Ҫ״̬���棬��Ҫtrue������Ҫfalse
	  String extno = null;// ��չ��
	  /**
	   * ��¼
	   * @param mobile
	   * @param password
	   * @return
	   */
	  @RequestMapping("/auth/login")
	  public @ResponseBody Map<String, Object> login(String mobile,String password,String regid){
	    Map<String, Object> mapRtn = new HashMap<String, Object>();
	    try {
			String psw = MD5Util.encode(password);
			User user=userService.getInfo(mobile,psw);
			if(user!=null){
				String isadmin = user.getIs_admin();
				List<User> userlist = userService.selectBymobil(mobile);
				String userid = userlist.get(0).getId();
				if(regid==null || regid.equals("")){
					Map<String, Object> extra = new HashMap<String, Object>();
					extra.put("isadmin", isadmin);
					mapRtn.put(RestDto.RESULT,extra);
				}else{
					String regisd=service.selectByregid(userid);
					userService.deleteinformation(userid);
					if(regisd!=null){
						boolean list = this.service.updatejpushinfo(regid,userid);
						System.out.println(list+"�޸�regid");
						Map<String, Object> extra = new HashMap<String, Object>();
						extra.put("isadmin", isadmin);
						mapRtn.put(RestDto.RESULT,extra);
					}else{
						jpushinfo jpush = new jpushinfo();
						jpush.setUserid(userid);
						jpush.setRegid(regid);
						boolean list = this.service.insert(jpush);
						System.out.println(list+"�õ�regid");
						System.out.println(list);
						Map<String, Object> extra = new HashMap<String, Object>();
						extra.put("isadmin", isadmin);
						mapRtn.put(RestDto.RESULT,extra);
					}
				}
			}else{
				mapRtn.put(RestDto.RESULT,false);
			}
		} catch (Exception e) {
			System.out.println("/auth/login ��¼");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	  }
	  //ע�ᷢ�Ͷ���
	  @RequestMapping(value={"/sms/send-create"})
	  public @ResponseBody Map<String, Object> smsSend(String mobile){
		  Map<String, Object> mapRtn = new HashMap<String, Object>();
		  try{
			  Date now = new Date();
			  String authCode = RandomStringUtils.randomNumeric(6);
			  String invideCode = CreateInviteCode.getRandomChar();
			  System.out.println(invideCode);
			  String users=userService.Bymobilenot(mobile);
			  User us=userService.mobileandpassword(mobile);
			  if(users==null){
				  String msg = "���ã�������֤����"+authCode+"����ӭ����ʹ�á�";// ��������
				  HttpSender.batchSend(url, account, pswd, mobile, msg,needstatus, extno);//��������ʹ��
				  //send(mobile,authCode);//��������Ķ���
				  sysuser user = new sysuser();
				  user.setMobile(mobile);
				  user.setAge("18");
				  user.setSex("��");
				  user.setStatus("Y");
				  user.setDate_created(now);
				  user.setDate_updated(now);
				  user.setNationality("�й�");
				  user.setIs_driver("0");
				  user.setIs_casher("0");
				  user.setIs_proerty_manager("0");
				  user.setIs_guard_manager("0");
				  user.setIs_daibo("0");
				  user.setIs_driver("0");
				  user.setIs_owner("0");
				  user.setIs_valid_mail("0");
				  user.setIs_admin("0");
				  user.setIs_operater("0");
				  user.setDriving_licence_register_date(now);
				  user.setInvideCode(invideCode);
				  user.setProperty("batp");
				  boolean userinfo=sservice.addsysuser(user);
				  if(userinfo){
					  boolean userss=userService.updateauth_code(authCode,mobile);
					  mapRtn.put(RestDto.RESULT,userss);
				  }
				  mapRtn.put(RestDto.RESULT,userinfo);
			  }else if(us!=null){
				  String msg = "���ã�������֤����"+authCode+"����ӭ����ʹ�á�";//��������
				  HttpSender.batchSend(url, account, pswd, mobile, msg,needstatus, extno);//��������ʹ��
				  //send(mobile,authCode);//���Ͷ���
				  boolean userss=userService.updateauth_code(authCode,mobile);
				  mapRtn.put(RestDto.RESULT,userss);
			  }else{
				  String type="1";
				  Map<String, Object> extra = new HashMap<String, Object>();
			      extra.put("type", type);
				  mapRtn.put(RestDto.RESULT,extra);
			  }
		} catch (Exception e) {
			System.out.println("ע�ᷢ�Ͷ���");
			 mapRtn.put(RestDto.RESULT,null);
		}
		  return mapRtn;
	  }
	  
	  //��Ƕ��������
	  //ע��
	  @RequestMapping({"/auth/register"})
	  public @ResponseBody Map<String, Object> register(String mobile,String password,String authCode,String plate_no){
		  Map<String, Object> mapRtn = new HashMap<String, Object>();
		  try{
			   String psw = MD5Util.encode(password);
			   User user=userService.selectBymobile(mobile);
			   String authcode=user.getAuth_code();
			  if(mobile!=null && psw!=null && authcode.equals(authCode)){
				  boolean register = userService.updateregister(psw,mobile);
				  if(register){
					    Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String date_creat = dateFormat.format(now);
						List<User> userlist = userService.selectBymobil(mobile);
						String userid = userlist.get(0).getId();
						Account acc=accservice.selectByUserid(userid);
						if(acc==null){
							Account account = new Account();
							account.setBalance(0);
							account.setUserId(userid);
							account.setNumber("1");
							account.setContent("1");
							account.setDate_creat(date_creat);
							account.setType("1");
							account.setFreezeMoney(0);
							if(account!=null){
								boolean list = accservice.insert(account);
								VehicleLicense vehic=new VehicleLicense();
								vehic.setPlate_no(plate_no);
								vehic.setUserId(userid);
								userService.addVehicleLicense(vehic);
								mapRtn.put(RestDto.RESULT,list);
							}
						}
				  }
				  mapRtn.put(RestDto.RESULT,register);//{"result":true}
			  }else{
				  mapRtn.put(RestDto.RESULT,false);//{"result":false}
			  }
			} catch (Exception e) {
				System.out.println("ע��");
				mapRtn.put(RestDto.RESULT,null);
			}
		  return mapRtn;
	  }
	  //�һ����뷢�Ͷ���
	  @RequestMapping({"/auth/sendforget"})
	  public @ResponseBody Map<String, Object> forgetsms(String mobile){
		  Map<String, Object> mapRtn = new HashMap<String, Object>();
		  try {
			String authCode = RandomStringUtils.randomNumeric(6);//�õ�������֤��
			  User users=userService.mobile(mobile);//�ж��ֻ������Ƿ����
			  if(users!=null){
				  String msg = "���ã�������֤����"+authCode+"����ӭ����ʹ�á�";// ��������
				  HttpSender.batchSend(url, account, pswd, mobile, msg,needstatus, extno);
				  //send(mobile,authCode);//���Ͷ���
				  boolean userss=userService.updateauth_code(authCode,mobile);
				  mapRtn.put(RestDto.RESULT,userss);
			  }else{
				  mapRtn.put(RestDto.RESULT,false);//�ֻ����벻����
			  }
		} catch (Exception e) {
			System.out.println("�һ����뷢�Ͷ���");
		}
		  return mapRtn;
	  }
	  //�һ�����
	  @RequestMapping({"/auth/regforget"})
	  public @ResponseBody Map<String, Object> forgetpassword(String mobile,String password,String authCode){
	       Map<String, Object> mapRtn = new HashMap<String, Object>();
		   new MD5Util(); 
		   try {
			String psw = MD5Util.encode(password);//�����µ�����
			   User user=userService.selectBymobile(mobile);
			   String authcode=user.getAuth_code();
			   if(authcode.equals(authCode)){
				   boolean register = userService.updateregister(psw,mobile);
				   System.out.println("�޸�����ɹ�");
				   mapRtn.put(RestDto.RESULT,register);//�޸�����
			   }else{
				   System.out.println("��֤�����");
				   mapRtn.put(RestDto.RESULT,false);//��֤�����
			   }
		} catch (Exception e) {
			System.out.println("�һ�����");
			mapRtn.put(RestDto.RESULT,null);//�ֻ����벻����
		}
		 return mapRtn;
	  }
	/*
	 * ���˶���
	public static void send(String mobile,String authCode){
	try 
	  { 
		String tp="������֤���ǣ�@����ӭ����ʹ�á���BATPͣ����";
		String result = IOUtils.toString(
		new URL("http://sh2.ipyy.com/sms.aspx?action=send&sendTime=&userid=&account=jksc362"
        + "&password=xiangbo558" + "&mobile=" + 
        mobile + "&content=" + 
        URLEncoder.encode(tp.replace("@",authCode), "utf-8"))
        .openConnection().getInputStream(), "utf-8");
		System.out.println(result);
    }catch (Exception e){
      throw new RuntimeException("Sms Send Error Caused.", e);
    }
  }*/
	//�˳���¼
	 @RequestMapping({"/auth/logout"})
	  public @ResponseBody Map<String, Object> layout(ModelMap map){
		 Map<String, Object> mapRtn = new HashMap<String, Object>();
		 try {
			HttpSession session = request.getSession();
			 session.invalidate();
			 mapRtn.put(RestDto.RESULT,session);
		} catch (Exception e) {
			System.out.println("�쳣������ɣ�");
		}
		return mapRtn;
	 }
}
