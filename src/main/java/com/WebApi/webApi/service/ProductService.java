package com.WebApi.webApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public Product addProduct(Product product, MultipartFile imageFile) throws Exception {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageBytes(imageFile.getBytes());

        return repo.save(product);
    }

    public void updateProduct(int id, Product product) {
        // check if product exists
        Product existingProduct = repo.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setAvailable(product.getAvailable());
            existingProduct.setImageName(product.getImageName());
            existingProduct.setImageType(product.getImageType());
            existingProduct.setImageBytes(product.getImageBytes());
            repo.save(existingProduct);
        } else {
            System.out.println("Product not found");
        }
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }

}
