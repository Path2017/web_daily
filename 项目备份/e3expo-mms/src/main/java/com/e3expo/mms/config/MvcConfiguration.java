package com.e3expo.mms.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.e3expo.mms.controller",
        "com.e3expo.mms.bean"
})

public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {


//	//配置缓存
//	/**
//	 * 缓存使用JSR-107, 配合ehcache 3.0
//	 * 注意要使用JSR-107的注解
//	 * @return
//	 */
//	@Bean
//	public JCacheCacheManager cacheManager() throws IOException {
//
//		return new JCacheCacheManager(getJCacheManager());
//	}
//
//	@Bean
//	public CacheManager getJCacheManager() throws IOException {
//		CachingProvider provider = Caching.getCachingProvider();
//		CacheManager cacheManager = null;
//		ClassPathResource resource = new ClassPathResource("ehcache.xml");
//		
//		URI uri = resource.getURI();
//		
//		cacheManager = provider.getCacheManager(uri, 
//				getClass().getClassLoader() );
//
//		return cacheManager;
//	}
//
//	
//	


    //配置国际化的属性文件
    @Bean
    public ResourceBundleMessageSource messageSource() {

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.addBasenames("message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;

    }


    // 配置资源
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 处理各种静态资源
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/fonts/");

    }


    // 配置怎么处理静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    /**
     * 配置thymeleaf模板
     */
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        templateResolver.setCharacterEncoding("utf-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(shiroDialect());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
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


    @Bean("multipartResolver")
    public MultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }


    private ApplicationContext applicationContext;


}
