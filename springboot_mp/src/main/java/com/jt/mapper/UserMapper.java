package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();

}
