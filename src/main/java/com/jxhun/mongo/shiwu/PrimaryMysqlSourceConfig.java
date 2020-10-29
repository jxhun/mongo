package com.jxhun.mongo.shiwu;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;


/**
 * @author Jxhun
 */
@Configuration
@MapperScan(basePackages = {"com.jxhun.mongo.mapper"},markerInterface = BaseMapper.class, sqlSessionFactoryRef = "systemSqlSessionFactory")
public class PrimaryMysqlSourceConfig {


    @Autowired
    @Qualifier("primaryMysql")
    private DataSource ds;

    @Bean
    @Primary
    public SqlSessionFactory systemSqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(ds);
        //指定mapper xml目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/**/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplateSystem() throws Exception {
        // 使用上面配置的Factory
        return new SqlSessionTemplate(systemSqlSessionFactory());
    }
}
