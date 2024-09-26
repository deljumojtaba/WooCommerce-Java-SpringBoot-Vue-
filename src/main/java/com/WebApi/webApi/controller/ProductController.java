package com.WebApi.webApi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.WebApi.webApi.model.Product;
import com.WebApi.webApi.service.ProductService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    // all products
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(service.getProducts());
    }

    // one product by id
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {

        Product product = service.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok().body(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // add product
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart("product") Product entity,
            @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            if (imageFile == null || imageFile.isEmpty()) {
                return new ResponseEntity<String>("Image file is missing", HttpStatus.BAD_REQUEST);
            }
            Product product = service.addProduct(entity, imageFile);
            return ResponseEntity.ok().body(product);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update product
    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart("product") Product entity,
            @RequestPart(name = "imageFile", required = false) MultipartFile imageFile) {
        try {
            if (imageFile != null) {
                entity.setImageName(imageFile.getOriginalFilename());
                entity.setImageType(imageFile.getContentType());
                entity.setImageBytes(imageFile.getBytes());
            } else {
                Product existingProduct = service.getProductById(id);
                entity.setImageName(existingProduct.getImageName());
                entity.setImageType(existingProduct.getImageType());
                entity.setImageBytes(existingProduct.getImageBytes());
            }
            service.updateProduct(id, entity);
            return ResponseEntity.ok().body("Product updated");
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete product
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = service.getProductById(id);

        if (product != null) {
            service.deleteProduct(id);
            return ResponseEntity.ok().body("Product deleted");
        }
        return ResponseEntity.notFound().build();
    }

    // get product image
    @GetMapping("/product/image/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable int id) {
        Product product = service.getProductById(id);
        byte[] imageBytes = product.getImageBytes();

        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageBytes);
    }

    // search products
    @GetMapping("/products/search/{keyword}")
    public ResponseEntity<List<Product>> searchProducts(@PathVariable String keyword) {
        return ResponseEntity.ok().body(service.searchProducts(keyword));
    }

}
