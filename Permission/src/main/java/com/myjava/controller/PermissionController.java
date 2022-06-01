package com.myjava.controller;

import com.myjava.domain.Permission;
import com.myjava.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @RequestMapping("/getPermissionList")
    @ResponseBody
    public List<Permission> getPermissionList(){
        List<Permission> permissionList = permissionService.getPermissionList();
        return permissionList;
    }

    @RequestMapping("/getPermissionByRid")
    @ResponseBody
    public List<Permission> getPermissionByRid(@RequestParam("rid") Long rid){
        List<Permission> permissionByRid = permissionService.getPermissionByRid(rid);
        return permissionByRid;
    }
}
