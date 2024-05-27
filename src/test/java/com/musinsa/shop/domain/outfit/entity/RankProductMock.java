package com.musinsa.shop.domain.outfit.entity;

import com.musinsa.shop.domain.rank.entity.RankProduct;

public class RankProductMock extends RankProduct {

    public RankProductMock(Long id, Long categoryId, String categoryName, String brandName, long price) {
        super(id, categoryId, categoryName, brandName, price);
    }

    public static RankProduct create(Long id, Long categoryId, String categoryName, String brandName, long price) {
        return new RankProductMock(id, categoryId, categoryName, brandName, price);
    }
}