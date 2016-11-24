package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Account;
import com.xtc.entity.sysuser;
import com.xtc.entity.tollRecords;
import com.xtc.service.IAccountService;
import com.xtc.service.SysuserService;
import com.xtc.service.tollRecordsService;
import com.xtc.util.CreateInviteCode;
import com.xtc.util.HttpUtil;
import com.xtc.util.RestDto;
import com.xtc.utils.HttpSender;

import net.sf.json.JSONObject;
/**
 * ��ҵ�����շ�Ա
 * @author Administrator
 */
@Controller
@RequestMapping("tollrecord")
public class TollRecordsController {
	@Autowired
	private tollRecordsService service;
	@Autowired
	private SysuserService sysservice;
	@Autowired
	private IAccountService accservice;
	@Autowired
	private SysuserService uservice;
	/**
	  * ��������
	  */
	  String url = "http://222.73.117.158/msg/HttpBatchSendSM";// Ӧ�õ�ַ
	  String account = "vip-xbtc1";// �˺�
	  String pswd = "Tch917428";// ����
	  boolean needstatus = true;// �Ƿ���Ҫ״̬���棬��Ҫtrue������Ҫfalse
	  String extno = null;// ��չ��
	/**
	 * �����շ�Ա
	 * @param name
	 * @param tollRremname
	 * @param parkid
	 * @param tollRrecphone
	 * @param tollRphone
	 * @return
	 * tollrecord/addtollrecords.action?name=123&tollRremname=diyishoufeikou&parkid=297ebe0e53a3aeb80153a3b0c1a50008&tollRrecphone=18888888888&tollRphone=17777777777
	 */
	@RequestMapping(value="addtollrecords",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> addtollrecords(String name,String tollRremname,String parkid,String tollRrecphone,String tollRphone){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(parkid!=null && tollRrecphone!=null && tollRphone!=null && tollRremname!=null){
				String phone=service.selectBymobile(tollRrecphone);
				if(phone==null){
					String invideCode = CreateInviteCode.getRandomChar();
					sysuser user=new sysuser();
					user.setName(name);
					user.setMobile(tollRrecphone);
					user.setPassword("e10adc3949ba59abbe56e057f20f883e");
					user.setAge("0");
					user.setIs_owner("2");
					user.setIs_admin("4");//0����Ա1��ҵ2����3��������Ա4�շ�Ա
					user.setFirst("1");//��һ�ε�¼
					user.setProperty("wy");
					user.setIsdelete("0");
					user.setParkId(parkid);
					user.setInvideCode(invideCode);
					user.setProusertime(new Date());
					boolean list=sysservice.addsysuser(user);
					if(list){
						String msg = "��ӭʹ��������ˣ������˺�Ϊ��ǰ�ֻ��ţ�Ĭ������123456,��ǰ������ƽ̨����APP����л����ʹ�á�";// ��������
						HttpSender.batchSend(url,account,pswd,tollRrecphone,msg,needstatus,extno);//��������ʹ��
						tollRecords toll=new tollRecords();
						toll.setParkid(parkid);
						toll.setTollname(name);
						toll.setTollRphone(tollRphone);
						toll.setTollRrecphone(tollRrecphone);//�շ�Ա�ֻ�����
						toll.setTollRremname(tollRremname);
						toll.setTollRtime(new Date());
						boolean tolllsit=service.addtollRecords(toll);
						  Date now = new Date();
						  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						  String date_creat = dateFormat.format(now);
						  sysuser ulist=uservice.selectsysuserfirst(tollRrecphone);
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
									boolean listd = accservice.insert(account);
									mapRtn.put(RestDto.RESULT,listd);
								}
							}
						mapRtn.put(RestDto.RESULT,tolllsit);
					}
				}else{
					boolean sysuer=service.updateTollRecord(name,tollRrecphone,"e10adc3949ba59abbe56e057f20f883e","0","0","4","1","wy","0",parkid);
					String tollphone=service.selectBymtollRrecphone(tollRrecphone);
					if(tollphone==null){
						tollRecords toll=new tollRecords();
						toll.setParkid(parkid);
						toll.setTollname(name);
						toll.setTollRphone(tollRphone);
						toll.setTollRrecphone(tollRrecphone);//�շ�Ա�ֻ�����
						toll.setTollRremname(tollRremname);
						toll.setTollRtime(new Date());
						boolean tolllsit=service.addtollRecords(toll);
						mapRtn.put(RestDto.RESULT,tolllsit);
					}
					mapRtn.put(RestDto.RESULT,sysuer);
				}
			}
		} catch (Exception e) {
			System.out.println("�Ѿ����ڸ��û�");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * ��ѯ�շ�Ա
	 * @param tollRphone
	 * @return
	 * tollrecord/htollrecord.action?tollRphone=18116237927
	 */
	@RequestMapping(value="htollrecord",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> selecthtollrecord(String tollRphone){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			sysuser sys =sysservice.selectsysuserfirst(tollRphone);
			String parkid=sys.getParkId();
			if(parkid!=null){
				List<tollRecords> list=service.slectTollRecords(parkid);
				mapRtn.put(RestDto.RESULT,list);
			}else{
				mapRtn.put(RestDto.RESULT,null);
			}
		} catch (Exception e) {
			System.out.println("��ѯ�շ�Ա");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * ����շ�Ա
	 * @param tollRrecphone
	 * @return
	 */
	@RequestMapping(value="relievetollrecord",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> relievetollrecord(String tollRrecphone){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			sysuser sys =sysservice.selectsysuserfirst(tollRrecphone);
			String is_owner=sys.getIs_owner();
			if(is_owner=="2"){
				service.deleteTollRecords(tollRrecphone);
				sysservice.updatesysuserrelations("0","","0",tollRrecphone);
				mapRtn.put(RestDto.RESULT,true);
			}else{
				service.deleteTollRecord(tollRrecphone);
				sysservice.updatesysuserrelations("0","","1",tollRrecphone);
				mapRtn.put(RestDto.RESULT,true);
			}
		} catch (Exception e) {
			System.out.println(" ����շ�Ա'");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * ��ѯ������¼
	 * @param parkid
	 * @param tollRphone
	 * @return
	 */
	@RequestMapping(value="selecttollrecord",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> selecttollrecord(String tollRphone){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<tollRecords> list=service.selecttollRecords(tollRphone);
			mapRtn.put(RestDto.RESULT,list);
		} catch (Exception e) {
			System.out.println("��ѯ������¼");
			 mapRtn.put(RestDto.RESULT,false);
		}	
		return mapRtn;
	}
	/**
	 * ��ҵ�����շ�Ա����
	 * @param tollRrecphone
	 * @return
	 */
	@RequestMapping(value="updateuserpwd",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateuserpwd(String tollRrecphone){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			boolean list=service.updatetollRecords("e10adc3949ba59abbe56e057f20f883e",tollRrecphone);
			if(list){
				mapRtn.put(RestDto.RESULT,list);
			}
		} catch (Exception e) {
			System.out.println("��ҵ�����շ�Ա����");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * ɾ��
	 * @param tollRrecphone
	 * tollrecord/deleteuser/action?mobile=
	 * @return
	 */
	@RequestMapping(value="deleteuser",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> delleteuser(String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			boolean list=service.deleteTollRecords(mobile);
			boolean listt=service.deleteTollRecord(mobile);
			if(list || listt){
				mapRtn.put(RestDto.RESULT,list);
			}
		} catch (Exception e) {
			System.out.println("deleteuser'");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * loclahost:8080/enjoy_parktollrecord/selectmobile.action?tollRphone=13101089314
	 * @param tollRphone
	 * @return
	 */
	@RequestMapping(value="selectmobile",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> selectmobile(String tollRphone){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			String mobile=service.selectBymobiles(tollRphone);
			if(mobile!=null){
				System.out.println(mobile);
			}else{
				System.out.println(mobile);
			}
		} catch (Exception e) {
			System.out.println("�쳣������ɣ�selectmobile");
		}
		return mapRtn;
	}
	
	/**
	 * ��ѯparkid
	 * @param mobile
	 * tollrecord/selectParkid.action?mobile=
	 * @return
	 */
	@RequestMapping(value="selectParkid",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> selectParkid(String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			sysuser user=service.selectByParkid(mobile);
			if(user!=null){
				String parkid=user.getParkId();
				System.out.println(parkid);
				Map<String, Object> extra = new HashMap<String, Object>();
				extra.put("parkid",parkid);
				mapRtn.put(RestDto.RESULT,extra);
			}
		} catch (Exception e) {
			System.out.println("��ѯparkid");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * �շ�ģʽ�����������ݽӿ�
	 * @param parkid
	 * @param feesmoney
	 * @param freetime
	 * @param shargetype
	 * localhost:8080/enjoy_park/tollrecord/feestype.action?parkid=297ebe0e544d53580154518295ab0027&freemoney=10000.0&frertime=0&shargetype=2&maxMoney=11111
	 * @return
	 * 1�ǰ����շ�
	 * 2�ǰ�ʱ��
	 */
	@RequestMapping(value="feestype",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> tollfeestype(String parkid,String frertime,String shargetype,String freemoney,String maxMoney){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(parkid!=null && freemoney!=null && frertime!=null && shargetype!=null){
				Integer freetime=Integer.valueOf(frertime);
				Double money=Double.valueOf(freemoney);
				Integer chargetype=Integer.valueOf(shargetype);
				String json="json";
				String httpUrl="http://interface.sharebo.cn/sharebodoc/sharebo/shanghai/batp/updateChargingMode?parkid="+parkid+"&money="+money+"&freetime="+freetime+"&chargetype="+chargetype+"&maxMoney="+maxMoney+"&_type="+json;
				String res=HttpUtil.request_post(httpUrl,"null");
				System.out.println(res);
				JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
				System.out.println(result);
				int state=JSONObject.fromObject(result).getInt("state");
				String msg=JSONObject.fromObject(result).getString("msg");
				System.out.println(state);
				if(state==200){
					mapRtn.put(RestDto.RESULT,true);
				}else{
					mapRtn.put(RestDto.RESULT,msg);
				}
			}else{
				mapRtn.put(RestDto.RESULT,null);
			}
		} catch (Exception e) {
			System.out.println("����δ�򿪣��շ�ģʽ�����������ݽӿ�");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * ��ѯС���շ�ģʽ
	 * @param parkid
	 * @return
	 * tollrecord/feellmode.action?parkid=297ebe0e544d53580154518295ab0027
	 */
	@RequestMapping(value="feellmode",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> feellmode(String parkid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			String json="json";
			String httpUrl="http://interface.sharebo.cn/sharebodoc/sharebo/shanghai/batp/getChargingMode?parkid="+parkid+"&_type="+json;
			String res=HttpUtil.request_post(httpUrl,"null");
			System.out.println(res);
			/*/�շ����ͣ�2 ����Сʱ��1���մ� ��ѷ���ʱ�� ����Ԥ���߼��շ� ����*/
			JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
			String chargetype=JSONObject.fromObject(result).getString("chargetype");
			String freetime=JSONObject.fromObject(result).getString("freetime");
			String issenior=JSONObject.fromObject(result).getString("issenior");
			String money=JSONObject.fromObject(result).getString("money");
			Long maxMoney=JSONObject.fromObject(result).getLong("maxMoney");
			Map<String,Object> resmap=new HashMap<String,Object>();
			resmap.put("chargetype",chargetype);
			resmap.put("freetime",freetime);
			resmap.put("issenior",issenior);
			resmap.put("money",money);
			resmap.put("maxMoney",maxMoney);
			mapRtn.put(RestDto.RESULT,resmap);
		} catch (Exception e) {
			System.out.println("��ѯС���շ�ģʽ");
			mapRtn.put(RestDto.RESULT,null);
		}
	  return mapRtn;
	}
}
