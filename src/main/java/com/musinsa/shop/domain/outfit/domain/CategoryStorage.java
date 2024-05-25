package com.musinsa.shop.domain.outfit.domain;

import com.musinsa.shop.error.NotFoundException;

import java.util.Map;

public class CategoryStorage {
    private Map<String, Category> byCategoryName;
    public CategoryStorage(Map<String, Category> byCategoryName) {
        this.byCategoryName = byCategoryName;
    }

    public Category find(String categoryName) {
        Category category = byCategoryName.get(categoryName);
        if (category == null) {
            throw new NotFoundException();
        }
        return category;
    }

    public int size() {
        return byCategoryName.size();
    }
}
