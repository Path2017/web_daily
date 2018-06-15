package com.e3expo.mms.bean.mapper;

import com.e3expo.mms.bean.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
	
	Role getRolebyId(int id);

	/**
	 * 查询所有角色信息
	 *
	 * @return 角色列表
	 */
	List<Role> getAllRoles();

	/**
	 * 更新指定用户的
	 *
	 * @param userID userID
	 * @param roleID roleID
	 */
	void updateUserRoleByUserID(@Param("userID") int userID,
								@Param("userID") int roleID);

	/**
	 * 根据用户查询角色ID
	 *
	 * @param userID user id
	 * @return role id
	 */
	int getRoleIDByUserID(int userID);
}
