package com.github.uuidcode.tx.test.dao.user;

import java.util.List;

import com.github.uuidcode.tx.test.domain.Book;

public interface BookDao {
    List<Book> select();
    int insert(Book book);
    int delete(Book book);
}
