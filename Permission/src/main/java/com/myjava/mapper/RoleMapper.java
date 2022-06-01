package com.myjava.mapper;

import com.myjava.domain.Role;

import java.util.List;

public interface RoleMapper {
    int addOneRole(Role role);
    List<Role> getAllRole();
    List<Role> getRoleByPrimaryKey(Long rid);
    int deleteRole(Long rid);
    int updateRolePermissionRel(Role role);
    int deleteRolePermissionRel(Long rid);
    List<Role> getRolesByEid(Long eid);
}
