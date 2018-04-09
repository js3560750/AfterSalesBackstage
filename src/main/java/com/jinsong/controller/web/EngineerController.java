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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jinsong.model.Engineer;
import com.jinsong.service.AccountService;
import com.jinsong.util.JsUtil;

/**
 * 工程师管理
 * 
 * @author 	Jinsong
 * @email 	188949420@qq.com
 * @date	2018年4月9日
 *
 */
@RestController
@RequestMapping("/web")
public class EngineerController {
	private static final Logger logger = LoggerFactory.getLogger(EngineerController.class);

	@Autowired
	AccountService accountService;

	/**
	 * 跳转到engineer注册页面
	 */
	@GetMapping("/engineer")
	public ModelAndView insertEngineerView() {

		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! 注意 !!!!!!!!!!!!!!!!!!!!!!!!
		// ModelAndView配置子路径，如下这样写就行了，开头不用加斜杠////////////////!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		ModelAndView model = new ModelAndView("engineer/engineer_insert");

		return model;
	}

	/**
	 * insert新的engineer
	 * 
	 */
	@PostMapping("/engineer")
	public ModelAndView insertEngineerAction(@ModelAttribute Engineer engineer) {
			//表单提交失败，报错Validation failed for object，因此添加BindingResult bindingResult
			//听说是因为使用了java.util.Date，应该使用java.sql.Date

		
		try {
			// 服务返回>0成功，否则失败
			// 但是返回给前端的code=0表示成功
			if (accountService.insertEngineer(engineer)> 0) {
				return new ModelAndView("engineer/engineer_insert_success");
			}
		} catch (Exception e) {
			logger.error("提交设备信息失败" + e.getMessage());
		}
		return new ModelAndView("engineer/engineer_insert_fail");
	}

	/**
	 * 获得所有engineer
	 */
	@GetMapping("/engineer/list")
	public ModelAndView selectAllEngineer(@RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn) {

		ModelAndView model = new ModelAndView("engineer/engineer_all");

		PageHelper.startPage(pn, 8);
		List<Engineer> list = accountService.selectAllEngineer();

		PageInfo<Engineer> pageInfo = new PageInfo<Engineer>(list);

		model.addObject("pageInfo", pageInfo);
		return model;

	}

	

	/**
	 * 根据id删除engineer
	 */
	@DeleteMapping("/engineer/{id}")
	public String deleteProductById(@PathVariable("id") long id) {
		try {
			if (accountService.deleteEngineerById(id)> 0) {
				return JsUtil.getJSONString(0, "engineer删除成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return JsUtil.getJSONString(1, "删除失败");
	}

	

	/**
	 * 跳转到engineer搜索页面
	 */
	@GetMapping("/engineer/search")
	public ModelAndView engineerSearchView() {
		ModelAndView model = new ModelAndView("engineer/engineer_search");
		return model;
	}

	/**
	 * 搜索动作
	 */
	@PostMapping("/engineer/search")
	public ModelAndView productSearchAction(@RequestParam("searchInfo") String searchInfo) {
		ModelAndView model = new ModelAndView("engineer/engineer_search_result");
		List<Engineer> engineerList =accountService.selectEngineerBySearch(searchInfo);
		if (engineerList.size() != 0) {
			model.addObject("engineerList", engineerList);
		}

		return model;
	}
}
