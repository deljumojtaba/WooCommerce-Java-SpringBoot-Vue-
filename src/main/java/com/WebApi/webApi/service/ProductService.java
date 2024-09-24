package com.WebApi.webApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.WebApi.webApi.model.Product;
import com.WebApi.webApi.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void addProduct(Product product) {
        repo.save(product);
    }

    public void updateProduct(int id, Product product) {
        // check if product exists
        Product existingProduct = repo.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            repo.save(existingProduct);
        } else {
            System.out.println("Product not found");
        }
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

}
