package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.invitation;
import com.xtc.service.IinvitationService;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("invitation")
public class InvitationContrller {
	@Autowired
	private IinvitationService service;
	@RequestMapping("selectBymobil")
	public @ResponseBody Map<String, Object> getByUserid(ModelMap map,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<invitation> in= service.selectBymobile(mobile);
			if(in!=null){
				mapRtn.put(RestDto.RESULT,in);
			}
		} catch (Exception e) {
			System.out.println("invitation");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
}
