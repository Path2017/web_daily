package com.e3expo.e3.webapp.config;



import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.e3expo.e3.lib.property.PropertyFileToHashMapConfiger;
import com.e3expo.e3.lib.shiro.BcryptPasswordMatcher;
import com.e3expo.e3.lib.shiro.RedisRealm;

@Configuration
@PropertySource("classpath:redis.properties")
//@ComponentScan("com.e3expo.e3.webapp.services.ShiroService")
@Import({ShiroConfig.class})

public class RootConfig implements EnvironmentAware {

	
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
	
	
	//这里需要配置Redis
	//配置使用redis服务器
	@Bean
	public JedisConnectionFactory getJedisConnectionFactory() {
		
		
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(env.getProperty("redis.host"));
		factory.setPort(new Integer(env.getProperty("redis.port")).intValue());
		factory.setPassword(env.getProperty("redis.password"));
		factory.setUsePool(true);
		return factory;
		
	}
	
	
	@Bean
	public <K, V> RedisTemplate<K, V> getRedisTemplate() {
		RedisTemplate<K, V> template = new RedisTemplate<K, V>();
		template.setConnectionFactory(getJedisConnectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
	
	
	@Bean
	public StringRedisTemplate getStringRedisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
		template.setConnectionFactory(getJedisConnectionFactory());
		
		return template;
		
	}
	
	
	
//	
	//定义Shiro的库
	@Bean(name="shiroFilter")
	@DependsOn("shiroPropertys")
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
		
//		RedisEnterpriseSessionDAO dao = new RedisEnterpriseSessionDAO(getJedisConnectionFactory());
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(60*60*1000);
//		sessionManager.setSessionDAO(dao);
		sessionManager.setSessionIdCookieEnabled(true);
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm());
		securityManager.setSessionManager(sessionManager);
		securityManager.setCacheManager(cacheManager);
		
		return securityManager;
		
	}
//	
//	
	
	@Bean
	public RedisRealm myRealm() {
		RedisRealm realm = new RedisRealm(getStringRedisTemplate(),getRedisTemplate());
		realm.setCredentialsMatcher(bcryptPasswordMatcher());
		return realm;
	}
	
	
//	@Bean
//	public ShiroRealm myRealm() {
////		ShiroRealm realm = new ShiroRealm((IUser)applicationContext.getBean("iUser"));
//		ShiroRealm realm = new ShiroRealm();
////		realm.setCredentialsMatcher(bcryptPasswordMatcher());
//		return realm;
//	}
//	
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
	
	
	
	@Override
	public void setEnvironment(Environment arg0) {
		this.env = arg0;
	}
	
	
	

	@Autowired
	private Environment env;


}
