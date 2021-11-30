package com.jt.test.controller;

import com.jt.test.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private User user;

    @RequestMapping("/getUser")
    public User getUser(){
        return user;
    }
}
