package com.myjava.service.Impl;

import com.myjava.domain.Permission;
import com.myjava.mapper.PermissionMapper;
import com.myjava.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public List<Permission> getPermissionList() {
        List<Permission> permissions = permissionMapper.selectAll();
        return permissions;
    }

    @Override
    public List<Permission> getPermissionByRid(Long rid) {
        List<Permission> permissionByRid = permissionMapper.getPermissionByRid(rid);
        return permissionByRid;
    }
}
