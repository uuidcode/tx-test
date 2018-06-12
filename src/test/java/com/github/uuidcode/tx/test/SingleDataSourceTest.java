package com.github.uuidcode.tx.test;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.github.uuidcode.tx.test.domain.User;
import com.github.uuidcode.tx.test.service.UserService;
import com.github.uuidcode.tx.test.util.CoreUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
    classes = com.github.uuidcode.tx.test.configuration.SingleDataSourceConfiguration.class)
public class SingleDataSourceTest {
    protected static Logger logger = LoggerFactory.getLogger(SingleDataSourceTest.class);

    @Autowired
    UserService userService;

    @Test
    public void test() {
        this.userService.insert(User.of().setName(CoreUtil.uuid()));
        List<User> user = this.userService.select();

        if (logger.isDebugEnabled()) {
            logger.debug(">>> test user: {}", CoreUtil.toJson(user));
        }
    }
}