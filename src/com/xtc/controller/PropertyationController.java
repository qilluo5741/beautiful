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

import com.xtc.entity.parkexpense;
import com.xtc.entity.propertyation;
import com.xtc.entity.sysuser;
import com.xtc.service.IparkexpenseService;
import com.xtc.service.propertyationService;
import com.xtc.util.RestDto;
/**
 * ����ҵ
 * @author Administrator
 */
@Controller
@RequestMapping("property")
public class PropertyationController {
	@Autowired
	private propertyationService pservice;
	@Autowired
	private IparkexpenseService service;
	/**
	 * �����豸
	 * @param propertyAddress
	 * @param parkid
	 * @param markid
	 * @param propertyphone
	 * @return
	 */
	@RequestMapping(value="propertyation",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> propertyation(String propertyAddress,String parkid,String markid,String propertyphone){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		 try {
			if(markid!=null && parkid!=null){
				 propertyation pry=new propertyation();
				 pry.setPropertyAddress(propertyAddress);
				 pry.setParkid(parkid);
				 pry.setMarkid(markid);
				 pry.setPropertyphone(propertyphone);
				 pry.setPropertytime(new Date());
				 boolean list = this.pservice.addpropertyation(pry);
				 if(list){
					 mapRtn.put(RestDto.RESULT,list);
				 }
			 }
		} catch (Exception e) {
			System.out.println("�����豸");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * ��ѯ��ǰͣ�����ѹ����豸
	 * @param parkid
	 * @return
	 */
	@RequestMapping(value="selectproperty",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> selectproperty(String parkid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		 try {
			List<propertyation> list=pservice.selectpropertyation(parkid);
			mapRtn.put(RestDto.RESULT,list);
		} catch (Exception e) {
			System.out.println("��ѯ��ǰͣ�����ѹ����豸");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * ��ѯ�շ�ģʽ
	 * @param parkid
	 * http://localhost:8080/enjoy_park/property/expense.action?mobile=&pageIndex=1&pageSize=10
	 * @return
	 */
	@RequestMapping(value="expense",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> parkexpense(String mobile,String pageIndex,String pageSize){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		 try {
			sysuser user=service.selectByuserid(mobile);
			String parkid=user.getParkId();
			String userid=user.getId();
			System.out.println(userid);
			int index=Integer.parseInt(pageIndex);
			int size=Integer.parseInt(pageSize);
			List<parkexpense> list=service.selectByexpense(parkid,userid,index,size);
			if(list !=null){
				mapRtn.put(RestDto.RESULT,list);
			}else{
				mapRtn.put(RestDto.RESULT,null);
			}
		} catch (Exception e) {
			System.out.println("��ѯ�շ�ģʽ");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
	/**
	 * ȷ���շ�
	 * @param chargestatus
	 * @param mobile
	 * @param parkid
	 *  0 δ�շ� 1 ���շ� 2 ��ҵ���� 3 ���ó��� 4 ��������',
	 * http://localhost:8080/enjoy_park/property/updateexpense.action?chargestatus=1&mobile=13101089314&parkdeid=4028378155e35a2e0155e35a5c4d0000
	 * @return
	 */
	@RequestMapping(value="updateexpense",method=RequestMethod.POST)
    public @ResponseBody Map<String, Object> updateexpense(String chargestatus,String mobile,String parkdeid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			if(chargestatus!=null && mobile!=null && parkdeid!=null){
				sysuser user=service.selectByuserid(mobile);
				String userid=user.getId();
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String firsttime = dateFormat.format(now);//����ʱ��
				boolean list=service.updateByparkexpense(chargestatus,userid,parkdeid,firsttime);
				if(list){
					mapRtn.put(RestDto.RESULT,list);
				}
			}else{
				mapRtn.put(RestDto.RESULT,null);
			}
		} catch (Exception e) {
			System.out.println("ȷ���շ�");
			mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}
}
