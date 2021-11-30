package com.jt.service;

import com.jt.mapper.AxiosMapper;
import com.jt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AxiosServiceImpl implements AxiosService{

    @Autowired
    private AxiosMapper axiosMapper;

    @Override
    public List<User> findUser() {
        return axiosMapper.findUser();
    }

    @Override
    public void saveUser(User user) {
        axiosMapper.saveUser(user);
    }

    @Override
    public void updateUser(User user) {
        axiosMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer id) {
        axiosMapper.deleteUser(id);
    }
}
