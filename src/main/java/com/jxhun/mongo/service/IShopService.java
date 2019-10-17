package com.jxhun.mongo.service;

import com.jxhun.mongo.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
