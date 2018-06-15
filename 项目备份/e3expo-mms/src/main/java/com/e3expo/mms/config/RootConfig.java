package com.e3expo.mms.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.e3expo.mms.bean.libext.shiro.CustomJDBCRealm;
import com.e3expo.mms.bean.libext.shiro.filter.CustomRolesAuthorizationFilter;
import com.e3expo.mms.tool.GetPropertiesToHashMap;
import net.sf.ehcache.CacheManager;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.io.IOException;


@Configuration
@PropertySource("classpath:druid.properties")
@MapperScan("com.e3expo.mms.bean.mapper")
public class RootConfig  implements EnvironmentAware  {
	
	//配置缓存
	/**
	 * 配置缓存使用ehcache 2.x
	 * 
	 * @return cacheManager
	 */
	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cManager) {
		return new EhCacheCacheManager(cManager);
	}

	
	@Bean
	public EhCacheManagerFactoryBean ehcache() {
		
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = 
				new EhCacheManagerFactoryBean();
		
		ehCacheManagerFactoryBean.setConfigLocation(
				new ClassPathResource("ehcache.xml")
				);
		
		ehCacheManagerFactoryBean.setShared(true);
		
		return ehCacheManagerFactoryBean;
		
	}

	
	
	
	
	
	/**
	 * 配置 MyBatis
	 * @return
	 * @throws IOException
	 */
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	
	
	/**
	 * 所有这里还是要设计一个从配置文件来读取的概念
	 * 
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		ClassPathResource resource = new ClassPathResource("mybatis-config.xml");
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getDataSource());
		sqlSessionFactoryBean.setConfigLocation(resource);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.e3expo.mms.bean.model");
		return sqlSessionFactoryBean.getObject();
		
		
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {

		return new DataSourceTransactionManager(getDataSource());
	}
	
	
	
	
	
	//定义Shiro的库
	@Bean(name="shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(CacheManager cm) throws IOException {

		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		shiroFilterFactoryBean.setSecurityManager(securityManager(cm));
		shiroFilterFactoryBean.setSuccessUrl("/index");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(
				GetPropertiesToHashMap.getMap("shirofilter.properties")
				);
		
		return shiroFilterFactoryBean;
	}
	
	
	@Bean
	public DefaultWebSecurityManager securityManager(CacheManager cm) throws IOException {
		
		//配置session管理器
		//和缓存管理器
		SessionManager sessionManager = new DefaultWebSessionManager();
		
		
		EhCacheManager myEhCacheManager = new EhCacheManager();
		myEhCacheManager.setCacheManager(cm);
		
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		securityManager.setRealm(defaultJDBCRealm());
		securityManager.setSessionManager(sessionManager);
		securityManager.setCacheManager(myEhCacheManager);
		
		
		return securityManager;
		
	}


	@Bean
	public JdbcRealm defaultJDBCRealm() {
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(getDataSource());
		jdbcRealm.setAuthenticationQuery(CustomJDBCRealm.DEFAULT_AUTHENTICATION_QUERY);
		jdbcRealm.setUserRolesQuery(CustomJDBCRealm.DEFAULT_USER_ROLES_QUERY);
		jdbcRealm.setPermissionsQuery(CustomJDBCRealm.DEFAULT_PERMISSIONS_QUERY);

		return jdbcRealm;
	}
	
	@Bean
	public CustomJDBCRealm myRealm() {
		CustomJDBCRealm realm = new CustomJDBCRealm();
//		realm.setCredentialsMatcher(bcryptPasswordMatcher());
		realm.setDataSource(getDataSource());
		
		return realm;
	}
	

	@Bean("anyRoles")
	public CustomRolesAuthorizationFilter customRolesAuthorizationFilter() {
		return new CustomRolesAuthorizationFilter();
	}
	
//	@Bean
//	public BcryptPasswordMatcher bcryptPasswordMatcher() {
//			return new BcryptPasswordMatcher();
//	}
	
	
	
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
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(CacheManager cm) throws IOException {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager(cm));
		return advisor;
	}
	
	
	
	
	
	
	
	
	
	
	
	
    //定义数据库链接
	@Bean(name="dataSource")
	public DruidDataSource  getDataSource() {
			
		DruidDataSource myDataSource = new DruidDataSource();
		myDataSource.setUrl(load("druid.url"));
		myDataSource.setUsername(load("druid.username"));
		myDataSource.setPassword(load("druid.password"));
		myDataSource.setInitialSize(loadInt("druid.initialSize"));
		myDataSource.setMinIdle(loadInt("druid.minIdle"));
		myDataSource.setMaxActive(loadInt("druid.maxActive"));
		myDataSource.setMaxWait(loadInt("druid.maxWait"));
		myDataSource.setTimeBetweenEvictionRunsMillis(
				loadLong("druid.timeBetweenEvictionRunsMillis"));
		myDataSource.setMinEvictableIdleTimeMillis(
				loadLong("druid.minEvictableIdleTimeMillis")
				);
		myDataSource.setValidationQuery(
				load("druid.validationQuery")
				);
		myDataSource.setTestWhileIdle(
				loadBoolean("druid.testWhileIdle")
				);
		myDataSource.setTestOnBorrow(
				loadBoolean("druid.testOnBorrow")
				);
		myDataSource.setTestOnReturn(
				loadBoolean("druid.testOnBorrow")
				);
		myDataSource.setPoolPreparedStatements(
				loadBoolean("druid.poolPreparedStatements")
				);
		
		
		LOGGER.info(myDataSource);
		
		return myDataSource;
		
	}
	

	
    private String load(String propertyName) {
        return env.getRequiredProperty(propertyName);
    }

    
    private int loadInt(String propertyName) {
        return env.getRequiredProperty(propertyName, Integer.class);
    }  
    
    
    private long loadLong(String propertyName) {
        return env.getRequiredProperty(propertyName, Long.class);
    }  

   
    private boolean loadBoolean(String propertyName) {
        return env.getRequiredProperty(propertyName, Boolean.class);
    }  
    
    
	
	

	@Override
	public void setEnvironment(Environment environment) {
		
		this.env = environment;
		
	}
	
	private Environment env;
	
	private static final Logger LOGGER = LogManager.getLogger(RootConfig.class.getName());
	

}
