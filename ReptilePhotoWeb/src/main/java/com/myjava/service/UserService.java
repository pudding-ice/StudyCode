package com.myjava.service;

import com.myjava.domain.User;

public interface UserService {
    /**
     * 添加用户到数据库中
     * @param user 要添加的用户
     * @return 如果是1 添加成功, 否则添加失败
     */
    int addUser(User user);

    /**
     * 根据一个user返回一个user
     * @param user
     * @return
     */
    User getUser(User user);

    /**
     * 根据username查找数据库中是否有这个字段
     * @param username
     * @return 如果找到,返回1 否则返回0
     */
    int queryUserByName(String username);
}
