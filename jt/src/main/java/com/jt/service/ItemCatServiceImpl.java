package com.jt.service;

import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> findItemCatList(Integer level) {

        return itemCatMapper.findItemCatList(level);
    }

    @Override
    public void updateStatus(ItemCat itemCat) {
        itemCatMapper.updateItemStatus(itemCat);
    }

    @Override
    public void saveItemCat(ItemCat itemCat) {
        Date date = new Date();
        itemCat.setStatus(true).setCreated(date).setUpdated(date);
        itemCatMapper.saveItemCat(itemCat);
    }

    @Override
    public void updateItemCat(ItemCat itemCat) {
        Date date = new Date();
        itemCat.setUpdated(date);
        itemCatMapper.updateItemCat(itemCat);
    }

    @Override
    public void deleteItemCat(ItemCat itemCat) {
        itemCatMapper.deleteItemCat(itemCat);
    }
}
