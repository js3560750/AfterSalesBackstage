package com.jinsong.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jinsong.model.Engineer;
import com.jinsong.model.Order;
import com.jinsong.service.TestService;
import com.jinsong.service.TestService2;

@RestController
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	TestService testService;

	@Autowired
	TestService2 testService2;

	/**
	 * 测试Springboot正常工作
	 * 
	 * @return
	 */
	@GetMapping("/test")
	public String test() {
		return "Test ! Moemil AfterSales";
	}

	/**
	 * 验证查询功能
	 * 
	 * @return
	 */
	@GetMapping("/test3")
	@ResponseBody
	public List<Engineer> test3() {
		List<Engineer> engineers = testService.listEngineer();

		return engineers;
	}

	/**
	 * 验证阿里fastjson功能
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/test4")
	public String test4(Model model) {
		List<Engineer> engineers = testService.listEngineer();

		// 阿里巴巴的fastjson工具，直接把对象、list\map等转换成JSON格式，非常方便！！！！
		// 比如json.toJSONString(engineers)
		JSONObject json = new JSONObject();

		model.addAttribute("result", engineers);
		return json.toJSONString(model);
	}

	/**
	 * 验证@ResponseBody直接返回JSON串
	 * 
	 * @return
	 */
	@GetMapping("/test5")
	@ResponseBody
	public List<Order> test5() {
		List<Order> list = new ArrayList<>();
		Order order1 = new Order();
		Order order2 = new Order();
		order1.setEngineerName("JS");
		order1.setOrderNumber("1234567891");
		order1.setAcceptTime("2017-11-27");
		order1.setEstimatedArrivalTime("2017-12-27");
		order1.setEngineerTel("18571686931");
		order2.setEngineerName("FYY");
		order2.setOrderNumber("4518613565");
		order2.setAcceptTime("2017-11-27");
		order2.setEstimatedArrivalTime("2017-12-27");
		order2.setEngineerTel("18571686931");
		list.add(order1);
		list.add(order2);

		return list;
	}

	/**
	 * 验证采用mybatis-generator产生的文件可以使用
	 */
	@GetMapping("/test6")
	@ResponseBody
	public List<Engineer> test6() {
		return testService.listEngineer2();
	}

	/**
	 * 验证post取值方法
	 * 
	 * 使用@ModelAttribute() 外加对象，可以自动装载form表单中相应的key值到对象的属性中
	 * 对象中没有的属性，可以用@RequestParam获得
	 * 
	 */
	@PostMapping("/test7")
	public void test7(@ModelAttribute() Engineer engineer) {
		System.out.println(JSONObject.toJSONString(engineer));
	}

	/**
	 * 插入测试
	 */
	@PostMapping("/test8")
	public void test8(@ModelAttribute() Engineer engineer) {
		Date date = new Date();
		engineer.setGmtCreate(date);
		engineer.setGmtModified(date);
		testService.insertEngineer(engineer);
	}

	/**
	 * 图片上传测试，从微信接收，单个、多个都可以
	 */
	@RequestMapping(value = "/test9", produces = "application/json; charset=utf-8")
	public void test9(@RequestParam("uploadImg") MultipartFile file, HttpServletRequest request) {
		if (file.isEmpty()) {
			logger.error("图片为空");
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		// 文件上传后的路径
		String filePath = "C:/Users/18894/Desktop/";
		File dest = new File(filePath + fileName);
		//若目录不存在创建目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}

		//上传动作
		try {
			file.transferTo(dest);
			logger.info("上传成功");
			;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
