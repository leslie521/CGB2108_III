package com.jt;

import com.jt.mapper.DeptMapper;
import com.jt.mapper.EmpMapper;
import com.jt.pojo.Dept;
import com.jt.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


class SpringbootDemo3ApplicationTests {
    private SqlSessionFactory sqlSessionFactory;
    @BeforeEach
    public void init() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    void oneToOne() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = empMapper.findAll();
        System.out.println(empList);
        sqlSession.close();
    }

    @Test
    void oneToOne1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = empMapper.selectChildren();
        System.out.println(empList);
        sqlSession.close();
    }

    /*驼峰映射--单表查询*/
    @Test
    void oneToOne2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = empMapper.findAutoMap();
        System.out.println(empList);
        sqlSession.close();
    }


    /*驼峰映射--多表查询*/
    @Test
    void oneToMore2() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = empMapper.selectAutoMap();
        System.out.println(empList);
        sqlSession.close();
    }

    @Test
    void oneToMore() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        List<Dept> deptList = deptMapper.findAll();
        System.out.println(deptList);
        sqlSession.close();
    }

    @Test
    void oneToMore1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DeptMapper deptMapper = sqlSession.getMapper(DeptMapper.class);
        List<Dept> deptList = deptMapper.selectChildren();
        System.out.println(deptList);
        sqlSession.close();
    }

    /*缓存测试*/
    //一级缓存
    @Test
    void testCache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = empMapper.getAll();
        List<Emp> empList1 = empMapper.getAll();
        List<Emp> empList2 = empMapper.getAll();
        System.out.println(empList);
        System.out.println(empList1);
        System.out.println(empList2);
        sqlSession.close();
    }

    //二级缓存
    @Test
    void testCache2() {
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        EmpMapper empMapper1 = sqlSession1.getMapper(EmpMapper.class);
        List<Emp> empList1 = empMapper1.getAll();
//        System.out.println(empList);
        sqlSession1.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        EmpMapper empMapper2 = sqlSession2.getMapper(EmpMapper.class);
        List<Emp> empList2 = empMapper2.getAll();
//        System.out.println(empList);
        sqlSession2.close();

    }

}
