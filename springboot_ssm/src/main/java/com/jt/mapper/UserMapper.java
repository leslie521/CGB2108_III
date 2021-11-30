package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

//@Mapper//将Mapper接口交给Spring容器管理
public interface UserMapper {

    List<User> findAll();

//    @Select("select * from demo_user where id = #{id}")
    User findUserById(Integer id);
    @Select("select * from demo_user where age = #{age} and sex = #{sex}")
    List<User> findUserByAS(User user);
    @Update("update demo_user set name=#{name},age=#{age},sex=#{sex} where id=#{id}")
    void updateById(User user);
//    @Select("select * from demo_user where name like \"%\"#{name}\"%\" ")
    List<User> findUserByLike(String name);

    List<User> findUserByIds(Integer[] ids);

    void saveUser(User user);


    User findUserByName(String name);
}
