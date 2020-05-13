package com.rakib.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rakib.domain.UserInfo;
import com.rakib.domain.repo.UserInfoRepo;
import com.rakib.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserInfoRepo userinfo;
	
	@Override
	public UserInfo saveUser(UserInfo userInfo) {
		return userinfo.save(userInfo);
	}

}
