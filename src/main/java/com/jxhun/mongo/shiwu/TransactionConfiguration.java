package com.jxhun.mongo.shiwu;

import com.atomikos.icatch.jta.UserTransactionManager;

import org.springframework.beans.factory.ObjectProvider;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Primary;

import org.springframework.core.env.Environment;

import org.springframework.data.mongodb.MongoDbFactory;

import org.springframework.data.mongodb.MongoTransactionManager;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.transaction.jta.JtaTransactionManager;

import javax.sql.DataSource;

import javax.transaction.UserTransaction;

import java.util.Properties;

/**
 * atomikos 分布式事务配置类
 * @author Jxhun
 */
@Configuration

public class TransactionConfiguration {

    @Autowired
    private Environment env;

//    @Bean(name = TransactionManagerConstant.MONGO_TRANSACTION_MANAGER)

    private MongoTransactionManager mongoTransactionManager(MongoDbFactory factory) {

        return new MongoTransactionManager(factory);

    }

//    @Bean

    public DataSourceTransactionManager transactionManager(DataSource dataSource,

              ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {

        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);

        transactionManagerCustomizers.ifAvailable((customizers) -> customizers.customize(transactionManager));

        return transactionManager;

    }

    @Bean(name = "primaryMysql")

    @Primary

    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")

    public DataSource primaryDataSource() throws Exception {

        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();

        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");

        ds.setUniqueResourceName("primaryMysql");

        ds.setPoolSize(5);

        ds.setXaProperties(build("spring.datasource.dynamic.datasource.master."));

        return ds;

    }

    @Bean(name = TransactionManagerConstant.TRANSACTION_MANAGER)

    @Primary

    @Autowired

    public JtaTransactionManager transactionManager(MongoDbFactory factory, MongoUtils mongoUtils) {

        UserTransactionManager userTransactionManager = new UserTransactionManager();

        UserTransaction userTransaction = new JtaTransactionImp(mongoTransactionManager(factory), mongoUtils);

        return new JtaTransactionManager(userTransaction, userTransactionManager);

    }

    private Properties build(String prefix) {

        Properties prop = new Properties();

        prop.put("url", env.getProperty(prefix + "url"));

        prop.put("username", env.getProperty(prefix + "username"));

        prop.put("password", env.getProperty(prefix + "password"));

        prop.put("driverClassName", env.getProperty(prefix + "driver-class-name"));
        return prop;

    }

}