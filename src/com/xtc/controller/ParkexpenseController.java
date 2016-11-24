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
				String ordernumber=RandomStringUtils.randomNumeric(14);//得到订单号
				parkexpense pmo=new parkexpense();
				pmo.setLicenseplate(licenseplate);
				pmo.setExpense(expense);
				pmo.setReceivable(receivable);
				pmo.setFirsttime(firsttime);
				pmo.setFactorytime(factorytime);
				pmo.setParkid(parkid);
				pmo.setChargestatus("0");
				pmo.setTollRremname(tollRremname);
				pmo.setPaymentstatus("0");//未支付状态
				pmo.setOrdernumber(ordernumber);//订单号
				pmo.setIssenior(issenior);
				boolean list=service.addparkexpense(pmo);
				if(list){
					return new ResultDto(200,"操作成功",list);
				}
			}else{
				return new ResultDto(10001,"请求参数为空！");
			}
		} catch (Exception e) {
			return new ResultDto(10002,"参数请求失败！");
		}
		return null;
	}
	/**
	 * 小区同步(维护)
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
			return new ResultDto(200,"同步成功!");
		} catch (Exception e) {
			System.out.println("返回结果异常！");
			return new ResultDto(20004,"返回结果异常！!");
		}
	}
	/**
	 * 进场计时
	 * expense/getordernum.action?order_num=20160420220847
	 * @param order_num
	 * @return
	 */
	@RequestMapping(value="getordernum",method=RequestMethod.POST)
	public ResultDto getordernum(String order_num){
		try{
			String starttiming =orderservice.getOrderinfstarttiming(order_num);
			if(order_num==null){
				return new ResultDto(10001,"请求参数为空！");
			}else if(starttiming!=null){
				return new ResultDto(10002,"已经开始计时了");
			}else{
				Orderinfo order=service.selectByOrder(order_num);
				String parkid=order.getParkId();
				String plate_no=order.getPlate_no();
				System.out.println(parkid+"车牌号："+plate_no);
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
					String mobile=orderservice.getuserinvideCode(userid);//得到手机号码
					String usid=orderservice.getyaoqinrenuserid(mobile);
					System.out.println(usid+"加钱人userid");
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
						System.out.println("其他的不给钱");
					}
					return new ResultDto(200,"计时成功！",resmap);
				}
				return new ResultDto(20002,"计时失败！.");
			}
		} catch (Exception e) {
			System.out.println("返回结果异常！");
			return new ResultDto(20004,"返回结果异常！!");
		}
	}
	/**
	 * 出场时间
	 * expense/getordernumout.action?order_num=20160606174417
	 * @param order_num
	 * @return
	 */
	@RequestMapping(value="getordernumout",method=RequestMethod.POST)
	public ResultDto getordernums(String order_num){
		try {
			if(order_num==null){
				return new ResultDto(10001,"请求参数为空！");
			}else{
				Orderinfo order=service.selectByOrder(order_num);
				String userid=order.getUserId();
				String mobile=service.selectBymobile(userid);
				System.out.println("短信手机号码"+mobile);
				String parkid=order.getParkId();//得到parkID
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
					SmsSendClientExample.sendchargeMessage(mobile,mobile);//短信
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
					Date date_endcreat=dateFormat.parse(outtime);
					SimpleDateFormat dateForma = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String datecreat = dateForma.format(date_endcreat);
					orderservice.updateOrderenttime(datecreat,order_num);
					parking park=service.selectByparking(parkid);//得到name address
					String use=park.getUserid();
					sysuser sys=service.selectByparkmobileing(use);
					String mobiles=sys.getMobile();//得到手机号码
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
					return new ResultDto(200,"结算成功！",resmap);
				}else if(state==-2){
					return new ResultDto(20005,"请设置收费模式!");
				}
				return new ResultDto(20003,"出场时间结算失败！请重新点击计时!");
			}
		} catch (Exception e){
			e.printStackTrace();
			System.out.println("返回结果异常！");
			return new ResultDto(20004,"返回结果异常！!");
		}
	}
	/**
	
	/**
	 * 用户收费订单
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
	 * 支付记录查询
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
	 * 支付宝支付
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
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"),"gbk");
			params.put(name,valueStr);
		}
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		//支付宝交易号String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String total_fee=new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		//获取支付宝的通知返回参数
		if(AlipayNotify.verify(params)){
			//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				//注意
				try {
					System.out.println("订单号："+out_trade_no+"金额"+total_fee);
					double total_feel=Double.valueOf(total_fee);
					parkexpense parkex=service.getorderuserandparkid(out_trade_no);
					String userid=parkex.getSysuserid();
					String Plate=parkex.getLicenseplate();
					String parkid=parkex.getParkid();
					List<sysuser> ulist=userservice.selectsysusermobilet(parkid);
					for(int i=0;i<ulist.size();i++){
						String mobile=ulist.get(i).getMobile();
						SmsSendClientExample.sendMessage(mobile, Plate);//华信短信
					}
					////////////////////业务区/////////////////////////
					boolean list=service.updateBypaymentstatus("1",out_trade_no);
					if(list){
						paymentrecords payment=new paymentrecords();
						payment.setPaymenttime(new Date());//支付时间
						payment.setPaymoney(total_feel);//支付金额
						payment.setUserid(userid);
						payment.setPayalipay(1);//支付类型(0：余额，1支付宝，2微信)
						paymenservice.addPaymentrecord(payment);
					}
				} catch (Exception e) {
					System.out.println("支付异常");
				}
				//////////////////////////////////////////////////////////
				//如果有做过处理，不执行商户的业务程序
				//注意：
				System.out.println(out_trade_no+"订单系统中查到该笔订单的详细");
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}
			return "success";//请不要修改或删除
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			return "fail";
		}
	}
	/**
	 * 微信支付
	 * @return
	 */
	@RequestMapping(value="parkweixin",method=RequestMethod.POST)
	public @ResponseBody String wxNotice(){
	//接受传过来的信息 并转换成键值对的集合
	Map<String, String> map;
	map = WeixinUtil.xmlToMap(request);
	String weixinSign=map.get("sign");
	String mySign = createSign(map);
	WeixinResult outRes=null;
		//验证签名
		if(weixinSign.equals(mySign)){//验证成功
			////////////////////////////////////可能需要的参数///////////////////////////////////////////
			//业务结果	result_code	是	String(16)	SUCCESS	SUCCESS/FAIL
			//错误代码	err_code	否	String(32)	SYSTEMERROR	错误返回的信息描述
			//总金额	total_fee	是	Int	100	订单总金额，单位为分
			//现金支付金额	cash_fee	是	Int	100	现金支付金额订单现金支付金额，详见支付金额
			//微信支付订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信支付订单号
			//商户订单号	out_trade_no	是	String(32)	1212321211201407033568112322	商户系统的订单号，与请求一致。
			//商家数据包	attach	否	String(128)	123456	商家数据包，原样返回
			//支付完成时间	time_end	是	String(14)	20141030133525	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
			if(map.get("result_code").equals("SUCCESS")){
				//////////////////////////////////处理业务/////////////////////////////////////
				String out_trade_no=map.get("out_trade_no");//商户订单号
				String total_fee=map.get("total_fee");//订单金额
				double total_feel=Double.valueOf(total_fee);
				parkexpense parkex=service.getorderuserandparkid(out_trade_no);
				String userid=parkex.getSysuserid();
				String Plate=parkex.getLicenseplate();
				String parkid=parkex.getParkid();
				List<sysuser> ulist=userservice.selectsysusermobilet(parkid);
				for(int i=0;i<ulist.size();i++){
					String mobile=ulist.get(i).getMobile();
					SmsSendClientExample.sendMessage(mobile, Plate);//华信短信
				}
				////////////////////业务区/////////////////////////
				boolean list=service.updateBypaymentstatus("1",out_trade_no);
				if(list){
					paymentrecords payment=new paymentrecords();
					payment.setPaymenttime(new Date());//支付时间
					payment.setPaymoney(total_feel);//支付金额
					payment.setUserid(userid);
					payment.setPayalipay(2);//支付类型(0：余额，1支付宝，2微信)
					paymenservice.addPaymentrecord(payment);
				}
				/////////////////////////////////////////////////////////////////////////////
				System.out.println(out_trade_no);
				outRes=new WeixinResult("SUCCESS","签名验证成功！");
			}
			else{
				Log.getInstance().debug("微信支付通知：错误代码："+map.get("err_code")+"错误代码描述："+map.get("err_code_des"));
				outRes=new WeixinResult("SUCCESS","业务结果验证为失败！");
			}
		}
		else{
			//验证签名失败！
			Log.getInstance().error("签名验证失败！");
			outRes=new WeixinResult("FAIL","签名验证失败！");
		}
		System.out.println(WeixinUtil.toXml(outRes));
		//相应微信是否接受成功
		return WeixinUtil.toXml(outRes);
	}
	
	@SuppressWarnings("rawtypes")
	public static String createSign(Map<String,String> parameters){ 	
		StringBuffer sb = new StringBuffer(); 		
		Set es = parameters.entrySet();
		//所有参与传参的参数按照accsii排序（升序） 		
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
	 * 登录
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
				return "0";//管理员登录
			}
			if(isadmin.equals("1")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "1";//物业登录
			}
			if(isadmin.equals("2")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "2";//保安登录
			}
			if(isadmin.equals("3")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "3";//超级管理员登录
			}
			if(isadmin.equals("4")){
				HttpSession session = request.getSession();
				session.setAttribute("mobile",mobile);
				session.setAttribute("isadmin",isadmin);
				session.setAttribute("first", first);
				return "4";//收费员登录
			}
		} catch (Exception e) {
			return "5";
		}
		return "6";
	}
	//广告
	@SuppressWarnings("unchecked")
	@RequestMapping("user/skad")
	public @ResponseBody String selectAd(){
		try {
			String httpUrl="http://localhost:8080/enjoy_park/skating/skat.action";
			String res=HttpUtil.request_post(httpUrl,"");
			JSONArray result=JSONObject.fromObject(res).getJSONArray("result");
			System.out.println(result.toString());
			List<skad> sks= JSONArray.toList(result, new skad(),new JsonConfig());//将json数组解析为list
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
	//物业关联收费员
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
		//解除关联
		@RequestMapping("deletetoll")
		public String deletetoll(String tollRrecphone){
			try {
				System.out.println(tollRrecphone+"收费员手机号码！");
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
		//邀请好友
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
		//修改收费模式
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
		//修改上下线
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
		//修改状态机状态
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
		//停车收费 确认收费
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
		//进场开始计时
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
				return new ResultDto(200,"开始计时",datecreat);
				}
				else{
				return new ResultDto(10010,"参数异常");
					}
				} catch (Exception e) {
				return new ResultDto(10010,"请求异常");
					}
				}
		//出场计时
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
				return new ResultDto(200,"结算成功!",datecreat);
				}
				else{
				return new ResultDto(10010,"参数异常");
					}
				} catch (Exception e) {
				return new ResultDto(10010,"请求异常");
					}
		}
}
		
