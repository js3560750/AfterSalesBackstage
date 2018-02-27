package com.jinsong.controller.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 	Jinsong
 * @email 	188949420@qq.com
 * @date	2018年2月25日
 *
 */
@RestController
@RequestMapping("/web")
public class ProductController {

	/**
	 * 跳转到登记设备页面
	 */
	@GetMapping("/product/insert")
	public ModelAndView showInsertProduct() {
		ModelAndView model = new ModelAndView("product_insert");
		return model;
	}
}
