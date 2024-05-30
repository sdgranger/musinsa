package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.rank.entity.BrandStatus;
import com.musinsa.shop.domain.rank.entity.RankProduct;

import java.util.List;

public interface BrandProductExtractor {
    List<RankProduct> findAllProducts();

    List<BrandStatus> findAllBrand();

    List<RankProduct> findByBrandId(Long brandId);
}
