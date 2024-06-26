package com.musinsa.shop.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.shop.service.dto.CategoryStatistics;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CategoryProductsStatusResponse {
    @JsonProperty("카테고리")
    @Schema(name = "카테고리", example = "아우터")
    private String category;
    @JsonProperty("최저가")
    private List<BrandProductResponse> lowest;
    @JsonProperty("최고가")
    private List<BrandProductResponse> highest;

    private CategoryProductsStatusResponse(String categoryName, List<BrandProductResponse> lowestPriceProducts, List<BrandProductResponse> highestPriceProducts) {
        this.category = categoryName;
        this.lowest = lowestPriceProducts;
        this.highest = highestPriceProducts;
    }

    public static CategoryProductsStatusResponse from(CategoryStatistics categoryDto) {
        List<BrandProductResponse> lowestBrandProduct = categoryDto.getLowestProduct().stream().map(BrandProductResponse::from).collect(Collectors.toList());
        List<BrandProductResponse> highestBrandProduct = categoryDto.getHighestProduct().stream().map(BrandProductResponse::from).collect(Collectors.toList());

        return new CategoryProductsStatusResponse(categoryDto.getCategoryName(), lowestBrandProduct, highestBrandProduct);
    }

    @Getter
    public static class BrandProductResponse {
        @JsonProperty("브랜드")
        @Schema(name = "브랜드", example = "A")
        private final String brandName;
        @JsonProperty("가격")
        @Schema(name = "가격", example = "2,300")
        private final String price;

        private BrandProductResponse(String brandName, Long price) {
            this.brandName = brandName;
            this.price = new DecimalFormat("###,###").format(price);
        }

        public static BrandProductResponse from(CategoryStatistics.BrandProduct brandProduct) {
            return new BrandProductResponse(brandProduct.getBrandName(), brandProduct.getPrice());
        }
    }
}
