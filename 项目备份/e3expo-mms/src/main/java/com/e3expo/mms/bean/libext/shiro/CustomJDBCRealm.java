package com.e3expo.mms.bean.libext.shiro;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.util.JdbcUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class CustomJDBCRealm extends JdbcRealm {
	
	
	
	@Autowired
	public void setDataSource(DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}

	protected Set<String> getRoleNamesForUser(Connection conn, String username) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Set<String> roleNames = new LinkedHashSet<String>();
		try {
			ps = conn.prepareStatement(userRolesQuery);
			ps.setString(1, username);

			// Execute query
			rs = ps.executeQuery();

			// Loop over results and add each returned role to a set
			while (rs.next()) {

				String roleName = rs.getString(1);

				// Add the role to the list of names if it isn't null
				if (roleName != null) {
					roleNames.add(roleName);
				} else {
					if (LOGGER.isWarnEnabled()) {
						LOGGER.warn("Null role name found while retrieving role names for user [" + username + "]");
					}
				}
			}
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(ps);
		}
		return roleNames;
	}

//	protected Set<String> getPermissions(Connection conn, String username, Collection<String> roleNames)
//			throws SQLException {
//		PreparedStatement ps = null;
//		Set<String> permissions = new LinkedHashSet<String>();
//		try {
//			ps = conn.prepareStatement(permissionsQuery);
//			for (String roleName : roleNames) {
//
////				ps.setString(1, roleName);
//
//				ResultSet rs = null;
//
//				try {
//					// Execute query
//					rs = ps.executeQuery();
//
//					// Loop over results and add each returned role to a set
//					while (rs.next()) {
//
//						String permissionString = rs.getString(1);
//
//						// Add the permission to the set of permissions
//						permissions.add(permissionString);
//					}
//				} finally {
//					JdbcUtils.closeResultSet(rs);
//				}
//
//			}
//		} finally {
//			JdbcUtils.closeStatement(ps);
//		}
//
//
//		try {
//			ps = conn.prepareStatement(userPermissionQuery);
////				ps.setString(1, username);
//
//				ResultSet rs = null;
//
//				try {
//					// Execute query
//					rs = ps.executeQuery();
//
//					// Loop over results and add each returned role to a set
//					while (rs.next()) {
//
//						String permissionString = rs.getString(1);
//
//						// Add the permission to the set of permissions
//						permissions.add(permissionString);
//					}
//				} finally {
//					JdbcUtils.closeResultSet(rs);
//				}
//
//		} finally {
//			JdbcUtils.closeStatement(ps);
//		}
//
//
//		return permissions;
//	}


	protected String authenticationQuery = DEFAULT_AUTHENTICATION_QUERY;
	protected String userRolesQuery = DEFAULT_USER_ROLES_QUERY;
	protected String permissionsQuery = DEFAULT_PERMISSIONS_QUERY;
	protected String userPermissionQuery = DEFAULT_USER_PERMISSIONS_QUERY;
	protected boolean permissionsLookupEnabled = true;
	protected SaltStyle saltStyle = SaltStyle.NO_SALT;

	public static final String DEFAULT_AUTHENTICATION_QUERY = "select password from user where phone_number = ? and is_resigned = 0";
	public static final String DEFAULT_USER_ROLES_QUERY = "select a.name as role_name from role a, user b, user_role c"
			+ " where b.phone_number = ? "
			+ " and c.role_id = a.id and b.id = c.user_id ";

//	private static final String DEFAULT_PERMISSIONS_QUERY = "select c.name as permission from rolePermissions a, "
	//			+ "	where  c.id = a.permissionId and b.id = a.roleId and " + "	  b.name = ?";
//			+ " roles b, permissions c "

	public static final String DEFAULT_PERMISSIONS_QUERY = "select 'all permissions' as permission";

//	private static final String DEFAULT_USER_PERMISSIONS_QUERY = "select c.name as permission from userPermissions a,  "
//			+ " users b, permissions c "
//			+ " where c.id = a.	permissionId and b.id = a.userId and " + " b.username = ?";

	public static final String DEFAULT_USER_PERMISSIONS_QUERY = "select 'all permissions' as permission";

	public static final Logger LOGGER = LogManager.getLogger(CustomJDBCRealm.class.getName());
	

}
