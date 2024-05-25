package com.musinsa.shop.domain.outfit.entity;

public class ProductMock extends Product {

    public ProductMock(Long id, Category category, Brand brand, long price) {
        super(id, category, brand, price);
    }

    public static Product create(Long id, Category category, Brand brand, long price) {
        return new ProductMock(id, category, brand, price);
    }
}