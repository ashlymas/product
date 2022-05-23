package com.example.product.controller;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.rest.ProductModel;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/product")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> save(@RequestBody ProductModel model) {
        try {
            Product product = new Product(model);
            return ResponseEntity.ok(productRepository.save(product));
        } catch (Exception c) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(value = "/get-product-by-category")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam("category") String category,
                                              @RequestParam("pageNum") int pageNum,
                                              @RequestParam("max") int max) {
        try {
            Pageable pageable = PageRequest.of(pageNum, max, Sort.by("createdAt").descending());
            return  ResponseEntity.ok(productRepository.findAllByCategory(category, pageable));
        } catch (Exception c) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
