package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findUserById(Integer id);

    List<User> findUserByAS(User user);

    void updateById(User user);

    List<User> findUserByLike(String name);

    List<User> findUserByIds(Integer[] ids);

    void saveUser(User user);


    User findUserByName(String name);
}
