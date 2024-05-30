package com.musinsa.shop.controller;

import com.musinsa.shop.controller.response.CategoryProductsStatusResponse;
import com.musinsa.shop.controller.response.LowestPriceProductByBrandResponse;
import com.musinsa.shop.controller.response.LowestPriceProductByCategoryResponse;
import com.musinsa.shop.service.dto.CategoryStatistics;
import com.musinsa.shop.service.dto.LowestPriceSumProductByBrand;
import com.musinsa.shop.service.dto.LowestPriceProductByCategory;
import com.musinsa.shop.domain.rank.service.RankService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rank")
@Tag(name = "Rank 조회 API")
public class RankController {
    private final RankService rankService;


    @Operation(summary = "카테고리별로 가장 낮은 금액의 상품을 조회", description = "")
    @GetMapping("/products/lowest-by-category")
    public LowestPriceProductByCategoryResponse getLowestPriceByCategory() {
        List<LowestPriceProductByCategory> productOfLowestPriceByCategory = rankService.getProductOfLowestPriceByCategory();

        return LowestPriceProductByCategoryResponse.from(productOfLowestPriceByCategory);
    }

    @Operation(summary = "상품 가격 총액이 가장 적은 브랜드의 카테고리별 상품 조회", description = "")
    @GetMapping("/brand/lowest-price-sum")
    public LowestPriceProductByBrandResponse getLowestPriceProductByBrand() {
        LowestPriceSumProductByBrand lowestPriceSumProductByBrand = rankService.getByBrand();
        return LowestPriceProductByBrandResponse.from(lowestPriceSumProductByBrand);
    }

    @Operation(summary = "카테고리 이름으로 최고가, 최저가 금액의 상품을 조회", description = "")
    @GetMapping("/category/products")
    public CategoryProductsStatusResponse getCategoryProductsStatus(@RequestParam("category") String categoryName) {
        CategoryStatistics categoryDto = rankService.getByCategoryName(categoryName);

        return CategoryProductsStatusResponse.from(categoryDto);
    }
}
