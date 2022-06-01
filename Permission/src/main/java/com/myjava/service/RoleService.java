package com.myjava.service;

import com.myjava.domain.AjaxResult;
import com.myjava.domain.PageListRes;
import com.myjava.domain.QueryVo;
import com.myjava.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {
    PageListRes getRoleList(QueryVo vo);
    AjaxResult deleteRole(Long rid);
    AjaxResult addRole(Role role);
    AjaxResult updateRole(Role role);
    List<Role> getAllRoleList();
}
