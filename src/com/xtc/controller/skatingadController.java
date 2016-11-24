package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.skatingad;
import com.xtc.service.IskatingadService;
import com.xtc.util.RestDto;

@Controller
@RequestMapping("skating")
public class skatingadController {
	@Autowired
	private IskatingadService service;
	/**
	 * π„∏Ê≤È—Ø
	 * localhost:8080/enjoy_park/skating/skat.action
	 * @return
	 */
	@RequestMapping(value="skat")
    public @ResponseBody Map<String, Object> skating(){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<skatingad> tool=service.selectByskatingad();
			if(tool!=null){
				mapRtn.put(RestDto.RESULT,tool);
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
}
