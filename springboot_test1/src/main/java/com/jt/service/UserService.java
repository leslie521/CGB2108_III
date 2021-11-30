package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {
    User findUserByName(String name);

    List<User> findUserByNA(User user);

    void saveUser(User user);
}
