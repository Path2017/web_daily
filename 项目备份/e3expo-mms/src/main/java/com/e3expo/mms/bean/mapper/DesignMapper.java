package com.e3expo.mms.bean.mapper;

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
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DesignMapper {

    /**
     * 查询行业字典
     *
     * @return 行业字典
     */
    List<DesignProfession> getDesignProfessions();

    /**
     * 查询结构字典
     *
     * @return 结构字典
     */
    List<DesignStructure> getDesignStructures();

    /**
     * 展示类型字典
     *
     * @return 展示类型字典
     */
    List<ExhibitionType> getExhibitionTypes();

    /**
     * 单条插入并且回写主键
     *
     * @param design design
     */
    void singleInsert(Design design);

    /**
     * 单条插入示意图并且回写主键
     *
     * @param sketch sketch
     */
    void singleInsertSketch(DesignSketch sketch);

    /**
     * 更新指定design的ossKey
     *
     * @param zipOssKey ossKey
     * @param id        id
     */
    void updateZipFileOSSKey(@Param("ossKey") String zipOssKey,
                             @Param("id") int id);

    /**
     * 分页条件查询
     *
     * @param param 参数
     * @return 分页条件查询结果
     */
    List<Design> pageConditionQuery(DesignListParam param);

    /**
     * 分页条件查询总条目数
     *
     * @param param 参数
     * @return 分页条件查询结果总条目数
     */
    int getDesignTotalNumberByParam(DesignListParam param);

    /**
     * 查询指定设计图
     *
     * @param designId designID
     * @return design
     */
    Design getDesignDetailById(int designId);

    /**
     * 插入到历史表中
     *
     * @param history history
     */
    void insertIntoDesignHistory(DesignHistory history);

    /**
     * 获取设计拥有者所有城市信息
     *
     * @param designId designId
     * @return 设计拥有者所有城市信息
     */
    City getDesignOwnerCity(int designId);

    /**
     * 查看数自增 1
     *
     * @param designId 设计图id
     */
    void increaseViews(int designId);

    /**
     * 分页查询设计图的历史
     *
     * @param param param
     * @return 分页查询结果
     */
    List<DesignHistory> pageQueryDesignHistory(DesignHistoryListParam param);

    /**
     * 根据ID查询sketch
     *
     * @param sketchId
     * @return 指定sketch
     */
    DesignSketch getSketchById(int sketchId);

    /**
     * 根据designId查询设计图列表
     *
     * @param designId 设计图id
     * @return 设计图列表
     */
    List<DesignSketch> getSketchListByDesignId(int designId);

    /**
     * 设置指定sketch是否为封面
     *  @param sketchId sketchId
     * @param isCover  是否为封面
     * @param l
     */
    void setSketchIsCover(@Param("sketchId") int sketchId,
                          @Param("isCover") byte isCover,
                          @Param("modifiedTime") long l);

    /**
     * 查询指定设计的所有操作历史总记录数
     *
     * @param designId designId
     * @return 总记录数
     */
    int getCountOfDesignHistory(int designId);


    /**
     * 设置指定设计图是否可见
     *  @param designId  设计图id
     * @param isVisible 是否可见
     * @param l
     */
    void setDesignIsVisible(@Param("designId") int designId,
                            @Param("isVisible") byte isVisible,
                            @Param("modifiedTime") long l);

    /**
     * 修改指定设计图的状态
     *  @param designID 设计id
     * @param status   状态
     * @param l
     */
    void setDesignStatus(@Param("designId") int designID,
                         @Param("status") byte status,
                         @Param("modifiedTime") long l);

    /**
     * 修改指定示意图的状态
     *  @param sketchId 设计id
     * @param status   状态
     * @param l
     */
    void setSketchStatus(@Param("sketchId") int sketchId,
                         @Param("status") byte status,
                         @Param("modifiedTime") long l);

    /**
     * 增加下载次数
     * @param designId 设计Id
     */
    void increaseDownloads(int designId);

    void modifyDesign(DesignParam param);


    /**
     * 更新压缩包文件名
     *
     * @param designId      设计图id
     * @param primitiveName 压缩包文件原名
     * @param modifiedTime  更新时间
     */
    void updateZipFileName(@Param("designId")int designId,
                           @Param("primitiveName") String primitiveName,
                           @Param("modifiedTime") long modifiedTime);

    /**
     * 查询除了系统管理员创建的其他本地设计图
     * @param cityID city id
     * @return 设计图列表
     */
    List<Design> getLocalDesignsExceptSysAdminCreated(byte cityID);

    /**
     * 更新封面图OSS key
     * @param sketchId
     * @param sketchOssKey
     */
    void updateSketchFileOssKey(int sketchId, String sketchOssKey);
}