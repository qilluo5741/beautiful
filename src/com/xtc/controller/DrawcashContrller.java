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
	 * ��ҳ��ѯ
	 * @return
	 */
	@RequestMapping("selectAll")
	public String querydrawcash(Pager<drawcash> pager,ModelMap model){
		//������ʾ������
		pager.setPageSize(20);
		pager.setPageIndex(pager.getPageIndex());
		//ȡ������
		pager=service.selectAll(pager.getPageIndex(), pager.getPageSize());
		model.addAttribute("drawcashPager", pager);
		//ȡ�ü���
		return "relation/drawcashManger";
	}
	/**
	 * ����״̬
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
			System.out.println("����״̬");
		}
		return drawid;
	}
}
