package com.musinsa.shop.domain.rank.repository;

import com.musinsa.shop.domain.rank.entity.BrandInfoByPriceSum;

public interface BrandProductByPriceSumRepository {
    void save(BrandInfoByPriceSum brandInfoByPriceSum);

    BrandInfoByPriceSum findLowestBrand();

    void delete(BrandInfoByPriceSum brandInfoByPriceSum);

    boolean contains(BrandInfoByPriceSum brandId);
}
