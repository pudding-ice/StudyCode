package com.myjava.dao;

import com.myjava.domain.User;

public interface UserDao {
    int addUser(User user);
    User getUser(User user);
    int queryUserByName(String username);
}
