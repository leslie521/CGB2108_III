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

    @GetMapping("/findAll")
    public List<User> findAll(){

        return userService.findAll();
    }

    /**
     * URL地址: http://localhost:8090/findUserById?id=526
     * */
    @GetMapping("/findUserById")
    public User findUserById(Integer id){

        return userService.findUserById(id);
    }

    /**
     * URL地址: http://localhost:8090/findUserByAS?age=18&sex=女
     * */
    @GetMapping("/findUserByAS")
    public List<User> findUserByAS(User user){

        return userService.findUserByAS(user);
    }

    /**
     * URL地址: http://localhost:8090/updateById?id=1&name=黑熊精&age=3000&sex=男
     * restFul结构传参数  参数分析{属性名称}
     *   参数接收：
     *      1.使用注解 @PathVariable
     *      2.如果多个参数接收 使用对象 MVC自动提供的功能。
     * URL地址: http://localhost:8090/updateById/1/黑熊精/3000/男
     * 参数：4个
     * 返回值："修改成功！！！"
     * */
//    @RequestMapping(value = "/updateById/{id}/{name}/{age}/{sex}",
//                    method = RequestMethod.PUT)
    @PutMapping("/updateById/{id}/{name}/{age}/{sex}")
    public String updateById(User user){

        userService.updateById(user);
        return "数据修改成功";
    }

    /*模糊查询 查询name中包含 "乔"的用户.
      http://localhost:8090/findUserByLike?name=乔 返回list集合*/
    @GetMapping("/findUserByLike")
    public List<User> findUserByLike(String name){

        return userService.findUserByLike(name);
    }

    /*批量查询 查询id=1,3,5,7的数据
      http://localhost:8090/findUserByIds?ids=1,3,5,7 返回list集合*/
    @GetMapping("/findUserByIds")
    public List<User> findUserByIds(Integer[] ids){

        return userService.findUserByIds(ids);
    }

    /*实现用户新增
      http://localhost:8090/saveUser/悟空/8000/男 返回成功的提示即可*/
    @PostMapping("/saveUser/{name}/{age}/{sex}")
    public String saveUser(User user){
        userService.saveUser(user);
        return "添加成功";
    }

    /*实现用户查询
    * http://localhost:8090/findUserByName?name=孙尚香*/
    @GetMapping("/findUserByName")
    public User findUserByName(String name){
        return userService.findUserByName(name);
    }
}
