package com.myjava.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.myjava.domain.AjaxResult;
import com.myjava.domain.Menu;
import com.myjava.domain.PageListRes;
import com.myjava.domain.QueryVo;
import com.myjava.mapper.MenuMapper;
import com.myjava.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;
    @Override
    public PageListRes getMenuList(QueryVo vo) {
        PageListRes pageListRes = new PageListRes();
        Page<Object> page = PageHelper.startPage(vo.getPage(),vo.getRows());
        List<Menu> menus = menuMapper.selectAll();
        PageInfo<Menu> info = new PageInfo<>(menus,vo.getPage());
        pageListRes.setRows(menus);
        pageListRes.setTotal(info.getTotal());
        return pageListRes;
    }

    @Override
    public List<Menu> getParentMenuList() {
        return menuMapper.selectAll();
    }

    @Override
    public AjaxResult addMenu(Menu menu) {
        Integer insert = menuMapper.insert(menu);
        AjaxResult ajaxResult = AjaxResult.getAjaxResult(insert,"插入成功","插入失败");
        return ajaxResult;
    }

    @Override
    public AjaxResult updateMenu(Menu menu) {
        int i = menuMapper.updateByPrimaryKey(menu);
        AjaxResult ajaxResult = AjaxResult.getAjaxResult(1, "更新成功", "更新失败");
        return ajaxResult;
    }

    @Override
    public AjaxResult deleteMenu(Long id) {
        int i = menuMapper.deleteByPrimaryKey(id);
        AjaxResult ajaxResult = AjaxResult.getAjaxResult(i, "删除成功", "删除失败");
        return ajaxResult;
    }

    @Override
    public  Menu getMenuTree() {
        return menuMapper.getMenuTree();
    }
}
