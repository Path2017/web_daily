package com.e3expo.e3.middleware.config;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.alibaba.druid.pool.DruidDataSource;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

@Configuration

@PropertySources(
		{@PropertySource("classpath:druid.properties"),
		@PropertySource("classpath:redis.properties"),
		@PropertySource("classpath:alipay.properties")}
		)


@MapperScan(value="com.e3expo.e3.middleware.mapper", 
sqlSessionTemplateRef="sqlSession")
@EnableCaching

public class RootConfig implements EnvironmentAware {
	
	
	
	/**
	 * 配置 MyBatis
	 * @return
	 * @throws IOException
	 */
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	

	/*
	 * 配置事务处理器
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		ClassPathResource resource = new ClassPathResource("mybatis-config.xml");
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(resource);
		sqlSessionFactoryBean.setTransactionFactory(transactionFactory);
		sqlSessionFactoryBean.setTypeAliasesPackage("com.e3expo.e3.model");		
		return sqlSessionFactoryBean.getObject();

	}

	/**
	 * 配置数据库连接池
	 * 
	 * @return
	 */
	@Bean
	public DruidDataSource dataSource() {

		DruidDataSource myDataSource = new DruidDataSource();
		myDataSource.setUrl(env.getProperty("druid.url"));
		myDataSource.setUsername(env.getProperty("druid.username"));
		myDataSource.setPassword(env.getProperty("druid.password"));
		myDataSource.setInitialSize(new Integer(env.getProperty("druid.initialSize")).intValue());
		myDataSource.setMinIdle(new Integer(env.getProperty("druid.minIdle")).intValue());
		myDataSource.setMaxActive(new Integer(env.getProperty("druid.maxActive")).intValue());
		myDataSource.setMaxWait(new Long(env.getProperty("druid.maxWait")).longValue());
		myDataSource.setTimeBetweenEvictionRunsMillis(
				new Long(env.getProperty("druid.timeBetweenEvictionRunsMillis")).longValue());
		myDataSource.setMinEvictableIdleTimeMillis(
				new Long(env.getProperty("druid.minEvictableIdleTimeMillis")).longValue());
		myDataSource.setValidationQuery(env.getProperty("druid.validationQuery"));
		myDataSource.setTestWhileIdle(new Boolean(env.getProperty("druid.testWhileIdle")).booleanValue());
		myDataSource.setTestOnBorrow(new Boolean(env.getProperty("druid.testOnBorrow")).booleanValue());
		myDataSource.setTestOnReturn(new Boolean(env.getProperty("druid.testOnBorrow")).booleanValue());
		myDataSource
				.setPoolPreparedStatements(new Boolean(env.getProperty("druid.poolPreparedStatements")).booleanValue());

		LOGGER.info(myDataSource);

		return myDataSource;

	}
	
//	@Bean
//	public AlipayClient getAplipayClient() {
//		AlipayClient client = new DefaultAlipayClient(env.getProperty("gatewayUrl"),env.getProperty("app_id"),env.getProperty("merchant_private_key"),env.getProperty("format"),env.getProperty("charset"),env.getProperty("alipay_public_key"),env.getProperty("sign_type"));
//		return client;
//	}
//	
	@Override
	public void setEnvironment(Environment arg0) {
		this.env = arg0;
	}
	
	
	@Bean
	public DataSourceTransactionManager transactionManager() {

		return new DataSourceTransactionManager(dataSource());
	}
	
	
	@Autowired
	private Environment env;

	private static final Logger LOGGER = LogManager.getLogger(RootConfig.class);



}
