package com.mine.jwt.service;

import com.mine.jwt.jpa.entity.TUserInfo;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/8/14
 */
public interface UserService {
	/**
	 * 获取全部用户信息
	 * @return
	 */
	List<TUserInfo> getAllUserInfoList();

	/**
	 * 通过用户名查询用户信息
	 * @param userName
	 * @return
	 */
	TUserInfo getUserInfoByUserName(String userName);

	/**
	 * 通过id查询用户信息
	 * @param userId
	 * @return
	 */
	TUserInfo getUserInfoByUserId(int userId);
}
