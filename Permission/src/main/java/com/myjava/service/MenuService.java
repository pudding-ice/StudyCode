package com.myjava.service;


import com.myjava.domain.AjaxResult;
import com.myjava.domain.Menu;
import com.myjava.domain.PageListRes;
import com.myjava.domain.QueryVo;

import java.util.List;

public interface MenuService {
    PageListRes getMenuList(QueryVo vo);

    List<Menu> getParentMenuList();

    AjaxResult addMenu(Menu menu);

    AjaxResult updateMenu(Menu menu);

    AjaxResult deleteMenu(Long id);

    Menu getMenuTree();
}
