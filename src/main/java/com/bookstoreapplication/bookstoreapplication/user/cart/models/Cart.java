package com.bookstoreapplication.bookstoreapplication.user.cart.models;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class Cart {
    @Id
    private  Long cartItemId;
    private Long itemId;
    private int quantity;
}
