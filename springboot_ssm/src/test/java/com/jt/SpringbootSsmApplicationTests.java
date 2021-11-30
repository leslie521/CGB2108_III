package com.jt;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootSsmApplicationTests {

    @Autowired
    private UserMapper usermapper;//IDEA编译提示
    //class com.sun.proxy.$Proxy54 代理对象 : Spring容器自动创建的代理对象
    /**
     * 测试SpringBoot整合Mybatis
     * */
    @Test
    public void testFindAll() {
        List<User> userList = usermapper.findAll();
        System.out.println(usermapper.getClass());
        System.out.println(userList);
    }

}
