package com.musinsa.shop.controller;

import com.musinsa.shop.controller.response.CategoryProductsStatusResponse;
import com.musinsa.shop.controller.response.LowestPriceProductByBrandResponse;
import com.musinsa.shop.controller.response.LowestPriceProductByCategoryResponse;
import com.musinsa.shop.service.dto.CategoryStatistics;
import com.musinsa.shop.service.dto.LowestPriceSumProductByBrand;
import com.musinsa.shop.service.dto.LowestPriceProductByCategory;
import com.musinsa.shop.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rank")
public class RankController {
    private final RankService rankService;

    @GetMapping("/products/lowest-by-category")
    public LowestPriceProductByCategoryResponse getLowestPriceByCategory() {
        List<LowestPriceProductByCategory> productOfLowestPriceByCategory = rankService.getProductOfLowestPriceByCategory();

        return LowestPriceProductByCategoryResponse.from(productOfLowestPriceByCategory);
    }

    @GetMapping("/brand/lowest-price-sum")
    public LowestPriceProductByBrandResponse getLowestPriceProductByBrand() {
        LowestPriceSumProductByBrand lowestPriceSumProductByBrand = rankService.getByBrand();
        return LowestPriceProductByBrandResponse.from(lowestPriceSumProductByBrand);
    }

    @GetMapping("/category/products")
    public CategoryProductsStatusResponse getCategoryProductsStatus(@RequestParam("category") String categoryName) {
        CategoryStatistics categoryDto = rankService.getByCategoryName(categoryName);

        return CategoryProductsStatusResponse.from(categoryDto);
    }
}
