package com.jxhun.mongo.controller;

import com.jxhun.mongo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Jxhun
 */
@Controller
public class TestController {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public TestController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping("/add")
    @ResponseBody
    public String add() {
        User user = new User();
//        user.setUsername("ym");
        user.setPassword("12321321");
        System.out.println(mongoTemplate.getDb().getName());
        System.out.println("----------------------------------------------");
        System.out.println(mongoTemplate.insert(user, "user"));
        return "success";
    }

    @PostMapping("/list")
    @ResponseBody
    public List<User> list() {
        return mongoTemplate.findAll(User.class);
    }

    @PostMapping("/test")
    public void test(HttpServletResponse response) {
        response.encodeRedirectURL("www.baidu.com");
    }
}
