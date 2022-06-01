package com.myjava.mapper;

import com.myjava.domain.User;

import java.util.List;

public interface UserMapper {
    int addUser(User user);

    /***
     * 传入一个user对象,查询数据库中是否有user
     * @param user 前端用户输入的user信息封装成的对象
     * @return 返回user对象,通过对象中的u_id是否为null来判断是否查询成功
     */
    List<User> getUser(User user);

}
