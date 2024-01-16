package com.bookstoreapplication.bookstoreapplication.controller;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import com.bookstoreapplication.bookstoreapplication.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping
    public Book create(Book book) {
        return bookService.create(book);
    }
    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }
    @GetMapping
    public Book getBookById(@PathVariable("id") long id) {
        Book book = BookService.getBookById(id);
        return book;
    }
    @PutMapping("/{id}")
    public Book updateUser(@PathVariable long id, @RequestBody Book book) {
        return bookService.updateUser(id, book);
    }
    @DeleteMapping
    public long removeBookById(@PathVariable("id") long id) {
        return bookService.removeBookById(id);
    }
}
