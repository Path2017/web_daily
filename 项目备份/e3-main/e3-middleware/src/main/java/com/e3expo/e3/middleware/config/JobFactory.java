package com.e3expo.e3.middleware.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.AdaptableJobFactory;

public class JobFactory  extends AdaptableJobFactory implements ApplicationContextAware{
        
    ApplicationContext applicationContext;  
      
//  public AutowireCapableBeanFactory getCapableBeanFactoryYunZong() {  
//      return capableBeanFactoryYunZong;  
//  }  
//  
//  
//  public void setCapableBeanFactoryYunZong(  
//          AutowireCapableBeanFactory capableBeanFactoryYunZong) {  
//      this.capableBeanFactoryYunZong = capableBeanFactoryYunZong;  
//  }  
  
  
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {  
        //调用父类的方法  
        Object jobInstance = super.createJobInstance(bundle);  
          
        //进行注入,这属于Spring的技术,不清楚的可以查看Spring的API.  
       AutowireCapableBeanFactory aaa = applicationContext.getAutowireCapableBeanFactory();  
       aaa.autowireBeanProperties(jobInstance, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, false);  
//      applicationContext.getAutowireCapableBeanFactory().autowireBean(jobInstance);  
//      capableBeanFactoryYunZong.autowireBean(jobInstance);  
        return jobInstance;  
      }  
  
  
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext)  
            throws BeansException {  
        this.applicationContext = applicationContext;  
          
    }  
}
