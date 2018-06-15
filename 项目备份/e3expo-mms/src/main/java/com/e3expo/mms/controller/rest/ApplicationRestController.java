package com.e3expo.mms.controller.rest;


import com.e3expo.mms.Exception.PermissionDeniedException;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.service.ApplicationService;
import com.e3expo.mms.controller.ParentController;
import com.e3expo.mms.controller.message.MmsMessage;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.e3expo.mms.bean.enumeration.RoleEnum.*;
@RestController
@RequestMapping("application")
public class ApplicationRestController extends ParentController {

	@Autowired
	private ApplicationService applicationService;

	@RequestMapping("approve/{applicationId}")
	public MmsMessage approveApplication(@PathVariable("applicationId") int applicationId) {
		// 同意请求
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if (!user.getRole().getName().equals(SYS_ADMIN.getRoleName())) {
			throw new PermissionDeniedException();
		}
		applicationService.approveApplication(applicationId);
		return new MmsMessage(200, "success");
	}

	@RequestMapping("reject/{applicationId}")
	public MmsMessage rejectApplication(@PathVariable("applicationId") int applicationId) {
		// 拒绝请求
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
		if (!user.getRole().getName().equals(SYS_ADMIN.getRoleName())) {
			throw new PermissionDeniedException();
		}
		applicationService.rejectApplication(applicationId);
		return new MmsMessage(200, "success");
	}
	
}
