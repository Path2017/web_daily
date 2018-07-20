package com.e3expo.e3.middleware.service;
/**
 * 该类主要用于后台管理系统的用户权限设定，包括菜单、功能、角色分配等
 * @author lizy
 *
 */

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.e3expo.e3.middleware.mapper.DesignerMapper;
import com.e3expo.e3.middleware.mapper.OsUserMapper;
import com.e3expo.e3.model.OsUser;
import com.e3expo.e3.util.NoUtil;
import com.e3expo.e3.util.DateUtils;
import com.e3expo.e3.util.PasswordUtil;

@Service
public class AdminUserService {
	private OsUserMapper mapper;
	private SqlSessionTemplate sqlSessionTemplate;

	@Autowired
	private void setSqlSessionTemplate(SqlSessionTemplate template) {
		this.sqlSessionTemplate = template;
		this.mapper = this.sqlSessionTemplate.getMapper(OsUserMapper.class);
	}

	/**
	 * 根据手机号和输入的密码获取系统用户
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
//	public OsUser getOsUser(String mobile, String password) {
//		password = PasswordUtil.encryptPassword(password);
//		OsUser model = mapper.getOsUserByMobileAndPassword(mobile, password);
//		return model;
//	}

	/**
	 * 新增系统用户
	 * 
	 * @param model
	 * @return
	 */
	public int addOsUser(OsUser model) {
		// 填充model
		long timeTamp = DateUtils.getTimeStamp();
		model.setPassword(PasswordUtil.encryptPassword(model.getPassword()));
		model.setCreateTime(timeTamp);
		model.setUpdateTime(timeTamp);
		model.setUserNo(NoUtil.getSevenDigits());
		model.setIsValid(1);
		int num = mapper.insertSelective(model);
		return num;
	}

	/**
	 * 更新系统用户的信息
	 * 
	 * @param model
	 * @return
	 */
	public int updateOsUser(OsUser model) {
		String password = model.getPassword();
		if (password == null || password.length() == 0) {

		} else {
			model.setPassword(PasswordUtil.encryptPassword(model.getPassword()));
		}
		long timeTamp = DateUtils.getTimeStamp();
		model.setUpdateTime(timeTamp);
		int num = mapper.updateByPrimaryKeySelective(model);
		return num;
	}

}
