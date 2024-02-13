package com.bookstoreapplication.bookstoreapplication.models;


import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
public class Cart {
    @Id
    private  Long cartItemId;
    private String itemId;
    private String quantity;
    private User userAccount;
    @JoinColumn(name = "accountId")
    private Long cartId;
}
