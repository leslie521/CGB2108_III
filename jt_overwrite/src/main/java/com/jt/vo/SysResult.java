package com.jt.vo;
//VO: 与前端页面进行数据交互  是一种统一的格式

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SysResult {
    private Integer status;  //200 成功  201 失败
    private String  msg;  //服务器提示信息
    private Object  data;  //服务器返回值数据

    public static SysResult fail(){
        return new SysResult(201,"服务调用失败",null);
    }

    public static SysResult success(){
        return new SysResult(200,"服务调用成功",null);
    }

    public static SysResult success(Object data){
        return new SysResult(200,"服务调用成功",data);
    }

    public static SysResult success(String msg,Object data){
        return new SysResult(200,msg,data);
    }

}
