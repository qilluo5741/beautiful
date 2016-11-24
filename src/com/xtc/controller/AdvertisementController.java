package com.xtc.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xtc.entity.Advertisement;
import com.xtc.service.FileService;
import com.xtc.service.IAdvertisementService;
import com.xtc.util.RestDto;
/**
 * 广告查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("advertisement")
public class AdvertisementController{
	@Autowired 
	private IAdvertisementService service;
	@Autowired
	private FileService fileService;
	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//广告查询
	@RequestMapping("list")
	public @ResponseBody Map<String, Object> list(ModelMap map){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<Advertisement> list = service.listALL();
			mapRtn.put(RestDto.RESULT,list);
		} catch (Exception e) {
			System.out.println("广告查询");
			mapRtn.put(RestDto.RESULT,null);
		}
		return mapRtn;
	}
	  //广告上传http://localhost:8080/sharepark/advertisement/add.action
	  @RequestMapping("add")
	  public @ResponseBody Map<String, Object> fileUpload(ModelMap map) throws IOException{
		  Map<String, Object> mapRtn = new HashMap<String, Object>();
		  Date now = new Date();
		  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  try {
			String time = dateFormat.format(now);
			  File ff = new File("D:\\Android\\6.jpg");
			  FileInputStream inputFile = new FileInputStream(ff);
			  byte[] buffer = new byte[(int)ff.length()];
			  inputFile.read(buffer); 
			  inputFile.close();
			  String file = new Base64().encodeToString(buffer);
			  byte[] oc = new Base64().decode(file);
			  ByteArrayInputStream org = new ByteArrayInputStream(oc);
			  String picture = fileService.uploadImage(org);
			  Advertisement ad = new Advertisement();
			  ad.setType("广告");
			  ad.setUrl(picture);
			  ad.setPicture(picture);
			  ad.setStart_time(time);
			  ad.setEnd_time(time);
			  ad.setDate_created(time);
			  if(ad!=null){
				  boolean list = service.insert(ad);
				  mapRtn.put(RestDto.RESULT,list);
			  }
		} catch (Exception e) {
			System.out.println("广告上传");
			  mapRtn.put(RestDto.RESULT,false);
		}
		return mapRtn;
	}

}
