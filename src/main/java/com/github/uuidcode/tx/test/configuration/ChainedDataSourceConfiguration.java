package com.github.uuidcode.tx.test.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.uuidcode.tx.test.Entry;

@Configuration
@ComponentScan(basePackageClasses = Entry.class)
@EnableTransactionManagement
public class ChainedDataSourceConfiguration {
    @Autowired
    PlatformTransactionManager transactionManager1;

    @Autowired
    PlatformTransactionManager transactionManager2;

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager() {
        return new ChainedTransactionManager(this.transactionManager1, this.transactionManager2);
    }
}
