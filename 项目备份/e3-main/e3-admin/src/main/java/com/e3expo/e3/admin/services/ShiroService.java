package com.e3expo.e3.admin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.e3expo.e3.model.User;
import com.e3expo.e3.service.interfaces.IUser;

@Repository("shiroService")
public class ShiroService {
	
	@Autowired
	private IUser user;
	
	public User userLogin(String mobile,String password) {
		return user.userLogin(mobile, password);
	}
}
