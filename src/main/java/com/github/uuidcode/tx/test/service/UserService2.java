package com.github.uuidcode.tx.test.service;

import static com.github.uuidcode.tx.test.database.UserDataSourceKey.USER1;
import static com.github.uuidcode.tx.test.database.UserDataSourceKey.USER2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.tx.test.dao.user.UserDao;
import com.github.uuidcode.tx.test.domain.User;
import com.github.uuidcode.tx.test.util.CoreUtil;

@Service
@Transactional
public class UserService2 {
    @Autowired
    DaoService daoService;

    public void insert() {
        this.insert(User.of().setUserDataSourceKey(USER1).setName(CoreUtil.uuid()));
        this.insert(User.of().setUserDataSourceKey(USER2).setName(CoreUtil.uuid()));
    }

    public int insert(User user) {
        return this.daoService.getDao(UserDao.class, user.getUserDataSourceKey())
            .insert(user);
    }
}
