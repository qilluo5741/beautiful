package com.xtc.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Advise;
import com.xtc.entity.User;
import com.xtc.service.FileService;
import com.xtc.service.IAdviseService;
import com.xtc.service.IUserService;
import com.xtc.util.Base64;
import com.xtc.util.RestDto;
/*
 * 意见建议
 */
@Controller
@RequestMapping("advise")
public class AdviseController{
	@Autowired
	private FileService fileService;
	@Autowired
	private IAdviseService adviservice;
	@Autowired
	private IUserService usersService;
	//意见建议
	@RequestMapping("addadvise")
	public @ResponseBody Map<String, Object> addadvise(ModelMap map,String mobile,String content,String picture) throws IOException{
		  Map<String, Object> mapRtn = new HashMap<String, Object>();
		  try {
			List<User> list = usersService.selectBymobil(mobile);
			  String userid = list.get(0).getId();
			  if(picture==null){
				  Advise advise = new Advise();
				  advise.setUserId(userid);
				  advise.setContent(content);
			      advise.setType("0");
			      if(userid!=null){
			    	  boolean advi = adviservice.insert(advise);
			    	  mapRtn.put(RestDto.RESULT,advi);
			      }
			  }else{
				  Advise advise = new Advise();
				  advise.setUserId(userid);
				  advise.setContent(content);
				  new Base64();
				  byte[] by = Base64.decode(picture);
				  ByteArrayInputStream is = new ByteArrayInputStream(by);
				  String picture_url = this.fileService.uploadImage(is);
				  advise.setPicture_url(picture_url);
				  advise.setType("0");
				  if(userid!=null){
			    	  boolean advi = adviservice.insert(advise);
			    	  mapRtn.put(RestDto.RESULT,advi);
			      }
			}
		} catch (Exception e) {
			System.out.println("意见建议");
			 mapRtn.put(RestDto.RESULT,false);
		}
		  return mapRtn;
	}
}
