package com.jt;

import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import jdk.nashorn.internal.parser.Scanner;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SpringbootDemo2ApplicationTests {

    private SqlSessionFactory sqlSessionFactory;

    /**
     * 注解说明：
     *      @BeforeEach 当每次执行@Test注解方法时，都会先执行该方法。
     * */

    @BeforeEach
    public void init() throws IOException {
        //1.定义核心配置文件的路径
        String resource = "mybatis/mybatis-config.xml";
        //2.通过工具API读取资源文件
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //3.构建SqlSessionFactory
        sqlSessionFactory =
                new SqlSessionFactoryBuilder().build(inputStream);
    }
    /**
     * 核心问题：SqlSession 理解为Mybatis操作数据的连接
     * 1. 通过 SqlSessionFactoryBuilder  构建SqlSessionFactory
     */
    @Test
   public void testMybatis01() {
//       //1.定义核心配置文件的路径
//       String resource = "mybatis/mybatis-config.xml";
//       //2.通过工具API读取资源文件
//       InputStream inputStream = Resources.getResourceAsStream(resource);
//       //3.构建SqlSessionFactory
//       SqlSessionFactory sqlSessionFactory =
//               new SqlSessionFactoryBuilder().build(inputStream);
       //4.获取SqlSession
       SqlSession sqlSession = sqlSessionFactory.openSession();
       //5.获取Mapper的接口
       UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
       //6.调用接口方法 获取返回值数据
       List<User> userlist = userMapper.findAll();
       System.out.println(userlist);
       //7.连接用完进行关闭
       sqlSession.close();
//       inputStream.close();
   }

    @Test
    public void testUserById() throws IOException {
        int id = 1;
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.findUserById(id);
        System.out.println(user);
        sqlSession.close();
        inputStream.close();
    }

    @Test
    public void testUserById1(){
        int id = 1;
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User userById = userMapper.findUserById(id);
        System.out.println(userById);
    }

    @Test
    public void testsaveUser(){
        User user = new User(null, "嫦娥", 20, "女");
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int rows = userMapper.saveUser(user);
        System.out.println("影响的行数："+rows);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testupdateUser(){
        User user = new User(231, "嫦娥奶奶", 99, null);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int rows = userMapper.updateUser(user);
        if (rows > 0){
            sqlSession.commit();//事务提交
        }
        System.out.println("影响了"+rows+"行");

        sqlSession.close();
    }

    @Test
    public void testdeleteUser(){
        String name = "嫦娥奶奶";
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int rows = userMapper.deleteUser(name);
        if (rows > 0){
            sqlSession.commit();//事务提交
        }
        System.out.println("影响了"+rows+"行");

        sqlSession.close();
    }

    @Test
    public void testMap(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        Map map = new HashMap();
        map.put("minAge", 100);
        map.put("maxAge", 1000);
//        List<User> userList = userMapper.findByAge(map);
        List<User> userList = userMapper.findByAge(map);
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testParam(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        int minAge = 100;
        int maxAge = 1000;
        List<User> userList = userMapper.findParam(minAge,maxAge);
        System.out.println(userList);
        sqlSession.close();
    }

    @Test
    public void testLike(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String name = "乔";
        List<User> userList = userMapper.findUserByLike(name);
        System.out.println(userList);
        sqlSession.close();
    }
}
