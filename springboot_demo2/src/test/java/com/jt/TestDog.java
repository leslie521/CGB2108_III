package com.jt;

import com.jt.mapper.DogMapper;
import com.jt.mapper.UserAnnoMapper;
import com.jt.pojo.Dog;
import com.jt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDog {
    private SqlSessionFactory sqlSessionFactory;
    @BeforeEach
    public void init() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testResultMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DogMapper dogMapper = sqlSession.getMapper(DogMapper.class);
        List<Dog> dogList = dogMapper.findAll();
        System.out.println(dogList);
        sqlSession.close();
    }

    /*测试注解功能*/
    //注解情况下的sql查询测试方法
    @Test
    public void testAnnofind(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserAnnoMapper userAnnoMapper = sqlSession.getMapper(UserAnnoMapper.class);
        List<User> userList = userAnnoMapper.findAll();
        System.out.println(userList);
        System.out.println("数据入库成功");
        sqlSession.close();
    }

    //注解情况下的sql新增测试方法
    @Test
    public void testAnnoInsert(){
        User user = new User(null, "广寒仙子", 5200, "女");
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserAnnoMapper userAnnoMapper = sqlSession.getMapper(UserAnnoMapper.class);
        int rows = userAnnoMapper.testInsert(user);
        System.out.println("影响"+rows+"行");
        sqlSession.close();
    }

    //注解情况下的sql修改测试方法
    @Test
    public void testAnnoUpdate(){
        User user = new User(520, "广寒仙子", 5200, "女");
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserAnnoMapper userAnnoMapper = sqlSession.getMapper(UserAnnoMapper.class);
        int rows = userAnnoMapper.testUpdate(user);
        System.out.println("影响"+rows+"行");
        sqlSession.close();
    }

    //注解情况下的sql删除测试方法
    @Test
    public void testAnnoDelete(){
        int id = 520;
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserAnnoMapper userAnnoMapper = sqlSession.getMapper(UserAnnoMapper.class);
        int rows = userAnnoMapper.testDelete(id);
        System.out.println("影响"+rows+"行");
        sqlSession.close();
    }
}
