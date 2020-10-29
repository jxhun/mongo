package com.jxhun.mongo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jxhun.mongo.entity.Dealer;
import com.jxhun.mongo.entity.Shop;
import com.jxhun.mongo.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店信息管理 服务类
 * </p>
 *
 * @author carabao
 * @since 2019-10-17
 */
public interface IShopService extends IService<Shop> {

    /**
     * 门店新增
     *
     * @param shop 门店实体
     * @return 返回是否成功
     */
    String insertShop(Shop shop);

    /**
     * 同步数据到MongoDB
     *
     * @return 返回是否成功
     */
    String synchronization();

    /**
     * 查询
     *
     * @return 返回是否成功
     */
    List<Dealer> listPage();

    /**
     * 查询
     *
     * @return 返回是否成功
     */
    String addUser();


    /**
     * 多表查询
     *
     * @return 返回是否成功
     */
    List<Shop> listPageTwo();

    /**
     * 分组查询
     *
     * @return 返回是否成功
     */
    List<Map> listPageThree();

    /**
     * 三表以上多表查询
     *
     * @return 返回是否成功
     */
    List<Shop> listPageFour();

    /**
     * 更新
     *
     * @return 返回是否成功
     */
    String updateShop();

    /**
     * 删除
     *
     * @return 返回是否成功
     */
    String removeShop();

    /**
     * 分页查询
     *
     * @return 返回结果
     */
    Page<User> queryUser();

    /**
     * Repository测试
     *
     * @return 返回结果
     */
    Page<User> queryUser2();


    /**
     * 门店新增
     *
     * @return 返回是否成功
     */
    String insertShopUserList();


    /**
     * 门店新增
     *
     * @return 返回是否成功
     */
    List<Shop> queryShopUser();


    /**
     * 过期时间设置
     *
     * @return 返回是否成功
     */
    String setTime();

    /**
     * 聚合函数测试
     *
     * @return 返回聚合函数
     */
    Object statistics();
}
