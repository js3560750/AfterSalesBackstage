package com.jinsong.controller.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
import com.jinsong.model.Admin;
import com.jinsong.service.AccountService;
import com.jinsong.util.JsUtil;

/**
 * 管理员 管理
 * 
 * @author 	Jinsong
 * @email 	188949420@qq.com
 * @date	2018年4月9日
 *
 */
@RestController
@RequestMapping("/web")
public class AdminController {
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AccountService accountService;
	
	/**
	 * 跳转到管理员添加页面
	 */
	@GetMapping("/admin")
	public ModelAndView insertAdminView() {
		
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!		注意		!!!!!!!!!!!!!!!!!!!!!!!!
		//ModelAndView配置子路径，如下这样写就行了，开头不用加斜杠////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		ModelAndView model = new ModelAndView("admin/admin_insert");
		
		
		return model;
	}
	
	/**
	 * insert新的管理员
	 */
	@PostMapping("/admin")
	public ModelAndView insertAdminAction(@ModelAttribute Admin admin) {

		try {
			//服务返回>0成功，否则失败
			//但是返回给前端的code=0表示成功
			if (accountService.insertAdmin(admin)>0) {
				return new ModelAndView("admin/admin_insert_success");
			}
		} catch (Exception e) {
			logger.error("提交设备信息失败" + e.getMessage());
		}
		return new ModelAndView("admin/admin_insert_fail");
	}
	
	/**
	 *  获得所有管理员
	 */
	@GetMapping("/admin/list")
	public ModelAndView selectAllAdmin(@RequestParam(required = false,defaultValue = "1",value = "pn")Integer pn) {
	
		
		ModelAndView model = new ModelAndView("admin/admin_all");
		
		PageHelper.startPage(pn,8);
		List<Admin> list = accountService.selectAllAdmin();

		PageInfo<Admin> pageInfo = new PageInfo<Admin>(list);

		model.addObject("pageInfo",pageInfo);
		return model;
		
	}
	
	/**
	 * 根据id获得单个管理员
	 */
	@GetMapping("/admin/{id}")
	public ModelAndView selectAdminById(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("admin/admin_update");
		Admin admin = accountService.selectAdminById(id);
		model.addObject("model", admin);
		return model;
	}
	
	/**
	 * 根据id删除管理员
	 */
	@DeleteMapping("/admin/{id}")
	public String deleteAdminById(@PathVariable("id") long id) {
		try {
			if(accountService.deleteAdminById(id)>0) {
				return JsUtil.getJSONString(0, "管理员删除成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "删除失败");
	}
	

	/**
	 * 根据id修改管理员
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
	@PutMapping("/admin")
	public ModelAndView updateAdmin(@ModelAttribute Admin admin,BindingResult bindingResult) {
		
		System.out.println("Put方式表单提交生效");
		try {
			if(accountService.updateAdmin(admin)>0) {
				return new ModelAndView("admin/admin_update_success");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ModelAndView("admin/admin_update_fail");
	}
	
	

}
