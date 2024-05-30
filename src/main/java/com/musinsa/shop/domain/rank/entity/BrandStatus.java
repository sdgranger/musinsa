package com.musinsa.shop.domain.rank.entity;

public class BrandStatus {
    private Long id;
    private String name;
    private Long priceSum;

    private BrandStatus(Long brandId, String brandName, Long priceSum) {
        this.id = brandId;
        this.name = brandName;
        this.priceSum = priceSum;
    }

    public static BrandStatus create(Long id, String name, long sum) {
        return new BrandStatus(id, name, sum);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPriceSum() {
        return priceSum;
    }
}
