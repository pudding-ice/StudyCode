package com.myjava.mapper;

import com.myjava.domain.Menu;
import com.myjava.domain.Permission;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    Menu getParentById(Long id);

    Menu getMenuTree();

    List<Menu> selectChildrenByPid(Long pid);

    Permission selectPermissionById(Long id);
}