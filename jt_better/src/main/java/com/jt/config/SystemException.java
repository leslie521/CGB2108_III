package com.jt.config;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//定义全局异常处理机制 内部封装了AOP
@RestControllerAdvice  //标识了Controller层 返回值JSON串
public class SystemException {

    @ExceptionHandler(value = RuntimeException.class)
    public SysResult fail(Exception e){
        //输出异常
        e.printStackTrace();
        return SysResult.fail();
    }
}
