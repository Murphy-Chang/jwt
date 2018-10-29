package com.mine.jwt.service.impl;

import com.mine.jwt.jpa.entity.TUserInfo;
import com.mine.jwt.jpa.repository.TUserInfoRepository;
import com.mine.jwt.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private TUserInfoRepository userInfoRepo;

	/**
	 * 获取全部用户信息
	 * @return
	 */
	@Override
	public List<TUserInfo> getAllUserInfoList(){
		return (List<TUserInfo>) userInfoRepo.findAll();
	}

	/**
	 * 通过用户名查询用户信息
	 * @param userName
	 * @return
	 */
	@Override
	public TUserInfo getUserInfoByUserName(String userName){
		return userInfoRepo.findByUserName(userName);
	}

	/**
	 * 通过id查询用户信息
	 * @param userId
	 * @return
	 */
	@Override
	public TUserInfo getUserInfoByUserId(int userId){
		return userInfoRepo.findByUserId(userId);
	}
 }
