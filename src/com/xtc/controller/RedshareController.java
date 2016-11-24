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

import com.xtc.entity.redshare;
import com.xtc.service.IredshareService;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("redshare")
public class RedshareController {
	@Autowired
	private IredshareService service;
	//支付的时候的
	/**
	 * http://localhost:8080/sharepark/redshare/getshare.action?mobile=13101089314
	 * @param mobile
	 * @return
	 */
	@RequestMapping("getshare")
	public @ResponseBody Map<String, Object> selectredshare(String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			Date date=new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String maxdate = dateFormat.format(date);
			List<redshare> list=service.selectredshare(mobile,maxdate);
			mapRtn.put(RestDto.RESULT,list);
		} catch (Exception e) {
			System.out.println("getshare");
		}
		return mapRtn;
	}
	@RequestMapping("getshares")
	public @ResponseBody Map<String, Object> selectredshares(String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			Date date=new Date();
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String maxdate = dateFormat.format(date);
			List<Object> lists=service.selectredshares(mobile,maxdate);
			if(lists!=null){
				Object [] o  = (Object[]) lists.get(0);
				redshare or = new redshare();
				or.setShareid((String) o[0]);
				or.setSharemoney((double) o[1]);
				lists.add(or);
				mapRtn.put(RestDto.RESULT,or);
			}
		} catch (Exception e) {
			System.out.println("getshares");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	//请确认支付的时候需要的修改
	@RequestMapping("updateshare")
	public @ResponseBody Map<String, Object> updatesharemoney(ModelMap map,String shareid,String order_num){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(shareid!=null && order_num!=null){
				List<redshare> share=service.selectshareid(shareid);
				double sharemoney=share.get(0).getSharemoney();
				boolean list=service.updateOrdersharemoney(sharemoney,order_num);
				if(list){
					for(int i=0;i<10;i++){
						Thread.currentThread();
						Thread.sleep(3000);
						String paystatus=service.selectOneByid(order_num);
						if(paystatus!=null){
							boolean lists=service.updatesharedel(shareid);
							mapRtn.put(RestDto.RESULT,lists);
						}else{
							System.out.println("未支付！");
						}
					}
					System.out.println("删除红包！");
				}
			}
		} catch (Exception e) {
			System.out.println("异常拦截！");
		}
		return mapRtn;
	}
}
