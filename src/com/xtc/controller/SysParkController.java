package com.xtc.controller;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xtc.entity.ResultDto;
import com.xtc.entity.parking;
import com.xtc.entity.syspark;
import com.xtc.entity.sysuser;
import com.xtc.entity.dto.parkinfoDto;
import com.xtc.service.IparkService;
import com.xtc.service.IsysparkService;
import com.xtc.service.SysuserService;
import com.xtc.util.HttpUtil;

import net.sf.json.JSONObject;

@RestController
@RequestMapping("syspark")
public class SysParkController {
	@Autowired
	private IsysparkService service;
	@Autowired
	private IparkService parkService;
	@Autowired
	private SysuserService uservice;
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date_creat = dateFormat.format(now);
	/**
	 * 停车场模糊查询
	 * @param name
	 * @return
	 * syspark/parkingplace.action?name=12
	 */
	@RequestMapping(value="parkingplace",method=RequestMethod.POST)
	public ResultDto Parkingplace(String name){
		try {
			if(name==null){
				return new ResultDto(10001,"请求参数为空！");
			}
			List<parkinfoDto> list=service.selectByNameorAddress(name);
			return new ResultDto(200,"操作成功",list);
		} catch (Exception e) {
			System.out.println("停车场模糊查询");
			return new ResultDto(2001,"操作异常");
		}
	}
	/**
	 * 停车场查询
	 * @param parkid
	 * @return
	 * syspark/parkingquery.action?parkid=297ebe0e53bd58fa0153c5bc8b58002d
	 */
	@RequestMapping(value="parkingquery",method=RequestMethod.POST)
	public ResultDto parkingquery(String parkid){
		try {
			if(parkid==null){
				return new ResultDto(10001,"请求参数为空！");
			}
			List<syspark> list=service.getParkcreat(parkid);
			return new ResultDto(200,"操作成功",list);
		} catch (Exception e) {
			System.out.println("停车场查询");
			return new ResultDto(2001,"操作异常");
		}
	}
	/**
	 * 停车场信修改
	 * @param city
	 * @param entry_address
	 * @param address
	 * @param capacity
	 * @param carnum
	 * @param type
	 * @param price
	 * @param is_cooperate
	 * @param name
	 * @param cost
	 * @param reservation
	 * @param dividedinto
	 * @param parkid
	 * @return
	 * syspark/getupdateparking.action?city=3&entry_address=3&address=3&capacity=3&carnum=3&type=3&price=3&is_cooperate=3&name=3&cost=3&reservation=3&dividedinto=3&parkid=1
	 */
	@RequestMapping(value="getupdateparking",method=RequestMethod.POST)
	public ResultDto getupdateparking(String city, String entry_address, String address, String capacity, String carnum,String type, String price, String is_cooperate, String name, String cost, String reservation,String dividedinto,String subscription,String parkid){
		try {
			if(parkid==null){
				return new ResultDto(10001,"请求参数为空！");
			}
			double subscriptions=Double.valueOf(subscription);
			boolean list=service.updatesyspark(city, entry_address, address, capacity, carnum, type, price, is_cooperate, name, cost, reservation, dividedinto,subscriptions,parkid);
			if(list){
				String id=parkService.selectmoralid(name);
				String key="14607db53fff73bc7ed6e611a6246fc1";
				String tableid="57563285afdf52393935af22";
				String httpUrl="http://yuntuapi.amap.com/datamanage/data/update";
				String httpArg="key="+key+"&tableid="+tableid+"&loctype=2&data={\"_id\":\""+id+"\",\"_address\":\""+entry_address+"\"}";
				HttpUtil.request_post(httpUrl,httpArg);
			}
			String json="json";
			String httpUrl="http://interface.sharebo.cn/sharebodoc/sharebo/shanghai/batp/updateDividedinto?parkid="+URLEncoder.encode(parkid,"UTF-8")+"&dividedinto="+URLEncoder.encode(dividedinto,"UTF-8")+"&_type="+json;
			String res=HttpUtil.request_post(httpUrl,"");
			JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
			int state=JSONObject.fromObject(result).getInt("state");
			String msg=JSONObject.fromObject(result).getString("msg");
			if(state==200){
				return new ResultDto(200,"操作成功");
			}else{
				return new ResultDto(2003,"操作失败",msg);
			}
		} catch (Exception e){
			System.out.println(" 停车场信修改");
			return new ResultDto(2001,"操作异常");
		}
	}
	
	/**
	 * 停车场添加
	 * @param mobile
	 * @param name
	 * @param city
	 * @param address
	 * @param entry_address
	 * @param capacity
	 * @param carnum
	 * @param type
	 * @param price
	 * @param is_cooperate
	 * @param cost
	 * @return
	 * syspark/getaddparking.action?mobile=13101089314&name=华东理工&city=1&address=3&entry_address=上海市华东理工大学南梅陇四村&capacity=3&carnum=3&type=3&price=3&is_cooperate=3&cost=3&reservation=3&dividedinto=3&subscription=11
	 */
	@RequestMapping(value="getaddparking",method=RequestMethod.POST)
	public ResultDto getaddparking(String mobile,String name, String city, String address, String entry_address, String capacity, String carnum, String type, String price, String is_cooperate,String cost,String reservation,String dividedinto,String subscription){
		try {
			if(mobile==null || name==null || dividedinto==null || reservation==null || name==null || address==null){
				return new ResultDto(10001,"请求参数为空！");
			}
			double subscriptions=Double.valueOf(subscription);
			sysuser sys =uservice.selectsysuserfirst(mobile);
			String userid=sys.getId();
			  parking pdd=new parking();
			  pdd.setName(name);
			  pdd.setCity(city);
			  pdd.setAddress(address);
			  pdd.setEntry_address(entry_address);
			  pdd.setCapacity(capacity);
			  pdd.setCarnum(carnum);
			  pdd.setType(type);
			  pdd.setPrice(price);
			  pdd.setCost(cost);
			  pdd.setStatus("2");
			  pdd.setStart_time(date_creat);//添加时间
			  pdd.setIs_cooperate(is_cooperate);
			  pdd.setUserid(userid);
			  pdd.setReservation(reservation);
			  pdd.setDividedinto(dividedinto);
			  pdd.setProperty("xb");
			  pdd.setSubscription(subscriptions);
			  boolean list = this.service.addsyspark(pdd);
			  if(list){
				String id=parkService.selectid(name);
				String status="2";
				String key="14607db53fff73bc7ed6e611a6246fc1";
				String tableid="57563285afdf52393935af22";
				String httpUrl="http://yuntuapi.amap.com/datamanage/data/create";
				String httpArg="key="+key+"&tableid="+tableid+"&loctype=2&data={\"_name\":\""+name+"\",\"_address\":\""+entry_address+"\",\"parkid\":\""+id+"\",\"status\":\""+status+"\"}";
				String res=HttpUtil.request_post(httpUrl,httpArg);
				String _id=JSONObject.fromObject(res).getString("_id");
				System.out.println(_id);
				parkService.updatemoralid(_id,id);
			  }
			  return new ResultDto(200,"操作成功",list);
		} catch (Exception e) {
			return new ResultDto(2001,"操作异常");
		}
	}
}
