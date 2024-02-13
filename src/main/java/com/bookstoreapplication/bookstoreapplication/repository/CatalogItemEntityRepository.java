package com.bookstoreapplication.bookstoreapplication.repository;


import com.bookstoreapplication.bookstoreapplication.entities.CatalogItemEntity;
import org.springframework.data.repository.CrudRepository;

public interface CatalogItemEntityRepository extends CrudRepository<CatalogItemEntity, Long> {
}