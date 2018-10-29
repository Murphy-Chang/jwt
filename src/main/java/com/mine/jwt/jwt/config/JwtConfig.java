package com.mine.jwt.jwt.config;

import com.mine.jwt.jwt.filter.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/9/12
 */
@Configuration
public class JwtConfig {
	/**
	 * 注入jwt验证过滤器
	 * @return
	 */
	@Bean
	protected JwtAuthenticationFilter jwtAuthenticationFilter(){
		return new JwtAuthenticationFilter();
	}

	/**
	 * jwt验证过滤器配置
	 * @return
	 */
	@Bean
	public FilterRegistrationBean userUrlFilterRegister() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		//注入过滤器
		registration.setFilter(jwtAuthenticationFilter());
		//拦截规则
		registration.addUrlPatterns("/web/user/*");
		//过滤器名称
		registration.setName("jwtAuthenticationFilter");
		//过滤器顺序
		registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
		return registration;
	}
}
