package com.musinsa.shop.domain.rank.repository;

import com.musinsa.shop.domain.rank.entity.BrandInfoByPriceSum;

import java.util.Optional;

public interface BrandOrderByPriceSumRepository {
    void save(BrandInfoByPriceSum brandInfoByPriceSum);

    Optional<BrandInfoByPriceSum> findLowestBrand();

    void delete(BrandInfoByPriceSum brandInfoByPriceSum);

    boolean contains(BrandInfoByPriceSum brandId);
}
