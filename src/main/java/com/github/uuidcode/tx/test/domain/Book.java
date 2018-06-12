package com.github.uuidcode.tx.test.domain;

import java.util.Date;

import com.github.uuidcode.tx.test.database.UserDataSourceKey;

public class Book {
    private Long bookId;
    private String name;
    private Date regDatetime;
    private UserDataSourceKey userDataSourceKey;

    public Long getBookId() {
        return this.bookId;
    }

    public Book setBookId(Long bookId) {
        this.bookId = bookId;
        return this;
    }

    public UserDataSourceKey getUserDataSourceKey() {
        return this.userDataSourceKey;
    }

    public Book setUserDataSourceKey(UserDataSourceKey userDataSourceKey) {
        this.userDataSourceKey = userDataSourceKey;
        return this;
    }

    public static Book of() {
        return new Book();
    }

    public Date getRegDatetime() {
        return this.regDatetime;
    }

    public Book setRegDatetime(Date regDatetime) {
        this.regDatetime = regDatetime;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Book setName(String name) {
        this.name = name;
        return this;
    }
}
