package com.jxhun.mongo.controller;


import com.jxhun.mongo.entity.Shop;
import com.jxhun.mongo.exception.CarabaoException;
import com.jxhun.mongo.service.IShopService;
import com.jxhun.mongo.vo.BaseOutput;
import io.github.yedaxia.apidocs.ApiDoc;
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
@RequestMapping("/api/shop")
@ApiDoc(result = ShopController.class, url = "/api/v1/admin/login2", method = "post")
public class ShopController {
    private final IShopService shopService;

    @Autowired
    public ShopController(IShopService shopService) {
        this.shopService = shopService;
    }

    /**
     * 门店新增接口
     *
     * @param shop 门店信息
     * @return 返回门店信息
     */
    @PostMapping("/insert")
    @ResponseBody
    public BaseOutput insert(@RequestBody Shop shop) {
        try {
            return BaseOutput.success(shopService.insertShop(shop));
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @PostMapping("/insertShopUserList")
    @ResponseBody
    public BaseOutput insertShopUserList() {
        try {
            return BaseOutput.success(shopService.insertShopUserList());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }

    @GetMapping("/synchronization")
    @ResponseBody
    public BaseOutput synchronization() {
        try {
            return BaseOutput.success(shopService.synchronization());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }

    @GetMapping("/listPage")
    @ResponseBody
    public BaseOutput listPage() {
        try {
            return BaseOutput.success().setData(shopService.listPage());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @GetMapping("/addUser")
    @ResponseBody
    public BaseOutput addUser() {
        try {
            return BaseOutput.success(shopService.addUser());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @GetMapping("/updateShop")
    @ResponseBody
    public BaseOutput updateShop() {
        try {
            return BaseOutput.success(shopService.updateShop());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }

    @DeleteMapping("/removeShop")
    @ResponseBody
    public BaseOutput removeShop() {
        try {
            return BaseOutput.success(shopService.removeShop());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @GetMapping("/listPageTwo")
    @ResponseBody
    public BaseOutput listPageTwo() {
        try {
            return BaseOutput.success().setData(shopService.listPageTwo());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @GetMapping("/listPageThree")
    @ResponseBody
    public BaseOutput listPageThree() {
        try {
            return BaseOutput.success().setData(shopService.listPageThree());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @GetMapping("/listPageFour")
    @ResponseBody
    public BaseOutput listPageFour() {
        try {
            return BaseOutput.success().setData(shopService.listPageFour());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @GetMapping("/pageUser")
    @ResponseBody
    public BaseOutput pageUser() {
        try {
            return BaseOutput.success().setData(shopService.queryUser2());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @GetMapping("/queryShopUser")
    @ResponseBody
    public BaseOutput queryShopUser() {
        try {
            return BaseOutput.success().setData(shopService.queryShopUser());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }


    @GetMapping("/setTime")
    @ResponseBody
    public BaseOutput setTime() {
        try {
            return BaseOutput.success().setData(shopService.setTime());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }

    /**
     * 聚合函数测试
     *
     * @return 返回聚合函数
     */
    @GetMapping("/statistics")
    @ResponseBody
    public BaseOutput statistics() {
        try {
            return BaseOutput.success().setData(shopService.statistics());
        } catch (CarabaoException e) {
            return BaseOutput.failure(e.getMsg());
        }
    }

}
