package com.github.uuidcode.tx.test.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.github.uuidcode.tx.test.database.UserDataSourceKey;
import com.github.uuidcode.tx.test.util.CoreUtil;

@Service
public class DaoService {
    protected static Logger logger = LoggerFactory.getLogger(DaoService.class);

    @Resource
    ApplicationContext context;

    public <T> T getDao(Class<T> daoClass) {
        return this.getDao(daoClass, null);
    }

    public <T> T getDao(Class<T> daoClass, UserDataSourceKey userDataSourceKey) {
        String name = CoreUtil.firstCharLowerCase(daoClass.getSimpleName());

        if (userDataSourceKey != null) {
           name += userDataSourceKey.getIndex();
        }

        if (logger.isDebugEnabled()) {
            logger.debug(">>> getDao name: {}", CoreUtil.toJson(name));
        }

        return this.context.getBean(name, daoClass);
    }
}
