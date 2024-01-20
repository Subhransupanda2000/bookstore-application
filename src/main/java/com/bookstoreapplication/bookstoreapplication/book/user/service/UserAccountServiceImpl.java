package com.bookstoreapplication.bookstoreapplication.book.user.service;
import com.bookstoreapplication.bookstoreapplication.book.user.models.UserAccount;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserAccountServiceImpl implements UserAccountService{

    @Override
    public UserAccount updateUser(Long accountId, UserAccount userAccount) {
        log.info(userAccount.toString());
        return userAccount;
    }

    @Override
    public Long removeByUserAccountId(Long accountId) {
        return accountId;
    }

    @Override
    public UserAccount findUserAccountById(Long accountId) {
        return new UserAccount();
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        userAccount.setAccountId(System.currentTimeMillis());
        return userAccount;
    }

    @Override
    public List<UserAccount> list() {
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountId(System.currentTimeMillis());
        userAccount.setNumber("9778336702");
        userAccount.setEmail("abcd@gmail.com");
        userAccount.setGender("male");
        userAccount.setFirstName("john");
        userAccount.setLastName("maxy");
        return List.of(userAccount);
    }
}
