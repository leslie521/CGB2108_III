package com.jt.service;

import com.jt.pojo.User;

import java.util.List;


public interface AxiosService {

    void saveUser(User user);

    List<User> findUserList();

    void updateUser(User user);

    void deleteUser(Integer id);
}
