package com.e3expo.e3.middleware.exporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.e3expo.e3.middleware.mapper.UserInfoFileMapper;
import com.e3expo.e3.model.UserInfoFile;
import com.e3expo.e3.model.UserRemarkLog;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.e3expo.e3.common.SerialNo;
import com.e3expo.e3.enumration.EnumAuditStatus;
import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.exceptions.UserException.ErrorCode;
import com.e3expo.e3.middleware.mapper.CityMapper;
import com.e3expo.e3.middleware.mapper.DesignerMapper;
import com.e3expo.e3.middleware.mapper.ProvinceMapper;
import com.e3expo.e3.middleware.mapper.UserAuditLogMapper;
import com.e3expo.e3.middleware.mapper.UserAuditMapper;
import com.e3expo.e3.middleware.mapper.UserAuditRemarkLogMapper;
import com.e3expo.e3.middleware.mapper.UserMapper;
import com.e3expo.e3.middleware.mapper.UserRemarkLogMapper;
import com.e3expo.e3.model.City;
import com.e3expo.e3.model.Designer;
import com.e3expo.e3.model.Province;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.UserAudit;
import com.e3expo.e3.model.UserAuditFile;
import com.e3expo.e3.model.UserAuditLog;
import com.e3expo.e3.model.UserAuditRemarkLog;
import com.e3expo.e3.model.form.AdminUserParam;
import com.e3expo.e3.model.view.DesignerUserView;
import com.e3expo.e3.model.view.PagedData;
import com.e3expo.e3.model.view.UserAuditDetailView;
import com.e3expo.e3.model.view.UserAuditView;
import com.e3expo.e3.model.view.UserView;
import com.e3expo.e3.service.interfaces.IAdminUser;
import com.e3expo.e3.util.DateUtils;

/**
 * 后台人员管理和审核
 * 
 * @author lizy
 *
 */
@Component
public class AdminUserExporter implements IAdminUser {
	private SqlSessionTemplate sqlSessionTemplate;
	private UserMapper userMapper;
	private ProvinceMapper provinceMapper;
	private CityMapper cityMapper;
	private DesignerMapper designerMapper;
	private UserInfoFileMapper designerImageMapper;
	private UserAuditLogMapper userAuditLogMapper;
	private UserRemarkLogMapper userRemarkLogMapper;
	private UserAuditMapper userAuditMapper;
	private UserAuditRemarkLogMapper userAuditRemarkLogMapper;
	private UserInfoFileMapper userInfoFileMapper;

	@Autowired
	private void setSqlSessionTemplate(SqlSessionTemplate template) {
		this.sqlSessionTemplate = template;
		this.userMapper = this.sqlSessionTemplate.getMapper(UserMapper.class);
		this.provinceMapper = this.sqlSessionTemplate.getMapper(ProvinceMapper.class);
		this.cityMapper = this.sqlSessionTemplate.getMapper(CityMapper.class);
		this.designerMapper = this.sqlSessionTemplate.getMapper(DesignerMapper.class);
		this.designerImageMapper = this.sqlSessionTemplate.getMapper(UserInfoFileMapper.class);
		this.userRemarkLogMapper = this.sqlSessionTemplate.getMapper(UserRemarkLogMapper.class);
		this.userAuditLogMapper = this.sqlSessionTemplate.getMapper(UserAuditLogMapper.class);
		this.userAuditMapper = this.sqlSessionTemplate.getMapper(UserAuditMapper.class);
		this.userAuditRemarkLogMapper = this.sqlSessionTemplate.getMapper(UserAuditRemarkLogMapper.class);
		this.userInfoFileMapper = this.sqlSessionTemplate.getMapper(UserInfoFileMapper.class);
	}

	@Override
	public AdminUserParam getPagedUserView(AdminUserParam param) {
		List<UserView> users = this.userMapper.selectPagedListByAdmin(param);
		Integer totalNum = this.userMapper.selectPagedTotalNumByAdmin(param);
		for (UserView userView : users) {
			try {
				userView.setStatusRemark(EnumAuditStatus.getRemark(userView.getStatus()));
			} catch (Exception ex) {

			}

		}
		param.setData(users);
		param.setTotal(totalNum);
		return param;
	}

	/**
	 * 查询设计师的分页数据
	 * 
	 * @param param
	 * @return
	 */
	public List<UserView> getUserList(AdminUserParam param) {
		List<UserView> users = this.userMapper.selectPagedListByAdmin(param);
		return users;
	}

	/**
	 * 获取设计师的所有资料信息
	 * 
	 * @param id
	 *            设计师的te_user的主键
	 * @return
	 *//*
		 * public UserOfDesigner getUserOfDesigner(Integer id) { //获取te_user User
		 * user=this.userMapper.selectByPrimaryKey(id); if(user!=null) { return null; }
		 * Integer userId=user.getId(); UserOfDesigner model=new UserOfDesigner();
		 * model.setUser(user); //获取province Province
		 * province=this.provinceMapper.selectByPrimaryKey(user.getProvinceId());
		 * //获取city City city=this.cityMapper.selectByPrimaryKey(user.getCityId());
		 * //获取designer Designer designer=this.designerMapper.selectByUserId(userId);
		 * //获取designerImage List<UserInfoFile>
		 * designerImageList=this.designerImageMapper.selectByUserId(userId);
		 * //获取userAuditLogMapper List<UserAuditLog>
		 * userAuditLogList=this.userAuditLogMapper.selectByUserId(userId);
		 * model.setProvince(province); model.setCity(city);
		 * model.setDesigner(designer); model.setUserInfoFileList(designerImageList);
		 * model.setUserAuditLogList(userAuditLogList); return model; }
		 */
	/**
	 * 后台管理人员审核用户信息
	 * 
	 * @param audit_userId
	 *            后台审核人员的主键
	 * @param userId
	 *            被审核人的逐渐
	 * @param status
	 *            审核状态 0，1，2
	 * @param auditRemark
	 *            审核评价
	 * @param auditStatus
	 *            资料的完成状态，资料待补等状态内容
	 */
	public void auditUser(Integer auditUserId, Integer userId, Integer status, String auditRemark) {
		long timeTamp = DateUtils.getTimeStamp();
		// 构建入参实体
		User user = new User();
		user.setId(userId);
		user.setAuditRemark(auditRemark);
		user.setStatus(status);
		user.setUpdateTime(timeTamp);
		UserAuditLog userAuditLog = new UserAuditLog();
		/*
		 * userAuditLog.setUserId(userId); userAuditLog.setAuditUserId(auditUserId);
		 * userAuditLog.setRemark(auditRemark);
		 */
		userAuditLog.setCreateTime(timeTamp);
		userAuditLog.setStatus(status);
		// 插入user_audit_log表
		this.userMapper.updateByPrimaryKey(user);
		// 更新te_user的audit_remark和status字段
		this.userAuditLogMapper.updateByPrimaryKey(userAuditLog);

	}

	@Override
	public DesignerUserView getDesignerUserView(Integer userId) {
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		DesignerUserView model = this.userMapper.getDesignerUserView(map);
		if (model != null) {
			model.setStatusRemark(EnumAuditStatus.getType(model.getStatus()).getName());
		}
		return model;
	}

	@Override
	public void updateUser(User user) {
		this.userMapper.updateByPrimaryKey(user);

	}

	@Override
	public int remarkUser(UserRemarkLog record) {
		record.setCreateTime(DateUtils.getMsTimeStamp());
		int num = this.userRemarkLogMapper.insertSelective(record);
		return num;
	}

	@Override
	public AdminUserParam selectAuditPagedListByAdmin(AdminUserParam param) {
		List<UserAuditView> users = this.userMapper.selectAuditPagedListByAdmin(param);
		Integer totalNum = this.userMapper.selectAuditPagedTotalNumByAdmin(param);
		for (UserAuditView userAuditView : users) {
			try {
				userAuditView.setStatusRemark(EnumAuditStatus.getRemark(userAuditView.getStatus()));
			} catch (Exception ex) {

			}

		}
		param.setData(users);
		param.setTotal(totalNum);
		return param;
	}

	@Override
	public UserAuditDetailView getUserAuditDetailView(Integer userAuditId) {
		// TODO Auto-generated method stub
		return this.userMapper.getUserAuditDetailView(userAuditId);
	}

	@Override
	public void updateUserAudit(UserAudit userAudit) {
		// TODO Auto-generated method stub
		long timeStamp = DateUtils.getMsTimeStamp();
		userAudit.setUpdateTime(timeStamp);
		this.userAuditMapper.updateByPrimaryKeySelective(userAudit);
		// return 0;
	}

	@Override
	public void auditUser(Integer userAdutiId, Integer status) {
		long timeTampe = DateUtils.getMsTimeStamp();
		// 修改user_audit表的同时，更新user表
		// 获取userAudit,按理应该判断，放一放
		UserAuditDetailView userAuditDetailView = this.userMapper.getUserAuditDetailView(userAdutiId);
		Integer userId = userAuditDetailView.getUserId();
		// 根据userAuditId获取user

		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		DesignerUserView designerUserView = this.userMapper.getDesignerUserView(map);
		// 填充数据
		UserAudit userAudit = new UserAudit();
		userAudit.setId(userAdutiId);
		userAudit.setUpdateTime(timeTampe);
		userAudit.setStatus(status);
		User user = new User();
		user.setUpdateTime(timeTampe);
		user.setStatus(status);
		// TODO 通过
		if (EnumAuditStatus.getType(status) == EnumAuditStatus.AUDIT_SUCCESS) {
			// 附件表里的数据全部覆盖到userFileInfo中，原数据删除
			for (UserInfoFile item : designerUserView.getImages()) {
				this.userInfoFileMapper.deleteByPrimaryKey(item.getId());
			}
			for (UserAuditFile item : userAuditDetailView.getImages()) {
				UserInfoFile userInfoFile = new UserInfoFile();
				userInfoFile.setCreateTime(timeTampe);
				userInfoFile.setUpdateTime(timeTampe);
				userInfoFile.setFileName(item.getFileName());
				userInfoFile.setFilePath(item.getFilePath());
				userInfoFile.setFileType(item.getFileType());
				userInfoFile.setUserId(userId);
				this.userInfoFileMapper.insertSelective(userInfoFile);
			}
			// 创建工号
			if (designerUserView.getJobNumber() != null && designerUserView.getJobNumber().length() != 0) {

			} else {
				user.setJobNumber(SerialNo.createJobNumber());
			}
		}
		// TODO 待补
		// TODO 不通过
		// 更新两张表的状态
		this.userMapper.updateByPrimaryKey(user);
		this.userAuditMapper.updateByPrimaryKey(userAudit);

	}

	/**
	 * 管理员审核用户时，添加备注
	 */
	@Override
	public void userAuditRemark(UserAuditRemarkLog userAuditRemarkLog) {
		// TODO Auto-generated method stub
		long timeStamp = DateUtils.getMsTimeStamp();
		userAuditRemarkLog.setCreateTime(timeStamp);
		this.userAuditRemarkLogMapper.insertSelective(userAuditRemarkLog);
	}

}
