package com.myjava.controller;

import com.github.pagehelper.PageHelper;
import com.myjava.domain.AjaxResult;
import com.myjava.domain.PageListRes;
import com.myjava.domain.QueryVo;
import com.myjava.domain.Role;
import com.myjava.service.EmployeeService;
import com.myjava.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.crypto.interfaces.PBEKey;
import java.util.List;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequestMapping("/getRoleList")
    @ResponseBody
    public PageListRes getRoleList(QueryVo vo){
        PageListRes roleList = roleService.getRoleList(vo);
        return roleList;
    }
    @RequestMapping("/getAllRoleList")
    @ResponseBody
    public List<Role> getAllRoleList(){
        List<Role> allRoleList = roleService.getAllRoleList();
        return allRoleList;
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public AjaxResult deleteRole(@RequestParam("rid")Long rid){
        AjaxResult result = roleService.deleteRole(rid);
        return result;
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public AjaxResult addRole(Role role){
        AjaxResult result = roleService.addRole(role);
        return result;
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public AjaxResult updateRole(Role role){
        AjaxResult result = roleService.updateRole(role);
        return result;
    }

}
