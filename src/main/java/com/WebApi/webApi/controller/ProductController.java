package com.WebApi.webApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.WebApi.webApi.model.Product;
import com.WebApi.webApi.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    // all products
    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    // one product by id
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    // add product
    @PostMapping("/products")
    public void addProduct(@RequestBody Product entity) {
        service.addProduct(entity);

    }

    // update product
    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable int id, @RequestBody Product entity) {
        service.updateProduct(id, entity);
    }

    // delete product
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
    }

}
