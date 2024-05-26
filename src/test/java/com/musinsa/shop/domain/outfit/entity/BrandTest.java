package com.musinsa.shop.domain.outfit.entity;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrandTest {

    @Test
    void createAndAddProductTest() {
        Brand brand = Brand.create("A");
        Product product = Product.create(Category.create("상의"), brand, 100000L);
        brand.addProduct(product);

        assertEquals(1, brand.getProducts().size());
        assertEquals(product, brand.getProducts().get(0));
        assertEquals(brand, brand.getProducts().get(0).getBrand());
        assertEquals("상의", brand.getProducts().get(0).getCategory().getName());
    }
}