package com.jt.test;

import com.jt.test.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 注意事项: 测试代码必须在主启动类的同包及子包中编辑
 *      只要在该类中 执行@Test测试方法，则就会启动Spring容器
 */
@SpringBootTest
public class TestUserDemo {

    @Autowired
    private User user;

    /**
     * 测试方法要求：
     *      1.不能有返回值 必须有void
     *      2.不能携带参数
     *      3.测试方法名称 不能叫test
     */

    @Test
    public void testUser1(){
        System.out.println(user);
    }
}
