package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jpush.api.DeviceEnum;
import com.jpush.api.JPushClient;
import com.weixin.config.WeixinConfig;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.TaskInfo;
import com.xtc.entity.User;
import com.xtc.entity.WeixinResult;
import com.xtc.entity.balancedetail;
import com.xtc.entity.jpushinfo;
import com.xtc.entity.dto.OrderUserRecord;
import com.xtc.entity.dto.orderadminandstatus;
import com.xtc.quartz.CancelTheOrder;
import com.xtc.quartz.TaskTimer;
import com.xtc.service.IOrderinfoService;
import com.xtc.service.IUserService;
import com.xtc.service.IbalancedetailService;
import com.xtc.service.IjpushinfoService;
import com.xtc.util.Log;
import com.xtc.util.MD5Util;
import com.xtc.util.RestDto;
import com.xtc.util.SmsSendClientExample;
import com.xtc.util.WeixinUtil;
/**
 * 微信支付
 * @author Administrator
 */
@Controller
@RequestMapping("weixinpay")
public class WxPayController {
	//在极光注册上传应用的 appKey 和 masterSecret
	private static final String appKey ="02598c822dede2bb0f4bac2c";////必填，例如466f7032ac604e02fb7bda89
	private static final String masterSecret = "abdc91fcc99cd38b19a4e9c3";//必填，每个应用都对应一个masterSecret
	private static JPushClient jpush = null;
	private static long timeToLive =  60 * 5;
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX/2;
	@Autowired
	private IOrderinfoService orderservice;
	@Autowired
	private IOrderinfoService os;
	@Autowired
	private IjpushinfoService jservice;
	@Autowired
	private IUserService userService;
	@Autowired
	private IbalancedetailService balService;
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private HttpServletRequest request;
	@RequestMapping("weixinNotice")
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
				Map<String, Object> mapRtn = new HashMap<String, Object>();
				try{
					List<Orderinfo> orderlist=orderservice.selectuserid(out_trade_no);
					String paystatuss=orderlist.get(0).getPaystatus();
					System.out.println(paystatuss);
					if(paystatuss !="1"){
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time=dateFormat.format(now);
						Orderinfo orderw =orderservice.selectOneByid(out_trade_no);
						String userid=orderw.getUserId();
						double money=orderw.getMoney();
						double thankcharge=orderw.getThankcharge();
						double sharemoney=orderw.getSharemoney();
						double sunmoney=money+thankcharge;
						double k=sunmoney-sharemoney;
						balancedetail balanc=new balancedetail();
						balanc.setUserid(userid);
						balanc.setStarttime(time);
						balanc.setBalancetype("1");
						balanc.setMoney(k);
						if(balanc!=null){
							boolean balist = balService.insert(balanc);
							System.out.println(balist);
						}
						TaskInfo ta=new TaskInfo();
						ta.setJobName(out_trade_no);
						ta.setOrderNo(out_trade_no);
						TaskTimer.addJob(ta,CancelTheOrder.class,300);
						System.out.println(out_trade_no+"测试订单号是否得到。。。。。。。。。。。。。。。。。。。。。。1");
						String exchange_time = dateFormat.format(now);//订单时间
						String pay_time = dateFormat.format(now);//支付时间
						String pay_type="0";
						String service_type="3";
						String paystatus="1";
						if(out_trade_no!=null){
						boolean resut =orderservice.updateOrder(exchange_time, pay_time, pay_type, service_type, paystatus,out_trade_no);
						System.out.println(resut);
						
						//付款完成后，支付宝系统发送该交易状态通知
						List<Object> oblists=orderservice.orderisadminandstatus(out_trade_no);
						for(int h = 0; h <oblists.size();h++){
							String uid = (String)oblists.get(h);
							List<Object> oblistss=orderservice.orderstatus(uid);
							List<orderadminandstatus> olistss = new ArrayList<orderadminandstatus>();
							for(int j = 0; j < oblistss.size(); j++) {
								Object [] o  = (Object[]) oblistss.get(j);
								orderadminandstatus or = new orderadminandstatus();
								or.setIs_admin((String) o[0]);
								or.setStatus((String)o[1]);
								olistss.add(or);
							}
							if(olistss.get(0).getIs_admin().equals("2") ||olistss.get(0).getIs_admin().equals("1")||olistss.get(0).getIs_admin().equals("3") && olistss.get(0).getStatus().equals("Y")){
								jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.Android);
								jpush = new JPushClient(masterSecret,appKey,timeToLive,DeviceEnum.IOS);
								jpush.setEnableSSL(true);
								List<OrderUserRecord> olist  = new ArrayList<OrderUserRecord>();
								List<Object> oblist=orderservice.orderJpush(out_trade_no);
								//测试发送消息或者通知
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
									System.out.println("车牌号：------------"+Plate);
									mapRtn.put(RestDto.RESULT,olist);
								}
							    String sendNo=RandomStringUtils.randomNumeric(7);
							    List<Orderinfo> orders=os.selectuserid(out_trade_no);
							    String parkid=orders.get(0).getParkId();
								List<User> userlists = userService.getByparkid(parkid);
								String idss=userlists.get(0).getId();
								String umobile=userService.Selectmobile(idss);
								System.out.println("微信支付发送的手机号码："+umobile);
								List<jpushinfo> jpushs=jservice.selectByUserid(idss);
								String regid=jpushs.get(0).getRegid();
								String Plate=olist.get(0).getPlate_no();
								SmsSendClientExample.sendMessage(umobile,Plate);
								String urt="车牌为"+Plate+"的用户预约了您的停车场!="+out_trade_no;
								String urts="车牌为"+Plate+"的用户预约了您的停车场!";
								jpush.sendRegIdMessage(sendNo,appKey,masterSecret,urt,regid);
								jpush.sendRegIdMessageTags(sendNo,appKey,masterSecret,urts,regid);
							}else{
								System.out.println("不是管理，不是保安,不在线");
							}
							mapRtn.put(RestDto.RESULT,olistss);
							}
						}
					}
				} catch (Exception e) {
					mapRtn.put(RestDto.RESULT,false);
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
}
