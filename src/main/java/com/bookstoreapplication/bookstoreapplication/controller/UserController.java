package com.bookstoreapplication.bookstoreapplication.controller;
import com.bookstoreapplication.bookstoreapplication.models.User;
import com.bookstoreapplication.bookstoreapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @GetMapping("/{accountId}")
    public User findUserAccountById(@PathVariable Long accountId) {
        User user = userService.findUserAccountById(accountId);
        return user;
    }
    @DeleteMapping("/{accountId}")
    public Long removeByUserAccountId(@PathVariable ("accountId") Long accountId)
    {
        return userService.removeByUserAccountId(accountId);
    }
    @PutMapping("/{accountId}")
    public User updateUser(@PathVariable("accountId") Long accountId, @RequestBody User user) {

        return userService.updateUser(accountId,user);
    }

}

