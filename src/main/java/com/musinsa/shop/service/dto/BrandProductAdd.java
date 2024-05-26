package com.musinsa.shop.service.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class BrandProductAdd {
    private String brandName;
    private List<CategoryProductAdd> categoryProductAdd;

    public BrandProductAdd(String brandName, List<CategoryProductAdd> categoryProductAdd) {
        this.brandName = brandName;
        this.categoryProductAdd = categoryProductAdd;
    }

    @Getter
    public static class CategoryProductAdd {
        private String categoryName;
        private Long price;

        public CategoryProductAdd(String categoryName, Long price) {
            this.categoryName = categoryName;
            this.price = price;
        }
    }
}
