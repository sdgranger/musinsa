package com.musinsa.shop.domain.rank.service;

import java.util.Optional;

public interface CategoryNamingCenter {
    Optional<CategoryTag> findByName(String categoryName);
}
