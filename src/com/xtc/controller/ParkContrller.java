package com.xtc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.Account;
import com.xtc.entity.Orderinfo;
import com.xtc.entity.Park;
import com.xtc.entity.User;
import com.xtc.entity.balancedetail;
import com.xtc.entity.parkexpenses;
import com.xtc.entity.parkuser;
import com.xtc.entity.sysuser;
import com.xtc.entity.dto.RegistCountDto;
import com.xtc.entity.dto.parkDaoDto;
import com.xtc.entity.dto.parkObject;
import com.xtc.entity.dto.statusDto;
import com.xtc.service.IAccountService;
import com.xtc.service.IOrderinfoService;
import com.xtc.service.IUserService;
import com.xtc.service.IbalancedetailService;
import com.xtc.service.IparkService;
import com.xtc.service.IparkexpenseService;
import com.xtc.service.SysuserService;
import com.xtc.util.CreateInviteCode;
import com.xtc.util.HttpUtil;
import com.xtc.util.Pager;
import com.xtc.util.RestDto;
import com.xtc.utils.HttpSender;

import net.sf.json.JSONObject;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("Park")
public class ParkContrller {
	@Autowired
	private IparkexpenseService pservice;
	@Autowired
	private SysuserService service;
	@Autowired
	private IparkService parkService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrderinfoService oderservice;
	@Autowired
	private IbalancedetailService balService;
	@Autowired
	private IAccountService accservice;
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String date_creat = dateFormat.format(now);
	 /**
	  * 创蓝短信
	  */
	  String url = "http://222.73.117.158/msg/HttpBatchSendSM";// 应用地址
	  String account = "vip-xbtc1";// 账号
	  String pswd = "Tch917428";// 密码
	  boolean needstatus = true;// 是否需要状态报告，需要true，不需要false
	  String extno = null;// 扩展码
	  @RequestMapping("selectByUserid")
	  public @ResponseBody Map<String, Object> getByUserid(ModelMap map,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{
			List<User> list = userService.selectBymobil(mobil);
			String userid = list.get(0).getId();
			Park park = parkService.selectByparkid(userid);
			parkDaoDto pdd = new parkDaoDto();
			if(null != park){
				pdd.setCity(park.getCity());
				pdd.setAddress(park.getAddress());
				pdd.setEntry_address(park.getEntry_address());
				pdd.setCapacity(park.getCapacity());
				pdd.setCarnum(park.getCarnum());
				pdd.setType(park.getType());
				pdd.setPrice(park.getPrice());
				pdd.setIs_cooperate(park.getIs_cooperate());
				pdd.setProperty(park.getProperty());
				pdd.setCost(park.getCost());
				mapRtn.put(RestDto.RESULT,pdd);
			}
		} catch (Exception e) {
			System.out.println("selectByUserid");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	
	@RequestMapping("selectBymobil")
	public @ResponseBody Map<String, Object> getByUseridname(ModelMap map,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> list = userService.selectBymobil(mobil);
			String userid = list.get(0).getId();
			List<Orderinfo> order=oderservice.selectByid(userid);
			mapRtn.put(RestDto.RESULT,order);
		} catch (Exception e) {
			System.out.println("selectBymobil");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**停车场报表
	 * Park/getparkorder.action?mobile=18581343206
	 * @param mobile
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="getparkorder")
	public String getparkorder(Pager<parkexpenses> pager,ModelMap model,String mobile){
		//设置显示的条数
		pager.setPageSize(12);
		pager.setPageIndex(pager.getPageIndex());
		//取得总数
		pager=pservice.selectpgeryexpense(mobile,pager.getPageIndex(),pager.getPageSize());
		model.addAttribute("pager",pager);
		//取得集合
		return "parkexpense";
	}
	//修改停车场收费模式
	@RequestMapping("updatecost")
	public @ResponseBody Map<String, Object> updatecost(ModelMap map,String name,String cost){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			boolean park = parkService.updatecost(cost,name);
			if(park){
				mapRtn.put(RestDto.RESULT,park);
			}
		} catch (Exception e) {
			System.out.println("修改停车场收费模式");
		}
		return mapRtn;
	}
	//修改停车场
	@RequestMapping("updatepark")
	public @ResponseBody Map<String, Object> updatepark(ModelMap map,String city,String entry_address,String address,String capacity,String carnum,String type,String is_cooperate,String price,String name){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			boolean park = parkService.updatepark(city,entry_address,address,capacity,carnum,type,price,is_cooperate,name);
			if(park){
				mapRtn.put(RestDto.SUCCESS,park);
			}else{
				mapRtn.put(RestDto.RESULT,park);
			}
		} catch (Exception e) {
			System.out.println("修改停车场1");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	//修改停车场
	@RequestMapping("updateparks")
	public @ResponseBody Map<String, Object> updateparks(ModelMap map,String city,String address,String capacity,String carnum,String type,String is_cooperate,String price,String cost,String name,String entry_address){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(name!=null){
				boolean park = parkService.updateparks(city,entry_address,address,capacity,carnum,type,price,is_cooperate,name,cost);
				String id=parkService.selectmoralid(name);
				String key="14607db53fff73bc7ed6e611a6246fc1";
				String tableid="57563285afdf52393935af22";
				String httpUrl="http://yuntuapi.amap.com/datamanage/data/update";
				String httpArg="key="+key+"&tableid="+tableid+"&loctype=2&data={\"_id\":\""+id+"\",\"_address\":\""+entry_address+"\"}";
				System.out.println(HttpUtil.request_post(httpUrl,httpArg));
				mapRtn.put(RestDto.SUCCESS,park);
			}
		} catch (Exception e) {
			System.out.println("修改停车场2");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	@RequestMapping("selectNameOrAddress")
	public @ResponseBody Map<String, Object> selectNameOrAddress(ModelMap map,String name){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<Park> list= parkService.selectByName(name);
			if(null!=list){
				mapRtn.put(RestDto.SUCCESS,list);
			}
		} catch (Exception e) {
			System.out.println("selectNameOrAddress");
			mapRtn.put(RestDto.SUCCESS,null);
		}
		return mapRtn;
	}
	/**
	 * 查询当前停车场状态以及收费模式
	 * @param map
	 * @param name
	 * @return
	 */
	@RequestMapping("selectOneName")
	public @ResponseBody Map<String, Object> selectOneName(ModelMap map,String name){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			Park park = parkService.selectByOneName(name);
			if(null!=park){
				mapRtn.put(RestDto.SUCCESS,park);
			}
		} catch (Exception e) {
			System.out.println("查询当前停车场状态以及收费模式");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	@RequestMapping("selectOneid")
	public @ResponseBody Map<String, Object> selectOneid(ModelMap map,String id){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			Park park = parkService.selectByOneId(id);
			if(null!=park){
				mapRtn.put(RestDto.RESULT,park);
			}
		} catch (Exception e) {
			System.out.println("selectOneid'");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	  //添加停车场
	  @RequestMapping("addpark")
	  public @ResponseBody Map<String, Object> addpark(ModelMap map,String mobil,String name, String city, String address, String entry_address, String capacity, String carnum, String type, String price, String is_cooperate){
	  Map<String, Object> mapRtn = new HashMap<String, Object>();
	    try {
			List<User> userlist = this.userService.selectBymobil(mobil);
			String userid = ((User)userlist.get(0)).getId();
			if (userid != null){
			      Park pdd = new Park();
			      pdd.setName(name);
			      pdd.setCity(city);
			      pdd.setAddress(address);
			      pdd.setEntry_address(entry_address);
			      pdd.setCapacity(capacity);
			      pdd.setCarnum(carnum);
			      pdd.setType(type);
			      pdd.setPrice(price);
			      pdd.setStatus("2");
			      pdd.setStart_time(date_creat);//添加时间
			      pdd.setIs_cooperate(is_cooperate);
			      pdd.setUserid(userid);
			      pdd.setProperty("xb");
			      boolean list = this.parkService.insert(pdd);
			      mapRtn.put(RestDto.SUCCESS, Boolean.valueOf(list));
			}
		} catch (Exception e) {
			System.out.println("添加停车场1");
			 mapRtn.put(RestDto.RESULT,false);
		}
	    return mapRtn;
	  }
	  //添加停车场
	  @RequestMapping("addparks")
	  public @ResponseBody Map<String, Object> addparks(ModelMap map,String mobil,String name, String city, String address, String entry_address, String capacity, String carnum, String type, String price, String is_cooperate,String cost){
	    Map<String, Object> mapRtn = new HashMap<String, Object>();
	    try {
			List<User> userlist = this.userService.selectBymobil(mobil);
			String userid = ((User)userlist.get(0)).getId();
			if (userid != null){
			      Park pdd = new Park();
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
			      pdd.setProperty("xb");
			      boolean list = this.parkService.insert(pdd);
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
			      mapRtn.put(RestDto.SUCCESS, Boolean.valueOf(list));
			}
		} catch (Exception e) {
			System.out.println("添加停车场2");
			 mapRtn.put(RestDto.RESULT,false);
		}
	    return mapRtn;
	  }
	//更改状态机0  1  2 
	@RequestMapping("updatstate")
	public @ResponseBody Map<String, Object> updatestate(ModelMap map,String state,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> list = userService.selectBymobil(mobil);
			String parkid = list.get(0).getParkId();
			boolean park = parkService.updateState(parkid,state);
			if(park){
				mapRtn.put(RestDto.SUCCESS,park);
			}
		} catch (Exception e) {
			System.out.println("更改状态机'");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}

	@RequestMapping("selectAll")
	public @ResponseBody Map<String, Object>selectAllToAjax(ModelMap map){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<Park> list = parkService.selectAll();
			List<parkObject> plist = new ArrayList<parkObject>();
			for (int i = 0; i < list.size(); i++) {
				Park p =list.get(i);
				parkObject po = new parkObject();
				po.setAddress(p.getEntry_address());
				po.setPrice(p.getPrice());
				po.setName(p.getName());
				po.setStatus(p.getStatus());
				po.setId(p.getId());
				plist.add(po);
			}
			if(null!=list){
				mapRtn.put(RestDto.SUCCESS,plist);
			}
		} catch (Exception e) {
			System.out.println("selectAll");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * 根据手机号查询状态机
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("getByUserid")
	public @ResponseBody Map<String, Object> getone(ModelMap map,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{
			List<User> list = userService.selectBymobil(mobil);
			String parkid = list.get(0).getParkId();
			List<Park> park = parkService.selectBystrs(parkid);
			String status=park.get(0).getStatus();
			statusDto p =new statusDto();
			p.setStatus(status);
			mapRtn.put(RestDto.RESULT,p);
		}catch (Exception e) {
			System.out.println("根据手机号查询状态机'");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 *关联保安
	 * @return
	 * @throws InterruptedException 
	 */
	@RequestMapping("updateUserid")
	public @ResponseBody Map<String, Object> updateUserid(ModelMap map,String name,String invideCode,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			    User u = userService.selectOne(invideCode);//根据邀请码获取用户id  权限  
				String userid  = u.getId();
				String isad=u.getIs_admin();
				Park park=parkService.selectByOneName(name);
				String parkid=park.getId();
				boolean f = parkService.updateuserid(name,userid);
				if(f){
					boolean fn = userService.updateis_guard(invideCode,"1");
					if(fn){
						if(isad.toString().equals("2")){
							boolean fv = userService.updateparkid(isad+"",parkid,invideCode);
							mapRtn.put(RestDto.SUCCESS,fv);
						}else if(isad.toString().equals("3")){
							boolean fv = userService.updateparkid(isad+"",parkid,invideCode);
							mapRtn.put(RestDto.SUCCESS,fv);
						}else if(isad.toString().equals("1")){
							int temp=Integer.valueOf(isad)+2;
							boolean fv = userService.updateparkid(temp+"",parkid,invideCode);
							mapRtn.put(RestDto.SUCCESS,fv);
						}else{
							int temp=Integer.valueOf(isad)+2;
							boolean fv = userService.updateparkid(temp+"",parkid,invideCode);
							//查询当前用户是否是邀请的，，被管理成保安就给邀请人10元
							//根据关联码查询邀请人的邀请码
							//然后给邀请人加10元
							String invCode=parkService.selectinvideCode(invideCode);
							if(invCode!=null){
								//得到邀请人邀请码
								String sysuserid=parkService.selectbyuserid(invCode);
								String blyuserid=parkService.selectnotbalancedetail(sysuserid);
								if(blyuserid==null){
									Date now = new Date();
									SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
									String times=dateFormat.format(now);
									balancedetail balanc=new balancedetail();
									balanc.setUserid(sysuserid);
									balanc.setStarttime(times);
									balanc.setBalancetype("7");
									balanc.setMoney(10);//归属其他10元记录
									if(balanc!=null){
										boolean balist = balService.insert(balanc);
										System.out.println("新用户关联保安成功！"+balist);
									}
									/*Account account=service.selectByUserid(sysuserid);
									double balance=account.getBalance();
									double b=balance+10;
									boolean list2=service.updateByUserid(sysuserid,b);*/
								}
							}
							mapRtn.put(RestDto.SUCCESS,fv);
						}
					}
					mapRtn.put(RestDto.SUCCESS,true);
				}
		} catch (Exception e){
			System.out.println("关联保安");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	
	/**
	 *解除保安
	 * @returnhttp
	 * @throws InterruptedException 
	 */
	@RequestMapping("updatenull")
	public @ResponseBody Map<String, Object> updatenull(ModelMap map,String name,String invideCode,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			User u = userService.selectOne(invideCode);//根据邀请码获取用户id  权限  
			String isad=u.getIs_admin();
			boolean f = parkService.updateuserid(name,"");
			if(f){
				boolean fn = userService.updateis_guard(invideCode,"0");
				if(fn){
					if(isad.toString().equals("2")){
						int temp=Integer.valueOf(isad)-2;
						boolean fv = userService.updateParkid(temp+"",invideCode);
						mapRtn.put(RestDto.SUCCESS,fv);
					}else if(isad.toString().equals("3")){
						int temp=Integer.valueOf(isad)-2;
						boolean fv = userService.updateParkid(temp+"",invideCode);
						mapRtn.put(RestDto.SUCCESS,fv);
					}else if(isad.toString().equals("1")){
						boolean fv = userService.updateParkid(isad+"",invideCode);
						mapRtn.put(RestDto.SUCCESS,fv);
					}
				}
				mapRtn.put(RestDto.SUCCESS,fn);
			}
		} catch (Exception e) {
			System.out.println("解除保安");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/***
	 * 报表查询
	 * @param map
	 * @return
	 */
	@RequestMapping(value="getRegistCount",method=RequestMethod.GET)
	public String getRegistCount(ModelMap mv,Integer year,Integer month){
		// 默认当前时间
		if (year == null || month == null) {
			Calendar calendarCountDays = new GregorianCalendar();
			calendarCountDays.setTime(new Date());
			year = calendarCountDays.get(Calendar.YEAR);
			month = calendarCountDays.get(Calendar.MONTH) + 1;
		}
		// 查询
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", year + "-" + (month < 10 ? "0" + month : month));
		map.put("end", year + "-"
				+ ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)));
		// 得到当前月的天数
		Calendar cd = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM");
		try {
			cd.setTime(s.parse(map.get("begin")));
		} catch (ParseException e) {
			System.out.println("时间转换失败！");
		}
		int daysCount = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
		List<String> daysList = new ArrayList<String>();// ---------一月的日期
		for (int i = 1; i <= daysCount; i++) {
			map.put(map.get("begin") + "-" + (i < 10 ? "0" + i : i), 0 + "");
			daysList.add("'" + map.get("begin") + "-" + i + "'");
		}
		List<RegistCountDto> olist  = new  ArrayList<RegistCountDto>();
		List<Object> list = parkService.RegistCount(year,month);
		for (int i = 0; i < list.size(); i++) {
			Object [] o  = (Object[]) list.get(i);
			RegistCountDto or = new RegistCountDto();
			or.setDatecreated((String) o[0]);
			or.setCount((Object) o[1]);
			olist.add(or);
			map.put(olist.get(i).getDatecreated(),olist.get(i).getCount()+ "");
		}
		List<String> daysListDate = new ArrayList<String>();// ---------一月的日期
		for (int i = 1; i <= daysCount; i++) {
			daysListDate.add(map.get(map.get("begin") + "-"
					+ (i < 10 ? "0" + i : i)));
		}
		mv.addAttribute("daysList", daysList);// 添加日期
		mv.addAttribute("daysListDate", daysListDate);// 添加数据
		mv.addAttribute("year", year);// 添加数据
		mv.addAttribute("month", month);// 添加数据
		return "PregistCount";
	}
	//停车场报表
	@RequestMapping("selectAlls")
	public String queryProcurement(Pager<Park> pager,ModelMap model){
		//设置显示的条数
		pager.setPageSize(12);
		pager.setPageIndex(pager.getPageIndex());
		//取得总数
		pager=parkService.selectAll(pager.getPageIndex(),pager.getPageSize());
		model.addAttribute("ParkPager", pager);
		//取得集合
		return "relation/ParkManger";
	}
	/**
	 * 查询停车场收费
	 */
	@RequestMapping("getpriceByid")
	public @ResponseBody Map<String, Object> getpriceByid(ModelMap map,String parkid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			String price=parkService.selectprice(parkid);
			Map<String,String> ms=new HashMap<String, String>();
			ms.put("price",price);
			mapRtn.put(RestDto.RESULT,ms);
		} catch (Exception e) {
			System.out.println("查询停车场收费");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * 物业端查询关联
	 * @param map
	 * @param parkid
	 * @return
	 */
	@RequestMapping(value="getrelational",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> Relational(ModelMap map,String parkid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<parkuser> olist  = new  ArrayList<parkuser>();
			List<Object> oblist = service.selectusermobile(parkid);
			for (int i = 0; i < oblist.size(); i++) {
				Object [] o  = (Object[]) oblist.get(i);
				parkuser or = new parkuser();
				or.setName((String) o[0]);
				or.setMobile((String) o[1]);
				olist.add(or);
			}
			mapRtn.put(RestDto.RESULT,olist);
		} catch (Exception e) {
			System.out.println("物业端查询关联");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * 物业端关联保安
	 * @param map
	 * @param mobile
	 * @param parkid
	 * @param name
	 * @return
	 */
	@RequestMapping(value="relation",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> relation(ModelMap map,String mobile,String parkid,String name){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			String iphone=service.selectmobile(mobile);//判断是否已经注册！
			if(iphone!=null){
				service.updatesysuserrelation(parkid,"0","wy",name,mobile,"2");
				List<parkuser> olist  = new  ArrayList<parkuser>();
				List<Object> oblist = service.selectusermobile(parkid);
				for (int i = 0; i < oblist.size(); i++) {
					Object [] o  = (Object[]) oblist.get(i);
					parkuser or = new parkuser();
					or.setName((String) o[0]);
					or.setMobile((String) o[1]);
					olist.add(or);
				}
				mapRtn.put(RestDto.RESULT,olist);
			}else{
				String invideCode = CreateInviteCode.getRandomChar();
				sysuser user=new sysuser();
				user.setName(name);
				user.setMobile(mobile);
				user.setPassword("e10adc3949ba59abbe56e057f20f883e");
				user.setAge("0");
				user.setIs_owner("0");
				user.setIs_admin("2");//0管理员1物业2保安3超级管理员4收费员
				user.setFirst("1");//第一次登录
				user.setProperty("wy");
				user.setIsdelete("0");
				user.setParkId(parkid);
				user.setInvideCode(invideCode);
				user.setProusertime(new Date());
				boolean list=service.addsysuser(user);
				if(list){
					  String msg = "欢迎使用享泊管理端，您的账号为当前手机号，默认密码123456,请前往各大平台下载APP，感谢您的使用。";// 短信内容
					  HttpSender.batchSend(url,account,pswd,mobile,msg,needstatus,extno);//创蓝短信使用
					  Date now = new Date();
					  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					  String date_creat = dateFormat.format(now);
					  sysuser ulist=service.selectsysuserfirst(mobile);
					  String userid = ulist.getId();
					  Account acc=accservice.selectByUserid(userid);
					  if(acc==null){
						Account account = new Account();
						account.setBalance(0);
						account.setUserId(userid);
						account.setNumber("1");
						account.setContent("1");
						account.setDate_creat(date_creat);
						account.setType("1");
						account.setFreezeMoney(0);
						if(account!=null){
							accservice.insert(account);
							service.updateparkuser(userid,parkid);
						}
					}
				}
				List<parkuser> olist  = new  ArrayList<parkuser>();
				List<Object> oblist = service.selectusermobile(parkid);
				for (int i = 0; i < oblist.size(); i++) {
					Object [] o  = (Object[]) oblist.get(i);
					parkuser or = new parkuser();
					or.setName((String) o[0]);
					or.setMobile((String) o[1]);
					olist.add(or);
				}
				mapRtn.put(RestDto.RESULT,olist);
			}
		} catch (Exception e) {
			System.out.println("物业端关联保安");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * 解除关联
	 * @param map
	 * @param mobile
	 * @param parkid
	 * @param name
	 * @return
	 */
	@RequestMapping(value="relieverelation",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> Relieverelation(ModelMap map,String mobile,String parkId){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			sysuser sys =service.selectsysuserfirst(mobile);
			String is_owner=sys.getIs_owner();
			if(is_owner.toString().equals("1")){
				service.updatesysuserrelation("0",parkId,mobile);
			}else{
				service.updatesysuserrelations("0",parkId,"1",mobile);
			}
			mapRtn.put(RestDto.RESULT,true);
		} catch (Exception e) {
			System.out.println("解除关联");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
}
