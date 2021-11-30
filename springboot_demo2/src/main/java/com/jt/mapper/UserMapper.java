package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    //方法查询user表中的所有的数据
    public List<User> findAll();
    //根据ID查询数据
    public User findUserById(Integer id);
    //实现数据新增
    public Integer saveUser(User user);
    //实现数据修改
    int updateUser(User user);

    int deleteUser(String name);

    List<User> findByAge(Map map);

    /**
     * 1. 规则Mapper中的接口方法，不能重名
     * 2. 关于Mybatis参数封装说明
     *    1.mybatis中只支持单值传参
     *    2.单值可以是具体的数字，字符串，对象
     *    3.多值转化为单值 首选Map集合
     *    4.@Param("minAge") int minAge将参数封装为map
     *    解析为：Map = {minAge = 100,maxAge = 1000}
     * */
    List<User> findParam(@Param("minAge") int minAge,
                         @Param("maxAge") int maxAge);

    List<User> findUserByLike(String name);
}
