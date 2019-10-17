package com.jxhun.mongo.controller;


import com.jxhun.mongo.entity.Shop;
import com.jxhun.mongo.exception.CarabaoException;
import com.jxhun.mongo.service.IShopService;
import com.jxhun.mongo.vo.BaseOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 门店信息管理 前端控制器
 * </p>
 *
 * @author carabao
 * @since 2019-10-17
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
    private final IShopService shopService;

    @Autowired
    public ShopController(IShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/insert")
    @ResponseBody
    public BaseOutput insert(@RequestBody Shop shop) {
        try {
            return BaseOutput.success(shopService.insertShop(shop));
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }
}
