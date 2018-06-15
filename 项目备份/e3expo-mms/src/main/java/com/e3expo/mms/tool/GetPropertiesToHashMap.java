package com.e3expo.mms.tool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

public class GetPropertiesToHashMap {



	public static HashMap<String, String> getMap(String fileName)  {

	       ClassPathResource resource = new ClassPathResource(fileName);

	        Properties properties = new Properties();

	        if ( resource.exists() ) {

	        	try {

		        	properties.load(resource.getInputStream());
			        HashMap<String, String> testMap = new HashMap<String, String>();


			        // 返回Properties中包含的key-value的Set视图
			        Set<Entry<Object, Object>> set = properties.entrySet();
			        // 返回在此Set中的元素上进行迭代的迭代器
			        Iterator<Map.Entry<Object, Object>> it = set.iterator();
			        String key = null, value = null;
			        // 循环取出key-value
			        while (it.hasNext()) {

			            Entry<Object, Object> entry = it.next();

			            key = String.valueOf(entry.getKey());
			            value = String.valueOf(entry.getValue());

			            key = key == null ? null : key.trim();
			            value = value == null ? null : value.trim();
			            // 将key-value放入map中
			            LOGGER.info("Key:" + key);
			            LOGGER.info("Value: " + value);

			            testMap.put(key, value);
			        }

		        	return testMap;

	        	} catch ( IOException e) {
	        		LOGGER.error(e.getMessage());
	        		return null;
	        	}

	        } else {

	        	return null;
	        }

	}


	private static final Logger LOGGER = LogManager.getLogger(GetPropertiesToHashMap.class.getName());


}
