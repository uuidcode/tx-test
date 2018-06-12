package com.github.uuidcode.tx.test.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.github.uuidcode.tx.test.dao.user.UserDao;
import com.github.uuidcode.tx.test.database.UserDataSourceKey;
import com.github.uuidcode.tx.test.database.UserDataSourceKeyHolder;
import com.github.uuidcode.tx.test.domain.User;
import com.github.uuidcode.tx.test.util.CoreUtil;

import static java.util.Optional.ofNullable;

@Service
@Transactional
public class UserService {
    protected static Logger logger = LoggerFactory.getLogger(UserService.class);

//    @Autowired
    private UserDao userDao;

    public List<User> select() {
        return select(null);
    }

    @Autowired
    UserService userService;

    public List<User> select(User user) {
        try {
            UserDataSourceKeyHolder.set(ofNullable(user)
                .map(User::getUserDataSourceKey)
                .orElse(null));
            return userDao.select();
        } catch (Throwable t) {
            throw t;
        } finally {
            UserDataSourceKeyHolder.remove();
        }
    }

    public int insert(User user) {
        try {
            UserDataSourceKeyHolder.set(user.getUserDataSourceKey());
            return userDao.insert(user);
        } catch (Throwable t) {
            throw t;
        } finally {
            UserDataSourceKeyHolder.remove();
        }
    }

//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insertForRequiredNew(User user) {
        int row = this.insert(user);
        this.debug("insertForRequiredNew");
        return row;
    }

    private void debug(String title) {
        if (logger.isInfoEnabled()) {
            logger.info(">>> ===================================");
        }

        TransactionSynchronizationManager.getResourceMap()
            .forEach((key, value) -> {
                if (logger.isInfoEnabled()) {
                    logger.info(">>> {} {} key: {}", Thread.currentThread().getName(), title, key);
                    logger.info(">>> {} {} value: {}", Thread.currentThread().getName(), title, value);
                }
            });

        if (logger.isInfoEnabled()) {
            logger.info(">>> ===================================");
        }
    }

    public void insert() {
        this.debug("insert0");
        this.userService.insert(User.of().setName(CoreUtil.uuid()));
        this.debug("insert1");
        this.userService.insertForRequiredNew(User.of().setName(CoreUtil.uuid()).setUserDataSourceKey(UserDataSourceKey.USER2));
        this.debug("insert2");
        this.userService.insert(User.of().setName(CoreUtil.uuid()));
        this.debug("insert3");
    }
}
