package com.xtc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.statistics;
import com.xtc.service.IstatisticsService;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("statistics")
public class StatisticsController {
	@Autowired
	private IstatisticsService service;
	
	@RequestMapping("updateBycount")
	public @ResponseBody Map<String, Object> updateByselectBystatisticcount(ModelMap map){
		 Map<String, Object> mapRtn = new HashMap<String, Object>();
		 try {
			String statisticid="24528570156056577";
			 statistics sta=service.selectBystatisticcount(statisticid);
			 int stticcount=sta.getStatisticcount();
			 System.out.println(stticcount);
			 int stta=stticcount+1;
			 System.out.println(stta);
			 boolean stticount = service.updateByselectBystatisticcount(stta,statisticid);
			 mapRtn.put(RestDto.SUCCESS,stticount);
		} catch (Exception e) {
			System.out.println("updateBycount");
			 mapRtn.put(RestDto.SUCCESS,false);
		}
		return mapRtn;
	}
}
