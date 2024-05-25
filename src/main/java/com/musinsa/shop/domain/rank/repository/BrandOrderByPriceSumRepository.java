package com.musinsa.shop.domain.rank.repository;

import com.musinsa.shop.domain.rank.entity.BrandOrderByPriceSum;

import java.util.Optional;

public interface BrandOrderByPriceSumRepository {
    void save(BrandOrderByPriceSum brandOrderByPriceSum);

    Optional<BrandOrderByPriceSum> findLowestBrand();

    void delete(BrandOrderByPriceSum brandOrderByPriceSum);
}
