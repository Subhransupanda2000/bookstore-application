package com.bookstoreapplication.bookstoreapplication.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private float price;

    public Book(long id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
