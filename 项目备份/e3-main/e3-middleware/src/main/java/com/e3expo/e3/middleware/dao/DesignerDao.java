package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.middleware.mapper.DesignerMapper;
import com.e3expo.e3.model.Designer;
import com.e3expo.e3.model.DesignerConfig;
import com.e3expo.e3.model.DesignerModel;
import com.e3expo.e3.model.User;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DesignerDao {

    private DesignerMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(DesignerMapper.class);
    }

    /**
     * 单条插入designer，并回写主键
     *
     * @return 影响条数
     */
    public int insert(Designer designer) {
        return mapper.insertSelective(designer);
    }
    
    /**
     * 根据设计师类型查询设计师
     * @param types
     * @return
     */
    public List<DesignerModel> selectDesignerByType(Integer[] types){
    	 List<DesignerModel> list = mapper.selectDesignerByType(types);
    	 return list;
    }
    
    /**
     * 根据设计师级别查询设计师
     * @param types
     * @return
     */
    public List<DesignerModel> selectDesignerByLevel(int level){
    	 List<DesignerModel> list = mapper.selectDesignerByLevel(level);
    	 return list;
    }
    
    /**
     * 查询某级别以上的设计师级别
     * @param level
     * @return
     */
    public List<DesignerConfig> selectDesignerLevelUp(int level){
    	return mapper.selectDesignerLevelUp(level);
    }
}
