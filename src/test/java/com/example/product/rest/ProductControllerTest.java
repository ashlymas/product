package com.example.product.rest;

import com.example.product.entity.Product;
import com.example.product.rest.ProductModel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import java.util.List;

class ProductControllerTest {

   RestTemplate restTemplate = new RestTemplate();

    @Test
    void save() {
        ProductModel productModel = new ProductModel();
        productModel.setBrand("Brand");
        productModel.setName("Name");
        productModel.setDescription("des....");
        productModel.setTags(List.of("Red", "Gtare"));
        productModel.setCategory("Cat");

        HttpStatus status = restTemplate.postForEntity("localhost:9090/v1/product/save", productModel, Product.class).getStatusCode();

        Assertions.assertTrue(status == HttpStatus.OK);
    }

    @Test
    void getProductByCategory() {
        HttpStatus status = restTemplate.getForEntity("localhost:9090/v1/product/get-product-by-category?category=apparel&pageNum=2&max=1", List.class).getStatusCode();

        Assertions.assertTrue(status == HttpStatus.OK);
    }
}