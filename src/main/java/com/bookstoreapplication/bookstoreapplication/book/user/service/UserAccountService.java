package com.bookstoreapplication.bookstoreapplication.book.user.service;

import com.bookstoreapplication.bookstoreapplication.book.user.models.UserAccount;

import java.util.List;

public interface UserAccountService {
 public    UserAccount updateUser(Long accountId, UserAccount userAccount);

  public   Long removeByUserAccountId(Long accountId);

  public  UserAccount findUserAccountById(Long accountId);

  public   UserAccount create(UserAccount userAccount);

public List<UserAccount> list();
}
