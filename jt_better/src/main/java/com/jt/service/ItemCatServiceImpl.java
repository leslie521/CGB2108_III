package com.jt.service;

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
    private ItemCatMapper itemCatMapper;

    /**
     * 利用Map集合封装所有的数据库记录
     * 封装数据:
     *      1.遍历所有的数据信息.
     *      2.获取每一个parentId的值.
     * 例子:
     *      1.{id=1,parentId=0,name="张三"}
     *      2.{id=2,parentId=0,name="李四"}
     *      3.{id=3,parentId=1,name="王五"}
     *      Map= {
     *          key : value
     *          0   : List[张三对象,李四对象.....],
     *          1   : List[王五对象......]
     *      }
     * @return
     */
    public Map<Integer,List<ItemCat>> getMap() {
        Map<Integer,List<ItemCat>> map = new HashMap<>();
        //1.查询所有的数据库信息
        List<ItemCat> itemCatList = itemCatMapper.selectList(null);
        //2.将数据封装到map集合中
        for (ItemCat itemCat : itemCatList){
            Integer key = itemCat.getParentId();//获取parentId当做key
            //3.判断map集合中是否有值.
            if (map.containsKey(key)){//有值
                //有值: 获取List集合,将自己追加到其中
                map.get(key).add(itemCat);
            }else{//无值
                //没值: 添加数据.将自己作为第一个元素填充
                List<ItemCat> list = new ArrayList<>();
                list.add(itemCat);
                map.put(key, list);
            }
        }
        return map;
    }

    @Override
    public List<ItemCat> findItemCatList(Integer level) {
        long starttime = System.currentTimeMillis();
        Map<Integer,List<ItemCat>> map = getMap();
        //根据level获取子级信息
        if (level == 1){//只获取一级列表信息
            return map.get(0);
        }
        if (level == 2){//获取一级和二级数据
            return getTwoList(map);
        }
        List<ItemCat> oneList = getThreeList(map);
        long stoptime = System.currentTimeMillis();
        System.out.println("优化前耗时500ms，现在耗时："+(stoptime - starttime)+"ms");
        return getThreeList(map);
    }

    //通过map集合 获取一级二级菜单信息.
    private List<ItemCat> getTwoList(Map<Integer, List<ItemCat>> map) {
        List<ItemCat> oneList = map.get(0);//一级列表
        //获取二级信息,应该先遍历一级集合
        for (ItemCat itemCat : oneList){
            Integer oneId = itemCat.getId();
            //根据一级Id,获取二级集合
            List<ItemCat> twoList = map.get(oneId);
            itemCat.setChildren(twoList);
        }
        return oneList;
    }

    //获取三级列表信息  先获取1级数据,再获取2级数据.再获取3级数据
    private List<ItemCat> getThreeList(Map<Integer, List<ItemCat>> map) {
        //1.调用2级菜单方法.
        List<ItemCat> oneList = getTwoList(map);
        //2.实现思路 遍历一级集合,获取二级数据. 封装三级菜单
        for (ItemCat oneItemCat : oneList){
            //2.1 获取二级数据
            List<ItemCat> twoList = oneItemCat.getChildren();
            if (twoList == null || twoList.size() == 0){
                //判断二级集合是否为null.如果为null,表示没有二级菜单.
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


    @Transactional
    @Override
    public void saveItemCat(ItemCat itemCat) {
//        Date date = new Date();
        itemCat.setStatus(true);
        itemCatMapper.insert(itemCat);
    }

    @Transactional
    @Override
    public void updateStatus(ItemCat itemCat) {
//        itemCat.setUpdated(new Date());
        itemCatMapper.updateById(itemCat);
    }

    @Transactional
    @Override
    public void updateItemCat(ItemCat itemCat) {
        ItemCat temp = new ItemCat();
        temp.setId(itemCat.getId())
            .setName(itemCat.getName());
//            .setUpdated(new Date())
        itemCatMapper.updateById(itemCat);
    }

    @Transactional
    @Override
    public void deleteItemCat(ItemCat itemCat) {
        //判断是否为3级菜单
        if (itemCat.getLevel() == 3){
            itemCatMapper.deleteById(itemCat.getId());
//            return ;
        }
        if (itemCat.getLevel() == 2){
            //如果是二级,应该先获取三级数据之后删除,再删除自己
            //delete from item_cat where parent_id=#{id} or id = #{id}
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper();
            /*方式一*/
//            queryWrapper.eq("parent_id", itemCat.getId());
//            itemCatMapper.delete(queryWrapper);
//            itemCatMapper.deleteById(itemCat.getId());
            /*方式二*/
            queryWrapper.eq( "parent_id", itemCat.getId())
                        .or()
                        .eq("id", itemCat.getId());
            itemCatMapper.delete(queryWrapper);
        }

        /**
         * 如何删除一级菜单?
         *  1.获取二级ID
         *  终极sql: delete from item_cat where parent_id in (twoIds)
         *          or  id in (twoIds)
         *          or  id = #{id}
         */
        if (itemCat.getLevel() == 1){
            /*方式一(不建议)*/
//            QueryWrapper queryWrapper = new QueryWrapper();
//            queryWrapper.eq("parent_id", itemCat.getId());
//            List<ItemCat> list1 = itemCatMapper.selectList(queryWrapper);
//            for (ItemCat itemCat1:list1){
//                QueryWrapper<ItemCat> queryWrapper1 = new QueryWrapper<>();
//                queryWrapper1.eq("parent_id", itemCat1.getId());
//                itemCatMapper.delete(queryWrapper1);
//                itemCatMapper.deleteById(itemCat1.getId());//删除二级列表
//            }
//            itemCatMapper.deleteById(itemCat.getId());//删除一级列表

            /*方式二*/
            QueryWrapper<ItemCat> queryWrapper2 = new QueryWrapper();
            queryWrapper2.eq("parent_id", itemCat.getId());
            /*需求: 动态查询主键字段(第一列数据) 适用范围 进行关联查询时使用.*/
            List twoIds = itemCatMapper.selectObjs(queryWrapper2);
            queryWrapper2.clear();
            //规则: 如果2级菜单有值,才会删除 2级和三级
            queryWrapper2.in(twoIds.size()>0, "parent_id", twoIds)
                         .or()
                         .in(twoIds.size()>0, "id", twoIds)
                         .or()
                         .in("id", itemCat.getId());
            itemCatMapper.delete(queryWrapper2);
        }
    }



    /**
     * 步骤1.查询一级菜单列表
     *
     * 1.上述的案例 采用多级循环的方式. 将来会耗费服务器资源 100次 内层100次 总循环1万次. 暂时可以接受
     * 2.上述的代码 频繁访问数据库.导致数据库压力增大.严重时可能导致数据库服务器宕机. 不能接受的
     * 优化策略: 降低数据库访问的次数
     * @param level
     * @return
     */
//    @Override
//    public List<ItemCat> findItemCatList(Integer level) {
//        long starttime = System.currentTimeMillis();
//        //1.查询一级菜单
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq("parent_id", 0);
//        List<ItemCat> oneList = itemCatMapper.selectList(queryWrapper);
//        //2.查询二级菜单 二级数据是一级数据的子级 封装到一级数据中.
//        for (ItemCat oneItemCat : oneList){
//            Integer oneId = oneItemCat.getId();//一级对象ID
//            //清空原始条件  必须有
//            queryWrapper.clear();
//            queryWrapper.eq("parent_id", oneId);
//            List<ItemCat> twoList = itemCatMapper.selectList(queryWrapper);
//            for (ItemCat twoItemCat : twoList){
//                //获取二级分类ID
//                Integer twoId = twoItemCat.getId();
//                //查询三级列表信息
//                queryWrapper.clear();
//                queryWrapper.eq("parent_id", twoId);
//                List<ItemCat> threeList = itemCatMapper.selectList(queryWrapper);
//                //将三级列表 封装到二级对象中
//                twoItemCat.setChildren(threeList);
//            }
//            //将二级数据封装到一级对象中
//            oneItemCat.setChildren(twoList);
//        }
//        long stoptime = System.currentTimeMillis();
//        System.out.println(starttime-stoptime+"ms");
//        return oneList;
//    }
}
