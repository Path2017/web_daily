package com.e3expo.e3.middleware.dao;

import com.e3expo.e3.enumration.RfpStatusEnum;
import com.e3expo.e3.middleware.mapper.RfpMapper;
import com.e3expo.e3.model.Rfp;
import com.e3expo.e3.model.form.RfpBaseInfoForm;
import com.e3expo.e3.model.param.PageParam;
import com.e3expo.e3.model.view.WebAppRfpView;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RfpDao {

    private RfpMapper mapper;
    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private void setSqlSessionTemplate(SqlSessionTemplate template) {
        this.sqlSessionTemplate = template;
        this.mapper = this.sqlSessionTemplate.getMapper(RfpMapper.class);
    }

    /**
     * 向te_rfp中插入一条记录并且回写主键
     *
     * @param rfp
     * @return
     */
    public int insertBaseInfo(Rfp rfp) {

        return mapper.insert(rfp);
    }

    public Rfp getById(Integer rfpId) {
        return mapper.selectByPrimaryKey(rfpId);
    }

    /**
     * 更新指定Rfp的状态
     * @param rfpId rfpId
     * @param value 状态值
     */
    public int updateRfpStatusByRfpId(Integer rfpId, int value) {
        Rfp rfp = new Rfp();
        rfp.setId(rfpId);
        rfp.setStatus(value);
        rfp.setUpdateTime(System.currentTimeMillis());
        return mapper.updateByPrimaryKeySelective(rfp);
    }

    /**
     * 根据主键选择性的更新字段，为null的就不更新
     *
     * @param rfp
     */
    public void updateRfpById(Rfp rfp) {
        mapper.updateByPrimaryKeySelective(rfp);
    }

    /**
     * 分页查询保存的Rfp
     *
     * @param page
     * @param userId
     * @return
     */
    public List<Rfp> pageQuerySavedRfp(PageParam<Rfp> page, Integer userId) {
        Set<Integer> statusSet = new HashSet<>();
        statusSet.add(RfpStatusEnum.BASE_INFO_DRAFT.getValue());
        statusSet.add(RfpStatusEnum.DETAIL_DRAFT.getValue());
        return mapper.pageQueryRfpByStatus(page, userId, statusSet);
    }

    /**
     * 查询保存的rfp总数
     *
     * @param userId
     * @return
     */
    public int selectTotalSavedRfpCount(Integer userId) {
        Set<Integer> statusSet = new HashSet<>();
        statusSet.add(RfpStatusEnum.BASE_INFO_DRAFT.getValue());
        statusSet.add(RfpStatusEnum.DETAIL_DRAFT.getValue());
        return mapper.selectTotalSavedRfpCount(userId, statusSet);
    }


    public WebAppRfpView getRfpDraftById(Integer rfpId) {
        return mapper.getRfpDraftById(rfpId);
    }
}
