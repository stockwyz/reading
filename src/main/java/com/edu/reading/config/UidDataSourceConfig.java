package com.edu.reading.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

//@Configuration
//@MapperScan(basePackages = "com.reignwood.evaluate.mapper.uid", sqlSessionFactoryRef = "uidSqlSessionFactory")
public class UidDataSourceConfig {
//	@Autowired
	ApplicationContext applicationContext;
	   
//    @Bean(name="uidDataSource")
//    @Qualifier("uidDataSource")
//    @ConfigurationProperties(prefix = "uid.db")
//    public DataSource uidDataSource() {
//        return DruidDataSourceBuilder.create().build();
//    }
    
    @Bean(name = "uidTransactionManager")
    public DataSourceTransactionManager uidTransactionManager() {
    	DataSource dataSource = applicationContext.getBean(DataSource.class);
    	return new DataSourceTransactionManager(dataSource);
//        return new DataSourceTransactionManager(uidDataSource());
    }

    @Bean(name = "uidSqlSessionFactory")
    public SqlSessionFactory uidSqlSessionFactory(@Qualifier("uidDataSource") DataSource uidDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(uidDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath:*.xml"));
        return sessionFactory.getObject();
    }
}
