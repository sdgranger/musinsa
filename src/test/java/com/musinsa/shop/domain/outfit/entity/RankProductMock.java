package com.musinsa.shop.domain.outfit.entity;

import com.musinsa.shop.domain.rank.entity.RankBrand;
import com.musinsa.shop.domain.rank.entity.RankCategory;
import com.musinsa.shop.domain.rank.entity.RankProduct;

public class RankProductMock extends RankProduct {

    public RankProductMock(Long id, RankCategory category, RankBrand brand, long price) {
        super(id, category, brand, price);
    }

    public static RankProduct create(Long id, RankCategory category, RankBrand brand, long price) {
        return new RankProductMock(id, category, brand, price);
    }
}