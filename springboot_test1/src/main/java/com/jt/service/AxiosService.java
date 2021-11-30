package com.jt.service;

import com.jt.pojo.User;

import java.util.List;

public interface AxiosService {
    List<User> findUser();

    void saveUser(User user);

    void updateUser(User user);

    void deleteUser(Integer id);
}
