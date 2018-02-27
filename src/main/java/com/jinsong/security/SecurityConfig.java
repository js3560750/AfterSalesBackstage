package com.jinsong.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Jinsong
 * @email 188949420@qq.com
 * @date 2018年2月23日
 *
 */
@Configuration // 要继承WebSecurityConfigurerAdapter
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// 数据库
	// 数据库的配置就是application.properties里写的本地security数据库
	@Autowired
	private DataSource dataSource;

	// 配置
	@Override
	public void configure(HttpSecurity http) throws Exception {

		// 表单登录
		http.formLogin().loginPage("/web/authentication/require") // 任何未登录的访问请求都会被重定向到这个URL，一定要在后面antMatchers()中允许这个URL，否则会报错重定向次数太多
				.loginProcessingUrl("/web/authentication/form") // 配置SpringSecurity默认的UsernamePasswordAuthenticationFilter过滤器验证对象
				.defaultSuccessUrl("/web/index")	//登录成功跳转
				.failureUrl("/web/login?error=true")	//登录失败跳转
				.and()
				.sessionManagement()
					.invalidSessionUrl("/web/login")	//Session失效后跳转的地址
					.maximumSessions(1)	//最大Session数量设置为1，这样相同用户只能存在一个，后面的登陆会踢掉之前的登录
					.expiredSessionStrategy(new MyExpiredSessionStrategy()) //添加自定义的Session并发登陆处理策略覆盖默认策略
				.and()
				.and()
				.authorizeRequests()	//下面是设置权限
					.antMatchers("/web/authentication/require", 
							"/web/login", 
							"/assets/**",
							"/hello").permitAll() // web/login不会被拦截，同时静态资源static目录下的assets文件夹也不会被拦截
					.antMatchers("/web/product/*").hasRole("USER")
					.anyRequest().authenticated() // 其他请求都要身份认证
				.and()
				.logout().invalidateHttpSession(true).logoutSuccessUrl("/web/login")	//登出操作，设置登出后清除SESSION，和登出后跳转的页面，前端只需设置登出按钮的URL为/logout就行，SpringSecurity默认实现，不需要写logout的控制器
				.and()
				.headers().frameOptions().sameOrigin()//springSecurty使用X-Frame-Options防止网页被Frame，这里要disable掉，否则内嵌网页无法显示
				.and().csrf().disable();

	}

	// 配置
	// 账户密码加密，这里的PasswordEncoder用org.springframework.security.crypto.这个包下的
	// 开启加密
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
