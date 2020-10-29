package com.jxhun.mongo.shiwu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.SessionSynchronization;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Jxhun
 */
@Component

public class MongoUtils {

    public void setSessionSynchronizationForTransactionBegin() {

        mongoTemplate.setSessionSynchronization(SessionSynchronization.ALWAYS);

    }

    public void setSessionSynchronizationForTransactionCompletion() {

          mongoTemplate.setSessionSynchronization(SessionSynchronization.ON_ACTUAL_TRANSACTION);

    }

    @Autowired
    MongoTemplate mongoTemplate;

}