package com.jt.mapper;

import com.jt.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper2 {

    List<User> findUser();

    List<User> findIn(int[] ids);

    List<User> findInList(List list);

    //多值封装为Map集合
    List<User> findInMap(@Param("ids") int[] ids,
                         @Param("sex") String sex);

    List<User> findSqlWhere(User user);

    int updateSqlSet(User user);

    List<User> findChoose(User user);
}
