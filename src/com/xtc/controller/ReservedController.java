package com.xtc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xtc.entity.ResultDto;
import com.xtc.entity.syspark;
/**
 * @author weimei
 */
@RestController
@RequestMapping("reserved")
public class ReservedController {
	
	@RequestMapping("getreserved")
	@ResponseBody
	public ResultDto getreserved(String mobile){
		if(mobile==null){
			return new ResultDto(200,"请求参数为空！");	
		}
		List<syspark> list=null;
		return new ResultDto(200,"操作成功",list);
	}
}
