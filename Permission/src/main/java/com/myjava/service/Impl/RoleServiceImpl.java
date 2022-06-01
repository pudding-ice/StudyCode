package com.myjava.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myjava.domain.AjaxResult;
import com.myjava.domain.PageListRes;
import com.myjava.domain.QueryVo;
import com.myjava.domain.Role;
import com.myjava.mapper.RoleMapper;
import com.myjava.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public PageListRes getRoleList(QueryVo vo) {
        Page<Object> page = PageHelper.startPage(vo.getPage(),vo.getRows());
        List<Role> allRole = roleMapper.getAllRole();
        PageInfo<Role> pageInfo = new PageInfo<>(allRole,vo.getPage());
        PageListRes res = new PageListRes();
        res.setTotal(pageInfo.getTotal());
        res.setRows(allRole);
        return res;
    }

    @Override
    public AjaxResult deleteRole(Long rid) {
        //先删除关系表,再删除角色表中的角色
        roleMapper.deleteRolePermissionRel(rid);
        int i = roleMapper.deleteRole(rid);
        return AjaxResult.getAjaxResult(i,"删除成功","删除失败");
    }

    @Override
    public AjaxResult addRole(Role role) {
        int i = roleMapper.addOneRole(role);
        //添加角色后,更新关系表,先删除再插入
        roleMapper.deleteRolePermissionRel(role.getRid());
        roleMapper.updateRolePermissionRel(role);
        return AjaxResult.getAjaxResult(i,"添加角色成功,角色有至少一个权限","添加角色失败");
    }

    @Override
    public AjaxResult updateRole(Role role) {
        roleMapper.deleteRolePermissionRel(role.getRid());
        int i = roleMapper.updateRolePermissionRel(role);
        return AjaxResult.getAjaxResult(i,"更新角色成功","更新角色失败");
    }

    @Override
    public List<Role> getAllRoleList() {
        List<Role> allRole = roleMapper.getAllRole();
        return allRole;
    }
}
