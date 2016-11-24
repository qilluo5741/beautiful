package com.xtc.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xtc.entity.Park;
import com.xtc.service.IparkService;

import net.sf.json.JSONObject;
/**
 * 同步小区到高德地图
 * @author Administrator
 *
 */
@Controller
@RequestMapping("Parktest")
public class Parktest {
	@Autowired
	private IparkService parkService;
	
	@RequestMapping("selectAll")
	public @ResponseBody Map<String, Object>selectAll(ModelMap map){
		Map<String, Object> mapRtn = new HashMap<String, Object>();
		List<Park> list = parkService.selectAll();
		for (Park park : list) {
			String name=park.getName();
			String address=park.getEntry_address();
			String parkid=park.getId();
			String status=park.getStatus();
			String key="14607db53fff73bc7ed6e611a6246fc1";
			String tableid="57563285afdf52393935af22";
			String httpUrl="http://yuntuapi.amap.com/datamanage/data/create";
			String httpArg="key="+key+"&tableid="+tableid+"&loctype=2&data={\"_name\":\""+name+"\",\"_address\":\""+address+"\",\"parkid\":\""+parkid+"\",\"status\":\""+status+"\"}";
			String res=HttpUtil.request_post(httpUrl,httpArg);
			String _id=JSONObject.fromObject(res).getString("_id");
			System.out.println(_id);
			parkService.updatemoralid(_id,parkid);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return mapRtn;
	}
}
