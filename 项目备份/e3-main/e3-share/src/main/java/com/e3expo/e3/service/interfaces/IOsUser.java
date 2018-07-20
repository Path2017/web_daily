package com.e3expo.e3.service.interfaces;

import com.e3expo.e3.model.OsUser;
import com.e3expo.e3.model.param.OsUserParam;

/**
 * 该接口用于webApp端的服务调用
 * @author lizy
 *
 */
public interface IOsUser {
	/**
	 * 根据手机号和输入的密码获取系统用户
	 * 
	 * @param mobile
	 * @param password
	 * @return
	 */
	 OsUser getOsUser(OsUserParam param);

	/**
	 * 新增系统用户
	 * 
	 * @param model
	 * @return
	 */
	 int addOsUser(OsUser model) ;

	/**
	 * 更新系统用户的信息
	 * 
	 * @param model
	 * @return
	 */
	 int updateOsUser(OsUser model) ;
}
