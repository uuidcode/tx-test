package com.github.uuidcode.tx.test.domain;

import java.util.Date;

import com.github.uuidcode.tx.test.database.UserDataSourceKey;

public class User {
    private Long userId;
    private String name;
    private Date regDatetime;
    private UserDataSourceKey userDataSourceKey;

    public UserDataSourceKey getUserDataSourceKey() {
        return this.userDataSourceKey;
    }

    public User setUserDataSourceKey(UserDataSourceKey userDataSourceKey) {
        this.userDataSourceKey = userDataSourceKey;
        return this;
    }

    public static User of() {
        return new User();
    }

    public Date getRegDatetime() {
        return this.regDatetime;
    }

    public User setRegDatetime(Date regDatetime) {
        this.regDatetime = regDatetime;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public User setName(String name) {
        this.name = name;
        return this;
    }    

    public Long getUserId() {
        return this.userId;
    }
    
    public User setUserId(Long userId) {
        this.userId = userId;
        return this;
    }
}
