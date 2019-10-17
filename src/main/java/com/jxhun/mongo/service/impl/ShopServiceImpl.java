package com.jxhun.mongo.service.impl;

import com.jxhun.mongo.entity.Shop;
import com.jxhun.mongo.exception.CarabaoException;
import com.jxhun.mongo.mapper.ShopMapper;
import com.jxhun.mongo.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 门店信息管理 服务实现类
 * </p>
 *
 * @author carabao
 * @since 2019-10-17
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    private final MongoTemplate mongoTemplate;

    public ShopServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 门店新增
     *
     * @param shop 门店实体
     * @return 返回是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertShop(Shop shop) {
        int insert = baseMapper.insert(shop);
        if (insert == 0) {
            throw new CarabaoException("新增失败");
        } else {
            mongoTemplate.insert(shop, "shop");
        }
        return "success";
    }
}
