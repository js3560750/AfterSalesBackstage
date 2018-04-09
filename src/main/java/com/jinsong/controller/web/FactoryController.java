package com.jinsong.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinsong.model.Factory;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;

/**
 * 质检登记设备出厂信息
 * 
 * @author 	Jinsong
 * @email 	188949420@qq.com
 * @date	2018年4月8日
 *
 */
@RestController
@RequestMapping("/web")
public class FactoryController {
	
	private static final Logger logger = LoggerFactory.getLogger(FactoryController.class);
	
	@Autowired
	CompanyService companyService;
	
	/**
	 * 跳转到质检登记设备页面
	 */
	@GetMapping("/factory")
	public ModelAndView insertFactoryView() {
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		注意		!!!!!!!!!!!!!!!!!!!!!!!!
		//ModelAndView配置子路径，如下这样写就行了，开头不用加斜杠////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		ModelAndView model = new ModelAndView("factory/factory_insert");
		
		
		return model;
	}
	
	/**
	 * insert新的设备信息
	 */
	@PostMapping("/factory")
	public ModelAndView insertFactoryAction(@ModelAttribute Factory factory) {

		try {
			//服务返回>0成功，否则失败
			//但是返回给前端的code=0表示成功
			if (companyService.insertFactory(factory)>0) {
				return new ModelAndView("factory/factory_insert_success");
			}
		} catch (Exception e) {
			logger.error("提交设备信息失败" + e.getMessage());
		}
		return new ModelAndView("factory/factory_insert_fail");
	}
	
	/**
	 *  获得所有质检部门登记的设备信息factory
	 */
	@GetMapping("/factory/list")
	public ModelAndView selectAllFactory(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
	
		
		ModelAndView model = new ModelAndView("factory/factory_all");
		
		PageHelper.startPage(pn,8);
		List<Factory> list = companyService.selectAllFactory();
		
		PageInfo<Factory> pageInfo = new PageInfo<Factory>(list);
		
		model.addObject("pageInfo",pageInfo);
		return model;
		
	}
	
	/**
	 * 根据id获得单个factory
	 */
	@GetMapping("/factory/{id}")
	public ModelAndView selectFactoryById(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("factory/factory_detail");
		Factory factory = companyService.selectFactoryById(id);
		model.addObject("model", factory);
		return model;
	}
	
	/**
	 * 根据id删除质检部门登记的设备信息factory
	 */
	@DeleteMapping("/factory/{id}")
	public String deleteFactoryById(@PathVariable("id") long id) {
		try {
			if(companyService.deleteFactoryById(id)>0) {
				return JsUtil.getJSONString(0, "设备信息删除成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "删除失败");
	}
	

	/**
	 * 根据id修改质检部门登记的设备信息factory
	 * 
	 * form表单只能用GET和POST方式
	 * 
	 * 但如果我们在表单内添加一个隐藏的input标签如下
	 * <input type="hidden" name="_method" value="put" />
	 * 并且表单提交仍然写POST
	 * 
	 * Springboot会自动识别这个隐藏input，并调用PUT方式的Controller方法来接受
	 * 
	 * 具体可以看factory_detail.html页面是怎么写的
	 * 
	 */
	@PutMapping("/factory")
	public ModelAndView updateFactory(@ModelAttribute Factory factory) {
		
		System.out.println("Put方式表单提交生效");
		try {
			if(companyService.updateFactory(factory)>0) {
				return new ModelAndView("factory/factory_update_success");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView("factory/factory_update_fail");
	}
	
	/**
	 * 跳转到factory搜索页面
	 */
	@GetMapping("/factory/search")
	public ModelAndView factorySearchView() {
		ModelAndView model = new ModelAndView("factory/factory_search");
		return model;
	}
	
	/**
	 * 搜索动作
	 */
	@PostMapping("/factory/search")
	public ModelAndView factorySearchAction(@RequestParam("searchInfo") String searchInfo) {
		ModelAndView model = new ModelAndView("factory/factory_search_result");
		List<Factory> factoryList = companyService.selectFactoryBySearch(searchInfo);
		
		
		if(factoryList.size()!=0) {
			model.addObject("factoryList", factoryList);
		}

		return model;
	}
	
	
	

}
