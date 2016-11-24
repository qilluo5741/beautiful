package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.User;
import com.xtc.entity.balancedetail;
import com.xtc.entity.invitation;
import com.xtc.entity.parkexpense;
import com.xtc.entity.tollRecords;
import com.xtc.entity.dto.OrderRecordInfo;
import com.xtc.service.IUserService;
import com.xtc.service.IbalancedetailService;
import com.xtc.util.HttpUtil;
import com.xtc.util.Pager;
import com.xtc.util.RestDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("detail")
public class BalancedetailController {
	@Autowired
	private IUserService us;
	@Autowired
	private IbalancedetailService balService;
	@Autowired
	private HttpSession session;
	@RequestMapping(value="balanced")
	public @ResponseBody Map<String, Object> getByUserid(ModelMap map,String mobile,String pageIndex,String pageSize){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> list = us.selectBymobil(mobile);
			String userid = list.get(0).getId();
			int index=Integer.parseInt(pageIndex);
			int size=Integer.parseInt(pageSize);
			List<balancedetail> oblist = balService.selectByuserid(userid,index,size);
			mapRtn.put(RestDto.RESULT,oblist);
		} catch (NumberFormatException e) {
			System.out.println("balanced");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	/**
	 * web��̨
	 * ������ϸ��¼��ѯ
	 * @param pager
	 * @param model
	 * @return
	 */
	@RequestMapping("selectAll")
	public String querybalancedetail(Pager<balancedetail> pager,ModelMap model){
		//������ʾ������
		pager.setPageSize(20);
		pager.setPageIndex(pager.getPageIndex());
		//ȡ������
		pager=balService.selectAll(pager.getPageIndex(),pager.getPageSize());
		model.addAttribute("pager", pager);
		//ȡ�ü���
		return "relation/balanceManger";
	}
	//�����¼
	@SuppressWarnings("unchecked")
	@RequestMapping(value="user/invite",method=RequestMethod.GET)
	public String selectInvie(String mobile,ModelMap mp){
		try {
			String httpUrl="http://localhost:8080/enjoy_park/invitation/selectBymobil.action?mobile="+mobile;
			String res=HttpUtil.request_post(httpUrl,"");
			JSONArray result=JSONObject.fromObject(res).getJSONArray("result");
			System.out.println(result);
			List<invitation> sks= JSONArray.toList(result, new invitation(),new JsonConfig());//��json����ת��Ϊlist����
			System.out.println(sks);
			mp.addAttribute("sks", sks);
			return "inviteRecord";
		}catch (Exception e){
			return "0";
		}
 }
	//ԤԼ������ѯ(����)detail/user/orderRecord
	@SuppressWarnings("unchecked")
	@RequestMapping(value="user/orderRecord",method=RequestMethod.GET)
	public String selectOrder(ModelMap mp,String mobile){
		try {
			/*String mobile=(String) session.getAttribute("mobile");*/
			/*String mobile="15836193514";*/
			String pageIndex="0";
			String pageSize="50";
			String httpUrl="http://localhost:8080/enjoy_park/orderinfo/getsecurorder.action?mobile="+mobile+"&pageIndex="+pageIndex+"&pageSize="+pageSize;
			String res=HttpUtil.request_post(httpUrl, "");
			JSONArray result=JSONObject.fromObject(res).getJSONArray("result");
			List<OrderRecordInfo> pager= JSONArray.toList(result,new OrderRecordInfo(),new JsonConfig());//��json����ת��Ϊlist����
			//ȡ������
			mp.addAttribute("pager",pager);
			return "OrderRecord";
			
		}catch (Exception e){
			return "0";
		}
	}
	/**ͣ��������
	 * detail/selectParkex.action
	 * @param pager
	 * @param model
	 * @param mobile
	 * @return
	 *//*
	@SuppressWarnings("unchecked")
	@RequestMapping(value="selectParkex",method=RequestMethod.GET)
	public String selectParkex(ModelMap mp,String mobile){
		try {
			String mobile="18581343206";
			String pageIndex="0";
			String pageSize="10";
			String httpUrl="http://localhost:8080/enjoy_park/expense/getparkorder.action?mobile="+mobile+"&pageIndex="+pageIndex+"&pageSize="+pageSize;
			String res=HttpUtil.request_post(httpUrl, "");
			JSONArray result=JSONObject.fromObject(res).getJSONArray("result");
			List<Parkex> pager= JSONArray.toList(result,new Parkex(),new JsonConfig());
			List<Parkex> pager=null;
			pager= JSONArray.toList(result,new Parkex(),new JsonConfig());//��json����ת��Ϊlist����
			System.out.println(pager.size());
			Integer in= (int) pager.get(0).getTotalRecords();
			mp.addAttribute("pager", pager);
			mp.addAttribute("pager",pager);
			return "parkexpense";
		}catch (Exception e){
			return "0";
		}
	}*/
	//ͣ���շ�
	@SuppressWarnings("unchecked")
	@RequestMapping(value="parkfee",method=RequestMethod.GET)
	public String selectParkFee(ModelMap mp,String mobile){
		try {
			String pageIndex="0";
			String pageSize="30";
			String httpUrl="http://localhost:8080/enjoy_park/property/expense.action?mobile="+mobile+"&pageIndex="+pageIndex+"&pageSize="+pageSize;
			String res=HttpUtil.request_post(httpUrl, "");
			JSONArray result=JSONObject.fromObject(res).getJSONArray("result");
			List<parkexpense> pager= JSONArray.toList(result,new parkexpense(),new JsonConfig());
			mp.addAttribute("pager",pager);
			return "parkfee";
		} catch (Exception e) {
			return "0";
		}
	}
	//��ҵ�����շ�Ա��¼
		@SuppressWarnings("unchecked")
		@RequestMapping(value="tollrecord",method=RequestMethod.GET)
		public String selectTollrecord(ModelMap mp){
			try {
				/*String tollRphone="13101089314";*/
				String tollRphone=(String) session.getAttribute("mobile");
				String httpUrl="http://localhost:8080/enjoy_park/tollrecord/selecttollrecord.action?tollRphone="+tollRphone;
				String res=HttpUtil.request_post(httpUrl, "");
				JSONArray result=JSONObject.fromObject(res).getJSONArray("result");
				List<tollRecords> pager= JSONArray.toList(result,new tollRecords(),new JsonConfig());
				mp.addAttribute("pager",pager);
				return "tollrecordinfo";
			} catch (Exception e) {
				return "0";
			}
		}
		/*//������
		@RequestMapping(value="code",method=RequestMethod.GET)
		public String code(ModelMap mp){
			String mobile=(String)session.getAttribute("mobile");
			User invideCode = us.selectBymobile(mobile);
			System.out.println(invideCode);
			mp.addAttribute("invideCode",invideCode);
			return "invite";
		}*/
		//�ҵ�Ǯ��
		@RequestMapping(value="selectPurse",method=RequestMethod.GET)
		public String purse(ModelMap mp){
			try {
				String mobil=(String) session.getAttribute("mobile");
				String httpUrl="http://localhost:8080/enjoy_park/account/selectByUserid.action?mobil="+mobil;
				String res=HttpUtil.request_post(httpUrl, "");
				JSONObject result=JSONObject.fromObject(res).getJSONObject("result");
				System.out.println(result);
				double balance=JSONObject.fromObject(result).getDouble("balance");
				System.out.println(balance);
				double freezeMoney=JSONObject.fromObject(result).getDouble("freezeMoney");
				System.out.println(freezeMoney);
				mp.addAttribute("balance",balance);
				mp.addAttribute("freezeMoney",freezeMoney);
				return "myPurse";
				} catch (Exception e) {
					return "0";
				}
			}
		//���׼�¼
		@SuppressWarnings("unchecked")
		@RequestMapping(value="balance",method=RequestMethod.GET)
		public String selectbalance(ModelMap mp,String mobile){
			try {
				String pageIndex="0";
				String pageSize="30";
				String httpUrl="http://localhost:8080/enjoy_park/detail/balanced.action?mobile="+mobile+"&pageIndex="+pageIndex+"&pageSize="+pageSize;
				String res=HttpUtil.request_post(httpUrl, "");
				JSONArray result=JSONObject.fromObject(res).getJSONArray("result");
				List<balancedetail> pager= JSONArray.toList(result,new balancedetail(),new JsonConfig());
				mp.addAttribute("pager",pager);
				return "Balancedetail";
				} catch (Exception e) {
					return "0";
				}
			}
		
}
