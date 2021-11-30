package com.jt.mapper;


import com.jt.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    User findUserByName(String name);

    List<User> findUserByNA(User user);

    void saveUser(User user);
}
