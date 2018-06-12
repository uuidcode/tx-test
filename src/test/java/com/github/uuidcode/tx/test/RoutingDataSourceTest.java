package com.github.uuidcode.tx.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.github.uuidcode.tx.test.configuration.RoutingDataSourceConfiguration;
import com.github.uuidcode.tx.test.database.UserDataSourceKey;
import com.github.uuidcode.tx.test.domain.User;
import com.github.uuidcode.tx.test.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
    classes = RoutingDataSourceConfiguration.class)
public class RoutingDataSourceTest {
    @Autowired
    UserService userService;

    @Test
    public void test() {
        int sizeOfUser1 = this.userService.select(User.of().setUserDataSourceKey(UserDataSourceKey.USER1)).size();
        int sizeOfUser2 = this.userService.select(User.of().setUserDataSourceKey(UserDataSourceKey.USER2)).size();

        this.userService.insert();

        assertThat(this.userService.select(User.of().setUserDataSourceKey(UserDataSourceKey.USER1)).size()).isGreaterThan(sizeOfUser1);
        assertThat(this.userService.select(User.of().setUserDataSourceKey(UserDataSourceKey.USER2)).size()).isGreaterThan(sizeOfUser2);
    }
}