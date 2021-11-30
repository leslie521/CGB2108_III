package com.jt.service;


import com.jt.mapper.UserMapper;
import com.jt.pojo.User;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    /**
     * 业务流程：
     *   1. 将密码进行加密处理
     *   2. 根据username/password 查询数据库获取操作
     *   3. 有数据 用户名密码正确
     *      无数据 用户名密码错误
     *
     * @param user
     * @return
     */
    @Override
    public String login(User user) {
        //1.将密码加密处理
        String password = user.getPassword();
        //2.利用md5加密算法 进行加密
        String md5Pass = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Pass);
        //3.查询数据库数据
        User userDB = userMapper.findUserByUP(user);
        if(userDB == null){
            //说明用户名和密码错误
            return null;
        }
        //说明用户名和密码正确
        String uuid = UUID.randomUUID().toString()
                          .replace("-", "");
        return uuid;
    }

    /**
     * 要求查询 1页10条
     * 特点： 数组的结果  口诀： 含头不含尾
     * 语  法：select * from user limit 起始位置,查询的条数
     * 第一页：select * from user limit 0,10
     * 第二页：select * from user limit 10,10
     * 第三页：select * from user limit 20,10
     * 第N页：select * from user limit 10*(N-1),10
     * @param pageResult
     * @return
     */
    @Override
    public PageResult findUserList(PageResult pageResult) {
        //1.记录总数 total
        long total = userMapper.getUserList();
        //2.分页后的数据
        int size = pageResult.getPageSize();
        int start = (pageResult.getPageNum() - 1) * size;
        String query = pageResult.getQuery();
        List<User> rows = userMapper.findUserListByPage(start,size,query);
//        String like = userMapper.getUserLike(query);

        return pageResult.setTotal(total).setRows(rows).setQuery(query);
    }

    @Transactional
    @Override
    public void updateStatus(User user) {
        user.setUpdated(new Date());
        userMapper.updateStatus(user);
    }

    /**
     * 1.密码进行加密
     * 2.添加状态码信息
     * 3.添加创建时间/修改时间
     * 4.完成入库操作 xml方式
     * @param user
     */
    @Transactional
    @Override
    public void addUser(User user) {
        //Date date = new Date();
        String password = user.getPassword();
        String md5pw = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5pw).setStatus(true)
                .setCreated(new Date())
                .setUpdated(user.getCreated());
        userMapper.addUser(user);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }


}
