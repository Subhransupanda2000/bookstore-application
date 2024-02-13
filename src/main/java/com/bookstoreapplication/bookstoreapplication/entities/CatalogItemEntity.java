package com.bookstoreapplication.bookstoreapplication.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "catalog_item")
@Data
@NoArgsConstructor
public class CatalogItemEntity {

    @Id
    private Long id;

    private String name;

    private Float price;

    public CatalogItemEntity(Long id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}