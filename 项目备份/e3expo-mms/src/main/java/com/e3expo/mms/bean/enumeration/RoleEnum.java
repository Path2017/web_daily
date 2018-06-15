package com.e3expo.mms.bean.enumeration;

/**
 * 系统角色枚举
 */
public enum RoleEnum {

    SYS_ADMIN("sys_admin", (byte) 1),
    LOCAL_ADMIN("local_admin", (byte) 2),
    DESIGNER("designer", (byte) 3);

    private final String roleName;
    private final byte roleId;

    RoleEnum(String roleName, byte roleId) {
        this.roleName = roleName;
        this.roleId = roleId;
    }

    public byte getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }
}