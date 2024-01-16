package com.bookstoreapplication.bookstoreapplication.service;

import com.bookstoreapplication.bookstoreapplication.models.Book;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class BookServiceImpl implements BookService {
    @Override
    public long removeBookById(long id) {
        return id;
    }

    @Override
    public Book updateUser(long id, Book book) {
        log.info(book.toString());
        return book;
    }

    @Override
    public List<Book> getBooks() {
        return getBooks();
    }

    @Override
    public Book create(Book book) {
        log.info(book.toString());
        return book;
    }

    @Override
    public Book getBookById(long id) {
        return new id;
    }
}
