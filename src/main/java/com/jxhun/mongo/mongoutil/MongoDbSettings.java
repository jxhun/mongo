package com.jxhun.mongo.mongoutil;

import com.mongodb.MongoClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MongoDb 设置
 *
 * @author Jxhun
 */
@Configuration
public class MongoDbSettings {
    // <mongo:options connections-per-host="500" socket-timeout="5000" max-wait-time="5000"
//    threads-allowed-to-block-for-connection-multiplier="1000"
//    connect-timeout="30000"/>
    @Bean
    public MongoClientOptions mongoOptions() {
        return MongoClientOptions
                .builder()
                .maxConnectionIdleTime(60000)
                .build();
    }

}