package com.xtc.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.util.AlipayNotify;
import com.weixin.config.WeixinConfig;
import com.xtc.entity.Account;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.Park;
import com.xtc.entity.ResultDto;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.WeixinResult;
import com.xtc.entity.balancedetail;
import com.xtc.entity.parkexpense;
import com.xtc.entity.parkexpenses;
import com.xtc.entity.parking;
import com.xtc.entity.paymentrecords;
import com.xtc.entity.sysuser;
import com.xtc.entity.dto.parkexpenseDto;
import com.xtc.entity.dto.skad;
import com.xtc.service.IAccountService;
import com.xtc.service.IOrderinfoService;
import com.xtc.service.IbalancedetailService;
import com.xtc.service.IparkService;
import com.xtc.service.IparkexpenseService;
import com.xtc.service.PaymentrecordsService;
import com.xtc.service.SysuserService;
import com.xtc.util.HttpUtil;
import com.xtc.util.Log;
import com.xtc.util.MD5Util;
import com.xtc.util.RestDto;
import com.xtc.util.SmsSendClientExample;
import com.xtc.util.WeixinUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
@RestController
@RequestMapping("expense")
public class ParkexpenseController{
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private IparkexpenseService service;
	@Autowired
	private IparkService parkService;
	@Autowired
	private SysuserService userservice;
	@Autowired
	private PaymentrecordsService paymenservice;
	@Autowired
	private  IOrderinfoService orderservice;
	@Autowired
	private IAccountService iservice;
	@Autowired
	private IbalancedetailService balService;
	@Autowired
	private HttpSession session;
	/**
	 * localhost:8080/enjoy_park/expense/addexpense.action?licenseplate=A8888&expense=1.0&receivable=3.0&firsttime=2016-07-13 15:15:38&tollRremname=diyisfeikou&parkid=297ebe0e53a3aeb80153a3b0c1a50008
	 * @param licenseplate
	 * @param expense
	 * @param receivable
	 * @param firsttime
	 * @param tollRremname
	 * @param parkid
	 * @return
	 */
	@RequestMapping("addexpense")
	public ResultDto addexpense(String licenseplate,double expense,double receivable,String firsttime,String factorytime,String tollRremname,String parkid,Integer issenior){
		try {
			if(licenseplate!=null && expense>=0 && receivable>=0 && firsttime!=null){
				String ordernumber=RandomStringUtils.randomNumeric(14);//�õ�������
				parkexpense pmo=new parkexpense();
				pmo.setLicenseplate(licenseplate);
				pmo.setExpense(expense);
				pmo.setReceivable(receivable);
				pmo.setFirsttime(firsttime);
				pmo.setFactorytime(factorytime);
				pmo.setParkid(parkid);
				pmo.setChargestatus("0");
				pmo.setTollRremname(tollRremname);
				pmo.setPaymentstatus("0");//δ֧��״̬
				pmo.setOrdernumber(ordernumber);//������
				pmo.setIssenior(issenior);
				boolean list=service.addparkexpense(pmo);
				if(list){
					return new ResultDto(200,"�����ɹ�",list);
				}
			}else{
				return new ResultDto(10001,"�������Ϊ�գ�");
			}
		} catch (Exception e) {
			return new ResultDto(10002,"��������ʧ�ܣ�");
		}
		return null;
	}
	/**
	 * С��ͬ��(ά��)
	 * expense/parkpense.action
	 * @return
	 */
	@RequestMapping(value="parkpense",method=RequestMethod.POST)
	public ResultDto parkpense(){
		try {
			List<Park> list = parkService.selectAll();
			for(Park park : list) {
				String name=park.getName();
				String address=park.getEntry_address();
				String parkid=park.getId();
				String json="json";
				String httpUrl="http://interface.sharebo.cn/sharebodoc/sharebo/shanghai/batp/Commsynchronization?parkid="+URLEncoder.encode(parkid,"UTF-8")+"&commname="+URLEncoder.encode(name,"UTF-8")+"&address="+URLEncoder.encode(address,"UTF-8")+"&_type="+json;
				String res=HttpUtil.request_post(httpUrl,"");
				System.out.println(res);
			}
			return new ResultDto(200,"ͬ���ɹ�!");
		} catch (Exception e) {
			System.out.println("���ؽ���쳣��");
			return new ResultDto(20004,"���ؽ���쳣��!");
		}
	}
	/**
	 * ������ʱ
	 * expense/getordernum.action?order_num=20160420220847
	 * @param order_num
	 * @return
	 */
	@RequestMapping(value="getordernum",method=RequestMethod.POST)
	public ResultDto getordernum(String order_num){
		try{
			String starttiming =orderservice.getOrderinfstarttiming(order_num);
			if(order_num==null){
				return new ResultDto(10001,"�������Ϊ�գ�");
			}else if(starttiming!=null){
				return new ResultDto(10002,"�Ѿ���ʼ��ʱ��");
			}else{
				Orderinfo order=service.selectByOrder(order_num);
				String parkid=order.getParkId();
				String plate_no=order.getPlate_no();
				System.out.println(parkid+"���ƺţ�"+plate_no);
				String json="json";
				String token="7EDEE404C96DA5BBD7E94FF95A669A56";
				String httpUrl="http://interface.sharebo.cn/sharebodoc/sharebo/shanghai/batp/carIn?token="+token+"&parkid="+parkid+"&carNo="+URLEncoder.encode(plate_no,"UTF-8")+"&_type="+json;
				String res=HttpUtil.request_post(httpUrl,"");
				JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
				int state=JSONObject.fromObject(result).getInt("state");
				if(state==200){
					String dtime=JSONObject.fromObject(result).getString("time");
					Map<String,String> resmap=new HashMap<String, String>();
					resmap.put("time",dtime);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					Date date_creat=dateFormat.parse(dtime);
					SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String datecreat = dateForma.format(date_creat);
					orderservice.updateOrderstarttime(datecreat,order_num);
					String userid=orderservice.getOrderinfuserid(order_num);
					Long count =orderservice.getOrderinfocount(userid);
					String mobile=orderservice.getuserinvideCode(userid);//�õ��ֻ�����
					String usid=orderservice.getyaoqinrenuserid(mobile);
					System.out.println(usid+"��Ǯ��userid");
					if(count==1){
						Date now = new Date();
						SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String times=dateFormat1.format(now);
						Account account=iservice.selectByUserid(usid);
						double balance=account.getBalance();
						double b=balance+10;
						iservice.updateByUserid(usid,b);
						balancedetail balanc=new balancedetail();
						balanc.setUserid(usid);
						balanc.setStarttime(times);
						balanc.setBalancetype("5");
						balanc.setMoney(10);
						balService.insert(balanc);
					}else{
						System.out.println("�����Ĳ���Ǯ");
					}
					return new ResultDto(200,"��ʱ�ɹ���",resmap);
				}
				return new ResultDto(20002,"��ʱʧ�ܣ�.");
			}
		} catch (Exception e) {
			System.out.println("���ؽ���쳣��");
			return new ResultDto(20004,"���ؽ���쳣��!");
		}
	}
	/**
	 * ����ʱ��
	 * expense/getordernumout.action?order_num=20160606174417
	 * @param order_num
	 * @return
	 */
	@RequestMapping(value="getordernumout",method=RequestMethod.POST)
	public ResultDto getordernums(String order_num){
		try {
			if(order_num==null){
				return new ResultDto(10001,"�������Ϊ�գ�");
			}else{
				Orderinfo order=service.selectByOrder(order_num);
				String userid=order.getUserId();
				String mobile=service.selectBymobile(userid);
				System.out.println("�����ֻ�����"+mobile);
				String parkid=order.getParkId();//�õ�parkID
				String plate_no=order.getPlate_no();
				System.out.println(plate_no);
				String json="json";
				String token="7EDEE404C96DA5BBD7E94FF95A669A56";
				String httpUrl="http://interface.sharebo.cn/sharebodoc/sharebo/shanghai/batp/carOut?token="+token+"&parkid="+parkid+"&carNo="+URLEncoder.encode(plate_no,"UTF-8")+"&_type="+json;
				String res=HttpUtil.request_post(httpUrl,"");
				System.out.println(res);
				JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
				int state=JSONObject.fromObject(result).getInt("state");
				if(state==200){
					String outtime=JSONObject.fromObject(result).getString("time");
					Map<String,String> resmap=new HashMap<String, String>();
					resmap.put("outtime",outtime);
					SmsSendClientExample.sendchargeMessage(mobile,mobile);//����
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					Date date_endcreat=dateFormat.parse(outtime);
					SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String datecreat = dateForma.format(date_endcreat);
					orderservice.updateOrderenttime(datecreat,order_num);
					parking park=service.selectByparking(parkid);//�õ�name address
					String use=park.getUserid();
					sysuser sys=service.selectByparkmobileing(use);
					String mobiles=sys.getMobile();//�õ��ֻ�����
					parkexpense parkd=service.getorderuserandparkid(plate_no, datecreat);
					String receivable= parkd.getReceivable()+"";
					parkexpenses parkex=new parkexpenses();
					parkex.setMobile(mobiles);
					parkex.setAddress(park.getAddress());
					parkex.setName(park.getName());
					parkex.setPrice(park.getPrice());
					parkex.setLicenseplate(parkd.getLicenseplate());
					parkex.setFirsttime(parkd.getFirsttime());
					parkex.setFactorytime(parkd.getFactorytime());
					parkex.setReceivable(receivable);
					service.addparkexpenses(parkex);
					return new ResultDto(200,"����ɹ���",resmap);
				}else if(state==-2){
					return new ResultDto(20005,"�������շ�ģʽ!");
				}
				return new ResultDto(20003,"����ʱ�����ʧ�ܣ������µ����ʱ!");
			}
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("���ؽ���쳣��");
			return new ResultDto(20004,"���ؽ���쳣��!");
		}
	}
	/**
	
	/**
	 * �û��շѶ���
	 * @param order_num
	 * @return
	 * expense/getorderinfo.action?mobile=13818413839&pageIndex=0&pageSize=10
	 */
	@RequestMapping(value="getorderinfo",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getorderinfo(String mobile,String pageIndex,String pageSize){
			Map<String, Object> mapRtn = new HashMap<String, Object>();
			try {
				sysuser user =userservice.selectsysuserfirst(mobile);
				String userid=user.getId();
				Integer index=Integer.parseInt(pageIndex);
				Integer size=Integer.parseInt(pageSize);
				List<VehicleLicense> Vehicle=service.selectByplate_no(userid);
				for(int i = 0; i < Vehicle.size(); i++) {
					String plate_no=Vehicle.get(i).getPlate_no();
					boolean list=service.updateByparkuserid(userid,plate_no);
					System.out.println(list);
				}
				List<parkexpenseDto> olist  = new  ArrayList<parkexpenseDto>();
				List<Object> oblist=service.selectByexpenseparkexpense(userid,index,size);
				for (int j = 0; j < oblist.size(); j++) {
					Object [] object  = (Object[]) oblist.get(j);
					parkexpenseDto or = new parkexpenseDto();
					or.setParkdeid((String) object[0]);
					or.setLicenseplate((String) object[1]);
					or.setExpense((Double) object[2]);
					or.setReceivable((Double) object[3]);
					or.setFirsttime((String) object[4]);
					or.setFactorytime((String) object[5]);
					or.setPaymentstatus((String) object[6]);
					or.setOrdernumber((String) object[7]);
					or.setName((String) object[8]);
					or.setEntry_address((String) object[9]);
					olist.add(or);
				}
				mapRtn.put(RestDto.RESULT,olist);
			} catch (Exception e) {
				mapRtn.put(RestDto.RESULT,false);
			}
			return mapRtn;
		}
	/**
	 * ֧����¼��ѯ
	 *  expense/getpayment.action?mobile=13101089314&pageIndex=0&pageSize=10
	 */
	@RequestMapping(value="getpayment",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getpayment(String mobile,String pageIndex,String pageSize){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(mobile!=null){
				sysuser user =userservice.selectsysuserfirst(mobile);
				String userid=user.getId();
				Integer index=Integer.parseInt(pageIndex);
				Integer size=Integer.parseInt(pageSize);
				List<paymentrecords> list=paymenservice.selectBypaymentrecords(userid,index,size);
				if(list!=null)
					mapRtn.put(RestDto.RESULT,list);
				}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * ֧����֧��
	 * @param map
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="parkalipay",method=RequestMethod.POST)
	public @ResponseBody String getByalipayprice(ModelMap map) throws UnsupportedEncodingException, InterruptedException{
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();){
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
				: valueStr + values[i] + ",";
			}
			//����������δ����ڳ�������ʱʹ�á����mysign��sign�����Ҳ����ʹ����δ���ת��
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//��ȡ֧������֪ͨ���ز������ɲο������ĵ���ҳ����תͬ��֪ͨ�����б�(���½����ο�)//
		//�̻�������
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//֧�������׺�String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//����״̬
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//��ȡ֧������֪ͨ���ز���
		if(AlipayNotify.verify(params)){
			//��֤�ɹ�
			//////////////////////////////////////////////////////////////////////////////////////////
			//������������̻���ҵ���߼��������
			if(trade_status.equals("TRADE_FINISHED")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				//ע�⣺
				//�˿����ڳ������˿����޺��������¿��˿��֧����ϵͳ���͸ý���״̬֪ͨ
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//�жϸñʶ����Ƿ����̻���վ���Ѿ���������
				//���û�������������ݶ����ţ�out_trade_no�����̻���վ�Ķ���ϵͳ�в鵽�ñʶ�������ϸ����ִ���̻���ҵ�����
				//���������������ִ���̻���ҵ�����
				//ע��
				try {
					System.out.println("�����ţ�"+out_trade_no+"���"+total_fee);
					double total_feel=Double.valueOf(total_fee);
					parkexpense parkex=service.getorderuserandparkid(out_trade_no);
					String userid=parkex.getSysuserid();
					String Plate=parkex.getLicenseplate();
					String parkid=parkex.getParkid();
					List<sysuser> ulist=userservice.selectsysusermobilet(parkid);
					for(int i=0;i<ulist.size();i++){
						String mobile=ulist.get(i).getMobile();
						SmsSendClientExample.sendMessage(mobile, Plate);//���Ŷ���
					}
					////////////////////ҵ����/////////////////////////
					boolean list=service.updateBypaymentstatus("1",out_trade_no);
					if(list){
						paymentrecords payment=new paymentrecords();
						payment.setPaymenttime(new Date());//֧��ʱ��
						payment.setPaymoney(total_feel);//֧�����
						payment.setUserid(userid);
						payment.setPayalipay(1);//֧������(0����1֧������2΢��)
						paymenservice.addPaymentrecord(payment);
					}
				} catch (Exception e) {
					System.out.println("֧���쳣");
				}
				//////////////////////////////////////////////////////////
				//���������������ִ���̻���ҵ�����
				//ע�⣺
				System.out.println(out_trade_no+"����ϵͳ�в鵽�ñʶ�������ϸ");
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
				//������ж�����ʱ��total_fee��seller_id��֪ͨʱ��ȡ��total_fee��seller_idΪһ�µ�
			}
			return "success";//�벻Ҫ�޸Ļ�ɾ��
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//��֤ʧ��
			return "fail";
		}
	}
	/**
	 * ΢��֧��
	 * @return
	 */
	@RequestMapping(value="parkweixin",method=RequestMethod.POST)
	public @ResponseBody String wxNotice(){
	//���ܴ���������Ϣ ��ת���ɼ�ֵ�Եļ���
	Map<String, String> map;
	map = WeixinUtil.xmlToMap(request);
	String weixinSign=map.get("sign");
	String mySign = createSign(map);
	WeixinResult outRes=null;
		//��֤ǩ��
		if(weixinSign.equals(mySign)){//��֤�ɹ�
			////////////////////////////////////������Ҫ�Ĳ���///////////////////////////////////////////
			//ҵ����	result_code	��	String(16)	SUCCESS	SUCCESS/FAIL
			//�������	err_code	��	String(32)	SYSTEMERROR	���󷵻ص���Ϣ����
			//�ܽ��	total_fee	��	Int	100	�����ܽ���λΪ��
			//�ֽ�֧�����	cash_fee	��	Int	100	�ֽ�֧�������ֽ�֧�������֧�����
			//΢��֧��������	transaction_id	��	String(32)	1217752501201407033233368018	΢��֧��������
			//�̻�������	out_trade_no	��	String(32)	1212321211201407033568112322	�̻�ϵͳ�Ķ����ţ�������һ�¡�
			//�̼����ݰ�	attach	��	String(128)	123456	�̼����ݰ���ԭ������
			//֧�����ʱ��	time_end	��	String(14)	20141030133525	֧�����ʱ�䣬��ʽΪyyyyMMddHHmmss����2009��12��25��9��10��10���ʾΪ20091225091010���������ʱ�����
			if(map.get("result_code").equals("SUCCESS")){
				//////////////////////////////////����ҵ��/////////////////////////////////////
				String out_trade_no=map.get("out_trade_no");//�̻�������
				String total_fee=map.get("total_fee");//�������
				double total_feel=Double.valueOf(total_fee);
				parkexpense parkex=service.getorderuserandparkid(out_trade_no);
				String userid=parkex.getSysuserid();
				String Plate=parkex.getLicenseplate();
				String parkid=parkex.getParkid();
				List<sysuser> ulist=userservice.selectsysusermobilet(parkid);
				for(int i=0;i<ulist.size();i++){
					String mobile=ulist.get(i).getMobile();
					SmsSendClientExample.sendMessage(mobile, Plate);//���Ŷ���
				}
				////////////////////ҵ����/////////////////////////
				boolean list=service.updateBypaymentstatus("1",out_trade_no);
				if(list){
					paymentrecords payment=new paymentrecords();
					payment.setPaymenttime(new Date());//֧��ʱ��
					payment.setPaymoney(total_feel);//֧�����
					payment.setUserid(userid);
					payment.setPayalipay(2);//֧������(0����1֧������2΢��)
					paymenservice.addPaymentrecord(payment);
				}
				/////////////////////////////////////////////////////////////////////////////
				System.out.println(out_trade_no);
				outRes=new WeixinResult("SUCCESS","ǩ����֤�ɹ���");
			}
			else{
				Log.getInstance().debug("΢��֧��֪ͨ��������룺"+map.get("err_code")+"�������������"+map.get("err_code_des"));
				outRes=new WeixinResult("SUCCESS","ҵ������֤Ϊʧ�ܣ�");
			}
		}
		else{
			//��֤ǩ��ʧ�ܣ�
			Log.getInstance().error("ǩ����֤ʧ�ܣ�");
			outRes=new WeixinResult("FAIL","ǩ����֤ʧ�ܣ�");
		}
		System.out.println(WeixinUtil.toXml(outRes));
		//��Ӧ΢���Ƿ���ܳɹ�
		return WeixinUtil.toXml(outRes);
	}
	
	@SuppressWarnings("rawtypes")
	public static String createSign(Map<String,String> parameters){ 	
		StringBuffer sb = new StringBuffer(); 		
		Set es = parameters.entrySet();
		//���в��봫�εĲ�������accsii�������� 		
		Iterator it = es.iterator(); 	
		while(it.hasNext()) { 		
			Map.Entry entry = (Map.Entry)it.next(); 	
			String k = (String)entry.getKey(); 		
			Object v = entry.getValue(); 		
			if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) { 
				sb.append(k + "=" + v + "&"); 	
				}
		}
		sb.append("key=" + WeixinConfig.APP_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(),"UTF-8").toUpperCase();
		return sign; 	
	}
	
	/**
	 * ��¼
	 * @param mobile
	 * @param password
	 * @return
	 * expense/user/login.action?mobile=13818413839&password=123456
	 */
	@RequestMapping("user/login")
	@ResponseBody
	public String userLogin(String mobile,String password){
		try {
			String regid=null;
			String is_admin=null;
			String httpUrl="http://localhost:8080/enjoy_park/sysuser/login.action?mobile="+mobile+"&password="+password+"&regid="+regid;
			String res=HttpUtil.request_post(httpUrl,"");
			System.out.println(res);
			JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
			System.out.println(result);
			String isadmin=JSONObject.fromObject(result).getString("isadmin");
			String first=JSONObject.fromObject(result).getString("first");
			System.out.println(first);
			sysuser sys=new sysuser();
			sys.setIs_admin(is_admin);
			if(isadmin.equals("0")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "0";//����Ա��¼
			}
			if(isadmin.equals("1")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "1";//��ҵ��¼
			}
			if(isadmin.equals("2")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "2";//������¼
			}
			if(isadmin.equals("3")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "3";//��������Ա��¼
			}
			if(isadmin.equals("4")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "4";//�շ�Ա��¼
			}
		} catch (Exception e) {
			return "5";
		}
		return "6";
	}
	//���
	@SuppressWarnings("unchecked")
	@RequestMapping("user/skad")
	public @ResponseBody String selectAd(){
		try {
			String httpUrl="http://localhost:8080/enjoy_park/skating/skat.action";
			String res=HttpUtil.request_post(httpUrl,"");
			JSONArray result=JSONObject.fromObject(res).getJSONArray("result");
			System.out.println(result.toString());
			List<skad> sks= JSONArray.toList(result, new skad(),new JsonConfig());//��json�������Ϊlist
			System.out.println(sks);
			for(int i=0;i<sks.size();i++){
				String adUrl=sks.get(i).getSkatpicture();
				System.out.println(adUrl);
				if(adUrl!=null){
					System.out.println(adUrl);
					return "1";
				}
			}
		} catch (Exception e) {
			return "0";
		}
		return "2";
	}
	//��ҵ�����շ�Ա
		@RequestMapping("addtoll")
		public boolean addtoll(String name,String tollRremname,String tollRrecphone){
			try {
				/*String tollRphone="13101089314";*/
				String tollRphone=(String) session.getAttribute("mobile");
				/*String parkid="297ebe0e53a3aeb80153a3b0c1a50008";*/
				String parkid=userservice.selectsysuserfirst(tollRphone).getParkId();
				String httpUrl="http://localhost:8080/enjoy_park/tollrecord/addtollrecords.action?name="+name+"&tollRremname="+tollRremname+"&parkid="+parkid+"&tollRrecphone="+tollRrecphone+"&tollRphone="+tollRphone;
				String res=HttpUtil.request_post(httpUrl,"");
				Boolean result=JSONObject.fromObject(res).getBoolean("result");
				if(result.booleanValue()==true){
					System.out.println(result.booleanValue());
					HttpSession session = request.getSession();
					session.setAttribute("tollRremname",tollRremname);
					session.setAttribute("tollRrecphone",tollRrecphone);
					return true;
				}else if(result.booleanValue()==false){
					System.out.println(result.booleanValue());
					return false;
				}
			}
			 catch (Exception e) {
				return false;
			}
			return false;
		}
		//�������
		@RequestMapping("deletetoll")
		public String deletetoll(String tollRrecphone){
			try {
				System.out.println(tollRrecphone+"�շ�Ա�ֻ����룡");
				//String tollRrecphone=(String) session.getAttribute("tollRrecphone");
				String httpUrl="http://localhost:8080/enjoy_park/tollrecord/relievetollrecord.action?tollRrecphone="+tollRrecphone;
				String res=HttpUtil.request_post(httpUrl,"");
				Boolean result=JSONObject.fromObject(res).getBoolean("result");
				if(result.booleanValue()==true){
					System.out.println(result.booleanValue());
					return "1";
				}else if(result.booleanValue()==false){
					System.out.println(result.booleanValue());
					return "0";
				}
			}
			 catch (Exception e) {
				return "0";
			}
			return "0";
		}
		//�������
		@RequestMapping("invitefriends")
		public String invite(String mobile, String plateNo){
			try {
				String myMobile=(String) session.getAttribute("mobile");
				String httpUrl="http://localhost:8080/enjoy_park/user/selectMsobileAndPlateno.action?myMobile="+myMobile+"&mobile="+mobile+"&plateNo="+plateNo;
				String res=HttpUtil.request_post(httpUrl,"");
				System.out.println(res);
				Boolean success=JSONObject.fromObject(res).getBoolean("success");
				if(success.booleanValue()==true){
					System.out.println(success.booleanValue());
					return "1";
				}else if(success.booleanValue()==false){
					System.out.println(success.booleanValue());
					return "0";
				}
			}
			 catch (Exception e) {
				return "0";
			}
			return "0";
		}
		//�޸��շ�ģʽ
		@RequestMapping("updateTollrecord")
		public String updateToll(String frertime,String shargetype,String freemoney,String maxMoney){
			try {
				/*String parkid="297ebe0e544d53580154518295ab0027";*/
				String parkid=userservice.selectsysuserfirst((String) session.getAttribute("mobile")).getParkId();
				String httpUrl="http://localhost:8080/enjoy_park/tollrecord/feestype.action?parkid="+parkid+"&freemoney="+freemoney+"&frertime="+frertime+"&shargetype="+shargetype+"&maxMoney="+maxMoney;
				String res=HttpUtil.request_post(httpUrl,"");
				System.out.println(res);
				Boolean result=JSONObject.fromObject(res).getBoolean("result");
				if(result.booleanValue()==true){
					System.out.println(result.booleanValue());
					return "1";
				}else{
					return "0";
				}
			} catch (Exception e) {
				return "0";
			}
		}
		//�޸�������
		@RequestMapping("updatestate")
		public String updatesta(String state){
			try {
				String mobile=(String) session.getAttribute("mobile");
				String httpUrl="http://localhost:8080/enjoy_park/user/updateState.action?mobile="+mobile+"&state="+state;
				String res=HttpUtil.request_post(httpUrl,"");
				System.out.println(res);
				Boolean success=JSONObject.fromObject(res).getBoolean("success");
				if(success.booleanValue()==true){
					System.out.println(success.booleanValue());
					return "1";
				}else{
					return "0";
				}
			} catch (Exception e) {
				return "0";
			}
		}
		//�޸�״̬��״̬
		@RequestMapping("updatestatus")
		public String updateStatus(String statetype){
			try {
				String mobile=(String) session.getAttribute("mobile");
				String httpUrl="http://localhost:8080/enjoy_park/stateinfo/insert.action?mobile="+mobile+"&statetype="+statetype;
				String res=HttpUtil.request_post(httpUrl,"");
				System.out.println(res);
				Boolean success=JSONObject.fromObject(res).getBoolean("success");
				if(success.booleanValue()==true){
					System.out.println(success.booleanValue());
					return "1";
				}else{
					return "0";
				}
			} catch (Exception e) {
				return "0";
			}
       }
		//ͣ���շ� ȷ���շ�
		@RequestMapping("updatepense")
		public String updateExpense(String chargestatus,String parkdeid){
			try {
				String mobile=(String) session.getAttribute("mobile");
				String httpUrl="http://localhost:8080/enjoy_park/property/updateexpense.action?chargestatus="+chargestatus+"&mobile="+mobile+"&parkdeid="+parkdeid;
				String res=HttpUtil.request_post(httpUrl,"");
				System.out.println(res);
				Boolean result=JSONObject.fromObject(res).getBoolean("result");
				if(result.booleanValue()==true){
					System.out.println(result.booleanValue());
					return "1";
				}else{
					return "0";
				}
			} catch (Exception e) {
				return "0";
			}
			
		}
		//������ʼ��ʱ
		@RequestMapping("getIntime")
		public @ResponseBody ResultDto getIntime(String order_num){
			try {
				String httpUrl="http://localhost:8080/enjoy_park/expense/getordernum.action?order_num="+order_num;
				String res=HttpUtil.request_post(httpUrl,"");
				JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
				String time=JSONObject.fromObject(result).getString("time");
				if(time!=null){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date_creat=dateFormat.parse(time);
				SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String datecreat = dateForma.format(date_creat);
				return new ResultDto(200,"��ʼ��ʱ",datecreat);
				}
				else{
				return new ResultDto(10010,"�����쳣");
					}
				} catch (Exception e) {
				return new ResultDto(10010,"�����쳣");
					}
				}
		//������ʱ
		@RequestMapping("getOuttime")
		public @ResponseBody ResultDto getOunttime(String order_num){
			try {
				String httpUrl="http://localhost:8080/enjoy_park/expense/getordernumout.action?order_num="+order_num;
				String res=HttpUtil.request_post(httpUrl,"");
				System.out.println(res);
				JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
				System.out.println(result);
				String outtime=JSONObject.fromObject(result).getString("outtime");
				System.out.println(outtime);
				if(outtime!=null){
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
				Date date_creat=dateFormat.parse(outtime);
				SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String datecreat = dateForma.format(date_creat);
				System.out.println(datecreat);
				return new ResultDto(200,"����ɹ�!",datecreat);
				}
				else{
				return new ResultDto(10010,"�����쳣");
					}
				} catch (Exception e) {
				return new ResultDto(10010,"�����쳣");
					}
		}
}
		
