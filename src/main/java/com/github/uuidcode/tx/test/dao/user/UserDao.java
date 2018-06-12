package com.github.uuidcode.tx.test.dao.user;

import java.util.List;

import com.github.uuidcode.tx.test.domain.User;

public interface UserDao {
    List<User> select();
    int insert(User user);
}
