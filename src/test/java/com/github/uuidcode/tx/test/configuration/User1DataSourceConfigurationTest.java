package com.github.uuidcode.tx.test.configuration;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import com.github.uuidcode.tx.test.dao.user.UserDao;
import com.github.uuidcode.tx.test.util.CoreUtil;

public class User1DataSourceConfigurationTest {
    protected static Logger logger = LoggerFactory.getLogger(User1DataSourceConfigurationTest.class);

    @Test
    public void test() {
        String classPath = ClassUtils.convertClassNameToResourcePath(UserDao.class.getPackage().getName());

        if (logger.isDebugEnabled()) {
            logger.debug(">>> test classPath: {}", CoreUtil.toJson(classPath));
        }
    }

}