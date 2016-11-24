package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.toolversion;
import com.xtc.service.ToolversionService;
import com.xtc.util.RestDto;
@Controller
@RequestMapping("tollversion")
public class TooversionController {
	@Autowired
	private ToolversionService tservice;
	/**
	 * 版本更新
	 * @return
	 */
	@RequestMapping(value="toversion")
    public @ResponseBody Map<String, Object> tooversion(){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<toolversion> tool=tservice.selectByToolversion();
			if(tool!=null){
				mapRtn.put(RestDto.RESULT,tool);
			}
		} catch (Exception e) {
			System.out.println("版本更新");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
}
