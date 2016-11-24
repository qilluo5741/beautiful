package com.xtc.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.util.AlipayNotify;
import com.jpush.api.DeviceEnum;
import com.jpush.api.JPushClient;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.TaskInfo;
import com.xtc.entity.User;
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
import com.xtc.util.RestDto;
import com.xtc.util.SmsSendClientExample;
/**
 * 支付宝支付
 * @author Administrator
 */
@Controller
@RequestMapping("alipay")
public class alipayController {
	@Autowired
	private IUserService us;
	@Autowired
	private IOrderinfoService os;
	@Autowired
	private IjpushinfoService jservice;
	@Autowired
	private IbalancedetailService balService;
	//在极光注册上传应用的 appKey 和 masterSecret
	private static final String appKey ="02598c822dede2bb0f4bac2c";////必填，例如466f7032ac604e02fb7bda89
	private static final String masterSecret = "abdc91fcc99cd38b19a4e9c3";//必填，每个应用都对应一个masterSecret
	private static JPushClient jpush = null;
	private static long timeToLive =  60 * 5; 
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX/2;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private IOrderinfoService orderservice;
	@Autowired
	private IUserService userService;
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
		private String  exchange_time;	//	交易时间	
		private String  pay_time	;	//	支付时间
		private String  pay_type;	//支付类型(支付宝、微信、银行卡、余额)
		private String  service_type;	//	类型（1预约成功 2未付款 3交易完成）
		private String  order_num;	//	订单号
		private String  paystatus;
	 * @throws InterruptedException 
	*/
	@SuppressWarnings({ "rawtypes", "unused" })
	//http://localhost:8080/sharepark/alipay/alipaycode.action
	@RequestMapping(value="alipaycode")
	public @ResponseBody String getByUserid(ModelMap map) throws UnsupportedEncodingException, InterruptedException{
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
				System.out.println(out_trade_no+"测试订单号是否得到。。。。。。。。。。。。。。。。。。。。。。");
				String exchange_time = dateFormat.format(now);//订单时间
				String pay_time = dateFormat.format(now);//支付时间
				String pay_type="0";
				String service_type="3";
				String paystatus="0";
				if(out_trade_no!=null){
					boolean resut =orderservice.updateOrder(exchange_time, pay_time, pay_type, service_type, paystatus,out_trade_no);
					//付款完成后，支付宝系统发送该交易状态通知
				}else{
					return "fail";
				}
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				//注意
				Map<String, Object> mapRtn = new HashMap<String, Object>();
				try {
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
					//付款完成后，支付宝系统发送该交易状态通知
					if(resut){
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
						balanc.setBalancetype("0");
						balanc.setMoney(k);
						if(balanc!=null){
							boolean balist = balService.insert(balanc);
							System.out.println(balist);
						}
					}
					List<Object> oblists=orderservice.orderisadminandstatus(out_trade_no);
					for(int h = 0; h <oblists.size();h++){
						String uid = (String)oblists.get(h);
						List<Object> oblistss=orderservice.orderstatus(uid);
						List<orderadminandstatus> olistss = new ArrayList<orderadminandstatus>();
						for (int j = 0; j < oblistss.size(); j++) {
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
							String time = RandomStringUtils.randomNumeric(7);//随机数
						    String sendNo=time;
						    List<Orderinfo> orders=os.selectuserid(out_trade_no);
						    String parkid=orders.get(0).getParkId();
							List<User> userlists = userService.getByparkid(parkid);
							String idss=userlists.get(0).getId();
							List<jpushinfo> jpushs=jservice.selectByUserid(idss);
							String regid=jpushs.get(0).getRegid();
							String Plate=olist.get(0).getPlate_no();
							String umobile=us.Selectmobile(idss);
							SmsSendClientExample.sendMessage(umobile,Plate);
							System.out.println("支付宝支付发送的手机号码："+umobile);
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
				} catch (Exception e) {
					System.out.println("支付宝 alipaycode");
					mapRtn.put(RestDto.RESULT,false);
				}
				//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
			}
			return "success";//请不要修改或删除
			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			return "fail";
		}
	}
	/**
	 * 保持 sendNo 的唯一性是有必要的
	 * It is very important to keep sendNo unique.
	 * @return sendNo
	 */
	public static int getRandomSendNo() {
	    return (int) (MIN + Math.random() * (MAX - MIN));
	}
	//http://localhost:8080/JoinPark/alipay/jpushManage.action?out_trade_no=2016041817395
	@RequestMapping("jpushManage")
	public @ResponseBody Map<String,Object> getByjpush(ModelMap map,String out_trade_no){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		List<OrderUserRecord> olist  = new ArrayList<OrderUserRecord>();
		List<Object> oblist=orderservice.orderJpush(out_trade_no);
		System.out.println("你返给我的---"+out_trade_no);
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
			mapRtn.put(RestDto.RESULT,olist);
		}
		return mapRtn;
	}
}
