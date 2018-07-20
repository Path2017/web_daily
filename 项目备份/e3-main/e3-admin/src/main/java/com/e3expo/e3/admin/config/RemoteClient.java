package com.e3expo.e3.admin.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.e3expo.e3.common.ServiceConst;
import com.e3expo.e3.service.interfaces.IAdminOrder;
import com.e3expo.e3.service.interfaces.IAdminUser;
import com.e3expo.e3.service.interfaces.IDic;
import com.e3expo.e3.service.interfaces.IOsUser;
import com.e3expo.e3.service.interfaces.ITest;
import com.e3expo.e3.service.interfaces.IUser;
import com.e3expo.e3.service.interfaces.IUtil;

@Configuration
@PropertySource("classpath:service.properties")
public class RemoteClient {

	private final static String SERVICE_URL = "";

	// @Bean
	public HessianProxyFactoryBean testService() {
		String testServiceURL = env.getProperty("test.service.url");

		LOGGER.info("Test Service URL:" + testServiceURL);

		HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
		factoryBean.setServiceUrl(testServiceURL);
		factoryBean.setServiceInterface(ITest.class);
		return factoryBean;

	}

	@Bean
	public HessianProxyFactoryBean userLogin() {
		String userServiceURL = getServiceUrl();
		userServiceURL = userServiceURL + ServiceConst.USER_LOGIN;

		HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
		factoryBean.setServiceUrl(userServiceURL);
		factoryBean.setServiceInterface(IUser.class);
		return factoryBean;

	}

	@Bean
	public HessianProxyFactoryBean osUser() {
		String serviceURL = getServiceUrl() + ServiceConst.OS_USER;
		HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
		factoryBean.setServiceUrl(serviceURL);
		factoryBean.setServiceInterface(IOsUser.class);
		return factoryBean;

	}

	@Bean
	public HessianProxyFactoryBean dic() {
		String serviceURL = getServiceUrl() + ServiceConst.DIC_QUERY;
		HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
		factoryBean.setServiceUrl(serviceURL);
		factoryBean.setServiceInterface(IDic.class);
		return factoryBean;

	}

	@Bean
	public HessianProxyFactoryBean adminUser() {
		String serviceURL = getServiceUrl() + ServiceConst.ADMIN_USER;
		HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
		factoryBean.setServiceUrl(serviceURL);
		factoryBean.setServiceInterface(IAdminUser.class);
		return factoryBean;

	}

	@Bean
	public HessianProxyFactoryBean adminOrder() {
		String serviceURL = getServiceUrl() + ServiceConst.ADMIN_ORDER;
		HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
		factoryBean.setServiceUrl(serviceURL);
		factoryBean.setServiceInterface(IAdminOrder.class);
		return factoryBean;

	}

	@Bean
	public HessianProxyFactoryBean util() {
		String serviceURL = getServiceUrl() + ServiceConst.UTIL;
		HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
		factoryBean.setServiceUrl(serviceURL);
		factoryBean.setServiceInterface(IUtil.class);
		return factoryBean;

	}

	private String getServiceUrl() {
		return env.getProperty("service.url");
	}

	@Autowired
	private Environment env;

	private static final Logger LOGGER = LogManager.getLogger(RemoteClient.class);

}
