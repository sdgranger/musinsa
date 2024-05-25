package com.musinsa.shop.domain.rank.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@EqualsAndHashCode(of = "brandId")
public class BrandInfoByPriceSum {
    private Long sumId;
    private Long brandId;
    private String brandName;
    private Long sumPrice;

    public BrandInfoByPriceSum(Long brandId, String brandName, long sumPrice) {
        this.sumId = BrandPriceSumIdGenerator.getAndIncrement();
        this.brandId = brandId;
        this.brandName = brandName;
        this.sumPrice = sumPrice;
    }

    @Getter
    public static class CategoryProduct {
        private final String categoryName;
        private final Long price;

        private CategoryProduct(String categoryName, Long price) {
            this.categoryName = categoryName;
            this.price = price;
        }

        public static CategoryProduct create(String categoryName, long price) {
            return new CategoryProduct(categoryName, price);
        }
    }

    static class BrandPriceSumIdGenerator {
        private static final AtomicLong ATOMIC_ID = new AtomicLong();

        public static Long getAndIncrement(){
            return BrandPriceSumIdGenerator.ATOMIC_ID.getAndIncrement();
        }
    }

    public Long getId() {
        return brandId;
    }
}
