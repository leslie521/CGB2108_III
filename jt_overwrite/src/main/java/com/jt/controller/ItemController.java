package com.jt.controller;

import com.jt.pojo.Item;
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

    /*?query=&pageNum=1&pageSize=10
    * 请求参数: 使用pageResult对象接收*/
    @GetMapping("/getItemList")
    public SysResult getItemList(PageResult pageResult){//3
        pageResult = itemService.getItemList(pageResult);//5
        return SysResult.success(pageResult);//5个
    }

    /*请求路径: /item/updateItemStatus
      请求类型: put
      请求参数: 使用对象接收*/
    @PutMapping("/updateItemStatus")
    public SysResult updateItemStatus(@RequestBody Item item){
        itemService.updateItemStatus(item);
        return SysResult.success();
    }

    /**
     * 业务需求: 根据Id 删除数据
     * URL: /item/deleteItemById
     * 参数: id
     * 返回值: SysResult对象
     */
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

}
