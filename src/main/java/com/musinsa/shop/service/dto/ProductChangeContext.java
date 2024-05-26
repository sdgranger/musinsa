package com.musinsa.shop.service.dto;

import java.util.Map;

public class ProductChangeContext {
    private final Map<String, Long> toBeChangeablePriceByCategoryName;
    public ProductChangeContext(Map<String, Long> toBeChangeablePriceByCategoryName) {
        this.toBeChangeablePriceByCategoryName = toBeChangeablePriceByCategoryName;
    }

    public Long getByCategoryName(String categoryName) {
        return toBeChangeablePriceByCategoryName.get(categoryName);
    }
}
