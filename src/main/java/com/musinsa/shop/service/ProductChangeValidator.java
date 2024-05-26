package com.musinsa.shop.service;

import com.musinsa.shop.domain.outfit.entity.CategoryStorage;
import com.musinsa.shop.error.InvalidArgumentException;
import com.musinsa.shop.service.dto.BrandProductChange;
import org.springframework.stereotype.Component;

@Component
public class ProductChangeValidator {
    public void validateCategorySize(CategoryStorage categories, BrandProductChange brandProductChange) {
        if (brandProductChange.getCategoryProducts().size() != categories.size()) {
            throw new InvalidArgumentException();
        }
    }
}
