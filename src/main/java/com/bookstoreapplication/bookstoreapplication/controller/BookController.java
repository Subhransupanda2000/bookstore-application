package com.bookstoreapplication.bookstoreapplication.controller;

import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookitems")
public class BookController {

    @Autowired
    private BookService bookService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createNewItem(@RequestBody Book book) {
        return bookService.create(book);
    }

    @GetMapping
    public List<Book> listItems() {
        return bookService.list();
    }

    @GetMapping("/{id}")
    public Book findBookById(@PathVariable Long id) {
        Book book = bookService.findBookById(id);
        return book;
    }

    @DeleteMapping
    public Long removeItemById(Long id) {
        return bookService.removeItemsById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {

        return bookService.updateBook(id, book);
    }

}
