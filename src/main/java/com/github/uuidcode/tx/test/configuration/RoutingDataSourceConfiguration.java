package com.github.uuidcode.tx.test.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.uuidcode.tx.test.database.UserDataSource;
import com.github.uuidcode.tx.test.database.UserDataSourceKey;
import com.github.uuidcode.tx.test.database.UserRoutingDataSource;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

//@Configuration
//@EnableTransactionManagement
//@ComponentScan(basePackageClasses = Entry.class)
//@MapperScan(basePackageClasses = com.github.uuidcode.tx.test.dao.user.UserDao.class)
public class RoutingDataSourceConfiguration {
    protected static Logger logger = LoggerFactory.getLogger(RoutingDataSourceConfiguration.class);

    @Bean
    public UserDataSource userDataSource1() {
        if (logger.isDebugEnabled()) {
            logger.debug(">>> userDataSource1");
        }

        UserDataSource dataSource = new UserDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/sandbox1");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUserDataSourceKey(UserDataSourceKey.USER1);
        return dataSource;
    }

    @Bean
    public UserDataSource userDataSource2() {
        if (logger.isDebugEnabled()) {
            logger.debug(">>> userDataSource2");
        }

        UserDataSource dataSource = new UserDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/sandbox2");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUserDataSourceKey(UserDataSourceKey.USER2);
        return dataSource;
    }

    @Bean
    public UserRoutingDataSource userRoutingDataSource() {
        if (logger.isDebugEnabled()) {
            logger.debug(">>> userRoutingDataSource");
        }

        List<UserDataSource> userDataSourceList = new ArrayList<>();
        userDataSourceList.add(this.userDataSource1());
        userDataSourceList.add(this.userDataSource2());

        Map<Object, Object> map = userDataSourceList.stream()
            .collect(toMap(UserDataSource::getUserDataSourceKey, identity()));

        map.forEach((key, dataSource) -> {
            if (logger.isDebugEnabled()) {
                logger.debug(">>> userRoutingDataSource key: {}", key);
                logger.debug(">>> userRoutingDataSource key: {}", dataSource);
            }
        });

        UserRoutingDataSource userRoutingDataSource = new UserRoutingDataSource();
        userRoutingDataSource.setTargetDataSources(map);
        userRoutingDataSource.setDefaultTargetDataSource(userDataSourceList.get(0));
        return userRoutingDataSource;
    }

    @Bean
    public LazyConnectionDataSourceProxy lazyConnectionDataSourceProxy() {
        return new LazyConnectionDataSourceProxy(this.userRoutingDataSource());
    }

    @Bean
    public SqlSessionFactory userSqlSessionFactory() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug(">>> userSqlSessionFactory");
        }

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(this.lazyConnectionDataSourceProxy());
        SqlSessionFactory factory = factoryBean.getObject();
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        if (logger.isDebugEnabled()) {
            logger.debug(">>> userRoutingTransactionManager");
        }

        return new DataSourceTransactionManager(this.lazyConnectionDataSourceProxy());
    }
}
