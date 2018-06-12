package com.github.uuidcode.tx.test.database;

public enum UserDataSourceKey {
    USER1,
    USER2;

    static {
        USER1.setIndex(1);
        USER2.setIndex(2);
    }

    private Integer index;

    public Integer getIndex() {
        return this.index;
    }

    public UserDataSourceKey setIndex(Integer index) {
        this.index = index;
        return this;
    }
}
