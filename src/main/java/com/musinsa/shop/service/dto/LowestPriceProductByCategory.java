package com.musinsa.shop.service.dto;

import com.musinsa.shop.domain.rank.entity.RankProduct;
import lombok.Getter;

@Getter
public class LowestPriceProductByCategory {
    private Long productId;
    private String brandName;
    private String categoryName;
    private Long price;

    public LowestPriceProductByCategory(Long productId, String brandName, String categoryName, Long price) {
        this.productId = productId;
        this.brandName = brandName;
        this.categoryName = categoryName;
        this.price = price;
    }

    public static LowestPriceProductByCategory from(RankProduct product) {
        return new LowestPriceProductByCategory(product.getId(), product.getBrandName(), product.getCategoryName(), product.getPrice());
    }
}
