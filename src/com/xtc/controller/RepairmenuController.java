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

import com.xtc.entity.Goodrepair;
import com.xtc.entity.Repairmenu;
import com.xtc.entity.User;
import com.xtc.entity.commodity;
import com.xtc.entity.goodproperty;
import com.xtc.entity.increorder;
import com.xtc.entity.dto.OrderunDto;
import com.xtc.entity.dto.increpropertyDto;
import com.xtc.entity.dto.unorderDto;
import com.xtc.service.IGoodrepairService;
import com.xtc.service.IRepairmenuService;
import com.xtc.service.IUserService;
import com.xtc.service.IcommodityService;
import com.xtc.service.IgoodpropertyService;
import com.xtc.service.IncreorderService;
import com.xtc.util.RestDto;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("repair")
public class RepairmenuController {
	@Autowired
	private IRepairmenuService service;
	@Autowired
	private IgoodpropertyService gpservice;
	@Autowired
	private IGoodrepairService grpservice;
	@Autowired
	private IcommodityService commService;
	@Autowired
	private IncreorderService icService;
	@Autowired
	private IUserService uService;
	/**
	 * 增值服务菜单列表
	 * @param map
	 * @return
	 */
	@RequestMapping("getrepairmenu")
	public @ResponseBody Map<String, Object> getRepairmenuAll(ModelMap map){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<Repairmenu> list = service.getRepairmenuAll();
			if(null != list){
				mapRtn.put(RestDto.RESULT,list);
			}
		} catch (Exception e) {
			System.out.println("增值服务菜单列表");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * 所有商品
	 */
	@RequestMapping("getgoodproperty")
	public @ResponseBody Map<String, Object> getgoodpropertyAll(ModelMap map,String repmenuid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<goodproperty> list = gpservice.selectgetgoodpropertyAll(repmenuid);
			if(null != list){
				mapRtn.put(RestDto.RESULT,list);
			}
		} catch (Exception e) {
			System.out.println("所有商品");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * 商品详情
	 * @param map
	 * @param repmenuid
	 * @return
	 */
	@RequestMapping("getGoodrepair")
	public @ResponseBody Map<String, Object> getGoodrepairAll(ModelMap map,String goodproperid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
			try {
				List<Goodrepair> oblist = grpservice.selectGoodrepairAll(goodproperid);
				if(null != oblist){
					mapRtn.put(RestDto.RESULT,oblist);
				}
			} catch (Exception e) {
				System.out.println("商品详情'");
			}
		return mapRtn;
	}
	/**
	 * 查询手机配件详情
	 */
	@RequestMapping("getcommodity")
	public @ResponseBody Map<String, Object> getcommodityAll(ModelMap map,String goodproperid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<commodity> list = commService.selectgetcommodityAll(goodproperid);
			if(null != list){
				mapRtn.put(RestDto.RESULT,list);
			}
		} catch (Exception e){
			System.out.println("查询手机配件详情");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * 下订单
	 * @param map
	 * @param goodproperid
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("addincreorder")
	public @ResponseBody Map<String, Object> getAddincreorder(ModelMap map,String unorder){
		System.out.println(unorder);
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			unorderDto unord=null;
			String inorderNo=RandomStringUtils.randomNumeric(10);//随机数
			try {
				Map<String,Class> confMap=new HashMap<String,Class>();
				confMap.put("order", OrderunDto.class);
				unord=(unorderDto) JSONObject.toBean(JSONObject.fromObject(unorder), unorderDto.class,confMap);
			} catch (Exception e) {
				mapRtn.put(RestDto.RESULT,null);
			}
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			String inordertime=sdf.format(date);  
			List<User> userlist = this.uService.selectBymobil(unord.getMobile());
			String userid = ((User)userlist.get(0)).getId();
			for(int i=0;i<unord.getOrder().size();i++){
				increorder increorder=new increorder();
				increorder.setInorderNo(inorderNo);
				increorder.setGoodproperid(unord.getGoodproperid());
				increorder.setRepmenuid("96607329759264769");
				increorder.setInordertime(inordertime);
				increorder.setCommodityid(unord.getOrder().get(i).getCommodityid());
				increorder.setInorderstatus(0);
				increorder.setInordcount(unord.getOrder().get(i).getInordcount());
				increorder.setInordphone(unord.getInordphone());//收件人手机
				increorder.setInordAddress(unord.getInordAddress());
				increorder.setInordUname(unord.getInordUname());
				increorder.setInstatus("0");//'支付状态(0:未支付 1:支付宝，2：微信)'
				increorder.setUserid(userid);
				increorder.setIsdelete(0);
				boolean list = icService.Addincreorder(increorder);
				if(list){
					Map<String,String> resmap=new HashMap<String, String>();
					resmap.put("RESULT",list+"");
					resmap.put("inorderNo",inorderNo);
					mapRtn.put(RestDto.RESULT,resmap);
				}
			}
		} catch (Exception e){
			System.out.println("下订单");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * 查询用户订单
	 * @param map
	 * @param mobile
	 * @param repmenuid
	 * @return
	 */
	@RequestMapping("selectincreorder")
	public @ResponseBody Map<String, Object> selectincreorder(ModelMap map,String mobile,String repmenuid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = this.uService.selectBymobil(mobile);
			String userid = ((User)userlist.get(0)).getId();
			List<increpropertyDto> olist  = new  ArrayList<increpropertyDto>();
			List<Object> oblist = icService.Increproperty(userid, repmenuid);
			for (int i = 0; i < oblist.size(); i++) {
				Object [] o  = (Object[]) oblist.get(i);
				increpropertyDto or = new increpropertyDto();
				or.setInorderid((String) o[0]);
				or.setInorderNo((String) o[1]);
				or.setInorderstatus((Integer) o[2]);
				or.setInordertime((String) o[3]);
				or.setGoodproname((String) o[4]);
				olist.add(or);
			}
			mapRtn.put(RestDto.RESULT,olist);
		} catch (Exception e) {
			System.out.println("查询用户订单");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	public static void main(String[] args) {
		String arr[] = new String[]{"a","b","c"};//定义一个字符串数组arr
		for(int i=0;i<arr.length;i++){//通过arr.length获取字符串数组长度
		   System.out.println(arr[i]);//循环输出字符串数组元素
		}
	}
}
