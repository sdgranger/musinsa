package com.musinsa.shop.config;

import com.musinsa.shop.domain.outfit.entity.Category;
import com.musinsa.shop.domain.outfit.repository.CategoryRepository;
import com.musinsa.shop.domain.rank.service.CategoryNamingCenter;
import com.musinsa.shop.domain.rank.service.CategoryTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ConfigurationCategory implements CategoryNamingCenter {
    private final CategoryRepository categoryRepository;


    @Override
    public Optional<CategoryTag> findByName(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);

        return category.map(this::toCategoryTag);
    }

    private CategoryTag toCategoryTag(Category category) {
        return new CategoryTag(category.getId(), category.getName());
    }

}
