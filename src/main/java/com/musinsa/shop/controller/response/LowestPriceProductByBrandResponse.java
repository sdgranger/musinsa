package com.musinsa.shop.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.shop.service.dto.LowestPriceSumProductByBrand;
import io.swagger.v3.oas.annotations.media.Schema;
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
        @Schema(name = "브랜드", example = "A")
        private String brandName;
        @JsonProperty("카테고리")
        @Schema(example = "[\n" +
                "            {\n" +
                "                \"카테고리\": \"상의\",\n" +
                "                \"가격\": \"1,100\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"카테고리\": \"아우터\",\n" +
                "                \"가격\": \"2,200\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"카테고리\": \"바지\",\n" +
                "                \"가격\": \"3,300\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"카테고리\": \"스니커즈\",\n" +
                "                \"가격\": \"3,400\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"카테고리\": \"가방\",\n" +
                "                \"가격\": \"4,500\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"카테고리\": \"모자\",\n" +
                "                \"가격\": \"5,600\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"카테고리\": \"양말\",\n" +
                "                \"가격\": \"6,700\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"카테고리\": \"액세서리\",\n" +
                "                \"가격\": \"7,800\"\n" +
                "            }\n" +
                "        ]")
        private List<CategoryResponse> category;
        @JsonProperty("총액")
        @Schema(name = "총액", example = "1,200,000")
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
        @Schema(name = "카테고리", example = "상의")
        private String categoryName;
        @JsonProperty("가격")
        @Schema(name = "가격", example = "5,000")
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
