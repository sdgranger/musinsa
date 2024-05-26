package com.musinsa.shop.controller.request;

import com.musinsa.shop.service.dto.BrandProductAdd;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BrandProductAddRequest {
    @NotBlank(message = "brand 명이 비어있습니다.")
    private String brandName;
    @Size(min = 8, max = 8)
    private List<CategoryProductRequest> categoryProducts;

    public BrandProductAdd toBrandProductAdd() {
        List<BrandProductAdd.CategoryProductAdd> productAddList = categoryProducts.stream().map(CategoryProductRequest::toAddMessage).collect(Collectors.toList());

        return new BrandProductAdd(brandName, productAddList);
    }

    @Getter
    public static class CategoryProductRequest {
        @NotBlank(message = "category 명이 비어있습니다.")
        private String categoryName;
        @Min(value = 1, message = "잘못된 가격입니다.")
        @NotNull(message = "잘못된 가격입니다.")
        private Long price;

        public CategoryProductRequest(String categoryName, @NotNull(message = "잘못된 가격입니다.") Long price) {
            this.categoryName = categoryName;
            this.price = price;
        }

        public BrandProductAdd.CategoryProductAdd toAddMessage() {
            return new BrandProductAdd.CategoryProductAdd(categoryName, price);
        }
    }
}
