package com.myjava.service;

import com.myjava.dao.UserDaoImpl;
import com.myjava.domain.User;

public class UserServiceImpl implements UserService{
    private UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public int addUser(User user) {
        //把用户存到数据库中
        int i = userDao.addUser(user);
        return i;
    }

    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    @Override
    public int queryUserByName(String username) {
        int res = userDao.queryUserByName(username);
        return res;
    }
}
