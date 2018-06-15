package com.e3expo.mms.bean.libext.shiro.filter;


import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义shiro auth filter
 *
 * hasRoles[<role1>,<role2>,...]
 */
public class CustomRolesAuthorizationFilter extends AuthorizationFilter {


	@Override
	protected boolean isAccessAllowed(ServletRequest req,
									  ServletResponse resp,
									  Object mappedValue) throws Exception {
		Subject subject = getSubject(req, resp);
		String[] rolesArray = (String[]) mappedValue;

		if (rolesArray == null || rolesArray.length == 0) {
			//没有角色限制，有权限访问
			return true;
		}
		for (String s : rolesArray) {
			if (subject.hasRole(s)) {
			//若当前用户是rolesArray中的任何一个，则有权限访问
				return true;
			}
		}

		return false;
	}
}
