package com.WebApi.webApi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, columnDefinition = "varchar(255) default 'Default Name'")
    private String name;

    @Column(nullable = true, columnDefinition = "varchar(255) default 'Default Description'")
    private String description;

    @Column(nullable = false, columnDefinition = "decimal(10,2) default 0.0")
    private BigDecimal price;

    @Column(nullable = false, columnDefinition = "int default 1")
    private int quantity;

    @Column(nullable = true, columnDefinition = "varchar(255) default 'default.png'")
    private String image;

    @Column(nullable = true, columnDefinition = "varchar(255) default 'Uncategorized'")
    private String category;

    @Column(nullable = true, columnDefinition = "varchar(255) default 'Generic'")
    private String brand;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean available;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
