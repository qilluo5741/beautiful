package com.xtc.controller;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xtc.entity.drawcash;
import com.xtc.service.IdrawcashService;
import com.xtc.util.Pager;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("drawcash")
public class DrawcashContrller {
	@Autowired
	private IdrawcashService service;
	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping("selectAll")
	public String querydrawcash(Pager<drawcash> pager,ModelMap model){
		//设置显示的条数
		pager.setPageSize(20);
		pager.setPageIndex(pager.getPageIndex());
		//取得总数
		pager=service.selectAll(pager.getPageIndex(), pager.getPageSize());
		model.addAttribute("drawcashPager", pager);
		//取得集合
		return "relation/drawcashManger";
	}
	/**
	 * 更新状态
	 */
	@RequestMapping(value="updateState")
	public String updateGoodsByid(String drawid){
		try {
			Date finaltime= new Date();
			SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(service.updateState(s.format(finaltime),drawid)){
				return "redirect:selectAll.action";
			}else{
				return "error";
			}
		} catch (Exception e) {
			System.out.println("更新状态");
		}
		return drawid;
	}
}
