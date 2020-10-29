package com.jxhun.mongo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxhun.mongo.entity.Dealer;
import com.jxhun.mongo.entity.MyData;
import com.jxhun.mongo.entity.Shop;
import com.jxhun.mongo.entity.User;
import com.jxhun.mongo.exception.CarabaoException;
import com.jxhun.mongo.mapper.ShopMapper;
import com.jxhun.mongo.mongoutil.MyRepository;
import com.jxhun.mongo.service.IDealerService;
import com.jxhun.mongo.service.IShopService;
import com.jxhun.mongo.service.IUserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

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
    private final IUserService userService;
    private final IDealerService dealerService;
    private final MyRepository myRepository;

    @Autowired
    public ShopServiceImpl(MongoTemplate mongoTemplate, IUserService userService, IDealerService dealerService, MyRepository myRepository) {
        this.mongoTemplate = mongoTemplate;
        this.userService = userService;
        this.dealerService = dealerService;
        this.myRepository = myRepository;
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
        // 创建索引
//        mongoTemplate.indexOps(Shop.class).ensureIndex(new Index().on("dealerCode", Sort.Direction.DESC));
//        mongoTemplate.indexOps(Dealer.class).ensureIndex(new Index().on("code", Sort.Direction.DESC));

        int insert = baseMapper.insert(shop);
        if (insert == 0) {
            throw new CarabaoException("新增失败");
        } else {
            mongoTemplate.insert(shop, "shop");
        }
        return "success";
    }

    /**
     * 门店新增
     *
     * @return 返回是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertShopUserList() {
//        User user = new User();
//        user.setId(1L).setUserName("张三");
//        List<User> list = new ArrayList<>();
//        list.add(user);
//        User user2 = new User();
//        user2.setId(2L).setUserName("李四");
//        list.add(user2);
//        User user3 = new User();
//        user3.setId(3L).setUserName("王五");
//        list.add(user3);
        Shop shop = new Shop();
        shop.setId(100022222L).setName("测试门店787922222").setCode("3333333333333333").setShopkeeper("shopkeeper")
                .setAddress("address");
        mongoTemplate.insert(shop, "shop");
        shop.insert();
        throw new CarabaoException("陈宫");
//        return "陈宫sasa";
    }

    /**
     * 同步数据到MongoDB
     *
     * @return 返回是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String synchronization() {
        List<Shop> shops = baseMapper.selectList(new QueryWrapper<Shop>().lambda().le(Shop::getId, 100000).ge(Shop::getId, 1));
        System.out.println(shops.size());
        mongoTemplate.insert(shops, Shop.class);
        return "success";
    }

    /**
     * 分页查询
     *
     * @return 返回是否成功
     */
    @Override
    public List<Dealer> listPage() {
        Query query = new Query();
        Query query2 = new BasicQuery(new Document("_id", 500));

        //检索id 6-18的
//        query.addCriteria(Criteria.where("id").gte(6).lte(18));
        // 时间
//        query.addCriteria(Criteria.where("created").gte(LocalDate.now().plusDays(-50)));
        //模糊查询名字
//        query.addCriteria(Criteria.where("real_name").regex("文"));
        // 查询总记录数
        Long count = mongoTemplate.count(query, Shop.class);
        System.out.println(count);
        // 这里代表的是从0到10分页，0代表页码不代表是从0开始，0代表第一页，DESC和id一看就知道是以id倒序排列
//        return
        mongoTemplate.find(query.with(PageRequest.of(1, 100,
                Sort.by(new Sort.Order(Sort.Direction.DESC, "id")))), Shop.class);
        long lo = System.currentTimeMillis();
        List<Shop> shops = mongoTemplate.findAll(Shop.class);
        List<Dealer> dealers = mongoTemplate.find(query2, Dealer.class);
        System.out.println(dealers);
        Iterator<Shop> iterator = shops.iterator();
        while (iterator.hasNext()) {
            Shop shop = iterator.next();
            List<Dealer> dealers2 = new ArrayList<>();
            dealers.forEach(dealer -> {
                if (dealer.getCode().equals(shop.getDealerCode())) {
                    dealers2.add(dealer);
                }
            });
            if (CollectionUtils.isEmpty(dealers2)) {
                iterator.remove();
            } else {
                shop.setDealers(dealers2);
            }
        }
        System.out.println(shops);
        long l = System.currentTimeMillis() - lo;
        System.out.println(l);
        long lo1 = System.currentTimeMillis();
        this.list();
        System.out.println(System.currentTimeMillis() - lo1);
        return dealers;
    }

    /**
     * 多表查询
     *
     * @return 返回是否成功
     */
    @Override
    public List<Shop> listPageTwo() {
        long l = System.currentTimeMillis();
        //拼装关联信息
        LookupOperation lookupOperation = LookupOperation.newLookup()
                //关联从表名
                .from("dealer")
                //主表关联字段
                .localField("dealerCode")
                //主表关联字段对应的次表字段
                .foreignField("code")
                //查询结果集合名,次表数据是存入的这个集合中
                .as("dealers");
//        带条件查询可以选择添加下面的条件
//        主表
        Criteria criteria = Criteria.where("_id").gte(1).lte(100000);
        //次表
        Criteria qqq = Criteria.where("_id").gte(1).lte(10000);
        // 次表match
        AggregationOperation match1 = Aggregation.match(qqq);
        // 主表match
        AggregationOperation match = Aggregation.match(criteria);
        // 这里加入了分页，这里的skip代表的是从10开始，不是10页，注意这里次表match放在左边
        Aggregation counts = Aggregation.newAggregation(match1, lookupOperation, match,
                Aggregation.sort(Sort.by(new Sort.Order(Sort.Direction.DESC, "_id"))),
                Aggregation.skip(1L), Aggregation.limit(200));
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);
        // 这里接收是以map来接收,这里也可以用对象来接收，但是entity中必须有对应的集合
        List<Shop> results = mongoTemplate.aggregate(counts, "shop", Shop.class).getMappedResults();
        //上面的student必须是查询的主表名
        System.out.println(results.size());
        System.out.println(System.currentTimeMillis() - l);
        return null;
    }

    /**
     * 分组查询
     *
     * @return 返回是否成功
     */
    @Override
    public List<Map> listPageThree() {
        // 以creatorCode作为分组来查询,last代表最后，同理fist代表最前
        Aggregation aggregation = Aggregation.newAggregation(Aggregation.group("creatorCode").count().as("users")
                        .last("code").as("code").last("_id").as("id"),
                Aggregation.skip(0L), Aggregation.limit(5),
                Aggregation.project("code", "id", "users").andExclude("_id").and("_id").as("creatorCode"));

        // 这里接收是最好以map来接收，这样能完全显示出所需字段，如果用entity对象，
        // group("creatorCode").出来这个creatorCode字段会替代_id这个字段,然后id就没法显示出来
        List<Map> results = mongoTemplate.aggregate(aggregation, "shop", Map.class).getMappedResults();
        //上面的student必须是查询的主表名
        System.out.println(results.size());
        System.out.println(results);
        return results;
    }

    /**
     * 三表以上多表查询
     *
     * @return 返回是否成功
     */
    @Override
    public List<Shop> listPageFour() {
        long l = System.currentTimeMillis();
        //拼装关联信息
        LookupOperation lookupOperation = LookupOperation.newLookup()
                //关联从表名
                .from("user")
                //主表关联字段
                .localField("creatorCode")
                //主表关联字段对应的次表字段
                .foreignField("code")
                //查询结果集合名,次表数据是存入的这个集合中
                .as("userShops");

        //拼装关联信息
        LookupOperation lookupOperation2 = LookupOperation.newLookup()
                //关联从表名
                .from("dealer")
                //主表关联字段
                .localField("dealerCode")
                //主表关联字段对应的次表字段
                .foreignField("code")
                //查询结果集合名,次表数据是存入的这个集合中
                .as("dealers");
//        带条件查询可以选择添加下面的条件
//        主表
//        Criteria criteria = Criteria.where("_id").gte(1).lte(100);
//        //次表
//        Criteria qqq = new Criteria().and("userShops.code").is("XSRY0001037");
//        // 次表match
//        AggregationOperation match1 = Aggregation.match(qqq);
        MatchOperation matchOperation = new MatchOperation(Criteria.where("userShops.userName").is("03340"));
        MatchOperation matchOperation2 = new MatchOperation(Criteria.where("_id").gte(1).lte(100));
        // 主表match
//        AggregationOperation match = Aggregation.match(criteria);
        // 这里加入了分页，这里的skip代表的是从10开始，不是10页
        TypedAggregation counts = Aggregation.newAggregation(Shop.class, lookupOperation, matchOperation, lookupOperation2, matchOperation2,
                Aggregation.sort(Sort.by(new Sort.Order(Sort.Direction.DESC, "id"))),
                Aggregation.skip(0L), Aggregation.limit(20));
        TypedAggregation counts2 = Aggregation.newAggregation(Shop.class, lookupOperation, lookupOperation2,
                Aggregation.sort(Sort.by(new Sort.Order(Sort.Direction.DESC, "id"))),
                Aggregation.skip(0L), Aggregation.limit(20));
        TypedAggregation counts3 = Aggregation.newAggregation(Shop.class, lookupOperation, matchOperation, lookupOperation2, matchOperation2,
                Aggregation.group("id").count().as("count").sum("id").as("sum"));
        AggregationResults aggregateResult = mongoTemplate.aggregate(counts3, Shop.class);
        System.out.println(aggregateResult.getMappedResults().size());
        System.out.println(System.currentTimeMillis() - l);
        long lo = System.currentTimeMillis();
        // 这里接收是以map来接收,这里也可以用对象来接收，但是entity中必须有对应的集合
        List<Shop> results = mongoTemplate.aggregate(counts, Shop.class).getMappedResults();
        //上面的student必须是查询的主表名
        System.out.println(results.size());
        System.out.println(System.currentTimeMillis() - lo);
        return results;
    }

    /**
     * 新增
     *
     * @return 返回是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addUser() {
        return "success";
    }

    /**
     * 更新
     *
     * @return 返回是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateShop() {
        Dealer dealer = new Dealer();
        dealer.setId(50000L).setName("经销商测试名称110");
        User user = new User();
        user.setId(1L).setUserName("张三");
        List<User> list = new ArrayList<>();
        list.add(user);
        User user2 = new User();
        user2.setId(2L).setUserName("李四");
        list.add(user2);
        User user3 = new User();
        user3.setId(3L).setUserName("王五");
        list.add(user3);

        // 更新查询到的第一条
//        UpdateResult updateResult = mongoTemplate.updateFirst(new Query(Criteria.where("_id").gte(900)),
//                new Update().set("name", dealer.getName()), Dealer.class);
        // 更新所有满足条件的文档
        UpdateResult updateResult = mongoTemplate.updateMulti(new Query(Criteria.where("_id").gte(888)),
                new Update().set("name", dealer.getName()).set("userShops", list), Dealer.class);
        // 有则更新第一条满足条件的文档，如果没有会自定义id然后将name新增进去
//        UpdateResult updateResult = mongoTemplate.upsert(new Query(Criteria.where("_id").gt(50000)),
//                new Update().set("name", dealer.getName()).set("userShops", list), Dealer.class);
        System.out.println("需要更新的文档数： " + updateResult.getMatchedCount());
        System.out.println("更新了的文档数： " + updateResult.getModifiedCount());
        return "success";
    }

    /**
     * 删除
     *
     * @return 返回是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String removeShop() {
        Query query = new Query(Criteria.where("name").is("经销商测试名称2225"));
        // 删除所有满足条件的文档
        DeleteResult remove = mongoTemplate.remove(query, Dealer.class);
        // 删除所有满足条件的文档
//        List<Dealer> allAndRemove = mongoTemplate.findAllAndRemove(query, Dealer.class);
        // 删除满足条件的第一条，并返回被删除的文档
//        Dealer andRemove = mongoTemplate.findAndRemove(query, Dealer.class);
//        System.out.println("删除的文档名称： " + andRemove.getId());
//        this.find();
        return "success";
    }

    /**
     * 查询工具方法
     */
    private void find() {
        List<Dealer> id = mongoTemplate.find(new Query(Criteria.where("_id").gte(880)), Dealer.class);
        for (Dealer dealer1 : id) {
            System.out.println(dealer1.getId());
        }
    }

    /**
     * 分页查询
     *
     * @return 返回结果
     */
    @Override
    public Page<User> queryUser() {
        // 查询全部并分页，这里第一页是0开始的，Sort.by(Sort.Order.asc("id"))代表从以id为升序排列，降序为Sort.by(Sort.Order.desc("id"))
        //  return myRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Order.asc("id"))));
        // 查询全部并分页，这里第一页是0开始的，Sort.Direction.ASC代表升序排列，降序为Sort.Direction.DESC,"id"表示用id作为排序
        return myRepository.findAll(PageRequest.of(0, 10, Sort.Direction.DESC, "id"));
    }

    /**
     * personRepository
     *
     * @return 返回结果
     */
    @Override
    public Page<User> queryUser2() {
        return myRepository.findAll(PageRequest.of(0, 10, Sort.Direction.DESC, "id"));
    }

    /**
     * 门店新增
     *
     * @return 返回是否成功
     */
    @Override
    public List<Shop> queryShopUser() {
        Query query = new Query(Criteria.where("userShops.userName").in("张三", "李四", "王五"));
        return mongoTemplate.find(query, Shop.class);
    }

    /**
     * 过期时间设置
     *
     * @return 返回是否成功
     */
    @Override
    public String setTime() {
//        mongoTemplate.createCollection(MyData.class);
        MyData myData = new MyData();
        myData.setExpireTime(new Date(System.currentTimeMillis()));
        myData.setTtl("测试");
        mongoTemplate.insert(myData, "myData");
        return "success";
    }

    /**
     * 聚合函数测试
     *
     * @return 返回聚合函数
     */
    @Override
    public Object statistics() {
//        GroupOperation groupOperation = Aggregation.group("shopId")
//                .sum("bottlesNum").as("bottlesNumSum")
//                .sum("tankNum").as("tankNumSum")
//                .last("shopId").as("shopId");
////        Aggregation.match(Criteria.where("tankNum").gte(1).and("shopId").is(13)),
//        // 这里要注意，这里的查询条件是查询分组聚合前的数据，不是之后的数据
//        TypedAggregation<Order> tankNum = Aggregation.newAggregation(Order.class,
//                Aggregation.match(Criteria.where("tankNum").gte(1).and("shopId").is(13)),
//                groupOperation);
////        List<Order> orders = mongoTemplate.aggregate(tankNum, Order.class).getMappedResults();
//        Aggregation agg = Aggregation.newAggregation(
//                // 挑选所需的字段，类似select *，*所代表的字段内容
//                Aggregation.project("id", "dealerCode", "lat", "lng", "isItSale", "currentStockTank", "currentStockBottles", "state", "deleted"),
//                // sql where 语句筛选符合条件的记录
//                Aggregation.match(Criteria.where("state").is(1).and("deleted").is(0))
//        );
////        AggregationResults<SalesMapDto> results = mongoTemplate.aggregate(agg, Shop.class, SalesMapDto.class);
////        List<SalesMapDto> a = results.getMappedResults();
//        List<SalesMapDto> salesMapDtoList = mongoTemplate.find(new Query(Criteria.where("id").lte(20)), SalesMapDto.class, "shop");
        //拼装关联信息
        LookupOperation lookupOperation = LookupOperation.newLookup()
                //关联从表名
                .from("order")
                //主表关联字段
                .localField("id")
                //主表关联字段对应的次表字段
                .foreignField("shopId")
                //查询结果集合名,次表数据是存入的这个集合中
                .as("orders");
        // $match条件筛选
        // MatchOperation matchOperation = new MatchOperation(Criteria.where("newDepartment.departmentName").is("信息开发系统"));

        // 3、Aggregation管道操作（还可以加入$match、$project等其他管道操作，但是得注意先后顺序）
        TypedAggregation aggregation = Aggregation.newAggregation(Shop.class, lookupOperation, lookupOperation);
        // TypedAggregation aggregation = Aggregation.newAggregation(Employee.class, removeDollarOperation, lookupOperation, matchOperation);
//            AggregationResults<SalesMapDto> results = mongoTemplate.aggregate(aggregation, SalesMapDto.class);
//            System.out.println(JSONArray.toJSONString(results.getMappedResults()));
//            return BaseOutput.success().setData(results.getMappedResults());

        // 文档中新增文档
//        mongoTemplate.upsert(new Query(Criteria.where("id").is(49)), new Update().addToSet("orderItems", item), Order.class);
        // 修改文档中文档
//        mongoTemplate.updateMulti(new Query(Criteria.where("orderItems.id").is(75).and("id").is(49)), new Update().set("orderItems.$.quantity", 3).set("totalPrice", 100), Order.class);
//        Order orders = mongoTemplate.findOne(new Query(Criteria.where("id").is(49)), Order.class);
        System.out.println(LocalDateTime.now().plusDays(-66));
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("state").is(1).and("deleted").is(0).and("id").lte(20)),
                // 进入子文档
                Aggregation.unwind("orders"),
                // 子文档查询条件
                Aggregation.match(Criteria.where("orders.createdTime").lte(LocalDateTime.now().plusDays(-66))),
                // f分组     dasdassdasdasdasdasdas
                Aggregation.group("id")
                        .sum("orders.sum").as("orderSum")
                        .last("orgId").as("orgId")
                        .last("provinceId").as("provinceId")
        );
        // 先查询门店维度
//        AggregationResults<TestDto> results2 = mongoTemplate.aggregate(agg, Shop.class, TestDto.class);
//        List<TestDto> smd2 = results2.getMappedResults();

        return null;
    }
}
