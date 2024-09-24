package com.WebApi.webApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.WebApi.webApi.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
