package com.musinsa.shop.domain.outfit.entity;

public class CategoryMock extends Category {

    public CategoryMock(Long id, String name) {
        super(id, name);
    }

    public static Category create(Long id, String name) {
        return new CategoryMock(id, name);
    }
}