package com.e3expo.e3.service.interfaces;

import java.util.List;
import java.util.Map;

import org.ietf.jgss.Oid;

import com.e3expo.e3.model.User;
import com.e3expo.e3.model.UserAudit;
import com.e3expo.e3.model.UserAuditRemarkLog;
import com.e3expo.e3.model.UserRemarkLog;
import com.e3expo.e3.model.form.AdminUserParam;
import com.e3expo.e3.model.view.DesignerUserView;
import com.e3expo.e3.model.view.PagedData;
import com.e3expo.e3.model.view.UserAuditDetailView;
import com.e3expo.e3.model.view.UserAuditView;
import com.e3expo.e3.model.view.UserView;

public interface IAdminUser {
	
	/**
	 * 用户管理列表
	 * @param param
	 * @return
	 */
	AdminUserParam getPagedUserView(AdminUserParam param); 
	/**
	 * 用户管理详情
	 * @param userId
	 * @return
	 */
	
    DesignerUserView getDesignerUserView(Integer userId);
    /**
     * 更新user 
     * @param user
     */
    void updateUser(User user);
    /**
     * 给用添加备注
     * @param record
     * @return
     */
    int remarkUser(UserRemarkLog record);
    /**===============================用户管理===============================**/
    /**===============================用户审核===============================**/
    /**
     * 用户审核列表
     * @param param
     * @return
     */
    AdminUserParam selectAuditPagedListByAdmin(AdminUserParam param);
    /**
     * 获取用户审核信息的详情
     * @param userAuditId 审核列表的Id
     * @return
     */
    UserAuditDetailView getUserAuditDetailView(Integer userAuditId);
    /**
     * 审核时，保存管理员修的改用户信息和添加的备注(审核的附件暂时没有处理，代码待补)
     * @param record
     * @return
     */
    void updateUserAudit(UserAudit userAudit) ;
    /**
     * 管理员审核用户
     * @param userAdutiId
     * @param status
     */
    void auditUser(Integer userAdutiId,Integer status);
    /**
     * 用户审核-添加备注
     * @param userAuditRemarkLog
     */
    void userAuditRemark(UserAuditRemarkLog userAuditRemarkLog);
    
}
