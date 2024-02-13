package com.bookstoreapplication.bookstoreapplication.repository;


import com.bookstoreapplication.bookstoreapplication.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserEntityRepository extends CrudRepository<UserEntity, Long> {
}