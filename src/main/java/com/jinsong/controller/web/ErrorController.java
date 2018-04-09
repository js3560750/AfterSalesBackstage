package com.jinsong.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinsong.model.Engineer;
import com.jinsong.model.Error;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;


/**
 * 故障信息管理
 * 
 * @author 	Jinsong
 * @email 	188949420@qq.com
 * @date	2018年4月9日
 *
 */
@RestController
@RequestMapping("/web")
public class ErrorController {
	
	@Autowired
	CompanyService companyService;
	
	/**
	 * 获得所有error
	 */
	@GetMapping("/error/list")
	public ModelAndView selectAllError(@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn) {

		ModelAndView model = new ModelAndView("errorInfo/error_all");

		PageHelper.startPage(pn, 8);
		List<Error> list = companyService.selectAllError();
		PageInfo<Error> pageInfo = new PageInfo<Error>(list);

		model.addObject("pageInfo", pageInfo);
		return model;

	}
	
	/**
	 * 根据id删除error
	 */
	@DeleteMapping("/error/{id}")
	public String deleteErrorById(@PathVariable("id") long id) {
		try {
			if(companyService.deleteErrorById(id)>0) {
				return JsUtil.getJSONString(0, "设备故障信息删除成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "删除失败");
	}

}
