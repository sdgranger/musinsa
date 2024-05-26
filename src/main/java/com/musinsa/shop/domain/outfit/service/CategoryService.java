package com.musinsa.shop.domain.outfit.service;

import com.musinsa.shop.domain.outfit.entity.Category;
import com.musinsa.shop.domain.outfit.repository.CategoryRepository;
import com.musinsa.shop.domain.outfit.entity.CategoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryStorage find() {
        Map<String, Category> byCategoryName = categoryRepository.findAll().stream()
                .collect(Collectors.toMap(Category::getName, Function.identity()));

        return new CategoryStorage(byCategoryName);
    }
}
