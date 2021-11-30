package com.jt.mapper;

import com.jt.pojo.User;

import java.util.List;

public interface AxiosMapper {
    void saveUser(User user);

    List<User> findUserList();

    void updateUser(User user);

    void deleteUser(Integer id);
}
