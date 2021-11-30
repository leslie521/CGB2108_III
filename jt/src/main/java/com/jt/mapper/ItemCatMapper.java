package com.jt.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.ItemCat;


import java.util.List;

public interface ItemCatMapper {
    List<ItemCat> findItemCatList(Integer level);

    void updateItemStatus(ItemCat itemCat);

    void saveItemCat(ItemCat itemCat);

    void updateItemCat(ItemCat itemCat);

    void deleteItemCat(ItemCat itemCat);
}
