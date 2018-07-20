package com.e3expo.e3.middleware.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.e3expo.e3.middleware.mapper.OrderMapper;
import com.e3expo.e3.middleware.mapper.SingleTimerConfigMapper;
import com.e3expo.e3.model.SingleTimerConfig;

@Repository
public class SingleTimerConfigDao {
	
	 @Autowired
	    private void setSql(SqlSessionTemplate template) {
	        this.sqlSessionTemplate = template;
	        this.mapper = this.sqlSessionTemplate.getMapper(SingleTimerConfigMapper.class);
	    }
	    
	    

	    private SingleTimerConfigMapper mapper;
	    private SqlSessionTemplate sqlSessionTemplate;
	    
	    /**
	     * 查询匹配设计师时间配置
	     * @return
	     */
	    public SingleTimerConfig selectInfo() {
	    	return mapper.selectInfo();
	    }
}
