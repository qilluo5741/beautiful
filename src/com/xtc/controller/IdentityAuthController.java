package com.xtc.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.Driver;
import com.xtc.entity.User;
import com.xtc.entity.VehicleLicense;
import com.xtc.service.FileService;
import com.xtc.service.IDriverService;
import com.xtc.service.IUserService;
import com.xtc.service.IvehicleLicenseService;
import com.xtc.util.Base64;

@Controller
@RequestMapping("identityAuth")
public class IdentityAuthController {
	@Autowired
	private IDriverService is;
	@Autowired
	private IvehicleLicenseService ivls;
	@Autowired
	private IUserService ius;
	@Autowired
	private FileService fileService;
	/**
	 * 显示驾驶员的信息
	 * @param mobile
	 * @return
	 */
	@RequestMapping("getIdentInfo")
	public @ResponseBody Map<String, Object> getIdentInfo(String mobile) {
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			User u = ius.selectBymobile(mobile);
			String userId = u.getId();
			Driver driver = is.getOne(userId);
			if (driver != null) {
				mapRtn.put("driver_license_front",driver.getDriver_license_front());
				mapRtn.put("driver_license_back", driver.getDriver_license_back());
			}
			else{
				mapRtn.put("driver_license_front",null);
				mapRtn.put("driver_license_back",null);
			}
			List<VehicleLicense> vls = this.ivls.getByUserid(userId);
			if (vls.size() != 0) {
				VehicleLicense vehicleLicense = (VehicleLicense) vls.get(0);
				mapRtn.put("vehicle_license_front",vehicleLicense.getVehicle_license_front());
				mapRtn.put("vehicle_license_back",vehicleLicense.getVehicle_license_back());
				mapRtn.put("plate_no", vehicleLicense.getPlate_no());
			}
			else {
				mapRtn.put("vehicle_license_front",null);
				mapRtn.put("vehicle_license_back",null);
				mapRtn.put("plate_no",null);
			}
			mapRtn.put("email",u.getEmail());

		} catch (Exception e) {
			System.out.println("显示驾驶员的信息！");
			mapRtn.put("result",false);
		}
		return mapRtn;
	}

	/**
	 * 查询车牌号
	 */
	@RequestMapping("getcarNum")
	public @ResponseBody Map<String, Object> getcarNum(ModelMap map ,String mobile) {
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		try {
			List<String> cars = new ArrayList<String>();
			User user = this.ius.selectBymobile(mobile);
			List<VehicleLicense> vl = this.ivls.getByUserid(user.getId());
			for (int i = 0; i < vl.size(); i++) {
				String car = vl.get(i).getPlate_no();
				cars.add(car);
			}
			mapRtn.put("cars", cars);
		} catch (Exception e) {
			System.out.println("查询车牌号");
			mapRtn.put("result", false);
		}
		return mapRtn;
	}
	/**
	 * 更新驾驶员信息
	 * 
	 */
	@RequestMapping("identification")
	public @ResponseBody Map<String, Object> identification(String email,String carNum,String vehicleLicenseFront, String vehicleLicenseBack,String driverLicenseFront,String driverLicenseBack,String mobile){
		 Map<String, Object> mapRtn = new HashMap<String, Object>();
		 try {
			    User u = this.ius.getOne(mobile);
			    String userId = null;
			    if (u != null){
			      userId = u.getId();
			      u.setEmail(email);
				  boolean f = this.ius.updateuser(u);
				  mapRtn.put("updateEmail",f);
			    }
			    VehicleLicense v = this.ivls.getVL(userId,carNum);
			    if (v!= null) {
				//图片解密
			      byte[] oc = Base64.decode(vehicleLicenseFront);
			      ByteArrayInputStream org = new ByteArrayInputStream(oc);
			      String vlf = this.fileService.uploadImage(org);	  
			      v.setVehicle_license_front(vlf);
				  byte[] velb = Base64.decode(vehicleLicenseBack);
			      ByteArrayInputStream vel = new ByteArrayInputStream(velb);
			      String vlb = this.fileService.uploadImage(vel);
			      v.setVehicle_license_back(vlb);
			      v.setPlate_no(carNum);
			      boolean f = this.ivls.update(v);
			      mapRtn.put("updateVelhicle", f);
			    }else{
			      VehicleLicense vLicense = new VehicleLicense();
				  byte[] oc = Base64.decode(vehicleLicenseFront);
			      ByteArrayInputStream org = new ByteArrayInputStream(oc);
			      String vlf = this.fileService.uploadImage(org);
			      vLicense.setVehicle_license_front(vlf);
				  byte[] velb = Base64.decode(vehicleLicenseBack);
			      ByteArrayInputStream vel = new ByteArrayInputStream(velb);
			      String vlb = this.fileService.uploadImage(vel);
			      vLicense.setVehicle_license_back(vlb);
			      vLicense.setUserId(userId);
			      vLicense.setPlate_no(carNum);
			     boolean f = this.ivls.create(vLicense);
			     mapRtn.put("createVelhicle", f);
			    }
			    Driver d = this.is.getOne(userId);
			    if (d != null) {
				byte[] f = Base64.decode(driverLicenseFront);
			      ByteArrayInputStream lf = new ByteArrayInputStream(f);
			      String dlf = this.fileService.uploadImage(lf);
			      d.setDriver_license_front(dlf);
				byte[] dl = Base64.decode(driverLicenseBack);
			      ByteArrayInputStream dll = new ByteArrayInputStream(dl);
			      String dlb = this.fileService.uploadImage(dll);
			      d.setDriver_license_back(dlb);
			      d.setIs_load("1");
			      boolean ff =this.is.updateDriver(d);
			      mapRtn.put("updateDriver", ff);
			    } else {
			      Driver driver = new Driver();
				byte[] f = Base64.decode(driverLicenseFront);
			      ByteArrayInputStream lf = new ByteArrayInputStream(f);
			      String dlf = this.fileService.uploadImage(lf);
			      driver.setDriver_license_front(dlf);
				byte[] dl = Base64.decode(driverLicenseBack);
			      ByteArrayInputStream dll = new ByteArrayInputStream(dl);
			      String dlb = this.fileService.uploadImage(dll);
			      driver.setDriver_license_back(dlb);
			      driver.setUserId(userId);
			      driver.setIs_load("1");
			      boolean ff = this.is.create(driver);
			      mapRtn.put("createDriver", ff);
			    }
			} catch (Exception e) {
				System.out.println("更新驾驶员信息");
				mapRtn.put("result",false);
			}
		 return mapRtn;
	  }
}