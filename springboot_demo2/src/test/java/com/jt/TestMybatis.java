package com.jt;

import com.jt.mapper.UserMapper2;
import com.jt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMybatis {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    public void init() throws IOException {
        String resource = "mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void findUser(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper2 userMapper2 = sqlSession.getMapper(UserMapper2.class);
        List<User> userList = userMapper2.findUser();
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testFindIn(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper2 userMapper2 = sqlSession.getMapper(UserMapper2.class);
        //将数据封装成数组
        int[] ids = {1,3,4,5,7};
        List<User> userList = userMapper2.findIn(ids);
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testFindInList(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper2 userMapper2 = sqlSession.getMapper(UserMapper2.class);
        //将数据封装成数组,将数组转化为List集合
        /*知识点：数组转化时，需要使用包装类型。
         * 根源： 基本类型没有get/set方法
         *       包装类型是对象 对象中方法*/
        Integer[] ids = {1,3,4,5,7};
        List list = Arrays.asList(ids);
        List<User> userList = userMapper2.findInList(list);
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testFindInMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper2 userMapper2 = sqlSession.getMapper(UserMapper2.class);
        //将数据封装成数组,将数组转化为List集合
        int[] ids = {1,3,4,5,7};
        String sex = "男";
//        Map map = new HashMap();
//        map.put("ids", ids);
//        map.put("sex", sex);
        List<User> userList = userMapper2.findInMap(ids,sex);
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testSqlWhere(){
        User user = new User(null, "黑熊精", 3000, "男");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper2 userMapper2 = sqlSession.getMapper(UserMapper2.class);
        List<User> userList = userMapper2.findSqlWhere(user);
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testSqlSet(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper2 userMapper2 = sqlSession.getMapper(UserMapper2.class);
        User user = new User(1, "守山使者", 3000, null);
        int rows = userMapper2.updateSqlSet(user);
        System.out.println("影响"+rows+"行");
        sqlSession.close();
    }

    @Test
    public void testChoose(){
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper2 userMapper2 = sqlSession.getMapper(UserMapper2.class);
        User user = new User(null,null,null,"男");
        List<User> userList = userMapper2.findChoose(user);
        System.out.println(userList);

        sqlSession.close();
    }
}
