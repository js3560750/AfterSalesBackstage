package com.jinsong.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jinsong.model.Install;
import com.jinsong.model.Maintain;
import com.jinsong.model.Repair;
import com.jinsong.service.CompanyService;
import com.jinsong.util.JsUtil;

@RestController
@RequestMapping("/weixin")
public class EngineerWeiXinController {
	
	private static final Logger logger = LoggerFactory.getLogger(EngineerWeiXinController.class);

	@Autowired
	CompanyService companyService;
	
	@PostMapping("/engineer/login")
	public String engineerLogin(@RequestBody Map<String, Object> params) {
		String account =params.get("account").toString();
		String password = params.get("password").toString();
		long flag =companyService.engineerLogin(account, password);
		
		try {
			if(flag==1) {
				return JsUtil.getJSONString(0,"提交成功");
			}else if(flag==-1) {
				return JsUtil.getJSONString(-1,"密码不对");
			}else if(flag==-3) {
				return JsUtil.getJSONString(-3,"用户名错误");
			}
			
		}catch (Exception e) {
				logger.error("提交登录表失败" + e.getMessage());
			}
		return JsUtil.getJSONString(1, "登录失败");
	}
	
	@GetMapping("/{account}/unfinished")
	@ResponseBody
	public HashMap<String, Object> engineerUnfinished(@PathVariable("account") String account) {
		
		List<Repair> repairList = companyService.selectUnfinishedRepairByEngineer(account);
		List<Install> installList = companyService.selectUnfinishedInstallByEngineer(account);
		List<Maintain> maintainList = companyService.selectUnfinishedMaintainByEngineer(account);
		
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
	
	@GetMapping("/{account}/finished")
	@ResponseBody
	public HashMap<String, Object> engineerFinished(@PathVariable("account") String account) {
		
		List<Repair> repairList = companyService.selectFinishedRepairByEngineer(account);
		List<Install> installList = companyService.selectFinishedInstallByEngineer(account);
		List<Maintain> maintainList = companyService.selectFinishedMaintainByEngineer(account);
		
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
	
	@GetMapping("/repair/{id}/signIn")
	public String repairSignIn(@PathVariable("id") long id) {
		try {
			if (companyService.repairSignIn(id)>0) {
				return JsUtil.getJSONString(0, "工程师签到成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	@GetMapping("/install/{id}/signIn")
	public String installSignIn(@PathVariable("id") long id) {
		try {
			if (companyService.installSignIn(id)>0) {
				return JsUtil.getJSONString(0, "工程师签到成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	@GetMapping("/maintain/{id}/signIn")
	public String maintainSignIn(@PathVariable("id") long id) {
		try {
			if (companyService.maintainSignIn(id)>0) {
				return JsUtil.getJSONString(0, "工程师签到成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	@GetMapping("/repair/{id}/finish")
	public String repairFinish(@PathVariable("id") long id) {
		try {
			if (companyService.repairFinish(id)>0) {
				return JsUtil.getJSONString(0, "工程师签到成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	@GetMapping("/install/{id}/finish")
	public String installFinish(@PathVariable("id") long id) {
		try {
			if (companyService.installFinish(id)>0) {
				return JsUtil.getJSONString(0, "工程师签到成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	@GetMapping("/maintain/{id}/finish")
	public String maintainFinish(@PathVariable("id") long id) {
		try {
			if (companyService.maintainFinish(id)>0) {
				return JsUtil.getJSONString(0, "工程师签到成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "提交失败");
	}
	
	@PostMapping("/repair/update")
	public String repairUpdate(@RequestBody Map<String, Object> params) {

		try {
			if (companyService.updateRepair(params)>0) {
				return JsUtil.getJSONString(0, "添加维修工单信息成功");
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return JsUtil.getJSONString(1, "提交失败");
	}

}
