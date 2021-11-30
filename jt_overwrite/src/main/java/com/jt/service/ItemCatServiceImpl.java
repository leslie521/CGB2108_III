package com.jt.service;

import ch.qos.logback.core.util.ContextUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    public ItemCatMapper itemCatMapper;

    public Map<Integer,List<ItemCat>> getMap(){
        Map<Integer,List<ItemCat>> map  = new HashMap<>();
        List<ItemCat> itemCatList = itemCatMapper.selectList(null);
        for (ItemCat itemCat : itemCatList){
            Integer key = itemCat.getParentId();
            if (map.containsKey(key)){
                map.get(key).add(itemCat);
            }else{
                List<ItemCat> list = new ArrayList<>();
                list.add(itemCat);
                map.put(key, list);
            }
        }
        return map;
    }


    @Override
    public List<ItemCat> findItemCatList(Integer level) {
        Map<Integer, List<ItemCat>> map = getMap();
        if (level == 1){
            return map.get(0);
        }
        if (level == 2){
            return getTwoList(map);
        }
        List<ItemCat> oneList = getThreeList(map);
        return oneList;
    }
    private List<ItemCat> getThreeList(Map<Integer, List<ItemCat>> map) {
        List<ItemCat> oneList = getTwoList(map);
        for (ItemCat oneItemList : oneList){
            List<ItemCat> twoList = oneItemList.getChildren();
            if (twoList == null || twoList.size() == 0){
                continue;
            }
            for (ItemCat twoItemList : twoList){
                Integer twoId = twoItemList.getId();
                List<ItemCat> threeList = map.get(twoId);
                twoItemList.setChildren(threeList);
            }
        }
        return oneList;
    }

    private List<ItemCat> getTwoList(Map<Integer, List<ItemCat>> map) {
        List<ItemCat> oneList = map.get(0);
        for (ItemCat oneItemCat : oneList){
            Integer oneId = oneItemCat.getId();
            List<ItemCat> twoList = map.get(oneId);
            oneItemCat.setChildren(twoList);
        }
        return oneList;
    }

    @Transactional
    @Override
    public void updateItemCatStatus(ItemCat itemCat) {
        itemCatMapper.updateById(itemCat);
    }

    @Transactional
    @Override
    public void saveItemCat(ItemCat itemCat) {
        itemCat.setStatus(true);
        itemCatMapper.insert(itemCat);
    }

    @Transactional
    @Override
    public void updateItemCat(ItemCat itemCat) {
        ItemCat temp = new ItemCat();
        temp.setId(itemCat.getId())
            .setName(itemCat.getName());
        itemCatMapper.updateById(itemCat);
    }

    @Transactional
    @Override
    public void deleteItemCat(Integer id, Integer level) {
       if (level == 3){
           itemCatMapper.deleteById(id);
       }
       if (level == 2){
           QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
           queryWrapper.eq("parent_id",id)
                       .or()
                       .eq("id", id);
           itemCatMapper.delete(queryWrapper);

       }
       if (level == 1){
           QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
           queryWrapper.eq("parent_id", id);
           List twoIds = itemCatMapper.selectObjs(queryWrapper);
           queryWrapper.clear();
           queryWrapper.in(twoIds.size()>0,"parent_id",twoIds)
                       .or()
                       .in(twoIds.size()>0,"id", twoIds)
                       .or()
                       .in("id",id);
           itemCatMapper.delete(queryWrapper);
       }
    }


}
