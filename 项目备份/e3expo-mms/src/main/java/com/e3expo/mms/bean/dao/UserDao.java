package com.e3expo.mms.bean.dao;



import com.e3expo.mms.bean.mapper.UserMapper;
import com.e3expo.mms.bean.model.User;
import com.e3expo.mms.bean.param.UserListParam;
import com.e3expo.mms.bean.param.UserParam;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.e3expo.mms.bean.enumeration.RoleEnum.*;

@Repository
@EnableCaching
public class UserDao {
	
//	@Cacheable(value="UserPage", key="#page")
	public ArrayList<User> getUserListByPage(int page) {

		int offset = (page - 1 ) * 15;
		
		ArrayList<User> users = mapper.getUserListByPage(15, offset);
		
		return users;
	}

	
	
	
//	@Cacheable(value="User")
	public int getUserTotalNumber() {
		int totalNumber = mapper.getTotalNumber();
		return totalNumber;
	}

	
	
	
//	@Cacheable(value="User", key="#id")
	public User getUserById( int id) {
		User user = mapper.getUserById(id);
		
		return user;
		
	}
	
	
	public User getUserByName(String name) {
		
		int id = getUserIdByName(name);
		User user = getUserById(id);
		
		return user;
		
	}
	
	
	public int getUserIdByName(String name) {
		
		return mapper.getUserIdByName(name);
		
	}
	
	
//	@CacheEvict(value="User", key="#id")
	public void updatePassword(int id, String password) {
		mapper.updatePassword(id, password);
		
	}


	
	

	public boolean checkExistsUserByName(String usernameString) {

		if ( mapper.getCountByUserName(usernameString) > 0  ) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 新建用户,	插入user_role 关系表
	 *
	 * @param user 用户信息
	 */
	public void createUser(User user) {
		mapper.insertUser(user);
		mapper.insertIntoUserRole(user.getId(), user.getRole().getId(), System.currentTimeMillis());
	}

	/**
	 * 获取条件查询结果总条目数
	 *
	 * @param param 条件参数
	 * @return 符合条件的总条目数
	 */
	public int getUserTotalNumberByParam(UserListParam param) {
		return mapper.getUserTotalNumberByParam(param);
	}


	/**
	 * 分页条件查询用户列表
	 *
	 * @param param param
	 * @return 分页条件数据
	 */
	public List<User> pageConditionQuery(UserListParam param) {
		return mapper.pageConditionQuery(param);
	}

	@Autowired
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
		this.mapper = this.sqlSession.getMapper(UserMapper.class);
	}
	
	private UserMapper mapper;
	private SqlSessionTemplate sqlSession;


	/**
	 * 更新用户信息。
	 *
	 * @param param 参数
	 */
	public void updateUserInfo(UserParam param) {
		param.setModifiedTime(System.currentTimeMillis());
		mapper.updateUserInfo(param);
	}

	/**
	 * 查询出系统管理员用户ID
	 *
	 * @return
	 */
	public int getSystemUserId() {
		return mapper.getSystemUserId();
	}

	/**
	 * 根据城市ID查询该城市的管理员
	 * @param cityId 城市ID
	 */
	public List<User> getLocalAdminsByCityId(byte cityId) {
		return mapper.getUsersByCityIdAndRoleName(cityId, LOCAL_ADMIN.getRoleName());
	}

	public boolean checkExistsUserByPhoneNumber(String phoneNumber) {
		User result = mapper.getUserByPhoneNumber(phoneNumber);
		return result != null;
	}
}
