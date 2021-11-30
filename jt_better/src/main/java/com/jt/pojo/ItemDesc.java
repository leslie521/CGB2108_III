package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/7
 */
@Data
@Accessors(chain = true)
@TableName("item_desc")
public class ItemDesc extends BasePojo{
    @TableId
    private Integer id;
    private String itemDesc;

}
