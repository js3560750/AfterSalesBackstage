package com.jinsong.controller.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Jinsong
 * @email 188949420@qq.com
 * @date 2018年2月23日
 *
 */
@RestController()
@RequestMapping("/web")
public class WebLoginController {

	// Spring默认把HTTP请求缓存到了HttpSessionRequestCache这个类里，我们通过RequestCache拿到HTTP请求的缓存
	private RequestCache requestCache = new HttpSessionRequestCache();

	// 页面跳转的工具
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	// @RestController注解的话，必须用ModelAndView才能返回页面
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("login"); // 返回login.html页面

		return model;
	}

	/*
	 * 未登录访问
	 * 如果是以.html结尾的请求就跳转到登录页去，否则只返回"访问的服务需要身份认证，请返回登录进行登录"这句话
	 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED) // 函数正常return就返回401状态码。HttpStatus.UNAUTHORIZED=401
	public String requireAutheintication(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// 拿到HTTP请求
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		
		if (savedRequest != null) {
			// 拿到引发跳转的URL
			String targetURL = savedRequest.getRedirectUrl();
			// 如果URL以.html结尾(忽略大小写)
			if (StringUtils.endsWithIgnoreCase(targetURL, ".html")) {
				// 页面跳转
				redirectStrategy.sendRedirect(request, response, "/web/login");
			}
		}

		return "访问的服务需要身份认证，请返回登录页";
	}
	
	/**
	 * 登录成功访问主页
	 */
	@GetMapping("/index")
	public ModelAndView loginSuccess() {
		ModelAndView model = new ModelAndView("index"); // 返回index.html页面
		
		return model;
	}
	
	/**
	 * 主页右侧框架欢迎页面
	 */
	@GetMapping("/welcome")
	public ModelAndView welcome() {
		ModelAndView model = new ModelAndView("welcome"); // 返回welcome.html页面
		String authority =SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
		System.out.println(authority);
		model.addObject("authority", authority);
		return model;
	}

}
