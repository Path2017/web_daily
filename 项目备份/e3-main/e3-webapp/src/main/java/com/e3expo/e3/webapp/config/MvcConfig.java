package com.e3expo.e3.webapp.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.sf.ehcache.CacheManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
		"com.e3expo.e3.webapp.controller",
		"com.e3expo.e3.webapp.services",
		"com.e3expo.e3.webapp.common"
})
@EnableCaching
public class MvcConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {


	// Config MessageConverter

	@Bean("validator")
	public LocalValidatorFactoryBean localValidatorFactoryBean() {
		return new LocalValidatorFactoryBean();
	}



//	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		requestMappingHandlerAdapter.setMessageConverters(converters);
		// 配置converter, jackson不序列化值为null的字段
		MappingJackson2HttpMessageConverter nonNullJsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		nonNullJsonConverter.setObjectMapper(objectMapper);
		converters.add(nonNullJsonConverter);

		return requestMappingHandlerAdapter;
	}


	// 处理multipart类型的表单请求
	@Bean("multipartResolver")
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	//配置在webapp端使用的缓存
	//必须注意的是spring要注入net.sf.ehcache.CacheManger到spring的EhCacheCacheManager, 
	//同时spring如何得到net.sf.ehcache.CacheManager的，它是通过注册了
	//EhCacheManagerFactoryBean来生成一个实例，并且注入到spring的容器中，具体的关于factoryBean这个
	//可以参考spring的文档
	@Bean
	public EhCacheCacheManager cacheManager(CacheManager cManager) {
		return new EhCacheCacheManager(cManager);
	}
	
	//配置Ehcache Manager的factory bean
	@Bean
	public EhCacheManagerFactoryBean getCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		ClassPathResource resource = new ClassPathResource("ehcache.xml");
		factoryBean.setConfigLocation(resource);
		factoryBean.setShared(true);
		return factoryBean;
	}
	
	
	
	
	//配置基本的静态页面
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//处理各种静态资源
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
		registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
		
	}
	
	
	//配置怎么处理静态资源
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer
			) {
		
		configurer.enable();
		
	}
	
	
	
	/**
	 * 配置thymeleaf模板
	 */
	@Bean
	public SpringResourceTemplateResolver templateResolver(){
	    // SpringResourceTemplateResolver automatically integrates with Spring's own
	    // resource resolution infrastructure, which is highly recommended.
	    SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
	    templateResolver.setApplicationContext(this.applicationContext);
	    templateResolver.setPrefix("/WEB-INF/view/");
	    templateResolver.setSuffix(".html");
	    // HTML is the default value, added here for the sake of clarity.
	    templateResolver.setTemplateMode(TemplateMode.HTML);
	    // Template cache is true by default. Set to false if you want
	    // templates to be automatically updated when modified.
	    templateResolver.setCacheable(false);
	    templateResolver.setCharacterEncoding("utf-8");
	    return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine(){
	    // SpringTemplateEngine automatically applies SpringStandardDialect and
	    // enables Spring's own MessageSource message resolution mechanisms.
	    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	    templateEngine.setTemplateResolver(templateResolver());
	    // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
	    // speed up execution in most scenarios, but might be incompatible
	    // with specific cases when expressions in one template are reused
	    // across different data types, so this flag is "false" by default
	    // for safer backwards compatibility.
	    templateEngine.addDialect(shiroDialect());
	    templateEngine.setEnableSpringELCompiler(true);
	    return templateEngine;
	}
	

	@Bean
	public ThymeleafViewResolver viewResolver(){
	    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	    viewResolver.setTemplateEngine(templateEngine());
	    // NOTE 'order' and 'viewNames' are optional
	    viewResolver.setCharacterEncoding("utf-8");
	    return viewResolver;
	}
	
	@Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
	
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		
	}
	
	private ApplicationContext applicationContext;
	
	
	
}
