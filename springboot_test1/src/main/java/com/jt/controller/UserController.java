package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /*实现根据用户姓名查询数据
     * http://localhost:8090/findUserByName?name=孙尚香
       参数：1个---name
       返回值类型：User
     */
    @GetMapping("/findUserByName")
    public User findUserByName(String name){

        return userService.findUserByName(name);
    }

    /*实现根据用户姓名查询数据
     * http://localhost:8090/findUserByNA?name=悟空&age=8000
       参数：2个---name、age
       返回值类型：List集合
     */
    @GetMapping("/findUserByNA")
    public List<User> findUserByNA(User user){
        return userService.findUserByNA(user);
    }

    /*实现用户新增
      http://localhost:8090/saveUser/悟空/8000/男
      参数：对象
      返回值类型：返回成功的提示即可
     */
    @PostMapping("/saveUser/{name}/{age}/{sex}")
    public String saveUser(User user){
        userService.saveUser(user);
        return "数据返回成功";
    }
}
