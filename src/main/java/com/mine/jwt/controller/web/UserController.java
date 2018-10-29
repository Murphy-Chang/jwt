package com.mine.jwt.controller.web;

import com.mine.jwt.jpa.entity.TUserInfo;
import com.mine.jwt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
@RequestMapping(value = "/web/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 跳转全部用户页面
	 * @param model
	 */
	@RequestMapping(value = "/allUser.html", method = {RequestMethod.GET})
	public String allUserHtml(Model model, @RequestParam(name = "jwt", required = false) String jwt) {
		LOGGER.info("跳转--全部用户页面");

		model.addAttribute("allUserList", userService.getAllUserInfoList());
		return "/user/allUser";
	}

	/**
	 * 跳转用户页面
	 * @param model
	 */
	@RequestMapping(value = "/userDetail.html", method = {RequestMethod.GET})
	public String userDetailHtml(Model model, int userId, @RequestParam(name = "jwt", required = false) String jwt) {
		LOGGER.info("跳转--用户页面");
		TUserInfo userInfo = userService.getUserInfoByUserId(userId);
		model.addAttribute("userInfo", userInfo);
		return "/user/userDetail";
	}
}
