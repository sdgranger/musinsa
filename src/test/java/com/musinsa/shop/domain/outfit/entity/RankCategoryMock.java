package com.musinsa.shop.domain.outfit.entity;

import com.musinsa.shop.domain.rank.entity.RankCategory;

public class RankCategoryMock extends RankCategory {

    public RankCategoryMock(Long id, String name) {
        super(id, name);
    }

    public static RankCategory create(Long id, String name) {
        return new RankCategoryMock(id, name);
    }
}