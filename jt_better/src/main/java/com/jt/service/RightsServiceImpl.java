package com.jt.service;

//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.RightsMapper;
import com.jt.pojo.Rights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsServiceImpl implements RightsService{

    @Autowired
    private RightsMapper rightsMapper;

    @Override
    public List<Rights> getRightsList() {
        //        QueryWrapper<Rights> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent_id", 0);
//        List<Rights> oneList = rightsMapper.selectList(queryWrapper);
//
//        for (Rights oneRights : oneList){
//
//            QueryWrapper<Rights> queryWrapper1 = new QueryWrapper<>();
//            queryWrapper1.eq("parent_id", oneRights.getId());
//            List<Rights> twoList = rightsMapper.selectList(queryWrapper1);
//            oneRights.setChildren(twoList);
//        }
//        return oneList;

        return rightsMapper.getRightsList();
    }






}
