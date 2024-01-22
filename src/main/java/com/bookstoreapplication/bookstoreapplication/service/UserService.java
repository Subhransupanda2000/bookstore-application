package com.bookstoreapplication.bookstoreapplication.service;

import com.bookstoreapplication.bookstoreapplication.models.User;
import org.springframework.stereotype.Service;

@Service

public interface UserService {
    public User updateUser(Long accountId, User user);

    public Long removeByUserAccountId(Long accountId);

    public User findUserAccountById(Long accountId);

    public User createUser(User user);


}
