package com.gm.driverGuard.web;

//import javax.inject.Inject;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gm.driverGuard.entity.SearchCritera;
import com.gm.driverGuard.json.JsonResponse;
import com.gm.driverGuard.service.NGIDataService;
import com.gm.driverGuard.util.ConstantUtils;
import com.gm.driverGuard.util.ConstantUtils.JSON;
import com.sun.corba.se.spi.ior.ObjectId;

@Controller
@RequestMapping(value = "/data")
public class NGIDataController extends BaseController{

	@Autowired
	private NGIDataService service;
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "upload")
	@ResponseBody
	public JsonResponse uploadData() throws IOException {
		long nowTimeMillis = Calendar.getInstance().getTimeInMillis();
		String requestStr = IOUtils.toString(this.request.getInputStream());
		JsonResponse response = new JsonResponse();
//		service.batchInsertIntoCollection(list, "ngidata");
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		return response;
	}
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "showData")
	@ResponseBody
	public JsonResponse getNGIData(SearchCritera critera) throws IOException {
//		List<DBObject> dbObjs = service.getDBObjects("ngidata", critera);
		JsonResponse response = new JsonResponse();
//		response.setData(dbObjs);
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		return response;
	}
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "init")
	public String init(Map<String, Object> map) throws IOException {
//		List<DBObject> dbObjs = service.getValueByParam("ngidata", new String[]{"vin_2_9", "vin_10_17"});
//		Set<DBObject> dbObjRes = new HashSet<DBObject>();
//		if(dbObjs != null){
//			for(DBObject dbo : dbObjs){
//				dbObjRes.add(dbo);
//			}
//		}
//		List<DBObject> configObjs = service.getDBObjects("ngidatatmp");
//		if(configObjs != null && !configObjs.isEmpty()){
//			DBObject configObj = configObjs.get(0);
//			configObj.removeField("_id");
//			Set<String> params = configObj.keySet();
//			map.put("params", params);
//		}
//		map.put("vins", dbObjRes);
		return "index";
	}
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "openNgiDataTemplateSetting")
	public String openNgiDataTemplateSetting() throws IOException {
		return "ngidatatmp";
	}
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "saveNgiDataTmp")
	@ResponseBody
	public JsonResponse saveNgiDataTmp() throws IOException {
		String requestStr = IOUtils.toString(this.request.getInputStream());
//		DBObject dbObject = (DBObject)JSON.parse(requestStr);
		JsonResponse response = new JsonResponse();
//		service.dropCollection("ngidatatmp");
//		DBObject db_res = service.addIntoCollection("ngidatatmp", dbObject);
		response.setResult(ConstantUtils.JSON.RESULT_OK);
		response.setMsg("save successfullly.");
//		response.setData(db_res);
		return response;
	}
	
	/**
	 * 
	 * @param condition
	 *            the condition
	 * @return the object
	 * @throws IOException 
	 */
	@RequestMapping(value = "viewDetail")
	@ResponseBody
	public JsonResponse viewDetail(int time, boolean isNew, int machine, long timeSecond, int inc) throws IOException {
		JsonResponse response = new JsonResponse();
//		ObjectId objId = new ObjectId(time,machine, inc);
//		DBObject obj =	service.getNGIRecordById(objId.toString(), "ngidata");
//		obj.removeField("_id");
//		obj.removeField("_class");
		response.setResult(ConstantUtils.JSON.RESULT_OK);
//		response.setData(obj.toString());
		return response;
	}
	
	  /** 
		* @Title: exportExcel 
		* @Description: 导出用户数据生成的excel文件
		* @param  model
		* @param  request
		* @param  response
		* @param  设定文件 
		* @return ModelAndView    返回类型 
		* @throws 
		*/
		@RequestMapping(value="/exportExcel",method=RequestMethod.POST)  
	    public String exportExcel(ModelMap model, SearchCritera critera) {  
//		   List<DBObject> dbObjs = service.getDBObjects("ngidata", critera);
//		   model.put("dataList", dbObjs);
//		   String params = critera.getParams();
//		   model.put("params", params);
	       return "exportExcel";   
	   }

	
}