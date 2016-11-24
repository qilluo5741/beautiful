package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jpush.api.DeviceEnum;
import com.jpush.api.JPushClient;
import com.xtc.entity.Account;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.TaskInfo;
import com.xtc.entity.User;
import com.xtc.entity.balancedetail;
import com.xtc.entity.jpushinfo;
import com.xtc.entity.dto.AccountDto;
import com.xtc.entity.dto.OrderUserRecord;
import com.xtc.entity.dto.orderadminandstatus;
import com.xtc.quartz.CancelTheOrder;
import com.xtc.quartz.TaskTimer;
import com.xtc.service.IAccountService;
import com.xtc.service.IOrderinfoService;
import com.xtc.service.IUserService;
import com.xtc.service.IbalancedetailService;
import com.xtc.service.IjpushinfoService;
import com.xtc.util.RestDto;
import com.xtc.util.SmsSendClientExample;
/**
 * �û��˻����֧��
 * @author Administrator
 */
@Controller
@RequestMapping("account")
public class AccountController{
	//�ڼ���ע���ϴ�Ӧ�õ� appKey �� masterSecret
	private static final String appKey ="02598c822dede2bb0f4bac2c";////�������466f7032ac604e02fb7bda89
	private static final String masterSecret = "abdc91fcc99cd38b19a4e9c3";//���ÿ��Ӧ�ö���Ӧһ��masterSecret
	private static JPushClient jpush = null;
	private static long timeToLive =  60 * 5; 
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX/2;
	@Autowired
	private IAccountService service;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrderinfoService os;
	@Autowired
	private IjpushinfoService jservice;
	@Autowired
	private IOrderinfoService orderservice;
	@Autowired
	private IbalancedetailService balService;
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * ����userid�鿴
	 * @return
	 */
	@RequestMapping("selectByUserid")
	public @ResponseBody Map<String, Object> getByUserid(ModelMap map,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{
			List<User> userlist = userService.selectBymobil(mobil);
			String userid = userlist.get(0).getId();
			Account account=service.selectByUserid(userid);
			AccountDto act = new AccountDto();
			if(null != account){
				act.setBalance(account.getBalance());
				act.setFreezeMoney(account.getFreezeMoney());
			}
			mapRtn.put(RestDto.RESULT, act);
		}catch (Exception e){
			System.out.println("����userid�鿴");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	//�������Լ�������ɵ�״̬��ʱ��Ĵ���
	@RequestMapping("updateByUserid")
	public @ResponseBody Map<String, Object> updateByUserid(ModelMap map,String mobil,String orderNum){
	    Map<String, Object> mapRtn = new HashMap<String, Object>();
	    try {
			List<Orderinfo> order=orderservice.selectuserid(orderNum);
			String paystatu=order.get(0).getPaystatus();
			if(paystatu.equals("0")){
				double money=order.get(0).getMoney();
				double thankcharge=order.get(0).getThankcharge();
				double sharemoney=order.get(0).getSharemoney();
				double security=order.get(0).getSecurity();
				double sunmoney=money+thankcharge+security;
				double k=sunmoney-sharemoney;
				System.out.println(k+"Ԫ");
				List<User> userlist = userService.selectBymobil(mobil);
				String userid = userlist.get(0).getId();//�����ֻ������ȡuserid
				Account account=service.selectByUserid(userid);//����userid��ȡ�˻�
				double balance = account.getBalance();//�û������
				if(balance>=k){//�ж��û�����Ƿ������Ҫ֧�����
					double balances = balance-k;
					boolean balan = service.updateByUserid(userid,balances);//�۳����
					String exchange_time = dateFormat.format(now);//����ʱ��
					String pay_time = dateFormat.format(now);//֧��ʱ��
					String pay_type="3";//֧������(0֧������1΢�š�2���п���3���)
					String service_type="3";//���ͣ�1ԤԼ 2���� 3ͣ�� 4֧����
					String paystatus="1";//֧��״̬(0:δ֧����1����֧����2���˿�)
					if(balan){//����֧�����״̬
						boolean result = os.updateOrder(exchange_time,pay_time,pay_type,service_type,paystatus,orderNum);
						if(result){
							balancedetail balanc=new balancedetail();
							balanc.setUserid(userid);
							balanc.setStarttime(exchange_time);
							balanc.setBalancetype("2");
							balanc.setMoney(k);
							if(balanc!=null){
								boolean balist = balService.insert(balanc);
								System.out.println(balist);
							}
						}
						TaskInfo ta=new TaskInfo();
						ta.setJobName(orderNum);
						ta.setOrderNo(orderNum);
						TaskTimer.addJob(ta,CancelTheOrder.class,300);
						//������ɺ�
						List<Object> oblists=orderservice.orderisadminandstatus(orderNum);//��ѯͣ��������
						for(int h = 0; h <oblists.size();h++){
							String uid = (String)oblists.get(h);
							List<Object> oblistss=orderservice.orderstatus(uid);
							List<orderadminandstatus> olistss = new ArrayList<orderadminandstatus>();
							for (int j = 0; j < oblistss.size(); j++){
								Object [] o  = (Object[]) oblistss.get(j);
								orderadminandstatus or = new orderadminandstatus();
								or.setIs_admin((String) o[0]);
								or.setStatus((String)o[1]);
								olistss.add(or);
							}//��ѯͣ���������Ƿ�����
							if(olistss.get(0).getIs_admin().equals("2") || olistss.get(0).getIs_admin().equals("1") || olistss.get(0).getIs_admin().equals("3") && olistss.get(0).getStatus().equals("Y")){
								jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.Android);
								jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.IOS);
								jpush.setEnableSSL(true);
								List<OrderUserRecord> olist  = new ArrayList<OrderUserRecord>();
								List<Object> oblist=orderservice.orderJpush(orderNum);
								//���Է�����Ϣ����֪ͨ
								if(oblist!=null){
									for (int i = 0; i <oblist.size();i++){
										Object [] ot  = (Object[]) oblist.get(i);
										OrderUserRecord ort = new OrderUserRecord();
										ort.setName((String) ot[0]);
										ort.setMobile((String) ot[1]);
										ort.setPark_start_time((String) ot[2]);
										ort.setPark_end_time((String) ot[3]);
										ort.setPay_time((String) ot[4]);
										ort.setPlate_no((String) ot[5]);
										ort.setOderstate((String) ot[6]);
										ort.setOrder_num((String) ot[7]);
										olist.add(ort);
									}
									String Plate =olist.get(h).getPlate_no().toString();
									System.out.println("���ƺţ�------------"+Plate);
								}
								String time = RandomStringUtils.randomNumeric(7);//�����
							    String sendNo=time;//��ȡ����id
							    List<Orderinfo> orders=os.selectuserid(orderNum);
								String parkid=orders.get(0).getParkId();
								List<User> userlists = userService.getByparkid(parkid);
								String idss=userlists.get(0).getId();
								String umobile=userService.Selectmobile(idss);
								List<jpushinfo> jpushs=jservice.selectByUserid(idss);
								String regid=jpushs.get(0).getRegid();//��ȡregid
								String Plate=olist.get(0).getPlate_no();//�õ����ƺ�
								SmsSendClientExample.sendMessage(umobile, Plate);
								System.out.println("���֧�����͵��ֻ����룺"+umobile);
								String urt="����Ϊ"+Plate+"���û�ԤԼ������ͣ����!="+orderNum;//���͵�����
								String urts="����Ϊ"+Plate+"���û�ԤԼ������ͣ����!";//���͵�����
								jpush.sendRegIdMessage(sendNo,appKey,masterSecret,urt,regid);
								jpush.sendRegIdMessageTags(sendNo,appKey,masterSecret,urts,regid);
							 }
						}
					}
					mapRtn.put(RestDto.SUCCESS,true);	
				}else{
					mapRtn.put(RestDto.SUCCESS,false);
				}
			}else{
				mapRtn.put(RestDto.SUCCESS,false);
			}
		} catch (Exception e) {
			System.out.println("�������Լ�������ɵ�״̬��ʱ��Ĵ���");
			mapRtn.put(RestDto.SUCCESS,true);	
		}
		return mapRtn;
	}
	/**
	 * ���û���������˻�
	 * @param map
	 * @param mobile
	 * @return
	 */
	@RequestMapping("addaccount")
	public @ResponseBody Map<String, Object> getaccount(ModelMap map,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date_creat = dateFormat.format(now);
			List<User> userlist = userService.selectBymobil(mobile);
			String userid = userlist.get(0).getId();
			Account acc=service.selectByUserid(userid);
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
					boolean list = service.insert(account);
					mapRtn.put(RestDto.SUCCESS,list);
				}
			}else{
				mapRtn.put(RestDto.SUCCESS,false);
			}
		  }catch (Exception e) {
			  System.out.println("���û���������˻�");
		   mapRtn.put(RestDto.SUCCESS,null);
		}
	  return mapRtn;
	}
}
	
