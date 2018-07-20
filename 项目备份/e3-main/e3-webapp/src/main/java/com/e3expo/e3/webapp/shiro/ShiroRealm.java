package com.e3expo.e3.webapp.shiro;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.e3expo.e3.exceptions.ShiroUserException;
import com.e3expo.e3.exceptions.ShiroUserException.ErrorCode;
import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.model.User;
import com.e3expo.e3.service.interfaces.IUser;
import com.e3expo.e3.webapp.common.ApplicationContextUtil;
import com.e3expo.e3.webapp.services.ShiroService;




/**
 * @author luning
 *
 */
public class ShiroRealm extends AuthorizingRealm {
//	@Autowired
//	private IUser iUser;
//	@Lazy
//	@Autowired
	private ShiroService iUser;
//	
//	public ShiroRealm(ShiroService service) {
//		this.iUser = service;
//	}
	
	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//根据用户配置用户与权限
		if (principals == null) {
			throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
		}
	
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			
			return info;
		
	}

	/* (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		User user;
		try {
			user = getiUser().userLogin(token.getUsername(), new String(token.getPassword()));
		} catch (UserException e) {
			ErrorCode code = ErrorCode.getType(e.getCode().getValue());
			throw new ShiroUserException(code);
		}
		
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

	public ShiroService getiUser() {
		return (ShiroService) ApplicationContextUtil.getBean("shiroService");
	}

//	public void setiUser(IUser iUser) {
//		this.iUser = iUser;
//	}

	

	

	
	
}
