package com.myjava.service;

import com.myjava.domain.User;
import com.myjava.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;

    @Override
    public User userLogin(User user){
        List<User> userList = userMapper.getUser(user);
        if (userList.isEmpty() || userList == null){
            return new User();
        }else {
            return userList.get(0);
        }
    }
    @Override
    public Boolean userRegister(User user){
        List<User> userList = userMapper.getUser(new User());
        Iterator<User> it = userList.iterator();
        String username = user.getUsername();
        while (it.hasNext()) {
            User next = it.next();
            //找到重复名字,返回false
            if (next.getUsername().equals(username)) {
                return false;
            }
        }
        //没有重复,返回之前持久化到数据库
        userMapper.addUser(user);
        return true;
    }
}
