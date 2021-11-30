package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.User;
import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/getItemList")
    public SysResult getItemList(PageResult pageResult){//3个
        pageResult = itemService.getItemList(pageResult);//5个
        return SysResult.success(pageResult);//5个
    }

    @PutMapping("/updateItemStatus")
    public SysResult updateItemStatus(@RequestBody Item item){
        itemService.updateItemStatus(item);
        return SysResult.success();
    }

    @DeleteMapping("/deleteItemById")
    public SysResult deleteItemById(Integer id){
        itemService.deleteItemById(id);
        return SysResult.success();
    }

    @PostMapping("/saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO){
        itemService.saveItem(itemVO);
        return SysResult.success();
    }

    @PutMapping("/updateItem")
    public SysResult updateItem(@RequestBody Item item){
        itemService.updateItem(item);
        return SysResult.success();
    }

    @GetMapping("/{id}")
    public SysResult getItemById(@PathVariable Integer id){
        Item item = itemService.getItemById(id);
        return SysResult.success(item);
    }
}
