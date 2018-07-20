package com.e3expo.e3.middleware.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.RequestingUserName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.stereotype.Service;

import com.e3expo.e3.middleware.dao.RfpLinkManDao;
import com.e3expo.e3.model.RfpLinkMan;
import com.e3expo.e3.model.User;
@Service
public class RfpLinkManService {
	@Autowired
	private RfpLinkManDao rfpLinkManDao;
	/**
	 * 获取指定展装工厂的所有联系人
	 * @param map
	 * @return
	 */
    public List<RfpLinkMan> getList(User user){
    	HashMap<String, Object> map=new HashMap<String,Object>();
    	map.put("UserId", user.getId());
    	List<RfpLinkMan> list=rfpLinkManDao.getListByUserId(map);
    	return list;
    }
    /**
     * 新增联系人
     * @param model
     * @return
     */
    public int Add(User user,RfpLinkMan model) {
    	model.setUserId(user.getId());
    	int num=rfpLinkManDao.insertRfpLinkMan(model);
    	return num;
    }
    /**
     * 指定默认联系人
     * @param user
     * @param id
     */
    public void setChoosed(User user,Integer id) {
    	Map<String, Object> map=new HashMap<String,Object>();
    	map.put("UserId", user.getId());
    	map.put("Id",id); 
    	//设置其他联系人为非默认    	
    	rfpLinkManDao.updateToNotChoosed(map);
    	//设置指定联系人为默认
    	rfpLinkManDao.updateToChoosed(map);
    	 
    }
    /**
     * 删除指定联系人
     * @param user
     * @param id
     * @return
     */
    public int delete(User user,Integer id) {
    	Map<String, Object> map=new HashMap<String,Object>();
    	map.put("UserId", user.getId());
    	map.put("Id",id);
    	int num=rfpLinkManDao.updateToNotValid(map);
    	return num;
    }
    /**
     * 获取指定联系人
     * @param user
     * @param id
     * @return
     */
    public RfpLinkMan getRfpLinkMan(User user,Integer id) {
    	Map<String, Object> map=new HashMap<String,Object>();
    	map.put("UserId", user.getId());
    	map.put("Id",id);
    	RfpLinkMan model=rfpLinkManDao.selectByUserId(map);
    	return model;
    }
    /**
     * 更新联系人
     * @param user
     * @param model
     * @return
     */
    public int updateById(User user,RfpLinkMan model) {
    	model.setUserId(user.getId());
    	int num=rfpLinkManDao.updateById(model);
    	return num;
    }
}
