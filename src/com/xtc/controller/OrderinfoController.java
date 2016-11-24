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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jpush.api.DeviceEnum;
import com.jpush.api.JPushClient;
import com.xtc.entity.Account;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.Ordersecurity;
import com.xtc.entity.User;
import com.xtc.entity.balancedetail;
import com.xtc.entity.jpushinfo;
import com.xtc.entity.parking;
import com.xtc.entity.dto.OrderMyRecord;
import com.xtc.entity.dto.OrderRecord;
import com.xtc.entity.dto.OrderUserRecord;
import com.xtc.entity.dto.OrderinfoRecord;
import com.xtc.entity.dto.orderDto;
import com.xtc.quartz.TaskTimer;
import com.xtc.service.IAccountService;
import com.xtc.service.IOrderinfoService;
import com.xtc.service.IUserService;
import com.xtc.service.IbalancedetailService;
import com.xtc.service.IjpushinfoService;
import com.xtc.util.Pager;
import com.xtc.util.RestDto;
import com.xtc.util.SmsSendClientExample;
/**
 * �û�����
 * @author Administrator
 */
@Controller
@RequestMapping("orderinfo")
public class OrderinfoController {
	@Autowired
	private IOrderinfoService os;
	@Autowired
	private IUserService us;
	@Autowired
	private IAccountService service;
	@Autowired
	private IjpushinfoService jservice;
	@Autowired
	private IbalancedetailService balService;
	
	private static final String appKey ="02598c822dede2bb0f4bac2c";////�������466f7032ac604e02fb7bda89
	
	private static final String masterSecret = "abdc91fcc99cd38b19a4e9c3";//���ÿ��Ӧ�ö���Ӧһ��masterSecret
	
	private static JPushClient jpush = null;
	
	public static final int MAX = Integer.MAX_VALUE;
	
	public static final int MIN = (int) MAX/2;
	private static long timeToLive =  60 * 5; 
	/**
	 * ���� sendNo ��Ψһ�����б�Ҫ��
	 */
	public static int getRandomSendNo() {
	    return (int) (MIN + Math.random() * (MAX - MIN));
	}
	/**
	 * ԤԼ����
	 */ 
	@RequestMapping("insert")
	public @ResponseBody Map<String, Object> insert(ModelMap map,String parkId,String mobile,String park_start_time,String  park_end_time,String plate_no){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			//�����ֻ��Ų鵽userid
			List<User> list = us.selectBymobil(mobile);
			String userid = list.get(0).getId();
			Date date=new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMddHHmmss");//���Է�����޸����ڸ�ʽ
			String num = dateFormat.format(date);
			Orderinfo info = new Orderinfo();
			info.setOrder_num(num);
			info.setParkId(parkId);
			info.setUserId(userid);
			info.setPark_start_time(park_start_time);
			info.setPark_end_time(park_end_time);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			String str=sdf.format(date);  
			info.setOrder_request_time(str);
			info.setService_type("1");
			double money=0;
			info.setMoney(money);
			info.setPlate_no(plate_no);
			info.setPaystatus("3");
			info.setOderstate("3");
			info.setThankcharge(0);//��л��
			info.setSharemoney(0);
			boolean f = os.insert(info);
			if(f){
				Map<String,String> resmap=new HashMap<String, String>();
				resmap.put("order_num",num);
				resmap.put("success",f+"");
				mapRtn.put(RestDto.SUCCESS,resmap);
			}
		} catch (Exception e) {
			System.out.println("ԤԼ����1");
			mapRtn.put(RestDto.SUCCESS,null);
		}
		return mapRtn;
	}
	/**
	 * //ԤԼ�Ѽ۸�۸�
	 * @param map
	 * @return
	 * orderinfo/subscription.action
	 */
	@RequestMapping("subscription")
	public @ResponseBody Map<String, Object> subscription(ModelMap map){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			mapRtn.put("subscription",5);
		} catch (Exception e) {
			System.out.println("ԤԼ�Ѽ۸�۸� ԤԼ�Ѽ۸�۸�");
		}
		return mapRtn;
	}
	/**
	 * orderinfo/getsubscription.action?parkid=
	 * @param parkid
	 * @return
	 */
	@RequestMapping(value="getsubscription",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getsubscription(String parkid){
		 Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			Double subscription=os.getsubscription(parkid);
			Map<String, Object> extra = new HashMap<String, Object>();
			extra.put("subscription",subscription);
			mapRtn.put(RestDto.RESULT,extra);
		} catch (Exception e){
			System.out.println("getsubscription");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	//�¶���
	@RequestMapping("addorder")
	public @ResponseBody Map<String, Object> addorder(ModelMap map,String parkId,String mobile,String  park_start_time,String  park_end_time,String plate_no){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			//�����ֻ��Ų鵽userid
			List<User> list = us.selectBymobil(mobile);
			String userid = list.get(0).getId();
			Date date=new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMddHHmmss");//���Է�����޸����ڸ�ʽ
			String num = dateFormat.format(date);
			Orderinfo info = new Orderinfo();
			info.setOrder_num(num);
			info.setParkId(parkId);
			info.setUserId(userid);
			info.setPark_start_time(park_start_time);
			info.setPark_end_time(park_end_time);
			SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String str=sdff.format(date);  
			info.setOrder_request_time(str);
			info.setService_type("1");
			double money=3;
			info.setMoney(money);
			info.setPlate_no(plate_no);
			info.setPaystatus("0");
			info.setOderstate("2");
			info.setThankcharge(0);//��л��
			info.setSharemoney(0);
			boolean f = os.insert(info);
			if(f){
				Map<String,String> resmap=new HashMap<String,String>();
				resmap.put("order_num",num);
				resmap.put("success",f+"");
				mapRtn.put(RestDto.SUCCESS,resmap);
			}
			else{
				mapRtn.put(RestDto.RESULT,f);
			}
		} catch (Exception e) {
			System.out.println("�¶���");
		}
		return mapRtn;
	}
	//�¶���
	@RequestMapping("addorders")
	public @ResponseBody Map<String, Object> addorders(ModelMap map,String money,String thankcharge,String parkId,String mobile,String  park_start_time,String  park_end_time,String plate_no){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		//�����ֻ��Ų鵽userid
		try{
			List<User> list = us.selectBymobil(mobile);
			String userid = list.get(0).getId();
			Date date=new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyMMddHHmmss");//���Է�����޸����ڸ�ʽ
			String num = dateFormat.format(date);
			double moneys=Double.valueOf(money);
			double thankcharges=Double.valueOf(thankcharge);
			Orderinfo info = new Orderinfo();
			info.setOrder_num(num);
			info.setParkId(parkId);
			info.setUserId(userid);
			info.setPark_start_time(park_start_time);
			info.setPark_end_time(park_end_time);
			SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String str=sdff.format(date);  
			info.setOrder_request_time(str);
			info.setService_type("1");
			info.setMoney(moneys);
			info.setPlate_no(plate_no);
			info.setPaystatus("0");
			info.setOderstate("2");
			info.setThankcharge(thankcharges);//��л��
			info.setSharemoney(0);
			boolean f = os.insert(info);
			if(f){
				Map<String,String> resmap=new HashMap<String,String>();
				resmap.put("order_num",num);
				resmap.put("success",f+"");
				mapRtn.put(RestDto.SUCCESS,resmap);
			}else{
				mapRtn.put(RestDto.RESULT,f);
			}
		} catch (Exception e) {
			System.out.println("addorders'�¶���");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	//���¶���״̬Ϊ���׳ɹ�
	@RequestMapping("updateState")
	public @ResponseBody Map<String, Object> updateState(ModelMap map,String order_num){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		//�����ֻ��Ų鵽userid
		try {
			boolean f = os.updateOrderState(order_num,"3");
			if(f){
				mapRtn.put(RestDto.SUCCESS,f);
			}
		} catch (Exception e) {
			System.out.println("���¶���״̬Ϊ���׳ɹ�");
		}
		return mapRtn;
	}
	//ԤԼ��¼���Ĳ�ѯ
	@RequestMapping("getOrder")
	public @ResponseBody Map<String, Object> orderrecord(String  mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> list = us.selectBymobil(mobile);
			String userid = list.get(0).getId();
			List<OrderRecord> olist  = new  ArrayList<OrderRecord>();
			List<Object> oblist = os.orderrecord(userid);
			for (int i = 0; i < oblist.size(); i++) {
				Object [] o  = (Object[]) oblist.get(i);
				OrderRecord or = new OrderRecord();
				or.setId((String) o[0]);
				or.setName((String) o[1]);
				or.setAddress((String) o[2]);
				or.setStarttime((String) o[3]);
				or.setEndtime((String) o[4]);
				olist.add(or);
			}
			mapRtn.put(RestDto.RESULT,olist);
		} catch (Exception e) {
			System.out.println("ԤԼ��¼���Ĳ�ѯ");
		}
		return mapRtn;
	}
	//�û�ԤԼ��¼��ѯ
	@RequestMapping("myorder")
	public @ResponseBody Map<String, Object> getorder(String mobile,String pageIndex,String pageSize){
		Map<String, Object> mapRtn = new HashMap<String,Object>();
		try {
			List<User> list = us.selectBymobil(mobile);
			String userid = list.get(0).getId();
			List<OrderMyRecord> olist  = new  ArrayList<OrderMyRecord>();
			int index=Integer.parseInt(pageIndex);
			int size=Integer.parseInt(pageSize);
			List<Object> oblist = os.ordercode(userid,index,size);
			for (int i = 0; i < oblist.size(); i++) {
				Object [] o  = (Object[]) oblist.get(i);
				OrderMyRecord or = new OrderMyRecord();
				or.setId((String) o[0]);
				or.setName((String) o[1]);
				or.setAddress((String) o[2]);
				or.setStarttime((String) o[3]);
				or.setEndtime((String) o[4]);
				or.setPaystatus((String) o[5]);
				or.setMoney((double) o[6]);
				or.setOrder_num((String) o[7]);
				or.setOderstate((String) o[8]);
				olist.add(or);
			}
			mapRtn.put(RestDto.RESULT,olist);
		} catch (Exception e) {
			System.out.println("�û�ԤԼ��¼��ѯ");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	//ɾ����¼
	@RequestMapping("delorderid")
	public @ResponseBody Map<String, Object> delorderid(String id){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(id!=null){
				boolean d=	os.deleteOrderid(id);
				mapRtn.put(RestDto.RESULT,d);
			}
		} catch (Exception e) {
			System.out.println("ɾ����¼");
		}
		return mapRtn;
	}
	//�û���¼��ѯ
	@RequestMapping("order")
	public @ResponseBody Map<String, Object> Servliceorder(String mobile,String pageIndex,String pageSize){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
			try {
				User user=us.selectBymobile(mobile);
				String parkid=user.getParkId();
				List<OrderUserRecord> olist  = new ArrayList<OrderUserRecord>();
				int index=Integer.parseInt(pageIndex);
				int size=Integer.parseInt(pageSize);
				System.out.println("-----------------ҳ��------------"+pageIndex);
				System.out.println("-----------------ҳ���С------------"+pageSize);
				List<Object> oblist=os.orderpark(parkid,index,size);
				//���Է�����Ϣ����֪ͨ
				if(oblist!=null){
					for (int i = 0; i < oblist.size(); i++) {
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
						mapRtn.put(RestDto.RESULT,olist);
					}
				}
			} catch (Exception e) {
				System.out.println("�û���¼��ѯ");
			}
		return mapRtn;
	}
	/**
	 *orderinfo/updatestate.action?oderstate=0&order_num=20160726171845&mobile=13818413839
	 */
	//�������ܣ��ܾ�
	@RequestMapping("updatestate")
	public @ResponseBody Map<String, Object> updateOrderstate(String oderstate,String order_num,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			String time = RandomStringUtils.randomNumeric(7);//�����
			String sendNo=time;
			Orderinfo orders=os.selectOneByid(order_num);
			String parkid=orders.getParkId();//������ѯͣ����id
			parking park=os.ByParkingreservation(parkid);
			String reservation=park.getReservation();
			System.out.println("ԤԼ�ֳ�"+reservation);
			double reser=Double.valueOf(reservation);
			Ordersecurity Order=os.ByOrdersecuritysecurity(order_num);
			double ser=Order.getSecurity();
			System.out.println(ser+"���Ͻ�");
			double mon=orders.getMoney();
			double erd=reser*mon/100;
			String oderstare=orders.getOderstate();
			System.out.println("�õ������Ƿ��ڽ���״̬"+oderstare);
			if(oderstate!=null && order_num!=null && oderstare.equals("2")){
				//0�ܾ�״̬ 1�������ܣ�2�Զ�ȡ������ 5�����޸�
				if(oderstate.equals("1")){
					boolean state=os.updateOrderstate(oderstate,order_num);
					if(state){
						jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.Android);
						jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.IOS);
						jpush.setEnableSSL(true);
						//�����ֻ��Ų鵽userid
						List<User> list = us.selectBymobil(mobile);
						String usid = list.get(0).getId();
						Account account=service.selectByUserid(usid);
						double balance=account.getBalance();
						double b=balance+erd;
						boolean list2=service.updateByUserid(usid,b);
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String times=dateFormat.format(now);
						balancedetail balanc=new balancedetail();
						balanc.setUserid(usid);
						balanc.setStarttime(times);
						balanc.setBalancetype("3");
						balanc.setMoney(5);//��������erdԪ��¼
						if(balanc!=null){
							boolean balist = balService.insert(balanc);
							String balancetype=balService.selectBybalancetype(usid);
							if(balancetype!=null){
								String baltype="6";
								boolean ballist=balService.updateBybalancetype(baltype,usid);
								if(ballist){
									double b1=balance+10+erd;
									boolean acclist=service.updateByUserid(usid,b1);
									System.out.println("�������ܵõ�Ǯ"+acclist+"ͬʱ���¼"+balist);
								}
							}
						}
						mapRtn.put(RestDto.RESULT,list2);
						System.out.println("----------------------��������һԪǮ-----------------------");
						List<Orderinfo> order=os.selectuserid(order_num);
						String userid=order.get(0).getUserId();
						List<jpushinfo> jpushs=jservice.selectByUserid(userid);
						String regid=jpushs.get(0).getRegid();
						String umobile=us.Selectmobile(userid);
						System.out.println("ԤԼ�û����ֻ����룺"+umobile);
						SmsSendClientExample.sendUserMessage(umobile,"");
						String aiot="���Ѿ�ԤԼ�ɹ��������Ѹ���Ԥ��λ�ã�ף��ͣ�����!";
						System.out.println(aiot+"------------------------------------");
						jpush.sendRegIdMessage(sendNo,appKey,masterSecret,aiot,regid);
						jpush.sendRegIdMessageTags(sendNo,appKey,masterSecret,aiot,regid);
						TaskTimer.closeJob(order_num);//�رռ�ʱ
					}
				}else if(oderstate.equals("0")){
					boolean state=os.updateOrderstate(oderstate,order_num);
					if(state){
						jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.Android);
						jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.IOS);
						jpush.setEnableSSL(true);
						List<Orderinfo> order=os.selectuserid(order_num);
						String userid=order.get(0).getUserId();
						double money=order.get(0).getMoney();
						double thankcharge=order.get(0).getThankcharge();
						double sharemoney=order.get(0).getSharemoney();
						double sunmoney=money+thankcharge;
						double k=sunmoney-sharemoney+ser;
						Date now = new Date();
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String times=dateFormat.format(now);
						Orderinfo orderw =os.selectOneByid(order_num);
						String useridd=orderw.getUserId();
						balancedetail balanc=new balancedetail();
						balanc.setUserid(useridd);
						balanc.setStarttime(times);
						balanc.setBalancetype("4");//�˿�
						balanc.setMoney(k);
						if(balanc!=null){
							boolean balist = balService.insert(balanc);
							System.out.println(balist);
						}
						Account account=service.selectByUserid(userid);
						List<jpushinfo> jpushs=jservice.selectByUserid(userid);
						String regid=jpushs.get(0).getRegid();
						String umobile=us.Selectmobile(userid);
						System.out.println("�ܾ��û����ֻ�����1��"+umobile);
						String aiot="�Բ�����Ķ����ѱ��ܾ���,���ԤԼ�ѽ���������֮�ڵ����������˻�!";
						SmsSendClientExample.sendisMessage(umobile,"");
						jpush.sendRegIdMessage(sendNo,appKey,masterSecret,aiot,regid);
						jpush.sendRegIdMessageTags(sendNo,appKey,masterSecret,aiot,regid);
						System.out.println("-----------------"+regid);
						double balance=account.getBalance();
						double b=balance+k;
						boolean list2=service.updateByUserid(userid,b);
						mapRtn.put(RestDto.RESULT,list2);
			    		TaskTimer.closeJob(order_num);//�رռ�ʱ
					}
				}
			}else{
				mapRtn.put(RestDto.RESULT,null);
			}
		} catch (Exception e) {
			System.out.println("�������ܣ��ܾ�");
		}
		return mapRtn;
	}
	
	//�±���ԤԼ��¼��ѯ---------------------------------------------------------------------------------------
	@RequestMapping("securityorder")
	public @ResponseBody Map<String, Object> getsecurity(String mobile,String pageIndex,String pageSize){
		Map<String, Object> mapRtn = new HashMap<String,Object>();
		try {
			User user=us.selectBymobile(mobile);
			String parkid=user.getParkId();
			List<OrderUserRecord> olist  = new ArrayList<OrderUserRecord>();
			int index=Integer.parseInt(pageIndex);
			int size=Integer.parseInt(pageSize);
			System.out.println("-----------------ҳ��------------"+pageIndex);
			System.out.println("-----------------ҳ���С------------"+pageSize);
			List<Object> oblist=os.orderpark(parkid,index,size);
			//���Է�����Ϣ����֪ͨ
			if(oblist!=null){
				for (int i = 0; i< oblist.size(); i++) {
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
					mapRtn.put(RestDto.RESULT,olist);
				}
			}
		} catch (Exception e) {
			System.out.println("�±���ԤԼ��¼��ѯ");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * �û�����user
	 * @param mobile
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * orderinfo/userorder.action?mobile=13101089314&pageIndex=0&pageSize=10
	 */
	@RequestMapping("userorder")
	public @ResponseBody Map<String, Object> userorder(String mobile,String pageIndex,String pageSize){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> list = us.selectBymobil(mobile);
			String userid = list.get(0).getId();
			List<OrderMyRecord> olist  = new  ArrayList<OrderMyRecord>();
			int index=Integer.parseInt(pageIndex);
			int size=Integer.parseInt(pageSize);
			List<Object> oblist = os.ordercode(userid,index,size);
			for (int i = 0; i < oblist.size(); i++) {
				Object [] o  = (Object[]) oblist.get(i);
				OrderMyRecord or = new OrderMyRecord();
				or.setId((String) o[0]);
				or.setName((String) o[1]);
				or.setAddress((String) o[2]);
				or.setStarttime((String) o[3]);
				or.setEndtime((String) o[4]);
				or.setPaystatus((String) o[5]);
				or.setMoney((double) o[6]);
				or.setOrder_num((String) o[7]);
				or.setOderstate((String) o[8]);
				or.setThankcharge((double) o[9]);
				or.setSharemoney((double) o[10]);
				or.setSecurity((double) o[11]);
				olist.add(or);
			}
			mapRtn.put(RestDto.RESULT,olist);
		} catch (Exception e) {
			System.out.println("'�û�����user");
			mapRtn.put(RestDto.RESULT,false);
		}	
		return mapRtn;
	}
	
	//������ϸ��¼��ѯ
	@RequestMapping("selectAll")
	public String queryorder(Pager<Orderinfo> pager,ModelMap model){
		//������ʾ������
		pager.setPageSize(20);
		pager.setPageIndex(pager.getPageIndex());
		//ȡ������
		pager=os.selectAll(pager.getPageIndex(),pager.getPageSize());
		model.addAttribute("orderpager",pager);
		//ȡ�ü���
		return "relation/orderManger";
	}
	//���ݶ����Ų�ѯ��������
	@RequestMapping("selectByOrderid")
	public @ResponseBody Map<String, Object> selectByOrderid(String orderid){
	    Map<String, Object> mapRtn = new HashMap<String, Object>();
			try {
				if(orderid==null || orderid.equals("")){
					String r =  "�����쳣";
					mapRtn.put(RestDto.RESULT, r);
				}
				   List<Object> list = os.selectByOrderid(orderid);
				   if(list!=null){
					orderDto od = new orderDto();
					for (int i = 0; i < list.size(); i++)
					{
						Object [] o  = (Object[]) list.get(i);
						od.setOderstate((String) o[0]);
					    od.setSharemoney((double) o[1]);
					    od.setMoney((double) o[2]);
					    od.setThankcharge((double) o[3]);
					    od.setStarttime((String) o[4]);
					    od.setEndtime((String) o[5]);
					    od.setName((String) o[6]);
					    od.setAddress((String) o[7]);
					    mapRtn.put(RestDto.SUCCESS, od);
					}
				}else{
					mapRtn.put(RestDto.SUCCESS, null);
				}
			} catch (Exception e) {
				System.out.println("���ݶ����Ų�ѯ��������");
			}
			return mapRtn;
		}
	/**
	 * ����������������
	 * @param mobile
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * orderinfo/getsecurorder.action?mobile=13101089314&pageIndex=0&pageSize=10
	 */
	@RequestMapping(value="getsecurorder",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getsecurorder(String mobile,String pageIndex,String pageSize){
		Map<String, Object> mapRtn = new HashMap<String,Object>();
		try {
			User user=us.selectBymobile(mobile);
			String parkid=user.getParkId();
			List<OrderinfoRecord> olist  = new ArrayList<OrderinfoRecord>();
			Integer index=Integer.parseInt(pageIndex);
			Integer size=Integer.parseInt(pageSize);
			List<Object> oblist=os.GetOrderinfoRecord(parkid,index,size);
			if(oblist.size()>0){
				for (int i = 0; i< oblist.size(); i++) {
					Object [] ot  = (Object[]) oblist.get(i);
					OrderinfoRecord ort = new OrderinfoRecord();
					ort.setName((String) ot[0]);
					ort.setMobile((String) ot[1]);
					ort.setPark_start_time((String) ot[2]);
					ort.setPark_end_time((String) ot[3]);
					ort.setPay_time((String) ot[4]);
					ort.setPlate_no((String) ot[5]);
					ort.setOderstate((String) ot[6]);
					ort.setOrder_num((String) ot[7]);
					ort.setStarttiming((String) ot[8]);
					ort.setEndtiming((String) ot[9]);
					olist.add(ort);
					mapRtn.put(RestDto.RESULT,olist);
				}
			}else{
				mapRtn.put(RestDto.RESULT,null);
			}
		} catch (Exception e) {
			System.out.println("����������������'");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
 }
