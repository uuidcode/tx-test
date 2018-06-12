package com.github.uuidcode.tx.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.github.uuidcode.tx.test.service.UserService2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
    classes = {
        com.github.uuidcode.tx.test.configuration.User1DataSourceConfiguration.class,
        com.github.uuidcode.tx.test.configuration.User2DataSourceConfiguration.class,
        com.github.uuidcode.tx.test.configuration.ChainedDataSourceConfiguration.class
    })
public class TwoDataSourceTest {
    @Autowired
    UserService2 userService2;

    @Test
    public void test() {
        this.userService2.insert();
    }
}