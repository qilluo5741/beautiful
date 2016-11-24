package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Account;
import com.xtc.entity.Relatedrecord;
import com.xtc.entity.sysuser;
import com.xtc.service.IAccountService;
import com.xtc.service.RelatedrecordService;
import com.xtc.service.SysuserService;
import com.xtc.service.tollRecordsService;
import com.xtc.util.CreateInviteCode;
import com.xtc.util.RestDto;
import com.xtc.utils.HttpSender;
/**
 * 享泊物业
 * @author Administrator
 */
@Controller
@RequestMapping("related")
public class RelatedrecordController {
	@Autowired
	private tollRecordsService tservice;
	@Autowired
	private RelatedrecordService service;
	@Autowired
	private SysuserService sysservice;
	@Autowired
	private IAccountService accservice;
	@Autowired
	private SysuserService uservice;
	 /**
	  * 创蓝短信
	  */
 	  String url = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
	  String account = "vip-xbtc1";// 账号
	  String pswd = "Tch917428";// 密码
	  boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
	  String extno = null;// 扩展码
	/**
	 * 关联物业
	 * @param parkid
	 * @param name
	 * @param mobile
	 * @param recordphone
	 * related/addrelated.action?parkid=297ebe0e53a3aeb80153a3b0c1a50008&name=1346&mobile=17777777777&recordphone=15836023146
	 * @return
	 */
	@RequestMapping(value="addrelated",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> addRelatedrecord(String parkid,String name,String mobile,String recordphone){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(parkid!=null && name!=null && mobile!=null && recordphone!=null){
				String phone=tservice.selectBymobile(mobile);
				if(phone==null){
					String invideCode = CreateInviteCode.getRandomChar();
					sysuser user=new sysuser();
					user.setName(name);
					user.setMobile(mobile);
					user.setPassword("e10adc3949ba59abbe56e057f20f883e");
					user.setAge("0");
					user.setIs_owner("0");
					user.setIs_admin("1");//0管理员1物业2保安3超级管理员4收费员
					user.setFirst("1");//第一次登录
					user.setProperty("wy");
					user.setIsdelete("0");
					user.setParkId(parkid);
					user.setInvideCode(invideCode);
					user.setProusertime(new Date());
					boolean list=sysservice.addsysuser(user);
					 if(list){
						  String msg = "欢迎使用享泊管理端，您的账号为当前手机号，默认密码123456,请前往各大平台下载APP，感谢您的使用。";// 短信内容
						  HttpSender.batchSend(url,account,pswd,mobile,msg,needstatus, extno);//创蓝短信使用
						  Relatedrecord record=new Relatedrecord();
						  record.setRecordphone(recordphone);
						  record.setRelatedphone(mobile);//物业手机
						  record.setParkid(parkid);
						  record.setRecordtime(new Date());
						  boolean cordlist=service.addRelatedrecord(record);
						  Date now = new Date();
						  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						  String date_creat = dateFormat.format(now);
						  sysuser ulist=uservice.selectsysuserfirst(mobile);
						  String userid = ulist.getId();
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
									boolean listd = accservice.insert(account);
									mapRtn.put(RestDto.RESULT,listd);
								}
							}
						  mapRtn.put(RestDto.RESULT,cordlist);
						}
				}else{
					boolean sysuer=tservice.updateTollRecord(name,mobile,"e10adc3949ba59abbe56e057f20f883e","0","0","1","1","wy","0",parkid);
					mapRtn.put(RestDto.RESULT,sysuer);
				}
			}
		} catch (Exception e) {
			System.out.println("该物业已经注册了");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
}
