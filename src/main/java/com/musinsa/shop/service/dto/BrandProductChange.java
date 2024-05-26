package com.musinsa.shop.service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BrandProductChange {
    private String brandName;
    private List<CategoryProductChange> categoryProducts;

    @Getter
    public static class CategoryProductChange {
        private String categoryName;
        private Long price;

        public CategoryProductChange(String categoryName, @NotNull(message = "잘못된 가격입니다.") Long price) {
            this.categoryName = categoryName;
            this.price = price;
        }
    }
}
