package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

    /**
     *  要求: 3+2(总记录数,分页结果)
     *  关于selectPage(参数说明)
     *   参数1: page MP提供的分页对象
     *   参数2: 条件构造器
     * @param pageResult
     * @return
     */
    @Override
    public PageResult getItemList(PageResult pageResult) {
        Page<Item> page = new Page<>(pageResult.getPageNum(), pageResult.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper();
        String query = pageResult.getQuery();
        boolean flag = StringUtils.hasLength(query);
        queryWrapper.like(flag, "title", query);

        page = itemMapper.selectPage(page, queryWrapper);
        long total = page.getTotal();
        List<Item> rows = page.getRecords();

        return pageResult.setTotal(total).setRows(rows);
    }

    @Transactional
    @Override
    public void updateItemStatus(Item item) {
        itemMapper.updateById(item);
    }

    @Transactional
    @Override
    public void deleteItemById(Integer id) {
        itemMapper.deleteById(id);
    }

    @Transactional
    @Override
    public void updateItem(Item item) {
        itemMapper.updateById(item);
    }
    /**
     * 问题: id是主键自增. 入库之后才有主键所以
     *      应该让主键动态回显
     * 1.Mybatis 动态实现回显
     *      <insert id="xxxx" useGeneratedKeys="true" keyColumn="id" keyProperty="id">
     *         insertinto xxxx
     *     </insert>
     * 2.MP是mybatis的增强版本.所以可以实现自动的主键回显!!!
     * 由于Item和ItemDesc 是典型的一对一. 所以要求 item.id = itemDesc.id
     * @param itemVO
     */
    @Transactional
    @Override
    public void saveItem(ItemVO itemVO) {
        Item item = itemVO.getItem();
        //设置状态
        item.setStatus(true);
        itemMapper.insert(item);
        //获取商品详情
        ItemDesc itemDesc = itemVO.getItemDesc();
        itemDesc.setId(item.getId());
        itemDescMapper.insert(itemDesc);
    }


}
