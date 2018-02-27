package com.jinsong.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinsong.model.Repair;
import com.jinsong.service.CompanyService;

/**
 * @author 	Jinsong
 * @email 	188949420@qq.com
 * @date	2018年2月25日
 *
 */
@RestController
@RequestMapping("/web")
public class OrderController {
	
	@Autowired
	CompanyService companyService;
	
	/**
	 * 网站查看所有repair工单
	 */
	@GetMapping("/repair/list")
	public ModelAndView allRepair(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		ModelAndView model = new ModelAndView("repair_all");
		
		PageHelper.startPage(pn,3);
		List<Repair> list = companyService.selectAll();
		
		PageInfo<Repair> pageInfo = new PageInfo<Repair>(list);
		
		model.addObject("pageInfo",pageInfo);
		
		return model;
	}
	
	/**
	 * 网站查看单个repair工单详情
	 */
	@GetMapping("/repair/{id}/detail")
	public ModelAndView selectRepairById(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("repair_detail");
		Repair repair = companyService.selectById(id);
		model.addObject("model", repair);
		return model;
	}

}
