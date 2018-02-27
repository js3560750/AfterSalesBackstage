package com.jinsong.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jinsong.mapper.RepairMapper;
import com.jinsong.model.Repair;
import com.jinsong.service.HospitalService;
import com.jinsong.util.JsUtil;

@RestController
public class HospitalController {

	private static final Logger logger = LoggerFactory.getLogger(HospitalController.class);

	@Autowired
	HospitalService hospitalService;
	

	/**
	 * 医生提交repair工单
	 * 
	 * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
	 */
	@PostMapping("/repair")
	public String insertRepair(@ModelAttribute Repair repair) {

		try {
			//服务返回>0成功，否则失败
			//但是返回给前端的code=0表示成功
			if (hospitalService.insertRepair(repair) > 0) {
				return JsUtil.getJSONString(0, "提交成功");
			}
		} catch (Exception e) {
			logger.error("提交repair工单失败" + e.getMessage());
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 医生提交repair工单上传的图片
	 */
	@PostMapping("/repairimg")
	public String insertRepairImg(@RequestParam("uploadImg") MultipartFile file, HttpServletRequest request) {
		try {
			if(hospitalService.insertRepairImg(file, request)>0) {
				return JsUtil.getJSONString(0, "提交成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 根据医生的微信昵称获取其提交的所有工单
	 */
	@PostMapping("/repair/{openid}/list")
	@ResponseBody
	public List<Repair> selectAllByOpenid(@PathVariable("openid")String openid){
		
		return hospitalService.selectAllByOpenid(openid);
		
	}
	
	/**
	 * 医生提交repair工单服务评价
	 * 
	 * 
	 * !!!!!!!!!!!!!!!!!!!!!!!微信小程序的请求和POSTMAN的请求处理方式不一样！！！！！！！！！！！！
	 * 这里是获得POST请求的单个数据，按理说直接用@RequestParam来接手就可以了，采用POSTMAN就可以的
	 * 如下：
	 * @PostMapping("/repair/{id}/rate")
	 * public String updateRepairRate(@PathVariable("id") long id ,@RequestParam("serviceRating") Byte serviceRating) 
	 * 
	 * 
	 * 但用微信小程序的wx.request()就不行，必须改成下面这种@RequestBody的方式来接收参数
	 * 
	 */
	@PostMapping("/repair/{id}/rate")
	public String updateRepairRate(@PathVariable("id") long id ,@RequestBody Map<String, Object> params) {
		
		Byte serviceRating= Byte.parseByte(params.get("serviceRating").toString());
		
		try {
			if(hospitalService.updateRepairRate(id, serviceRating)>0) {
				return JsUtil.getJSONString(0, "更新服务评价成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 医生提交repair工单的投诉
	 */
	@PostMapping("/repair/{id}/complain")
	public String updateRepairComplaint(@PathVariable("id") long id ,@RequestBody Map<String, Object> params) {
		String hospitalComplaint = params.get("hospitalComplaint").toString();
		
		try {
			if(hospitalService.updateRepairComplaint(id, hospitalComplaint)>0) {
				return JsUtil.getJSONString(0, "更新医院投诉成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	
	

}
