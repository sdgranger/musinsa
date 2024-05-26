package com.musinsa.shop.domain.rank.repository;

import com.musinsa.shop.domain.rank.entity.RankProduct;

import java.util.List;

public interface RankProductByCategoryRepository {
    void saveAllSorted(Iterable<RankProduct> products);

    List<RankProduct> findLowestPriceProductByCategory();

    List<RankProduct> findByCategoryId(Long categoryId);

    void delete(List<RankProduct> products);
}
