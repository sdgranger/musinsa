package com.musinsa.shop.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.shop.service.dto.LowestPriceSumProductByBrand;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LowestPriceProductByBrandResponse {
    @JsonProperty("최저가")
    private LowestPriceProductResponse lowestPriceProductBrand;

    private LowestPriceProductByBrandResponse(LowestPriceProductResponse lowestPriceProductResponse) {
        this.lowestPriceProductBrand = lowestPriceProductResponse;
    }

    public static LowestPriceProductByBrandResponse from(LowestPriceSumProductByBrand lowestPriceSumProductByBrand) {
        return new LowestPriceProductByBrandResponse(LowestPriceProductResponse.from(lowestPriceSumProductByBrand));
    }

    @Getter
    public static class LowestPriceProductResponse {
        @JsonProperty("브랜드")
        private String brandName;
        @JsonProperty("카테고리")
        private List<CategoryResponse> category;
        @JsonProperty("총액")
        private String displayTotalPrice;

        private LowestPriceProductResponse(LowestPriceSumProductByBrand lowestPriceSumProductByBrand) {
            List<CategoryResponse> collect = lowestPriceSumProductByBrand.getCategoryProduct().stream().map(CategoryResponse::from).collect(Collectors.toList());
            this.brandName = lowestPriceSumProductByBrand.getBrandName();
            this.category = collect;
            this.displayTotalPrice = new DecimalFormat("###,###").format(lowestPriceSumProductByBrand.getTotalPrice());
        }

        public static LowestPriceProductResponse from(LowestPriceSumProductByBrand lowestPriceSumProductByBrand) {

            return new LowestPriceProductResponse(lowestPriceSumProductByBrand);
        }
    }

    @Getter
    static class CategoryResponse {
        @JsonProperty("카테고리")
        private String categoryName;
        @JsonProperty("가격")
        private String price;

        private CategoryResponse(String categoryName, long price) {
            this.categoryName = categoryName;
            this.price = new DecimalFormat("###,###").format(price);
        }

        public static CategoryResponse from(LowestPriceSumProductByBrand.CategoryProduct dto) {
            return new CategoryResponse(dto.getCategoryName(), dto.getPrice());
        }

    }
}
