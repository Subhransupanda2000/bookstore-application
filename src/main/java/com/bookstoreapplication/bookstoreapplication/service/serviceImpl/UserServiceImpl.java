package com.bookstoreapplication.bookstoreapplication.service.serviceImpl;

import com.bookstoreapplication.bookstoreapplication.models.User;
import com.bookstoreapplication.bookstoreapplication.repository.UserEntityRepository;
import com.bookstoreapplication.bookstoreapplication.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bookstoreapplication.bookstoreapplication.entities.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User updateUser(Long accountId, User user) {
        log.info(user.toString());
        return user;
    }

    @Override
    public Long removeByUserAccountId(Long accountId) {
        return accountId;
    }

    @Override
    public User findUserAccountById(Long accountId) {
        return new User();
    }

    @Override
    public User createUser(User user) {
        user.setId(System.nanoTime());
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));
        UserEntity userEntity = userEntityRepository.save(user.toEntity());
        return new User().fromEntity(userEntity);
    }


}
