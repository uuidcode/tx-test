package com.github.uuidcode.tx.test.database;

import org.apache.commons.dbcp.BasicDataSource;

public class UserDataSource extends BasicDataSource {
    private UserDataSourceKey userDataSourceKey;

    public UserDataSourceKey getUserDataSourceKey() {
        return this.userDataSourceKey;
    }

    public UserDataSource setUserDataSourceKey(UserDataSourceKey userDataSourceKey) {
        this.userDataSourceKey = userDataSourceKey;
        return this;
    }
}
