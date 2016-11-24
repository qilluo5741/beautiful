package com.xtc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.User;
import com.xtc.entity.relation;
import com.xtc.service.IUserService;
import com.xtc.service.IinvitationService;
import com.xtc.service.IrelationService;
import com.xtc.util.Pager;
import com.xtc.util.RestDto;
/**
 * @author Administrator
 */
@Controller
@RequestMapping("relation")
public class RelationController {
	@Autowired
	private IrelationService service;
	@Autowired
	private IUserService userService;
	@Autowired
	private  IinvitationService invservice;
	/**
	 * 用户邀请注册后给邀请人加10元
	 * @return relation/addrelation.action
	 */
	@RequestMapping("addrelation")
	public @ResponseBody Map<String, Object> addrelation(ModelMap map,String mobil,String invideCode,String mobile){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			Date now = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(mobil!=null){
				String invideCode1 = userService.selectis_guardBymobile(mobil);
				if(invideCode1!=null){
					relation re=new relation();
					re.setInvideCode(invideCode);
					re.setRelationCode(invideCode1);
					re.setInvideDate(new Date());
					boolean list = service.insert(re);
					if(re!=null){
						String date_created = dateFormat.format(now);
						boolean m=invservice.updateByUserid(date_created,mobil);
						mapRtn.put(RestDto.SUCCESS,m);
					}
					mapRtn.put(RestDto.SUCCESS,list);
				}else{
					mapRtn.put(RestDto.SUCCESS,true);
				}
			}else{
				String invideCode1 = userService.selectis_guardBymobile(mobile);
				if(invideCode1!=null){
					relation re=new relation();
					re.setInvideCode(invideCode);
					re.setRelationCode(invideCode1);
					re.setInvideDate(new Date());
					boolean list = service.insert(re);
					if(re!=null){
						String date_created = dateFormat.format(now);
						boolean m=invservice.updateByUserid(date_created,mobile);
						mapRtn.put(RestDto.SUCCESS,m);
					}
					mapRtn.put(RestDto.SUCCESS,list);
				}else{
					mapRtn.put(RestDto.SUCCESS,true);
				}
			}
		} catch (Exception e) {
			System.out.println("addrelation");
			mapRtn.put(RestDto.RESULT, false);
		}
		return mapRtn;
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	@RequestMapping("selectAll")
	public String queryProcurement(Pager<relation> pager,ModelMap model){
		//设置显示的条数
		pager.setPageSize(20);
		pager.setPageIndex(pager.getPageIndex());
		//取得总数
		pager=service.selectAll(pager.getPageIndex(),pager.getPageSize());
		model.addAttribute("relationPager", pager);
		//取得集合
		return "relation/relationManger";
	}
	@RequestMapping("selectAlls")
	public String queryuser(Pager<User> pager,ModelMap model){
		//设置显示的条数
		pager.setPageSize(20);
		pager.setPageIndex(pager.getPageIndex());
		//取得总数
		pager=userService.selectAll(pager.getPageIndex(),pager.getPageSize());
		model.addAttribute("UserPager", pager);
		//取得集合
		return "relation/UserManger";
	}
	
	@RequestMapping(value="updateState")
	public String updateuserByid(String id){
		try {
			if(userService.updateState(id)){
				return "redirect:selectAlls.action";
			}else{
				return "error";
			}
		} catch (Exception e) {
			System.out.println("updateState！");
		}
		return id;
	}
	@RequestMapping(value="updateStates")
	public String updateusersByid(String id){
		try {
			if(userService.updateStates(id)){
				return "redirect:selectAlls.action";
			}else{
				return "error";
			}
		} catch (Exception e) {
			System.out.println("updateStates！");
		}
		return id;
	}
	@RequestMapping(value="updateSex")
	public String updatesexByid(String id){
		try {
			if(userService.updatesex(id)){
				return "redirect:selectAlls.action";
			}else{
				return "error";
			}
		} catch (Exception e) {
			System.out.println("updateSex！");
		}
		return id;
	}
}
