package com.e3expo.mms.bean.dao;


import com.e3expo.mms.bean.mapper.DownloadMapper;
import com.e3expo.mms.bean.model.Application;
import com.e3expo.mms.bean.param.DownloadListParam;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

import java.util.List;
import static com.e3expo.mms.bean.enumeration.ApplicationStatusEnum.*;
@Repository
@EnableCaching
public class DownloadDao {

	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
		this.mapper = this.sqlSession.getMapper(DownloadMapper.class);
	}

	private DownloadMapper mapper;
	private SqlSessionTemplate sqlSession;


	public List<Application> pageConditionQuerySysAdminDownloadList() {
		return mapper.pageConditionQuerySysAdminDownloadList();
	}

	/**
	 * 分页查询用户下载列表
	 * @param param 参数
	 * @return 下载列表
	 */
	public List<Application> pageConditionQueryDownloadList(DownloadListParam param) {
		return mapper.pageConditionQueryDownloadList(param);
	}

	/**
	 * 分页查询用户下载列表总数
	 * @param param 参数
	 * @return 下载列表
	 */
	public int getDownloadListTotalNumber(DownloadListParam param) {
		return mapper.getDownloadListTotalNumber(param);
	}

	/**
	 * 检查该用户是否可以下载文件
	 * @param userId   用户id
	 * @param designId 设计图id
	 * @return 可以，返回true，反之，false。
	 */
	public boolean checkUserHasDownloadRight(int userId, int designId) {
	    byte status = mapper.getApplicationStatus(userId, designId);
		return status == APPROVED.getValue() || status == DEFAULT_APPROVED.getValue();
	}
}
