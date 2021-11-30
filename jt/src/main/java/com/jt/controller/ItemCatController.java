package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.pojo.User;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("/findItemCatList/{level}")
    public SysResult findItemCatList(@PathVariable Integer level){
        List<ItemCat> itemCatList = itemCatService.findItemCatList(level);
        return SysResult.success(itemCatList);
    }

    @PutMapping("/status/{id}/{status}")
    public SysResult updateStatus(ItemCat itemCat){
        itemCatService.updateStatus(itemCat);
        return SysResult.success();
    }

    @PostMapping("/saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat){
        itemCatService.saveItemCat(itemCat);
        return SysResult.success();
    }

    @PutMapping("/updateItemCat")
    public SysResult updateItemCat(@RequestBody ItemCat itemCat){
        itemCatService.updateItemCat(itemCat);
        return SysResult.success();
    }

    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat){
        itemCatService.deleteItemCat(itemCat);
        return SysResult.success();
    }
}
