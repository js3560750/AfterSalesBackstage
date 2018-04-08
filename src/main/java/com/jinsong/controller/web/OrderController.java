package com.jinsong.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinsong.controller.HospitalController;
import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Repair;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;

/**
 * @author 	Jinsong
 * @email 	188949420@qq.com
 * @date	2018年2月25日
 *
 */
@RestController
@RequestMapping("/web")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	CompanyService companyService;
	
	/**
	 * 网站查看所有repair工单
	 */
	@GetMapping("/repair/list")
	public ModelAndView allRepair(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		ModelAndView model = new ModelAndView("repair_all");
		
		PageHelper.startPage(pn,8);
		List<Repair> list = companyService.selectAll();
		
		PageInfo<Repair> pageInfo = new PageInfo<Repair>(list);
		
		model.addObject("pageInfo",pageInfo);
		
		return model;
	}
	
	/**
	 * 网站查看单个repair工单详情
	 */
	@GetMapping("/repair/{id}")
	public ModelAndView selectRepairById(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("repair_detail");
		Repair repair = companyService.selectById(id);
		model.addObject("model", repair);
		return model;
	}
	
	/**
	 * 网站根据id删除repair工单
	 */
	@DeleteMapping("/repair/{id}")
	public String deleteRepairById(@PathVariable("id") long id) {
		try {
			if(companyService.deleteRepairById(id)>0) {
				return JsUtil.getJSONString(0,"提交成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return JsUtil.getJSONString(1, "提交失败"+e);
		}
		
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 网站查看所有install工单
	 */
	@GetMapping("/install/list")
	public ModelAndView allInstall(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		ModelAndView model = new ModelAndView("install_all");
		
		PageHelper.startPage(pn,3);
		List<Install> list = companyService.selectAllInstall();
		
		PageInfo<Install> pageInfo = new PageInfo<Install>(list);
		
		model.addObject("pageInfo",pageInfo);
		
		return model;
	}
	
	/**
	 * 网站查看单个install工单详情
	 */
	@GetMapping("/install/{id}")
	public ModelAndView selectInstallById(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("install_detail");
		Install install = companyService.selectInstallById(id);
		model.addObject("model", install);
		return model;
	}
	
	/**
	 * 网站根据id删除install工单
	 */
	@DeleteMapping("/install/{id}")
	public String deleteInstallById(@PathVariable("id") long id) {
		try {
			if(companyService.deleteInstallById(id)>0) {
				return JsUtil.getJSONString(0,"提交成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return JsUtil.getJSONString(1, "提交失败"+e);
		}
		
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 网站查看所有maintain工单
	 */
	@GetMapping("/maintain/list")
	public ModelAndView allMaintain(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
		ModelAndView model = new ModelAndView("maintain_all");
		
		PageHelper.startPage(pn,3);
		List<Maintain> list = companyService.selectAllMaintain();
		
		PageInfo<Maintain> pageInfo = new PageInfo<Maintain>(list);
		
		model.addObject("pageInfo",pageInfo);
		
		return model;
	}
	
	/**
	 * 网站查看单个maintain工单详情
	 */
	@GetMapping("/maintain/{id}")
	public ModelAndView selectMaintainById(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("maintain_detail");
		Maintain maintain = companyService.selectMaintainById(id);
		model.addObject("model", maintain);
		return model;
	}
	
	/**
	 * 网站根据id删除maintain工单
	 */
	@DeleteMapping("/maintain/{id}")
	public String deleteMaintainById(@PathVariable("id") long id) {
		try {
			if(companyService.deleteMaintainById(id)>0) {
				return JsUtil.getJSONString(0,"提交成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return JsUtil.getJSONString(1, "提交失败"+e);
		}
		
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 跳转到工单搜索页面
	 */
	@GetMapping("/order/search")
	public ModelAndView orderSearchView() {
		ModelAndView model = new ModelAndView("search_order");
		return model;
	}
	
	/**
	 * 搜索动作
	 */
	@PostMapping("/order/search")
	public ModelAndView orderSearchAction(@RequestParam("searchInfo") String searchInfo) {
		ModelAndView model = new ModelAndView("search_order_result");
		List<Repair> repairList = companyService.selectRepairBySearch(searchInfo);
		List<Install> installList = companyService.selectInstallBySearch(searchInfo);
		List<Maintain> maintainList = companyService.selectMaintainBySearch(searchInfo);
		
		if(repairList.size()!=0) {
			model.addObject("repairList", repairList);
		}
		if(installList.size()!=0) {
			model.addObject("installList", installList);
		}
		if(maintainList.size()!=0) {
			model.addObject("maintainList", maintainList);
		}
		//System.out.println(JSONObject.toJSON(repairList));
		return model;
	}

}
