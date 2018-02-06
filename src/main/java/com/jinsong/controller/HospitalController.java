package com.jinsong.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
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
	 */
	@PostMapping("/repair")
	public String insertRepair(@ModelAttribute Repair repair) {

		try {

			if (hospitalService.insertRepair(repair) > 0) {
				return JsUtil.getJSONString(0, "提交成功");
			}
		} catch (Exception e) {
			logger.error("提交repair工单失败" + e.getMessage());
		}
		return JsUtil.getJSONString(1, "提交失败");
	}

	
}
