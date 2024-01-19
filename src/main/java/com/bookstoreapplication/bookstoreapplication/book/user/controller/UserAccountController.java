package com.bookstoreapplication.bookstoreapplication.book.user.controller;
import com.bookstoreapplication.bookstoreapplication.book.user.models.UserAccount;
import com.bookstoreapplication.bookstoreapplication.book.user.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/accounts")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @PostMapping
    public UserAccount create(@RequestBody UserAccount userAccount){
        return userAccountService.create(userAccount);
    }
    @GetMapping("/{accountId}")
    public UserAccount findUserAccountById(@PathVariable Long accountId) {
        UserAccount userAccount = userAccountService.findUserAccountById(accountId);
        return userAccount;
    }
    @DeleteMapping("/{accountId}")
    public Long removeByUserAccountId(@PathVariable ("accountId") Long accountId)
    {
        return userAccountService.removeByUserAccountId(accountId);
    }
    @PutMapping("/{accountId}")
    public UserAccount updateUser(@PathVariable("accountId") Long accountId, @RequestBody UserAccount userAccount) {

        return userAccountService.updateUser(accountId,userAccount);
    }
    @GetMapping
    public List<UserAccount> listItems() {
        return userAccountService.list();
    }
}

