package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xtc.entity.Ordersecurity;
import com.xtc.entity.ResultDto;
import com.xtc.entity.sysuser;
import com.xtc.service.OrdersecurityService;
import com.xtc.service.SysuserService;

@RestController
@RequestMapping("security")
public class OrdersecurityController {
	@Autowired
	private OrdersecurityService service;
	@Autowired
	private SysuserService uservice;
	/**
	 * 下订单
	 * @param money
	 * @param thankcharge
	 * @param parkId
	 * @param mobile
	 * @param park_start_time
	 * @param park_end_time
	 * @param plate_no
	 * @return
	 * security/getaddorder.action?money=100&thankcharge=100&parkId=297ebe0e544d53580154518295ab0027&mobile=13101089314&park_start_time=2016-4-27 17:1&park_end_time=2016-07-26 23:33&plate_no=沪A88888&security=10
	 */
	@RequestMapping(value="getaddorder",method=RequestMethod.POST)
	public ResultDto getaddorder(String money,String thankcharge,String parkid,String mobile,String  park_start_time,String  park_end_time,String plate_no,String security){
		try {
			if(money==null || thankcharge==null || parkid==null || mobile==null ||  park_start_time==null || park_end_time==null || plate_no==null || security==null){
				return new ResultDto(10001,"请求参数为空！");
			}else{
				Date date=new Date();
				SimpleDateFormat sdff=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
				String str=sdff.format(date);
				sysuser sys =uservice.selectsysuserfirst(mobile);
				String userid=sys.getId();
				String ordernum = RandomStringUtils.randomNumeric(13);//订单号
				double moneys=Double.valueOf(money);
				double thankcharges=Double.valueOf(thankcharge);
				double securitys=Double.valueOf(security);
				Ordersecurity info=new Ordersecurity();
				info.setOrder_num(ordernum);
				info.setParkId(parkid);
				info.setUserId(userid);
				info.setPark_start_time(park_start_time);
				info.setPark_end_time(park_end_time);
				info.setOrder_request_time(str);
				info.setService_type("1");
				info.setMoney(moneys);
				info.setPlate_no(plate_no);
				info.setPaystatus("0");
				info.setOderstate("2");
				info.setThankcharge(thankcharges);//感谢费
				info.setSharemoney(0);
				info.setSecurity(securitys);
				boolean list=service.addOrdersecurity(info);
				if(list){
					Map<String,String> resmap=new HashMap<String,String>();
					resmap.put("order_num",ordernum);
					return new ResultDto(200,"操作成功",resmap);
				}
			}
		} catch(Exception e) {
			System.out.println("下订单 getaddorder");
			return new ResultDto(2001,"操作失败");
		}
		return new ResultDto(2001,"操作失败");
	}
}
