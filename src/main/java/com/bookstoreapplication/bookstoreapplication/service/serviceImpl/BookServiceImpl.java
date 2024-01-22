package com.bookstoreapplication.bookstoreapplication.service.serviceImpl;

import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Override
    public Book create(Book book) {
        book.setId(System.currentTimeMillis());
        return book;
    }

    @Override
    public List<Book> list() {
        Book book = new Book();
        book.setId(System.currentTimeMillis());
        book.setName("first Book");
        book.setPrice(390.40f);
        return List.of(book);
    }

    @Override
    public Long removeItemsById(Long id) {
        return id;
    }

    @Override
    public Book updateBook(Long id, Book book) {
        log.info(book.toString());
        return book;
    }

    @Override
    public Book findBookById(Long id) {
        return new Book();
    }
}
