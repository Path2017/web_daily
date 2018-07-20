package com.e3expo.e3.admin.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.e3expo.e3.admin.controller.rest.RestfulJsonModelAndView;
import com.e3expo.e3.enumration.EnumUserType;
import com.e3expo.e3.model.User;
import com.e3expo.e3.model.UserAudit;
import com.e3expo.e3.model.UserAuditRemarkLog;
import com.e3expo.e3.model.UserRemarkLog;
import com.e3expo.e3.model.form.AdminUserParam;
import com.e3expo.e3.model.view.DesignerUserView;
import com.e3expo.e3.model.view.PagedData;
import com.e3expo.e3.model.view.UserAuditDetailView;
import com.e3expo.e3.model.view.UserView;
import com.e3expo.e3.service.interfaces.IAdminUser;
import com.e3expo.e3.util.DateUtils;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

@Controller
public class UserController extends BaseController {
	@Autowired
	private IAdminUser iAdminUser;

	/**
	 * @api {get} /pagedUserView
	 * @apiGroup UserManage
	 * @apiDescription 用户管理-获取用户列表(api)
	 * @apiParam {int} userType 用户类型1，设计师。2，展装公司。必填
	 * @apiParam {int} countryId 国家ID
	 * @apiParam {int} provinceId 省份ID
	 * @apiParam {int} cityId 城市ID
	 * @apiParam {String} name 名字
	 * @apiParam {String} mobile 手机号
	 * @apiParam {int} status 状态
	 * @apiParam {long} startTime 开始时间
	 * @apiParam {long} endTime 结束时间
	 * @apiParam {int} pageIndex 第几页，必填
	 * @apiSuccess {UserView} userView 用户信息(list)
	 * @apiSuccess {int} userView.id id
	 * @apiSuccess {String} userView.countryName 国家
	 * @apiSuccess {String} userView.provinceName 省份
	 * @apiSuccess {String} userView.cityName 城市
	 * @apiSuccess {String} userView.name 名字
	 * @apiSuccess {String} userView.mobile 手机号
	 * @apiSuccess {String} userView.statusRemark 状态的中文描述
	 * @apiSuccess {String} userView.auditRemark 审核的评价内容
	 * @apiSuccess {Date} userView.createTime 申请时间
	 * @apiSuccess {int} totalNum 总条数
	 * 
	 */

	@GetMapping("/pagedUserView")
	public ModelAndView getPagedUserView(AdminUserParam param) {
		param.setUserType(EnumUserType.getType(param.getUserType()).getValue());
		param = iAdminUser.getPagedUserView(param);
		return RestfulJsonModelAndView.buildJsonModelAndView(param);
	}

	/**
	 * @api {get} /designerUserView
	 * @apiGroup UserManage
	 * @apiDescription 用户管理-获取设计师或者展装公司详情详情(themeLeaf)
	 * @apiParam {int} userId 设计师编号
	 * @apiSuccess {desingerUserView} desingerUserView 设计师所有信息
	 * @apiSuccess {int} desingerUserView.id id
	 * @apiSuccess {string} desingerUserView.mobile 手机
	 * @apiSuccess {string} desingerUserView.email 邮箱
	 * @apiSuccess {Date} desingerUserView.createTime 创建时间
	 * @apiSuccess {int} desingerUserView.status 用户状态
	 * @apiSuccess {string} desingerUserView.statusRemark 用户状态说明
	 * @apiSuccess {string} desingerUserView.qq QQ
	 * @apiSuccess {string} desingerUserView.name 名字
	 * @apiSuccess {DesignerImage} desingerUserView.images 附件信息
	 * @apiSuccess {string} desingerUserView.images.fileName 附件名字
	 * @apiSuccess {string} desingerUserView.images.filePath 附件路径
	 * @apiSuccess {string} desingerUserView.images.fileType 附件类型
	 * @apiSuccess {country} desingerUserView.country 国家
	 * @apiSuccess {int} desingerUserView.country.id 国家编号
	 * @apiSuccess {string} desingerUserView.country.name 国家名字
	 * @apiSuccess {province} desingerUserView.province 省份
	 * @apiSuccess {int} desingerUserView.country.id 省份编号
	 * @apiSuccess {string} desingerUserView.country.name 省份名称
	 * @apiSuccess {city} desingerUserView.city 城市
	 * @apiSuccess {int} desingerUserView.city.id 城市编号
	 * @apiSuccess {string} desingerUserView.city.name 城市名字
	 * @apiSuccess {auditLogs} desingerUserView.auditLogs 审核日志
	 * @apiSuccess {string} desingerUserView.auditLogs.remark 审核备注
	 * @apiSuccess {int} desingerUserView.auditLogs.status 审核时用户的状态
	 * @apiSuccess {OsUser} desingerUserView.auditLogs.osUser 审核人
	 * @apiSuccess {int} desingerUserView.auditLogs.osUser.id 审核人编号
	 * @apiSuccess {string} desingerUserView.auditLogs.osUser.name 审核人姓名
	 */
	@GetMapping("/designerUserView")
	public ModelAndView getDesignerUserView(Integer userId) {
		// userId=106;
		DesignerUserView model = iAdminUser.getDesignerUserView(userId);
		return RestfulJsonModelAndView.buildJsonModelAndView(model);
	}

	/**
	 * @api {post} /startUser
	 * @apiGroup UserManage
	 * @apiDescription 用户管理-用户详情-启用或者停用用户(api)
	 * @apiParam {int} isValid 启用或者停用标志 -1 停用。1启用
	 * @apiParam {int} userId 用户编号
	 */
	@PostMapping("/startUser")
	public ModelAndView startUser(Integer userId, Integer isValid) {
		User user = new User();
		user.setId(userId);
		user.setIsValid(isValid);
		iAdminUser.updateUser(user);
		return RestfulJsonModelAndView.buildJsonModelAndView();
	}

	/**
	 * @api {post} /remarkUser
	 * @apiGroup UserManage
	 * @apiDescription 用户管理-用户详情-管理员备注用户(api)
	 * @apiParam {int} userId
	 * @apiParam {string} remark 备注信息
	 */
	@PostMapping("/remarkUser")
	public ModelAndView remarkUser(UserRemarkLog record) {
		record.setAdminUserId(getUserSession().getId());
		int num = iAdminUser.remarkUser(record);
		return RestfulJsonModelAndView.buildJsonModelAndView(num);
	}

	/**
	 * 用户管理
	 * ===============================================================================================================================================
	 * 用户审核
	 */
	/**
	 * @api {get} /userAuditPagedData
	 * @apiGroup UserAudit
	 * @apiDescription 用户审核-获取用户的审核列表(api)
	 * @apiParam {int} userType 用户类型1，设计师。2，展装公司,必填
	 * @apiParam {int} countryId 国家ID
	 * @apiParam {int} provinceId 省份ID
	 * @apiParam {int} cityId 城市ID
	 * @apiParam {String} name 名字
	 * @apiParam {String} mobile 手机号
	 * @apiParam {int} status 状态
	 * @apiParam {long} startTime 开始时间
	 * @apiParam {long} endTime 结束时间
	 * @apiParam {int} pageIndex 第几页，必填
	 * @apiSuccess {UserView} userView 用户信息
	 * @apiSuccess {int} userView.id id 用户id
	 * @apiSuccess {String} userView.countryName 国家
	 * @apiSuccess {String} userView.provinceName 省份
	 * @apiSuccess {String} userView.cityName 城市
	 * @apiSuccess {String} userView.name 名字
	 * @apiSuccess {String} userView.mobile 手机号
	 * @apiSuccess {String} userView.statusRemark 状态的中文描述
	 * @apiSuccess {String} userView.auditRemark 审核的评价内容
	 * @apiSuccess {Date} userView.createTime 申请时间
	 * @apiSuccess {int} totalNum 总条数
	 * 
	 */
	@GetMapping("/userAuditPagedData")
	public ModelAndView getUserAuditPagedData(AdminUserParam param) {
		param.setUserType(EnumUserType.getType(param.getUserType()).getValue());
		param = iAdminUser.selectAuditPagedListByAdmin(param);
		return RestfulJsonModelAndView.buildJsonModelAndView(param);

	}

	/**
	 * @api {get} /userAuditDetailView
	 * @apiGroup UserAudit
	 * @apiDescription 用户审核-获取设计师或者展装公司详情详情(themeLeaf)
	 * @apiParam {int} userAuditId 审核编号
	 * @apiSuccess {UserAuditDetailView} userAuditDetailView 设计师所有审核信息
	 * @apiSuccess {int} userAuditDetailView.id id
	 * @apiSuccess {string} userAuditDetailView.mobile 手机号
	 * @apiSuccess {string} userAuditDetailView.email 邮箱
	 * @apiSuccess {Date} userAuditDetailView.createTime 创建时间
	 * @apiSuccess {int} userAuditDetailView.status 用户状态
	 * @apiSuccess {string} userAuditDetailView.statusRemark 状态说明
	 * @apiSuccess {string} userAuditDetailView.qq QQ
	 * @apiSuccess {string} userAuditDetailView.name 姓名
	 * @apiSuccess {userAuditFile} userAuditDetailView.images 附件
	 * @apiSuccess {string} userAuditDetailView.images.fileName 附件名称
	 * @apiSuccess {string} userAuditDetailView.images.filePath 附件路径
	 * @apiSuccess {string} userAuditDetailView.images.fileType 附件类型
	 * @apiSuccess {int} userAuditDetailView.images.userAuditId 审核id
	 * @apiSuccess {country} userAuditDetailView.country
	 * @apiSuccess {int} userAuditDetailView.country.id
	 * @apiSuccess {string} userAuditDetailView.country.name
	 * @apiSuccess {province} userAuditDetailView.province
	 * @apiSuccess {int} userAuditDetailView.country.id
	 * @apiSuccess {string} userAuditDetailView.country.name
	 * @apiSuccess {city} userAuditDetailView.city
	 * @apiSuccess {int} userAuditDetailView.city.id
	 * @apiSuccess {string} userAuditDetailView.city.name
	 * @apiSuccess {UserAuditRemarkLog} userAuditDetailView.userAuditRemarkLogs 审核日志
	 * @apiSuccess {string} userAuditDetailView.userAuditRemarkLogs.remark 评价
	 * @apiSuccess {Date} userAuditDetailView.userAuditRemarkLogs.auditCreateTime 创建时间
	 * @apiSuccess {OsUser} userAuditDetailView.userAuditRemarkLogs.osUser 审核人
	 * @apiSuccess {int} userAuditDetailView.userAuditRemarkLogs.osUser.id 审核人Id
	 * @apiSuccess {string} userAuditDetailView.userAuditRemarkLogs.osUser.name  审核人姓名
	 */
	@GetMapping("/userAuditDetailView")
	public ModelAndView getUserAuditDetailView(Integer userAuditId) {
		UserAuditDetailView model = iAdminUser.getUserAuditDetailView(userAuditId);
		return RestfulJsonModelAndView.buildJsonModelAndView(model);
	}

	/**
	 * @api {post} /userAudit
	 * @apiGroup UserAudit
	 * @apiDescription 用户审核-修改审核信息(点击审核通过，不通过时，将上面编辑的所有信息保存,附件信息没有处理，待处理)(api)
	 * @apiParam {int} id
	 * @apiParam {string} name 姓名
	 * @apiParam {string} qq QQ
	 * @apiParam {string} email 邮箱
	 * @apiParam {int} country_id 国家id
	 * @apiParam {int} province_id 省份ID
	 * @apiParam {int} city_id 城市Id
	 * @apiParam {int} status 审核状态
	 * @apiSuccess {int} code 200成功
	 * 
	 */
	@PostMapping("/userAudit")
	public ModelAndView userAudit(UserAudit userAudit) {

		
		iAdminUser.updateUserAudit(userAudit);
		return RestfulJsonModelAndView.buildJsonModelAndView();
	}
	/**
	 * @api {post} /userAuditRemark
	 * @apiGroup UserAudit
	 * @apiDescription 用户审核-添加备注(api)
	 * @apiParam {string} userAuditRemark 备注内容
	 * @apiSuccess {int} code 200成功
	 * 
	 */
	@PostMapping("/userAuditRemark")
	public ModelAndView userAudit(String userAuditRemark) {
		UserAuditRemarkLog userAuditRemarkLog = new UserAuditRemarkLog();
		userAuditRemarkLog.setRemark(userAuditRemark);
		userAuditRemarkLog.setAdminUserId(getUserSession().getId());
		iAdminUser.userAuditRemark(userAuditRemarkLog);
		return RestfulJsonModelAndView.buildJsonModelAndView();
	}
	

}
