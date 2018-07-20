package com.e3expo.e3.middleware.exporter;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.exceptions.UserException.ErrorCode;
import com.e3expo.e3.middleware.mapper.OsUserMapper;
import com.e3expo.e3.middleware.service.AdminUserService;
import com.e3expo.e3.model.OsUser;
import com.e3expo.e3.model.param.OsUserParam;
import com.e3expo.e3.service.interfaces.IOsUser;
import com.e3expo.e3.util.DateUtils;
import com.e3expo.e3.util.NoUtil;
import com.e3expo.e3.util.PasswordUtil;
/**
 * 后台系统用户的操作
 * 比如新增、操作系统用户等
 * @author lizy
 *
 */
@Component
public class OsUserExporter implements IOsUser {
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
	public OsUser getOsUser(OsUserParam param) {
		String password = PasswordUtil.encryptPassword(param.getPassword());
		param.setPassword(password);
		OsUser model = mapper.getOsUserByMobileAndPassword(param);
		if(model==null) {
			throw new UserException(ErrorCode.USER_NOT_EXSITED);
		}
		return model;
	}

	/**
	 * 新增系统用户
	 * 
	 * @param model
	 * @return
	 */
	public int addOsUser(OsUser model) {
		// 填充model
		long timeTamp = DateUtils.getMsTimeStamp();
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
