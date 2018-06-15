package com.e3expo.mms.bean.dao;


import com.e3expo.mms.bean.mapper.DesignMapper;
import com.e3expo.mms.bean.model.City;
import com.e3expo.mms.bean.model.Design;
import com.e3expo.mms.bean.model.DesignHistory;
import com.e3expo.mms.bean.model.DesignProfession;
import com.e3expo.mms.bean.model.DesignSketch;
import com.e3expo.mms.bean.model.DesignStructure;
import com.e3expo.mms.bean.model.ExhibitionType;
import com.e3expo.mms.bean.param.DesignHistoryListParam;
import com.e3expo.mms.bean.param.DesignListParam;
import com.e3expo.mms.bean.param.DesignParam;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.e3expo.mms.bean.enumeration.BooleanStatusEnum.*;
import static com.e3expo.mms.bean.enumeration.ModelStatusEnum.DELETED;
import static com.e3expo.mms.bean.enumeration.ModelStatusEnum.VALID;

@Repository
@EnableCaching
public class DesignDao {


    /**
     * 查询设计图展示类型字典
     *
     * @return 展示类型字典
     */
    public List<ExhibitionType> getExhibitionTypes() {
        return mapper.getExhibitionTypes();
    }

    /**
     * 查询设计图结构字典
     *
     * @return 设计图结构字典
     */
    public List<DesignStructure> getDesignStructures() {
        return mapper.getDesignStructures();
    }

    /**
     * 查询设计图行业字典
     *
     * @return 设计图行业字典
     */
    public List<DesignProfession> getDesignProfessions() {
        return mapper.getDesignProfessions();
    }

    @Autowired
    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
        this.mapper = this.sqlSession.getMapper(DesignMapper.class);
    }

    private DesignMapper mapper;
    private SqlSessionTemplate sqlSession;


    /**
     * 插入到design表并且返回主键
     *
     * @param design design
     */
    public void singleInsert(Design design) {
        mapper.singleInsert(design);
    }

    /**
     * 插入到design 示意图表并且返回主键
     *
     * @param sketch design
     */
    public void singleInsertSketch(DesignSketch sketch) {
        mapper.singleInsertSketch(sketch);
    }

    /**
     * 更新压缩包的OSS ket
     *
     * @param zipOssKey oss key
     * @param id        id
     */
    public void updateZipFileOSSKey(String zipOssKey, int id) {
        mapper.updateZipFileOSSKey(zipOssKey, id);
    }

    /**
     * 分页条件查询
     *
     * @param param 参数
     * @return 分页条件查询结果
     */
    public List<Design> pageConditionQuery(DesignListParam param) {
        return mapper.pageConditionQuery(param);
    }

    /**
     * 分页条件查询同条目数
     *
     * @param param 参数
     * @return 分页条件查询结果
     */
    public int getDesignTotalNumberByParam(DesignListParam param) {
        return mapper.getDesignTotalNumberByParam(param);
    }

    /**
     * 查询指定设计图
     *
     * @param designId designID
     * @return design
     */
    public Design getDesignDetailById(int designId) {
        Design target = mapper.getDesignDetailById(designId);
        target.setSketchList(target.getSketchList().stream()
                .filter(designSketch -> designSketch.getStatus() == VALID.getValue())
                .collect(Collectors.toList()));
        return target;
    }

    /**
     * 插入单条记录到历史表中
     *
     * @param history history
     */
    public void insertIntoDesignHistory(DesignHistory history) {
        mapper.insertIntoDesignHistory(history);
    }

    /**
     * 获取设计图拥有者所在城市
     *
     * @param designId 设计图id
     * @return city
     */
    public City getDesignOwnerCity(int designId) {
        return mapper.getDesignOwnerCity(designId);
    }


    /**
     * 查看数自增 1
     *
     * @param designId 设计图id
     */
    public void increaseViews(int designId) {
        mapper.increaseViews(designId);
    }

    public List<DesignHistory> pageQueryDesignHistory(DesignHistoryListParam param) {
        return mapper.pageQueryDesignHistory(param);
    }

    public DesignSketch getSketchById(int sketchId) {
        return mapper.getSketchById(sketchId);
    }

    public List<DesignSketch> getSketchListBySketchId(int sketchId) {
        DesignSketch sketch = mapper.getSketchById(sketchId);
        return mapper.getSketchListByDesignId(sketch.getDesignID());
    }

    public void changeCover(int newCoverId, int oldCoverId) {
        mapper.setSketchIsCover(newCoverId, TRUE.getValue(), System.currentTimeMillis());
        mapper.setSketchIsCover(oldCoverId, FALSE.getValue(), System.currentTimeMillis());
    }

    /**
     * 查询指定设计的所有操作历史总记录数
     *
     * @param designId designId
     * @return 总记录数
     */
    public int getCountOfDesignHistory(int designId) {
        return mapper.getCountOfDesignHistory(designId);
    }

    /**
     * 设置指定设计图是否可见
     *
     * @param designId  设计图id
     * @param isVisible 是否可见
     */
    public void setDesignIsVisible(int designId, byte isVisible) {
        mapper.setDesignIsVisible(designId, isVisible, System.currentTimeMillis());
    }

    /**
     * 检查指定设计是否存在
     *
     * @param designId 设计id
     * @return 如果存在返回true，反之返回false
     */
    public boolean checkDesignExist(int designId) {
        return mapper.getDesignDetailById(designId) != null;
    }

    /**
     * 删除指定设计，同时删除
     *
     * @param designID 设计id
     */
    public void deleteDesignById(int designID) {
        mapper.setDesignStatus(designID, DELETED.getValue(), System.currentTimeMillis());
    }

    /**
     * 删除指定示意图
     *
     * @param sketchId 设计id
     */
    public void deleteSketchById(int sketchId) {
        mapper.setSketchStatus(sketchId, DELETED.getValue(), System.currentTimeMillis());
    }


    /**
     * 增加下载次数
     * @param designId 设计Id
     */
    public void increaseDownloads(int designId) {
        mapper.increaseDownloads(designId);
    }

    public void modifyDesign(DesignParam param) {
        param.setModifiedTime(System.currentTimeMillis());
        mapper.modifyDesign(param);
    }

    /**
     * 更新zipfile 原始文件名
     * @param designId         design id
     * @param originalFilename 原文件名
     */
    public void updateZipFileName(int designId, String originalFilename) {
        mapper.updateZipFileName(designId, originalFilename, System.currentTimeMillis());
    }

    /**
     * 查询除了系统管理员创建的其他本地设计图
     * @param cityID city id
     * @return 设计图列表
     */
    public List<Design> getLocalDesignsExceptSysAdminCreated(byte cityID) {
        return mapper.getLocalDesignsExceptSysAdminCreated(cityID);
    }

    public void updateSketchFileOssKey(int sketchId, String appendSketchOssKey, String originalFilename) {
        mapper.updateSketchFileOssKey(sketchId, appendSketchOssKey);
    }
}
