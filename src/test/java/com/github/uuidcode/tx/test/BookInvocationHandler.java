package com.github.uuidcode.tx.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookInvocationHandler implements InvocationHandler {
    protected static Logger logger = LoggerFactory.getLogger(BookInvocationHandler.class);

    private Book book;

    public Book getBook() {
        return this.book;
    }

    public BookInvocationHandler setBook(Book book) {
        this.book = book;
        return this;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (logger.isDebugEnabled()) {
            logger.debug(">>> invoke method: {}", method.getName());
        }

        return method.invoke(this.book, args);
    }
}
