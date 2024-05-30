package com.musinsa.shop.controller.request;

import com.musinsa.shop.service.dto.BrandProductChange;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BrandProductChangeRequest {
    @NotBlank(message = "brand 명이 비어있습니다.")
    @Schema(example = "A")
    private String brandName;
    @Size(min = 1)
    @Schema(example = "[\n" +
            "       {\n" +
            "            \"price\": 1100,\n" +
            "            \"categoryName\": \"상의\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 2200,\n" +
            "            \"categoryName\": \"아우터\"\n" +
            "       },       \n" +
            "       {\n" +
            "            \"price\": 3300,\n" +
            "            \"categoryName\": \"바지\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 3400,\n" +
            "            \"categoryName\": \"스니커즈\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 4500,\n" +
            "            \"categoryName\": \"가방\"\n" +
            "       }, \n" +
            "       {\n" +
            "            \"price\": 5600,\n" +
            "            \"categoryName\": \"모자\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 6700,\n" +
            "            \"categoryName\": \"양말\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 7800,\n" +
            "            \"categoryName\": \"액세서리\"\n" +
            "       }\n" +
            "    ]")
    private List<CategoryProductRequest> categoryProducts;

    public BrandProductChange toBrandProductChange() {
        List<BrandProductChange.CategoryProductChange> productChanges = categoryProducts.stream()
                .map(CategoryProductRequest::to).collect(Collectors.toList());
        return new BrandProductChange(brandName, productChanges);
    }

    @Getter
    public static class CategoryProductRequest {
        @NotBlank(message = "category 명이 비어있습니다.")
        @Schema(example = "상의")
        private String categoryName;
        @Min(value = 1, message = "잘못된 가격입니다.")
        @NotNull(message = "잘못된 가격입니다.")
        @Schema(example = "2300")
        private Long price;

        public CategoryProductRequest(String categoryName, @NotNull(message = "잘못된 가격입니다.") Long price) {
            this.categoryName = categoryName;
            this.price = price;
        }

        public BrandProductChange.CategoryProductChange to() {
            return new BrandProductChange.CategoryProductChange(categoryName, price);
        }
    }
}
