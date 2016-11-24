package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.Account;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.Ordersecurity;
import com.xtc.entity.TaskInfo;
import com.xtc.entity.balancedetail;
import com.xtc.entity.jpushinfo;
import com.xtc.service.IAccountService;
import com.xtc.service.IOrderinfoService;
import com.xtc.service.IUserService;
import com.xtc.service.IbalancedetailService;
import com.xtc.service.IjpushinfoService;
import com.xtc.util.JPushClientExample;
import com.xtc.util.RestDto;
import com.xtc.util.SmsSendClientExample;
/**
 * 计时器处理
 * 订单超时处理
 */
@Controller
@RequestMapping("quart")
public class QuartController{
	@Autowired
	private IUserService us;
	@Autowired
	private IOrderinfoService orderservice;
	@Autowired
	private IAccountService accountservice;
	@Autowired
	private IjpushinfoService jservice;
	@Autowired
	private IbalancedetailService balService;
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String exchange_time = dateFormat.format(now);//订单时间
	@RequestMapping("closeOrder")
	public @ResponseBody Map<String, Object> test(ModelMap map,TaskInfo t){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{
			String order_num=t.getOrderNo();
			List<Orderinfo> order=orderservice.selectuserid(order_num);
			String userid=order.get(0).getUserId();
			double money=order.get(0).getMoney();
			Ordersecurity Order=orderservice.ByOrdersecuritysecurity(order_num);
			double ser=Order.getSecurity();
			System.out.println(ser+"保障金");
			double thankcharge=order.get(0).getThankcharge();
			double sharemoney=order.get(0).getSharemoney();
			System.out.println("红包钱"+sharemoney);
			double sunmoney=money+thankcharge;
			System.out.println("预约费加感谢费"+sunmoney);
			double k=sunmoney-sharemoney+ser;
			System.out.println("退款的金额"+k);
			String oderstate="0";
			String umobile=us.Selectmobile(userid);
			System.out.println("退款用户的手机号码："+umobile);
			boolean state=orderservice.updateOrderstate(oderstate,order_num);
			SmsSendClientExample.sendisMessage(umobile,"");
			System.out.println(oderstate+"退款了"+order_num+"退款金额"+k);
			if(state){
				List<jpushinfo> jpushs=jservice.selectByUserid(userid);
				String regid=jpushs.get(0).getRegid();
				JPushClientExample.sendandroidRegIdMessages(regid);
				Account account=accountservice.selectByUserid(userid);//根据userid获取账户
				double balance = account.getBalance();//用户的余额
				balancedetail balanc=new balancedetail();
				balanc.setUserid(userid);
				balanc.setStarttime(exchange_time);
				balanc.setBalancetype("4");
				balanc.setMoney(k);
				if(balanc!=null){
					boolean balist = balService.insert(balanc);
					System.out.println(balist);
				}
				double b=balance+k;
				boolean list=accountservice.updateByUserid(userid,b);
				//订单超时业务处理
				mapRtn.put(RestDto.RESULT,list);
			}
		} catch (Exception e) {
			System.out.println("计时器处理！");
		}
		return mapRtn;
	}
}
