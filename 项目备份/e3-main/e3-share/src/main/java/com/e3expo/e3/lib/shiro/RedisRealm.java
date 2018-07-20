package com.e3expo.e3.lib.shiro;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.e3expo.e3.enumration.EnumAuditStatus;
import com.e3expo.e3.exceptions.UserException;
import com.e3expo.e3.exceptions.UserException.ErrorCode;
import com.e3expo.e3.model.User;
import com.e3expo.e3.util.PasswordUtil;

public class RedisRealm<K, V> extends AuthorizingRealm {
	
	public RedisRealm(StringRedisTemplate template) {
		this.template = template;
	}
	
	public  RedisRealm(StringRedisTemplate template,RedisTemplate<K, V> redisTemplate) {
		this.template = template;
		this.redisTemplate = redisTemplate;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //null usernames are invalid
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        String username = (String) getAvailablePrincipal(principals);

        Set<String> roleNames = getRoleNamesForUser(username);
        Set<String> permissions = getPermissions(username, roleNames);
  

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
        info.setStringPermissions(permissions);
        return info;
        
	}

	
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		User user = new User();
		String username = upToken.getUsername();

		// Null username is invalid
		if (username == null) {
			throw new AccountException("Null usernames are not allowed by this realm.");
		}

		SimpleAuthenticationInfo info = null;
		Object id = template.opsForHash().get("users",  username);
    	if(id == null) {
    		throw new UserException(ErrorCode.USER_MOBILE_IS_NULL);
    	}
    	int userId = Integer.parseInt(id.toString());
    	Object pass = template.opsForHash().get("user:" + userId, "password");
    	String password = new String(upToken.getPassword());
    	String basePassword = null;
    	if(pass != null) {
    		basePassword = pass.toString();
    	}
    	password = PasswordUtil.encryptPassword(password);
    	if(!password.equals(basePassword)) {
    		throw new UserException(ErrorCode.USER_PASSWORD_ERROR);
    	}
    	Object tempStatus = template.opsForHash().get("user:"+userId, "status");
    	if(tempStatus == null || Integer.parseInt(tempStatus.toString()) != EnumAuditStatus.AUDIT_SUCCESS.getValue()) {
    		throw new UserException(ErrorCode.USER_NOT_AUDIT);
    	}
    	Object tempIsValid =  template.opsForHash().get("user:"+userId, "is_valid");
    	if(tempIsValid == null || Integer.parseInt(tempIsValid.toString()) == -1) {
    		throw new UserException(ErrorCode.USER_IS_STOP);
    	}
    	Object tempUserType = template.opsForHash().get("user:"+userId, "userType");
    	Integer userType = null;
    	if(tempUserType != null) {
    		userType = Integer.parseInt(tempUserType.toString());
    	}
    	Object tempName =  template.opsForHash().get("user:"+userId, "name");
    	String name = null;
    	if(tempName != null) {
    		name = tempUserType.toString();
    	}
    	user.setMobile(username);
    	user.setPassword(password);
    	user.setUserType(userType);
    	user.setName(name);
		info = new SimpleAuthenticationInfo(user, password, getName());

		return info;
	}
	
	

    protected Set<String> getRoleNamesForUser(String username)  {
        Set<String> roleNames = new LinkedHashSet<String>();
        
        int userId = new Double(template.opsForZSet().score("users", username)).intValue();
        //拿到用户ID之后，返回所有的roles名字
        roleNames = template.opsForSet().members("role:user:" + userId );
        return roleNames;
    }
	
	

    protected Set<String> getPermissions(String username, Collection<String> roleNames)  {

    		Set<String> permissions = new LinkedHashSet<String>();
        	
        for(String roleName: roleNames ) {
        		int roleId = new Double(template.opsForZSet().score("roles", roleName)).intValue();
        		//拿到了roleId
        		Set<String> tempPermissions = template.opsForSet().members("permission:role:" + roleId);
        		
        		permissions.addAll(tempPermissions);
        	
        }

        return permissions;
    }
    
    
	
    private String getPasswordForUser(String username) {
    	Double id = template.opsForZSet().score("users",  username);
    	if(id == null) {
    		throw new UserException(ErrorCode.USER_MOBILE_IS_NULL);
    	}
    	int userId = new Double(id).intValue();
        String password = (String)template.opsForHash().get("user:" + userId, "password");
        return password;
        
    }
    
	
	
	private StringRedisTemplate template;
	
	private RedisTemplate<K, V> redisTemplate;
	
}
