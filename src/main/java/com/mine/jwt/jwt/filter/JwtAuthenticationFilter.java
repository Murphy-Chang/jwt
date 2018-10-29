package com.mine.jwt.jwt.filter;

import com.mine.jwt.jwt.service.JwtAuthencationService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/9/11
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	/**
	 * 重写doFilterInternal方法
	 * @param request
	 * @param response
	 * @param filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		Object jwt = request.getParameter("jwt");
//		request.getHeader("jwt");
		try {
			Jws<Claims> claims = JwtAuthencationService.parseJsonWebToken(jwt==null?"null":jwt.toString());
			request.setAttribute("claims", claims);
		} catch (JwtException e) {
			String errorMessage = new String("登录信息无效".getBytes("UTF-8"),"iso-8859-1");
			response.sendRedirect("/web/login/login.html?errorMessage="+errorMessage);
		}

		filterChain.doFilter(request, response);
	}

}
