package com.jxhun.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author Jxhun
 */
@Data
// 这里不加上Document会导致expireAfterSeconds失效
@Document(collection = "myData")
public class MyData {

    @Id
    private String _id;

    //过期时间为1000秒
    @Indexed(expireAfterSeconds = 60)
    private String ttl;

    @Indexed(expireAfterSeconds = 60)
    private Date expireTime;

}
