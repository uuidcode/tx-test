package com.github.uuidcode.tx.test.service;

import static com.github.uuidcode.tx.test.database.UserDataSourceKey.USER1;
import static com.github.uuidcode.tx.test.database.UserDataSourceKey.USER2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.uuidcode.tx.test.dao.user.BookDao;
import com.github.uuidcode.tx.test.domain.Book;
import com.github.uuidcode.tx.test.util.CoreUtil;

@Service
@Transactional
public class BookService {
    @Autowired
    DaoService daoService;

    @Autowired
    UserService2 userService2;

    public void insertAndDelete() {
        Book book = Book.of().setUserDataSourceKey(USER1).setName(CoreUtil.uuid());
        this.insert(book);
        this.delete(book);

        book = Book.of().setUserDataSourceKey(USER2).setName(CoreUtil.uuid());
        this.insert(book);
        this.delete(book);
    }

    public int insert(Book book) {
        return this.daoService.getDao(BookDao.class, book.getUserDataSourceKey())
            .insert(book);
    }

    public int delete(Book book) {
        return this.daoService.getDao(BookDao.class, book.getUserDataSourceKey())
            .delete(book);
    }

    public void doSomething() {
        this.insertAndDelete();
        this.userService2.insert();
    }
}
