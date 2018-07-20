package com.e3expo.e3.webapp.config;

import com.caucho.hessian.io.SerializerFactory;
import com.e3expo.e3.service.interfaces.IDesignerBid;
import com.e3expo.e3.service.interfaces.IOrder;
import com.e3expo.e3.service.interfaces.IRfp;
import com.e3expo.e3.webapp.config.hessian.serializer.BigDecimalSerializerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;

import com.e3expo.e3.common.ServiceConst;
import com.e3expo.e3.service.interfaces.ITest;
import com.e3expo.e3.service.interfaces.IUser;

@Configuration
@PropertySource("classpath:service.properties")
public class RemoteClient {

    private final static String SERVICE_URL = "";

    	@Bean
    public HessianProxyFactoryBean testService() {
//        String testServiceURL = env.getProperty("test.service.url");
        String userServiceURL = getServiceUrl();
        String testServiceURL = userServiceURL+"/test";
        LOGGER.info("Test Service URL:" + testServiceURL);

        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl(testServiceURL);
        factoryBean.setServiceInterface(ITest.class);
        return factoryBean;

    }

    @Bean
    public HessianProxyFactoryBean rfp() {
        String userServiceURL = getServiceUrl();
        userServiceURL = userServiceURL + ServiceConst.RFP;
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl(userServiceURL);
        factoryBean.setServiceInterface(IRfp.class);
//        factoryBean.setOverloadEnabled(true);
        // Set SerializerFactory
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.addFactory(new BigDecimalSerializerFactory());
        factoryBean.setSerializerFactory(serializerFactory);
        return factoryBean;
    }


    @Bean
    public HessianProxyFactoryBean designerBid() {
        String userServiceURL = getServiceUrl();
        userServiceURL = userServiceURL + ServiceConst.DESIGNER_BID;
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl(userServiceURL);
        factoryBean.setServiceInterface(IDesignerBid.class);
        // Set SerializerFactory
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.addFactory(new BigDecimalSerializerFactory());
        factoryBean.setSerializerFactory(serializerFactory);

        return factoryBean;
    }

    @Bean
    public HessianProxyFactoryBean order() {
        String userServiceURL = getServiceUrl();
        userServiceURL = userServiceURL + ServiceConst.ORDER;
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl(userServiceURL);
        factoryBean.setServiceInterface(IOrder.class);
        // Set SerializerFactory
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.addFactory(new BigDecimalSerializerFactory());
        factoryBean.setSerializerFactory(serializerFactory);

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

    private String getServiceUrl() {
        return env.getProperty("service.url");
    }

    @Autowired
    private Environment env;

    private static final Logger LOGGER = LogManager.getLogger(RemoteClient.class);

}
