package com.jinsong.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinsong.model.Engineer;
import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Repair;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;

@RestController
public class CompanyController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	CompanyService companyService;
	
	
	/**
	 * 管理员提交新的repair工单
	 * 
	 * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
	 * 
	 */
	@PostMapping("/companyrepair")
	public String insertRepair(@ModelAttribute Repair repair) {

		try {
			//服务返回>0成功，否则失败
			//但是返回给前端的code=0表示成功
			if (companyService.insertRepair(repair) > 0) {
				return JsUtil.getJSONString(0, "提交成功");
			}
		} catch (Exception e) {
			logger.error("提交repair工单失败" + e.getMessage());
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 管理员提交新的maintain工单
	 * 
	 * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
	 * 
	 */
	@PostMapping("/companymaintain")
	public String insertMaintain(@ModelAttribute Maintain maintain) {

		try {
			//服务返回>0成功，否则失败
			//但是返回给前端的code=0表示成功
			if (companyService.insertMaintain(maintain) > 0) {
				return JsUtil.getJSONString(0, "提交成功");
			}
		} catch (Exception e) {
			logger.error("提交repair工单失败" + e.getMessage());
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 管理员提交新的install工单
	 * 
	 * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
	 * 
	 */
	@PostMapping("/companyinstall")
	public String insertMaintain(@ModelAttribute Install install) {

		try {
			//服务返回>0成功，否则失败
			//但是返回给前端的code=0表示成功
			if (companyService.insertInstall(install) > 0) {
				return JsUtil.getJSONString(0, "提交成功");
			}
		} catch (Exception e) {
			logger.error("提交repair工单失败" + e.getMessage());
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 获得所有工程师
	 */
	@GetMapping("/engineer/list")
	public List<Engineer> listEngineer(){
		return companyService.listEngineer();
	}
	
	 /**
     * 获得status=null也就是“未处理”的repair工单
     */
	@GetMapping("/repair/list/statusnull")
    public List<Repair> selectByStautsNull(){
    	return companyService.selectByStautsNull();
    };
    
    /**
     * 获得所有repair工单
     */
    @GetMapping("/repair/list")
    public List<Repair> selectAll(){
    	return companyService.selectAll();
    }
    
    
    /**
     * 根据id获得单个repair工单
     */
    @GetMapping("repair/{id}")
    public Repair selectById(@PathVariable("id") long id) {
    	return companyService.selectById(id);
    }
    
    /*
     * 更新repair工单的工程师信息
     */
    @PostMapping("repair/{id}/engineer")
    public String updateRepairEngineer(@PathVariable("id") long id ,@RequestBody Map<String, Object> params) {
    	String engineer = params.get("engineer").toString();
    	try {
			if(companyService.updateRepairEngineer(id, engineer)>0) {
				return JsUtil.getJSONString(0, "订单委派工程师成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
    }
    
    /**
	 * 获得所有install工单
	 */
	@GetMapping("install/list")
	public List<Install> selectAllInstall(){
		return companyService.selectAllInstall();
	}
	
	 /**
		 * 获得所有maintain工单
		 */
		@GetMapping("maintain/list")
		public List<Maintain> selectAllMaintain(){
			return companyService.selectAllMaintain();
		}
}
