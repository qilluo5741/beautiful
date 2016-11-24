package com.xtc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Discount;
import com.xtc.service.IDiscountService;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("discount")
public class DiscountController {
	@Autowired
	private IDiscountService dis;
	/**
	 * 查看所有
	 * @param map
	 * @return
	 */
	@RequestMapping("selectall")
	public @ResponseBody Map<String, Object> selectall(ModelMap map){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<Discount> list = dis.selectAll();
			if(null != list){
				mapRtn.put(RestDto.RESULT, list);
			}
		} catch (Exception e) {
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
}
