package com.e3expo.e3.lib.property;

import java.util.HashMap;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class PropertyFileToHashMapConfiger extends PropertyPlaceholderConfigurer {
	
	
	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
			throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		
		
		
		for(Object key: props.keySet() ) {
			
			String keyString = key.toString();
			String valString = props.getProperty(keyString).toString();
			
			propertyMap.put(keyString,  valString);
			
		}
		
	}
	
	
	public String getPropertyValue(String key) {
		return propertyMap.get(key);
	}

	public HashMap<String, String> getFileMap() {
		return propertyMap;
	}
	
	private final HashMap<String, String> propertyMap = new HashMap<String, String>();
	
}
