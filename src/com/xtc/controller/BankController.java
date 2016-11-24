package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Bank;
import com.xtc.entity.User;
import com.xtc.service.IBankService;
import com.xtc.service.IUserService;
import com.xtc.util.RestDto;
/**
 * 绑定的银行卡
 * @author Administrator
 *
 */
@Controller
@RequestMapping("Bank")
public class BankController {
	@Autowired
	private IBankService bservice;
	@Autowired
	private IUserService userService;
	
	@RequestMapping("selectByUserid")
	public @ResponseBody Map<String, Object> getByUserid(ModelMap map,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try{
			List<User> userlist = userService.selectBymobil(mobil);
			String id = userlist.get(0).getId();
			Bank bank = bservice.selectByUserid(id);
			if(null != bank){
				mapRtn.put(RestDto.RESULT, bank);
			}
		} catch (Exception e) {
			System.out.println("selectByUserid");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
	@RequestMapping("delete")
	public @ResponseBody Map<String, Object> deleteBank(ModelMap map,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		List<User> userlist = userService.selectBymobil(mobil);
		String userid = userlist.get(0).getId();
		boolean f = bservice.deleteBank(userid);
		if(f){
			mapRtn.put(RestDto.SUCCESS, f);
		}
		return mapRtn;
	}
	@RequestMapping("updateBank")
	public @ResponseBody Map<String, Object> updateBank(ModelMap map,String accountname,String accountnumbe,String bankname,
	    String bankplace , String subbank ,String mobil){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<User> userlist = userService.selectBymobil(mobil);
			String id = userlist.get(0).getId();
			Bank b = bservice.selectByUserid(id);
				if(b!=null){
					boolean f = bservice.updateBank(accountname ,accountnumbe,bankname,bankplace,subbank,id);
					mapRtn.put(RestDto.SUCCESS,f);
				}else{
					Bank bank = new Bank();
					bank.setAccountname(accountname);
					bank.setAccountnumbe(accountnumbe);
					bank.setBankname(bankname);
					bank.setBankplace(bankplace);
					bank.setSubbank(subbank);
					bank.setUserid(id);
					boolean fs = bservice.insert(bank);
					mapRtn.put(RestDto.SUCCESS,fs);
				}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
}
