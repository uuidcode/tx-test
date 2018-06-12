package com.github.uuidcode.tx.test.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.github.uuidcode.tx.test.util.CoreUtil;

public class UserRoutingDataSource extends AbstractRoutingDataSource {
    protected static Logger logger = LoggerFactory.getLogger(UserRoutingDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        UserDataSourceKey userDataSourceKey = UserDataSourceKeyHolder.get();

        if (logger.isDebugEnabled()) {
            logger.debug(">>> determineCurrentLookupKey userDataSourceKey: {}", CoreUtil.toJson(userDataSourceKey));
        }

        return userDataSourceKey;
    }
}
