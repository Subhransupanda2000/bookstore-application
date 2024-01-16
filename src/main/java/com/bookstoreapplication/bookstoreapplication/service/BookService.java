package com.bookstoreapplication.bookstoreapplication.service;
import com.bookstoreapplication.bookstoreapplication.models.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BookService {



 public Book updateUser(long id, Book updatedUser) ;
 public List<Book> getBooks();
 public Book create(Book book);
 public long removeBookById(long id);


}
