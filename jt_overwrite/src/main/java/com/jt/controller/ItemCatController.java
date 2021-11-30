package com.jt.controller;

import com.jt.pojo.Item;
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

    /*/itemCat/status/{id}/{status}*/
    @PutMapping("/status/{id}/{status}")
    public SysResult updateItemCatStatus(ItemCat itemCat){
        itemCatService.updateItemCatStatus(itemCat);
        return SysResult.success();
    }

    /*请求路径: /itemCat/saveItemCat
      请求类型: post
      请求参数: 表单数据*/
    @PostMapping("/saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat){
        itemCatService.saveItemCat(itemCat);
        return SysResult.success();
    }

    /*  请求路径: /itemCat/updateItemCat
        请求类型: put
        请求参数: 表单数据 ItemCat对象
        返回值: SysResult对象
*/
    @PutMapping("/updateItemCat")
    public SysResult updateItemCat(@RequestBody ItemCat itemCat){
        itemCatService.updateItemCat(itemCat);
        return SysResult.success();
    }

    /*请求路径: /itemCat/deleteItemCat
    请求类型: delete
    业务描述: 当删除节点为父级时,应该删除自身和所有的子节点
    请求参数:id/level*/
    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(Integer id,Integer level){
        itemCatService.deleteItemCat(id,level);
        return SysResult.success();
    }

}
