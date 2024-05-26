package com.musinsa.shop.domain.rank.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class RankProduct {
    private Long id;
    private RankCategory category;
    private RankBrand brand;
    private Long price;

    protected RankProduct() {}

    private RankProduct(RankCategory category, RankBrand brand, long price) {
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    protected RankProduct(Long id, RankCategory category, RankBrand brand, long price) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public static RankProduct create(Long id, RankCategory category, RankBrand brand, long price) {
        return new RankProduct(id , category, brand, price);
    }

    public String getCategoryName() {
        return category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setBrand(RankBrand brand) {
        this.brand = brand;
    }
}
