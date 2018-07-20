package com.e3expo.e3.rabbitmq.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.e3expo.e3.rabbitmq.controller")
public class MvcConfig extends WebMvcConfigurerAdapter {

}
