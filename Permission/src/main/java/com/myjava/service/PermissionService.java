package com.myjava.service;

import com.myjava.domain.Permission;

import java.util.List;

public interface PermissionService {
    List<Permission> getPermissionList();
    List<Permission> getPermissionByRid(Long rid);
}
