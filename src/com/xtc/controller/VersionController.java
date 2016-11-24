package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Version;
import com.xtc.service.IVersionService;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("version")
public class VersionController {
	@Autowired
	private IVersionService service;
	/**
	 * 查看所有
	 * @param map
	 * @return
	 */
	@RequestMapping("selectall")
	public @ResponseBody Map<String, Object> selectall(ModelMap map){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		List<Version> list = service.getAll();
		try {
			if(null != list){
				mapRtn.put(RestDto.RESULT, list);
			}
		} catch (Exception e) {
			System.out.println("查看所有");
		}
		return mapRtn;
		}
		@RequestMapping("selectalls")
		public @ResponseBody Map<String, Object> selectalls(ModelMap map) {
		Map<String, Object> mapRtn = new HashMap<String, Object>();
            try {
				List<Version> list = service.getAlls();
				if(null != list){
					mapRtn.put(RestDto.RESULT, list);
				  }
			} catch (Exception e) {
				System.out.println("selectalls");
				mapRtn.put(RestDto.RESULT, false);
			}
	         return mapRtn;
		}
}
