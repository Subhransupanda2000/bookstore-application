package com.bookstoreapplication.bookstoreapplication.book.user.models;

import lombok.Data;

@Data
public class UserAccount {
    private Long accountId;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String number;

}
