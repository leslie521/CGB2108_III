package com.jt.vo;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/16
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO {  //该对象封装上传图片的所有的参数信息

    private String virtualPath; //图片的虚拟路径
    private String urlPath;     //图片回显的URL地址
    private String fileName;    //文件上传后的文件名称
}
