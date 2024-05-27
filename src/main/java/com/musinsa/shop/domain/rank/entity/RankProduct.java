package com.musinsa.shop.domain.rank.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class RankProduct {
    private Long id;

    private Long categoryId;
    private String categoryName;
    private String brandName;
    private Long price;

    protected RankProduct(Long id, Long categoryId, String categoryName, String brandName, long price) {
        this.categoryId = categoryId;
        this.id = id;
        this.categoryName = categoryName;
        this.brandName = brandName;
        this.price = price;
    }

    public static RankProduct create(Long id, Long categoryId, String categoryName, String brandName, long price) {
        return new RankProduct(id, categoryId, categoryName, brandName, price);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Long getId() {
        return id;
    }
}
