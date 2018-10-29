package com.mine.jwt.controller.web;

import com.mine.jwt.jpa.entity.TUserInfo;
import com.mine.jwt.jwt.service.JwtAuthencationService;
import com.mine.jwt.mvc.entity.Response;
import com.mine.jwt.service.UserService;
import com.mine.jwt.util.MapUtil;
import com.mine.jwt.util.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/9/12
 */
@Controller
@RequestMapping(value = "/web/login")
public class LoginContorller {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginContorller.class);

	@Autowired
	private UserService userService;

	/**
	 * 跳转--登录页面
	 * @return
	 */
	@RequestMapping(value = "/login.html")
	public String loginHtml(Model model, String errorMessage){
		LOGGER.info("跳转--登录页面");

		model.addAttribute("errorMessage", errorMessage);
		return "/login/login";
	}

	/**
	 * 登录提交接口
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login")
	public ResponseEntity<Object> login(String userName, String password){
		LOGGER.info("登录接口");
		//获取用户信息
		TUserInfo userInfo = userService.getUserInfoByUserName(userName);
		//用户不存在
		if(userInfo == null){
			return Response.authorityFailed("用户名密码错误");
		}
		//用户名密码错误
		if(!password.equals(userInfo.getPassword())){
			return Response.authorityFailed("用户名密码错误");
		}
		Map<String, Object> map = MapUtil.objToMap(userInfo);
		String jwt = JwtAuthencationService.createJsonWebToken(map);

		Map<String, Object> dataMap = new HashMap<>();
		dataMap.put("jwt", jwt);
		return Response.success(dataMap);
	}
}
