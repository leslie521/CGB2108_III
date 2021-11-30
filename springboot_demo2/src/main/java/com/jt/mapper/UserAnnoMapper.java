package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserAnnoMapper {
    /**
     * 注解使用规则：
     *  1.注解表示接口方法， 接口方法调用，直接注解的内容
     *  2.注解将查询的结果集，根据方法的返回值类型(例如:User)动态映射。
     * */
    //查询user表的数据记录
    @Select("select * from demo_user")
    List<User> findAll();

    @Insert("insert into demo_user(id,name,age,sex) values(null,#{name},#{age},#{sex})")
    Integer testInsert(User user);

    @Update("update demo_user set id=#{id} where name=#{name}")
    int testUpdate(User user);

    @Delete("delete from demo_user where id=#{id}")
    int testDelete(Integer id);




}
