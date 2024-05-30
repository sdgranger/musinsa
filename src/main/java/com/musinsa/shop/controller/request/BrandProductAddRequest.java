package com.musinsa.shop.controller.request;

import com.musinsa.shop.service.dto.BrandProductAdd;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "D")
    private String brandName;
    @Size(min = 8, max = 8)
    @Schema(example = "[\n" +
            "       {\n" +
            "            \"price\": 100,\n" +
            "            \"categoryName\": \"상의\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 200,\n" +
            "            \"categoryName\": \"아우터\"\n" +
            "       },       \n" +
            "       {\n" +
            "            \"price\": 300,\n" +
            "            \"categoryName\": \"바지\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 300,\n" +
            "            \"categoryName\": \"스니커즈\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 400,\n" +
            "            \"categoryName\": \"가방\"\n" +
            "       }, \n" +
            "       {\n" +
            "            \"price\": 500,\n" +
            "            \"categoryName\": \"모자\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 600,\n" +
            "            \"categoryName\": \"양말\"\n" +
            "       },\n" +
            "       {\n" +
            "            \"price\": 700,\n" +
            "            \"categoryName\": \"액세서리\"\n" +
            "       }\n" +
            "    ]")
    private List<CategoryProductRequest> categoryProducts;

    public BrandProductAdd toBrandProductAdd() {
        List<BrandProductAdd.CategoryProductAdd> productAddList = categoryProducts.stream().map(CategoryProductRequest::toAddMessage).collect(Collectors.toList());

        return new BrandProductAdd(brandName, productAddList);
    }

    @Getter
    public static class CategoryProductRequest {
        @NotBlank(message = "category 명이 비어있습니다.")
        @Schema(example = "스니커즈")
        private String categoryName;
        @Min(value = 1, message = "잘못된 가격입니다.")
        @NotNull(message = "잘못된 가격입니다.")
        @Schema(example = "100000")
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
