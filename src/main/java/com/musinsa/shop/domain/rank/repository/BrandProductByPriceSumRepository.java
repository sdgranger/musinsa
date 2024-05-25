package com.musinsa.shop.domain.rank.repository;

import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;

public interface BrandProductByPriceSumRepository {
    void save(BrandInfoByLowestPriceSum brandInfoByLowestPriceSum);

    BrandInfoByLowestPriceSum findBrandByLowestPriceSum();

    void delete(BrandInfoByLowestPriceSum brandInfoByLowestPriceSum);

    boolean contains(BrandInfoByLowestPriceSum brandId);
}
