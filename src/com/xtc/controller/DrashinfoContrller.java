package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.DrawcashInfo;
import com.xtc.entity.dto.drawinfoDtoImpl;
import com.xtc.service.IDrawcashInfoService;
import com.xtc.util.RestDto;
/**
 * 提现时间
 * @author Administrator
 *
 */
@Controller
@RequestMapping("drawcash")
public class DrashinfoContrller {
	@Autowired
	private IDrawcashInfoService dservice;
	
	@RequestMapping("selectdrawid")
	public @ResponseBody Map<String, Object> getBydrawid(ModelMap map,String drawid){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			DrawcashInfo d = dservice.selectBydrawid(drawid);
			drawinfoDtoImpl dr = new drawinfoDtoImpl();
			if(d!=null){
			dr.setEndtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d.getEndtime()));
			dr.setFinaltime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d.getFinaltime()));
			mapRtn.put(RestDto.SUCCESS,dr);
			}
		} catch (Exception e) {
			System.out.println("drawcash");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
}
