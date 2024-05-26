package com.musinsa.shop.service.dto;

import com.musinsa.shop.domain.outfit.entity.Product;
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

    public static LowestPriceProductByCategory from(Product product) {
        return new LowestPriceProductByCategory(product.getId(), product.getBrand().getName(), product.getCategoryName(), product.getPrice());
    }
}