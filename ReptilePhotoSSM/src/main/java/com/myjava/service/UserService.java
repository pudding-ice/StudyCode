package com.myjava.service;

import com.myjava.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    User userLogin(User user);
    Boolean userRegister(User user);
}
