package com.jinsong.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jinsong.mapper.AdminMapper;
import com.jinsong.model.Admin;

/**
 * 表单登录会调用的类
 * 
 * @author Jinsong
 * @email 188949420@qq.com
 * @date 2018年2月23日
 *
 */
@Component // 组件别掉了，不然Springboot识别不了
public class MyUserDetailService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	AdminMapper adminMapper;

	// 需要在SecurityConfig中配置
	// PasswordEncoder这个接口有两个方法，一个是encode()，负责加密，一个是matches()，负责验证
	// 实际项目中，应该在用户注册的时候调用这个passwordEncoder.encode()方法，然后把加密后的密码存入数据库
	// 这里的PasswordEncoder用org.springframework.security.crypto.这个包下的
	//@Autowired
	//PasswordEncoder passwordEncoder;

	/*
	 * 实现了SpringSecurity自带的UserDetailService接口
	 * 
	 * 这个默认方法就是表单登录时会调用的方法，返回的UserDetails也是SpringSecurity自带的对象，我们往里面添加权限信息
	 * 
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info(username);

		Admin admin = adminMapper.selectByAccount(username);
		if (admin == null) {
			logger.info("用户名不正确");
			throw new BadCredentialsException("用户名不正确");

		}
		// 如果没抛异常则用户名存在，根据用户名得到数据库中对应的密码
		String password = admin.getPassword();
		logger.info(password);
		// 得到用户权限
		String authority = admin.getAuthority();
		logger.info("权限信息："+authority);

		// SpringSecurity会拿这个数据库的password和用户输入的密码自动进行比对，比对不一致的话就无法登陆
		return new User(username, 
				password, 
				true, // 这几个参数我们写死了，其实这几个参数我们可以写验证逻辑，如果true就验证通过，如果传入的false验证就不能通过，需要我们自己去实现。
				true, // 根据形参名字，第一个true的位置应该是账户是否删除，第二个账户是否过期，第三个是密码是否过期，第四个true是账户是否冻结
				true, 
				true, 
				AuthorityUtils.commaSeparatedStringToAuthorityList(authority));
	}

}
