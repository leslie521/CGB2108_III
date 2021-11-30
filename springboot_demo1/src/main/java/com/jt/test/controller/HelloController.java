package com.jt.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PropertySource(value = "classpath:/name.properties",encoding = "UTF-8")
public class HelloController {
    //$ : springel  spel表达式
    @Value("${cgbname}")
    private String name;//= "李四";

    @Value("${cgbname2}")
    private String name2;

    @Value("${cgbname3}")
    private String name3;

    @RequestMapping("/getName")
    public String getName(){
        return name+"|"+name2+"|"+name3;
    }
}
