package com.jt.service;

import com.jt.pojo.ItemCat;

import java.util.List;

public interface ItemCatService{
    List<ItemCat> findItemCatList(Integer level);

    void updateItemCatStatus(ItemCat itemCat);

    void saveItemCat(ItemCat itemCat);

    void updateItemCat(ItemCat itemCat);

    void deleteItemCat(Integer id, Integer level);
}
