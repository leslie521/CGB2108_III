package com.jt.test.config;

import com.jt.test.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 需求：自定义User对象，交给Spring容器管理
 * 注解讲解:
 *      1.@Configuration 该类是一个配置类
 */
@Configuration
public class UserConfig {
    /**
     * 要求: 必须有返回值
     * 功能: 被@Bean修饰的方法，讲方法名当做key--user，将返回值对象当做值value
     *      根据key-value  存储到Map集合中 交给Spring容器管理
     *      Map<user,User对象>
     * @return
     */
    @Bean
    public User user(){
        User user = new User();
        user.setId(100).setName("tomcat猫").setSex("男").setAge(18);
        return user;
    }
}
