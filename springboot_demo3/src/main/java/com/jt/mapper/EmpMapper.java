package com.jt.mapper;

import com.jt.pojo.Emp;

import java.util.List;

public interface EmpMapper {
    List<Emp> findAll();

    List<Emp> selectChildren();

    List<Emp> findAutoMap();//一对一

    List<Emp> selectAutoMap();//一对多

    List<Emp> getAll();
}
