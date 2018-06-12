package com.github.uuidcode.tx.test.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.github.uuidcode.tx.test.dao.user.UserDao;
import com.github.uuidcode.tx.test.util.CoreUtil;

@Configuration
@MapperScan(value = "com.github.uuidcode.tx.test.dao.user2",
    sqlSessionFactoryRef = "userSqlSessionFactory2")
public class User2DataSourceConfiguration {
    @Bean
    public DataSource userDataSource2() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/sandbox2");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

    @Bean
    public SqlSessionFactory userSqlSessionFactory2() throws Exception {
        return CoreUtil.sqlSessionFactory(this.userDataSource2(), UserDao.class);
    }

    @Bean
    public PlatformTransactionManager transactionManager2() {
        return new DataSourceTransactionManager(this.userDataSource2());
    }
}
