package com.musinsa.shop.domain.rank.repository;

import com.musinsa.shop.domain.outfit.entity.Product;

import java.util.List;

public interface RankProductByCategoryRepository {
    void saveAllSorted(Iterable<Product> products);

    List<Product> findLowestPriceProductByCategory();

    List<Product> findByCategoryId(Long categoryId);

    void delete(List<Product> products);
}
