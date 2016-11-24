package com.xtc.controller;

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
import com.xtc.entity.Bank;
import com.xtc.entity.DrawcashInfo;
import com.xtc.entity.User;
import com.xtc.service.IAccountService;
import com.xtc.service.IBankService;
import com.xtc.service.IDrawcashInfoService;
import com.xtc.service.IUserService;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("drawcashInfo")
public class DrawcashInfoController {

	@Autowired
	private IBankService bs;
	@Autowired
	private IDrawcashInfoService dservice;
	@Autowired
	private IAccountService as;
	@Autowired 
	private IUserService us;

	@RequestMapping("insertDrawcashInfo")
	public @ResponseBody Map<String, Object> insertDrawcashInfo(ModelMap map,String mobil,String balance){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = us.selectBymobil(mobil);
			String userid = userlist.get(0).getId();
			Bank bank = bs.selectByUserid(userid);
			DrawcashInfo info  = new DrawcashInfo();
			Account a  = as.selectByUserid(userid);
			double balances=Double.parseDouble(balance);
			double b = a.getBalance();
			if(b>=balances && balances >=100 && bank!=null){
				double bb = b-balances;
				Date time =new Date();
				info.setEndtime(time);
				info.setMoney(balances);
				info.setUserid(userid);
				info.setBank(bank);
				info.setState("1");
				info.setFinaltime(time);
				boolean f = dservice.insert(info);
				if(f){
				boolean fl= as.updateByUserid(userid,bb);
					mapRtn.put(RestDto.SUCCESS,fl);
				}
			}else{
				mapRtn.put(RestDto.RESULT,false);
			}
		} catch (Exception e) {
			System.out.println("insertDrawcashInfo");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	@RequestMapping("selectByuserid")
	public @ResponseBody Map<String, Object>selectByuserid(ModelMap map,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{
			List<User> userlist = us.selectBymobil(mobil);
			String userid = userlist.get(0).getId();
			List<DrawcashInfo> list = dservice.selectByUserid(userid);
			mapRtn.put(RestDto.RESULT, list);
		} catch (Exception e) {
			System.out.println("selectByuserid");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
}
