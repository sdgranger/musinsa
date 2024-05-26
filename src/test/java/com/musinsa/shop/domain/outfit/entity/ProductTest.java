package com.musinsa.shop.domain.outfit.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void createTest() {
        Category category = Category.create("상의");
        Brand brand = Brand.create("A");

        Product product = Product.create(category, brand, 10000L);

        assertEquals(1, brand.getProducts().size());
        assertEquals(brand, brand.getProducts().get(0).getBrand());
        assertEquals(brand, product.getBrand());
        assertEquals(category, product.getCategory());
        assertEquals(10000L, product.getPrice());
    }
}