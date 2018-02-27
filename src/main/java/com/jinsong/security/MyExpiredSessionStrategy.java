package com.jinsong.security;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

/**
 * 自定义session并发登陆的处理策略
 * 
 * 被踢掉的用户再操作会有下面的中文提示，默认是英文提示
 * 
 * @author 188949420@qq.com
 *
 */
public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent eventØ) throws IOException, ServletException {
		// TODO Auto-generated method stub
		//	被踢掉的用户再操作会有下面的中文提示
		eventØ.getResponse().setContentType("application/json;charset=UTF-8");
		eventØ.getResponse().getWriter().write("Session并发登陆！！！后登陆踢掉之前登录");
	}

}
