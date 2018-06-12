package com.github.uuidcode.tx.test.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.uuidcode.tx.test.util.CoreUtil;

public class UserDataSourceKeyHolder {
    protected static Logger logger = LoggerFactory.getLogger(UserDataSourceKeyHolder.class);

    private static ThreadLocal<UserDataSourceKey> threadLocal
        = new ThreadLocal<>();

    public static void set(UserDataSourceKey userDataSourceKey) {
        if (logger.isDebugEnabled()) {
            logger.debug(">>> UserDataSourceKeyHolder set: {}", CoreUtil.toJson(userDataSourceKey));
        }

        threadLocal.set(userDataSourceKey);
    }

    public static UserDataSourceKey get() {
        UserDataSourceKey userDataSourceKey = threadLocal.get();

        if (logger.isDebugEnabled()) {
            logger.debug(">>> UserDataSourceKeyHolder get: {}", CoreUtil.toJson(userDataSourceKey));
        }

        return userDataSourceKey;
    }

    public static void remove() {
        threadLocal.remove();
    }
}
