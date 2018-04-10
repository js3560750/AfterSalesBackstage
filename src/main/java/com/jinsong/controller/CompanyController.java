package com.jinsong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jinsong.model.Engineer;
import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Repair;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;

@RestController
@RequestMapping("/weixin")
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	CompanyService companyService;
	
	
	
	/**
	 * 微信公司端账号登录
	 * 只有authority=ROLE_ADMIN权限的账号返回成功
	 */
	@PostMapping("/company/login")
	public String companyLogin(@RequestBody Map<String, Object> params) {
		String account =params.get("account").toString();
		String password = params.get("password").toString();
		long flag = companyService.companyLogin(account, password);
		
		try {
			if(flag==1) {
				return JsUtil.getJSONString(0,"提交成功");
			}else if(flag==-2) {
				return JsUtil.getJSONString(-2,"权限不是ROLE_ADMIN");
			}else if(flag==-1) {
				return JsUtil.getJSONString(-1,"密码不对");
			}
			
		}catch (Exception e) {
				logger.error("提交repair工单失败" + e.getMessage());
			}
		return JsUtil.getJSONString(1, "提交失败");
		
	}

	/**
	 * 管理员提交新的repair工单
	 * 
	 * @ModelAttribute 的请求头必须用"Content-Type": "application/x-www-form-urlencoded"
	 * 
	 */
	@PostMapping("/companyrepair")
	public String insertRepair(@ModelAttribute Repair repair) {

		try {
			// 服务返回>0成功，否则失败
			// 但是返回给前端的code=0表示成功
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
			// 服务返回>0成功，否则失败
			// 但是返回给前端的code=0表示成功
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
			// 服务返回>0成功，否则失败
			// 但是返回给前端的code=0表示成功
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
	public List<Engineer> listEngineer() {
		return companyService.listEngineer();
	}

	/**
	 * 获得status=null也就是“未处理”的repair工单
	 */
	@GetMapping("/repair/list/statusnull")
	public List<Repair> selectByStautsNull() {
		return companyService.selectByStautsNull();
	};

	/**
	 * 获得所有repair工单
	 */
	@GetMapping("/repair/list")
	public List<Repair> selectAllRepair() {
		return companyService.selectAll();
	}

	/**
	 * 根据id获得单个repair工单
	 */
	@GetMapping("repair/{id}")
	public Repair selectRepairById(@PathVariable("id") long id) {
		return companyService.selectById(id);
	}

	/*
	 * 更新repair工单的工程师信息
	 */
	@PostMapping("repair/{id}/engineer")
	public String updateRepairEngineer(@PathVariable("id") long id, @RequestBody Map<String, Object> params) {
		String engineer = params.get("engineer").toString();
		try {
			if (companyService.updateRepairEngineer(id, engineer) > 0) {
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
	public List<Install> selectAllInstall() {
		return companyService.selectAllInstall();
	}
	
	/**
	 * 根据id获得单个install工单
	 */
	@GetMapping("install/{id}")
	public Install selectInstallById(@PathVariable("id") long id) {
		return companyService.selectInstallById(id);
	}
	
	/*
	 * 更新install工单的工程师信息
	 */
	@PostMapping("install/{id}/engineer")
	public String updateInstallEngineer(@PathVariable("id") long id, @RequestBody Map<String, Object> params) {
		String engineer = params.get("engineer").toString();
		try {
			if (companyService.updateInstallEngineer(id, engineer) > 0) {
				return JsUtil.getJSONString(0, "订单委派工程师成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}

	/**
	 * 获得所有maintain工单
	 */
	@GetMapping("maintain/list")
	public List<Maintain> selectAllMaintain() {
		return companyService.selectAllMaintain();
	}
	
	/**
	 * 根据id获得单个maintain工单
	 */
	@GetMapping("maintain/{id}")
	public Maintain selectMaintainById(@PathVariable("id") long id) {
		return companyService.selectMaintainById(id);
	}
	
	/*
	 * 更新maintain工单的工程师信息
	 */
	@PostMapping("maintain/{id}/engineer")
	public String updateMaintainEngineer(@PathVariable("id") long id, @RequestBody Map<String, Object> params) {
		String engineer = params.get("engineer").toString();
		try {
			if (companyService.updateMaintainEngineer(id, engineer) > 0) {
				return JsUtil.getJSONString(0, "订单委派工程师成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	/**
	 * 搜索动作
	 */
	@PostMapping("/order/search")
	@ResponseBody
	public HashMap<String, Object> orderSearchAction(@RequestBody Map<String, Object> params) {
		String searchInfo = params.get("searchInfo").toString();
		
		List<Repair> repairList = companyService.selectRepairBySearch(searchInfo);
		List<Install> installList = companyService.selectInstallBySearch(searchInfo);
		List<Maintain> maintainList = companyService.selectMaintainBySearch(searchInfo);
		
		HashMap<String, Object> map = new HashMap<>();
		
		if(repairList.size()!=0) {
			map.put("repairList", repairList);
		}
		if(installList.size()!=0) {
			map.put("installList", installList);
		}
		if(maintainList.size()!=0) {
			map.put("maintainList", maintainList);
		}
		
		return map;
	}
}
