package com.zhengguan.crush.dao;

import com.zhengguan.crush.entity.Role;

public interface RoleDao {
    public Role getRole(String username);

    public Role findRoleByName(String roleName);
}
