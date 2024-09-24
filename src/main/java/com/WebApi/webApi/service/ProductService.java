package com.WebApi.webApi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.WebApi.webApi.model.Product;

@Service
public class ProductService {

    List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Product 1", "This is product 1", 10.0),
            new Product(2, "Product 2", "This is product 2", 20.0),
            new Product(3, "Product 3", "This is product 3", 30.0)));

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst()
                .orElse(new Product(1000, "No Item", "No Description", 0.0));
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void updateProduct(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getId() == id) {
                // Update fields except for the id
                p.setName(product.getName());
                p.setDescription(product.getDescription());
                p.setPrice(product.getPrice());
                return;
            }
        }
    }

    public void deleteProduct(int id) {
        products.removeIf(p -> p.getId() == id);
    }

}
