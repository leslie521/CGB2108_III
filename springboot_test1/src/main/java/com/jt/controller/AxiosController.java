package com.jt.controller;

import com.jt.mapper.AxiosMapper;
import com.jt.pojo.User;
import com.jt.service.AxiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/axios")
public class AxiosController {

    @Autowired
    private AxiosService axiosService;

    @GetMapping("/findUserList")
    public List<User> findUser(){
        return axiosService.findUser();
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user){
        axiosService.saveUser(user);
        return "数据新增成功";
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        axiosService.updateUser(user);
        return "数据修改成功";
    }

    @DeleteMapping("/deleteUser")
    public String deleteUser(Integer id){
        axiosService.deleteUser(id);
        return "数据删除成功";
    }
}
