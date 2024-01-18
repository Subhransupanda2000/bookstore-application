package com.bookstoreapplication.bookstoreapplication.service;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import java.util.List;
import java.util.*;

public interface BookService {
 public Book create(Book book);

 public List<Book> list();

public Long removeItemsById(Long id);

public  Book updateBook(Long id, Book book);

public Book findBookById(Long id);
}
