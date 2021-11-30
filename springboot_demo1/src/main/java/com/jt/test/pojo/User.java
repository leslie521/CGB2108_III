package com.jt.test.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true) //链式加载
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;

//    public User setId(Integer id){
//        this.id = id;
//        return this;
//    }
//    public User setName(String name){
//        this.name = name;
//        return this;
//    }
}
