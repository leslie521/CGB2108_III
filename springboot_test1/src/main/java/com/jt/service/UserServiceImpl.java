package com.jt.service;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByName(String name) {

        return userMapper.findUserByName(name);
    }

    @Override
    public List<User> findUserByNA(User user) {
        return userMapper.findUserByNA(user);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }
}
