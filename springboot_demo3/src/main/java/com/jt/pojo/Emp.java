package com.jt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Emp implements Serializable {
    private Integer empId;
    private String empName;
    //关联关系 1：一个员工对应一个部门
    private Dept dept;
}
