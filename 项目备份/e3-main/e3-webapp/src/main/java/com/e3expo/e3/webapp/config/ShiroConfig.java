package com.e3expo.e3.webapp.config;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;

import com.e3expo.e3.lib.property.PropertyFileToHashMapConfiger;
import com.e3expo.e3.lib.shiro.BcryptPasswordMatcher;
import com.e3expo.e3.webapp.shiro.ShiroRealm;

@Configuration

public class ShiroConfig {
	
	//配置一个属性文件
		//Shiro的过滤器
		@Bean
		@Qualifier("shirofilter")
		public PropertyFileToHashMapConfiger shiroPropertys() {
			PropertyFileToHashMapConfiger configer = new PropertyFileToHashMapConfiger();
			ClassPathResource resource = new ClassPathResource("shirofilter.properties");
			configer.setLocation(resource);
			return configer;
		}
		
		

		//定义Shiro的库
		@Bean(name="shiroFilter")
//		@DependsOn("shiroPropertys")
		public ShiroFilterFactoryBean getShiroFilterFactoryBean()  {

			ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
			shiroFilterFactoryBean.setLoginUrl("/login");
			shiroFilterFactoryBean.setSecurityManager(securityManager());
			shiroFilterFactoryBean.setSuccessUrl("/home");
			shiroFilterFactoryBean.setFilterChainDefinitionMap(
					shiroPropertys().getFileMap()
					);
			
			return shiroFilterFactoryBean;
		}
		
		@Bean
		public DefaultWebSecurityManager securityManager() {
			
			//配置session管理器
			//和缓存管理器
			
//			RedisEnterpriseSessionDAO dao = new RedisEnterpriseSessionDAO(getJedisConnectionFactory());
			DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
			sessionManager.setGlobalSessionTimeout(60*60*1000);
//			sessionManager.setSessionDAO(dao);
			sessionManager.setSessionIdCookieEnabled(true);
//			EhCacheManager cacheManager = new EhCacheManager();
//			cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
			
			DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
			securityManager.setRealm(myRealm());
			securityManager.setSessionManager(sessionManager);
//			securityManager.setCacheManager(cacheManager);
			
			return securityManager;
			
		}
		
		@Bean
		public ShiroRealm myRealm() {
//			ShiroRealm realm = new ShiroRealm((IUser)applicationContext.getBean("iUser"));
			ShiroRealm realm = new ShiroRealm();
//			realm.setCredentialsMatcher(bcryptPasswordMatcher());
			return realm;
		}
		
		@Bean
		public BcryptPasswordMatcher bcryptPasswordMatcher() {
				return new BcryptPasswordMatcher();
		}
		
		
		@Bean
		public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
			
			return new LifecycleBeanPostProcessor();
		}
		
		@Bean
		@DependsOn(value="lifecycleBeanPostProcessor")
		public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
			return new DefaultAdvisorAutoProxyCreator();
		}
		
		
		@Bean 
		public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
			AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
			advisor.setSecurityManager(securityManager());
			return advisor;
		}
}
