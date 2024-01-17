package com.bookstoreapplication.bookstoreapplication.models;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Book {
@Id
    private Long id;
    private String name;
    private Float price;
}

