package com.xtc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.Orderinfo;
import com.xtc.entity.Park;
import com.xtc.entity.Tag;
import com.xtc.entity.User;
import com.xtc.entity.VehicleLicense;
import com.xtc.entity.information;
import com.xtc.entity.invitation;
import com.xtc.entity.dto.BaoanDto;
import com.xtc.entity.dto.RegistCountDto;
import com.xtc.entity.dto.Userdto;
import com.xtc.service.FileService;
import com.xtc.service.IOrderinfoService;
import com.xtc.service.ITagService;
import com.xtc.service.IUserService;
import com.xtc.service.IinvitationService;
import com.xtc.service.IparkService;
import com.xtc.service.IvehicleLicenseService;
import com.xtc.util.CreateInviteCode;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private IUserService us;
	@Autowired
	private IparkService ps;
	@Autowired
	private IvehicleLicenseService vehic;
	@Autowired
	private IinvitationService service;
	@Autowired
	private ITagService tagService;
	@Autowired
	private IOrderinfoService os;
	@Autowired
	private FileService fileService;
	/**
	 * ����userid�鿴�û�״̬
	 * 
	 * @param map
	 * @return
	 */
	// ��ѯ������
	@RequestMapping("selectStateBymobile")
	public @ResponseBody Map<String, Object> getBymobile(ModelMap map,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = us.selectBymobil(mobile);
			String state = userlist.get(0).getStatus();
			if (null != state) {
				System.out.println("��ѯ������");
				System.out.println(state);
				mapRtn.put(RestDto.RESULT,state);
			}
		} catch (Exception e) {
			System.out.println("��ѯ�������쳣");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}

	/**
	 * ����������
	 * @param map
	 * @param mobile
	 * @param state
	 * @return
	 */
	@RequestMapping("updateState")
	public @ResponseBody Map<String, Object> updateState(ModelMap map, String mobile, String state){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			boolean f = us.updateUser(mobile, state);
			if (f) {
				mapRtn.put(RestDto.SUCCESS, f);
			}
		} catch (Exception e) {
			System.out.println("����������");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}

	@RequestMapping("updateInvideCode")
	public @ResponseBody Map<String, Object> updateInvideCode(ModelMap map,String mobile, String Code){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			Code = CreateInviteCode.getRandomChar();
			boolean f = us.updateInvideCode(Code, mobile);
			if (f) {
				mapRtn.put(RestDto.SUCCESS, f);
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}

	@RequestMapping("selectinvideCode")
	public @ResponseBody Map<String, Object> updateInvideCode(ModelMap map,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			Userdto d = new Userdto();
			if (d != null) {
				List<User> user = us.selectBymobil(mobile);
				String invide = user.get(0).getInvideCode();
				d.setInvideCode(invide);
				mapRtn.put(RestDto.SUCCESS, d);
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}

	@RequestMapping("selectOne")
	public @ResponseBody Map<String, Object> selectOne(ModelMap map,String mobile, String invideCode){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			User user = us.selectOne(invideCode);
			if (user != null) {
				mapRtn.put(RestDto.RESULT, user);
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}

	/**
	 * ����ͣ������id����
	 * @param map
	 * @param mobile
	 * @param invideCode
	 * @return
	 */
	@RequestMapping("selectByparkid")
	public @ResponseBody Map<String, Object> selectByparkid(ModelMap map,String parkname){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<Park> p = ps.selectByName(parkname);
			String id = p.get(0).getId();
			List<User> list = us.getByparkid(id);
			List<BaoanDto> baolist = new ArrayList<BaoanDto>();
			User u = new User();
			for (int i = 0; i < list.size(); i++) {
				u = list.get(i);
				BaoanDto b = new BaoanDto();
				String in = u.getInvideCode();
				String n = u.getName();
				String is_guard = u.getIs_guard();
				b.setInvideCode(in);
				b.setName(n);
				b.setIs_guard(is_guard);
				baolist.add(b);
			}
			if (list != null) {
				mapRtn.put(RestDto.RESULT, baolist);
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
	//��������
	@RequestMapping("selectMsobileAndPlateno")
	public @ResponseBody Map<String, Object> selectTwo(ModelMap map,String myMobile, String mobile, String plateNo){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			User mob = us.selectBymobile(mobile);
			User Code = us.selectBymobile(myMobile);
			VehicleLicense vehi = vehic.selectByplateNo(plateNo);
			if (mob != null) {
				mapRtn.put(RestDto.RESULT, -1);
				return mapRtn;
			} else if (vehi != null) {
				mapRtn.put(RestDto.RESULT, -2);
				return mapRtn;
			} else {
				share(Code.getInvideCode(),myMobile,mobile);
				if (mobile != null) {
					String state = "0";
					invitation inv = new invitation();
					Date now = new Date();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String startDate = dateFormat.format(now);
					inv.setStartDate(startDate);
					inv.setStartmoble(myMobile);
					inv.setRtartmoble(mobile);
					inv.setState(state);
					boolean list = service.insert(inv);
					mapRtn.put(RestDto.SUCCESS, list);
				}
				return mapRtn;
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT, -1);
		}
		return mapRtn;
	}

	/**
	 * @param name������
	 * @param mobile
	 *��������ֻ�����
	 */
	@SuppressWarnings("unused")
	public static void share(String code, String name, String mobile){
		String tp = "���ĺ���@������ʹ�á�BATPͣ�������ͣ�����⣬���ע΢�Ź���ƽ̨��ǰ���̳�����APP���ҵ��������ǣ�"+ code + "��ע��ʱ�ǵ�����Ӵ����BATPͣ����";
		try {
			String result = IOUtils.toString(
					new URL(
			"http://sh2.ipyy.com/sms.aspx?action=send&sendTime=&userid=&account=jksc362"
			+ "&password=xiangbo558"+ "&mobile="+ mobile+ "&content="
			+ URLEncoder.encode(tp.replace("@", name),"utf-8")).openConnection()
			.getInputStream(), "utf-8");
		} catch (Exception e) {
			throw new RuntimeException("Sms Send Error Caused.", e);
		}
	}

	/**
	 *������Ϣ����
	 * @param name
	 * @param carNumbers
	 * @param tags
	 * @param mobile
	 */
	@RequestMapping("updateUser")
	public @ResponseBody Map<String, Object> updateUser(String name,String carNumbers,String tags,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{	
		List<User> user = this.us.selectBymobil(mobile);
		String userid= user.get(0).getId();
	    if(user != null) {
	      user.get(0).setName(name);
	      User u = user.get(0);
	      boolean f= this.us.updateuser(u);//���ݴ��������û��������޸�--true
	      mapRtn.put(RestDto.RESULT,f);
	    }
	    //�жϳ��ƺ��Ƿ�Ϊ��
	    if(carNumbers!=null){
	    	List<VehicleLicense> list = this.vehic.getByUserid(userid);
    		this.vehic.delete(userid);
    		System.out.println(list);
	    	String[] cars = carNumbers.split(",");
		    for (int i = 0; i < cars.length; i++) {
			     Boolean f = addCar(cars[i], mobile);
			     mapRtn.put(RestDto.RESULT,f);
		    }
	    }else{
	    	 //���ƺ�Ϊ��-��ճ��ƺ�
	    	if(carNumbers==null){
	    		List<VehicleLicense> list = this.vehic.getByUserid(userid);
	    		this.vehic.delete(userid);
	    		System.out.println("��ճ��ƺ�"+list);
	    		mapRtn.put(RestDto.RESULT,true);
	    	}
	    }
	    //�жϱ�ǩ�Ƿ�Ϊ��
	    if(tags!=null){
	    	List<Tag> taglist = this.tagService.taglist(userid);
    	    this.tagService.delete(userid);
    	    System.out.println(taglist);
	    	String[] tag = tags.split(",");
		    for (int j = 0; j < tag.length; j++){
			     Boolean f= add(tag[j], mobile);
			     mapRtn.put(RestDto.RESULT,f);
		    }
	    }else{
	    	//�жϱ�ǩΪ��-��ձ�ǩ
	    	if(tags==null){
	    		List<Tag> taglist = this.tagService.taglist(userid);
	    	    this.tagService.delete(userid);
	    	    System.out.println("���tag"+taglist);
	    	    mapRtn.put(RestDto.RESULT,true);
	    	}
	    }
	} catch (Exception e) {
		 mapRtn.put(RestDto.SUCCESS,false);
	}
    return mapRtn;
  }

	/**
	 *��ӱ�ǩ
	 * @param name
	 * @param mobile
	 */
	public Boolean add(String name, String mobile) {
		try {
			User user = this.us.selectBymobile(mobile);
			String userId = null;
			if (user != null) {
				userId = user.getId();
			}
			Tag t = this.tagService.getTag(userId, name);
			if (t == null) {
				Tag tag = new Tag();
				tag.setName(name);
				tag.setUserId(userId);
				tag.setType("0");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String str = sdf.format(new Date());
				tag.setDate_updated(str);
				this.tagService.create(tag);
				return true;
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String str = sdf.format(new Date());
				t.setDate_updated(str);
				this.tagService.update(t);
				return true;
			}
		} catch (Exception e) {
			System.out.println("��ǩ");
		}
		return null;
	}

	/**
	 *�������
	 * @param birthDate
	 * @return
	 */
	public int getAge(Date birthDate){
		int age;
		try {
			age = 0;
			Date now = new Date();
			SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
			SimpleDateFormat format_M = new SimpleDateFormat("MM");
			String birth_year = format_y.format(birthDate);
			String this_year = format_y.format(now);
			String birth_month = format_M.format(birthDate);
			String this_month = format_M.format(now);
			age = Integer.parseInt(this_year) - Integer.parseInt(birth_year);
			if (this_month.compareTo(birth_month) < 0)
				age--;
			if (age < 0)
				age = 0;
			return age;
		} catch (Exception e) {
			System.out.println("����");
		}
		return 1;
	}

	/**
	 * ������Ϣ��ѯ
	 * @param mobile
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("getInfo")
	public @ResponseBody Map<String, Object> getInfo(ModelMap map,String mobile){
		 Map<String, Object> mapRtn = new HashMap<String, Object>();
		 Map<String,Object> resmap=new HashMap<String, Object>();
		 try{
			User user = this.us.selectBymobile(mobile);
			String userid=user.getId();
			List<VehicleLicense> v = this.vehic.getByUserid(userid);
			List<Tag> tags = this.tagService.taglist(userid);
			if(tags!=null){
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < tags.size(); i++) {
					String name = tags.get(i).getName();
					list.add(name);
				}
				resmap.put("tags",list);
			}
			List<String> carnums = new ArrayList<String>();
		    if(v!=null){
				for (int i = 0; i < v.size();i++) {
					String carnum = v.get(i).getPlate_no();
					carnums.add(carnum);
				}
				resmap.put("carnums",carnums);
		    }
			String use=us.selectinformation(userid);
			if(use==null){
				information infom=new information();
				Date register=user.getDriving_licence_register_date();
				if(register!= null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String jling = sdf.format(register);
					int jl = 0;
					jl = getAge(sdf.parse(jling));
					infom.setJl(jl);
				}
				List<Orderinfo> lists = this.os.getOrder(userid);
				if (lists.size() != 0) {
					long duration = this.os.getDuration(userid);
					if(duration != 0L){
						duration /= 3600L;
					}
					infom.setDuration(duration);
					long times = this.os.getTimes(userid);
					infom.setTimes(times);
				}
				infom.setUserid(userid);
				us.addinformation(infom);
			}/*else{
				Date register=user.getDriving_licence_register_date();
				int jl = 0;
				if(register!= null){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String jling = sdf.format(register);
					jl = getAge(sdf.parse(jling));
				}
				List<Orderinfo> lists = this.os.getOrder(userid);
				long duration = 0;
				long times = 0;
				if (lists.size() != 0) {
					duration = this.os.getDuration(userid);
					if(duration != 0L){
						duration /= 3600L;
					}
					times = this.os.getTimes(userid);
				}
				us.updateinformation(duration,jl,times,userid);
			}*/
			information mation=us.getinformation(userid);
			resmap.put("jl",mation.getJl());
			resmap.put("duration",mation.getDuration());
			resmap.put("times",mation.getTimes());
			resmap.put("name",user.getName());
			resmap.put("age",user.getAge());
			resmap.put("avatar",user.getAvatar());
			mapRtn.put(RestDto.RESULT,resmap);
		}catch (Exception e){
			System.out.println("�쳣������ɣ�");
			mapRtn.put("SUCCESS",null);
		}
		 return mapRtn;
	}
	/*@RequestMapping("getsInfo")
	public @ResponseBody Map<String, Object> getsInfo(ModelMap map, String mobile) throws ParseException{
		 Map<String, Object> mapRtn = new HashMap<String, Object>();
		 User user = this.us.selectBymobile(mobile);
		 String userid=user.getId();
		 Date register=user.getDriving_licence_register_date();
		try {
			if(register!= null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String jling = sdf.format(register);
				int jl = 0;
				jl = getAge(sdf.parse(jling));
				mapRtn.put("jl",Integer.valueOf(jl));
			}
			List<Orderinfo> lists = this.os.getOrder(userid);
			if (lists.size() != 0) {
				UserDao dao=new UserDao();
				long duration=dao.getDuration(userid);
				if(duration != 0){
					duration /= 3600;
				}
				mapRtn.put("duration", Long.valueOf(duration));
				long times=dao.getTimes(userid);
				mapRtn.put("times",times);
			}
		} catch (Exception e){
			mapRtn.put("SUCCESS",null);
		}
		 return mapRtn;
	}*/
	/**
	 * user/getsInfo.action?mobile=13101089314
	 * @param map
	 * @param mobile
	 * @return
	 */
	@RequestMapping("getsInfo")
	public @ResponseBody Map<String, Object> getsInfo(ModelMap map, String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			 User user = this.us.selectBymobile(mobile);
			 String userid=user.getId();
			 Date register=user.getDriving_licence_register_date();
			if(register!= null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String jling = sdf.format(register);
				int jl = 0;
				jl = getAge(sdf.parse(jling));
				mapRtn.put("jl",Integer.valueOf(jl));
			}
			List<Orderinfo> lists = this.os.getOrder(userid);
			if (lists.size() != 0) {
				long duration = this.os.getDuration(userid);
				if(duration != 0L){
					duration /= 3600L;
				}
				mapRtn.put("duration", Long.valueOf(duration));
				long times = this.os.getTimes(userid);
				mapRtn.put("times", times);
			}else{
				mapRtn.put("duration",0);
				mapRtn.put("times", 0);
			}
		} catch (Exception e) {
			User user = this.us.selectBymobile(mobile);
			 String userid=user.getId();
			 Date register=user.getDriving_licence_register_date();
			if(register!= null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String jling = sdf.format(register);
				int jl = 0;
				try {
					jl = getAge(sdf.parse(jling));
				} catch (ParseException e1) {
					System.out.println("");
				}
				mapRtn.put("jl",Integer.valueOf(jl));
			}
			List<Orderinfo> lists = this.os.getOrder(userid);
			if (lists.size() != 0) {
				long duration = this.os.getDuration(userid);
				if(duration != 0L){
					duration /= 3600L;
				}
				mapRtn.put("duration", Long.valueOf(duration));
				long times = this.os.getTimes(userid);
				mapRtn.put("times", times);
			}else{
				mapRtn.put("duration",0);
				mapRtn.put("times", 0);
			}
		}
		return mapRtn;
	}
	/**
	 * ��ӳ��ƺ�
	 * @param plateNo
	 * @param mobile
	 */
	public Boolean addCar(String plateNo, String mobile) {
		try {
			User user = this.us.selectBymobile(mobile);
			String userId = null;
			if (user != null) {
				userId = user.getId();
			}
			VehicleLicense vl = this.vehic.getVL(userId,plateNo);
			if (vl == null) {
				VehicleLicense v = new VehicleLicense();
				v.setPlate_no(plateNo);
				v.setUserId(userId);
				this.vehic.create(v);
				return true;
			} else {
				vl.setDate_updated(new Date());
				this.vehic.update(vl);
				return true;
			}
		} catch (Exception e) {
			System.out.println("����");
		}
		return null;
	}
	
    //�޸�ͷ��
	@RequestMapping("/image/upload")
	public @ResponseBody Map<String, Object> selectTwo(ModelMap map,String file,String mobile) throws IOException {
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			byte[] by = new Base64().decode(file);
			ByteArrayInputStream is = new ByteArrayInputStream(by);
			//д�뵽ָ����Ŀ¼
			String avatar =this.fileService.uploadImage(is);
			if(avatar!=null){
				boolean user=us.updateavatar(avatar,mobile);
				mapRtn.put(RestDto.RESULT,user);
				mapRtn.put("avatar",avatar);
			}else{
				mapRtn.put(RestDto.RESULT,false);
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/***
	 * �����ѯ
	 * @param map
	 * @return
	 */
	@RequestMapping(value="getRegistCount",method=RequestMethod.GET)
	public String getRegistCount(ModelMap mv,Integer year,Integer month){
		//Ĭ�ϵ�ǰʱ��
		if (year == null || month == null) {
			Calendar calendarCountDays = new GregorianCalendar();
			calendarCountDays.setTime(new Date());
			year = calendarCountDays.get(Calendar.YEAR);
			month = calendarCountDays.get(Calendar.MONTH) + 1;
		}
		//��ѯ
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", year + "-" + (month < 10 ? "0" + month : month));
		map.put("end", year + "-"
				+ ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)));
		//�õ���ǰ�µ�����
		Calendar cd = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM");
		try {
			cd.setTime(s.parse(map.get("begin")));
		} catch (ParseException e) {
			System.out.println("ʱ��ת��ʧ�ܣ�");
		}
		int daysCount = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
		List<String> daysList = new ArrayList<String>();// ---------һ�µ�����
		for (int i = 1; i <= daysCount; i++) {
			map.put(map.get("begin") + "-" + (i < 10 ? "0" + i : i), 0 + "");
			daysList.add("'" + map.get("begin") + "-" + i + "'");
		}
		List<RegistCountDto> olist  = new  ArrayList<RegistCountDto>();
		List<Object> list = us.RegistCount(year,month);
		for (int i = 0; i < list.size(); i++) {
			Object [] o  = (Object[]) list.get(i);
			RegistCountDto or = new RegistCountDto();
			or.setDatecreated((String) o[0]);
			or.setCount((Object) o[1]);
			olist.add(or);
			map.put(olist.get(i).getDatecreated(),olist.get(i).getCount()+ "");
		}
		List<String> daysListDate = new ArrayList<String>();//---------һ�µ�����
		for (int i = 1; i <= daysCount; i++) {
			daysListDate.add(map.get(map.get("begin") + "-"
					+ (i < 10 ? "0" + i : i)));
		}
		mv.addAttribute("daysList", daysList);//�������
		mv.addAttribute("daysListDate", daysListDate);//�������
		mv.addAttribute("year", year);//�������
		mv.addAttribute("month", month);//�������
		return "RegistCount";
	}
}
