package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AxiosMapper {
    @Select("select * from demo_user")
    List<User> findUser();
    @Insert("insert into demo_user(id,name,age,sex) value(null,#{name},#{age},#{sex})")
    void saveUser(User user);
    @Update("update demo_user set name=#{name},age=#{age},sex=#{sex} where id=#{id}")
    void updateUser(User user);
    @Delete("delete from demo_user where id=#{id}")
    void deleteUser(Integer id);
}
