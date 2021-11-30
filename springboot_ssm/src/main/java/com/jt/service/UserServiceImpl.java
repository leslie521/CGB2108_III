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
    public List<User> findAll() {

        return userMapper.findAll();
    }

    @Override
    public User findUserById(Integer id) {

        return userMapper.findUserById(id);
    }

    @Override
    public List<User> findUserByAS(User user) {

        return userMapper.findUserByAS(user);
    }

    //spring整合mybatis之后，事务自动提交
    @Override
    public void updateById(User user) {

        userMapper.updateById(user);
    }

    @Override
    public List<User> findUserByLike(String name) {

        return userMapper.findUserByLike(name);
    }

    @Override
    public List<User> findUserByIds(Integer[] ids) {

        return userMapper.findUserByIds(ids);
    }

    @Override
    public void saveUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findUserByName(name);
    }


}
