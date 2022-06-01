package com.myjava.controller;

import com.myjava.domain.AjaxResult;
import com.myjava.domain.Menu;
import com.myjava.domain.PageListRes;
import com.myjava.domain.QueryVo;
import com.myjava.service.MenuService;
import org.apache.shiro.util.PermissionUtils;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    MenuService menuService;
    @RequestMapping("/getMenuList")
    @ResponseBody
    public PageListRes getMenuList(QueryVo vo){
        return menuService.getMenuList(vo);
    }

    @RequestMapping("/getParentMenuList.action")
    @ResponseBody
    public List<Menu> getParentMenuList(){
        return menuService.getParentMenuList();
    }

    @RequestMapping("/addMenu.action")
    @ResponseBody
    public AjaxResult addMenu(Menu menu){
        return menuService.addMenu(menu);
    }

    @RequestMapping("/updateMenu.action")
    @ResponseBody
    public AjaxResult updateMenu(Menu menu){
        return menuService.updateMenu(menu);
    }

    @RequestMapping("/deleteMenu")
    @ResponseBody
    public AjaxResult deleteMenu(Long id){
        return menuService.deleteMenu(id);
    }

    @RequestMapping("/getMenuTree")
    @ResponseBody
    public List<Menu> getMenuTree(){
        Menu m  = menuService.getMenuTree();
        List<Menu> menus = new ArrayList<>();
        menus.add(m);
        return menus;
    }

    public void doPermissionCheck(){

    }
}
