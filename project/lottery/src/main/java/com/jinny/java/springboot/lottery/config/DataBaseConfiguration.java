package com.jinny.java.springboot.lottery.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@MapperScan(value="com.jinny.java.springboot.lottery.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class DataBaseConfiguration {
    @Bean(name ="dataSource")
    @Primary
    @ConfigurationProperties(prefix ="spring.datasource")
    public DataSource dbDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean(name ="sqlSessionFactory")
    @Primary
    public SqlSessionFactory gamecraftSqlSessionFactory(@Qualifier("dataSource") DataSource mysqlDataSource, ApplicationContext applicationContext)throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean =new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(mysqlDataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name ="sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate gamecraftSqlSessionTemplate(SqlSessionFactory dbSqlSessionFactory)throws Exception {
        return new SqlSessionTemplate(dbSqlSessionFactory);
    }

}
