package com.jt.controller;

import com.jt.pojo.User;
import com.jt.service.AxiosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/axios")//公共部分请求的抽取前缀
public class AxiosController {

    @Autowired
    private AxiosService axiosService;

    /**
     * 实现用户入库操作
     * url地址: http://localhost:8090/axios/saveUser
     * 参数:  JSON串 {"name":"tomcat","age":18,"sex":"女"}
     * 返回值: 成功消息
     * 难点:  前端传递的是JSON,后端不可以直接使用User对象接收.
     * 解决方案:
     *       1.对象可以转化为JSON串  @ResponseBody
     *       2.JSON串转化为对象     @RequestBody
     */
    @PostMapping("/saveUser")
    public String saveUser(@RequestBody User user){
        axiosService.saveUser(user);
        return "用户新增成功";
    }

    /**
     * 需求: 查询所有的用户信息
     * URL: /axios/findUserList
     * 返回值: List<User>
     */
    @GetMapping("/findUser")
    public List<User> findUserList(){

        return axiosService.findUserList();
    }

    /**
     * 用户修改操作
     * URL: /axios/updateUser
     * 参数: JSON串
     * 返回值: String
     */
    @PutMapping("/updateUser")
    public String updateUser(@RequestBody User user){
        axiosService.updateUser(user);
        return "数据修改成功";
    }

    /**
     * 用户删除操作
     * URL: /axios/deleteUser
     * 参数:  id
     * 返回值: String
     */
    @DeleteMapping("/deleteUser")
    public String deleteUser(Integer id){

        axiosService.deleteUser(id);
        return "用户删除成功";
    }

}
