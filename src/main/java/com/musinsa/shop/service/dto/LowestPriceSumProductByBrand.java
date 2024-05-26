package com.musinsa.shop.service.dto;

import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.domain.outfit.entity.Product;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LowestPriceSumProductByBrand {
    private String brandName;
    private List<CategoryProduct> categoryProduct;
    private Long totalPrice;

    private LowestPriceSumProductByBrand(String brandName, List<CategoryProduct> categoryProductResponse) {
        this.brandName = brandName;
        this.categoryProduct = categoryProductResponse;
        this.totalPrice = categoryProductResponse.stream().mapToLong(dto -> dto.price).sum();
    }

    public static LowestPriceSumProductByBrand from(BrandInfoByLowestPriceSum brandProductByPriceSum, List<Product> products) {
        List<CategoryProduct> productDto = products.stream()
                .map(categoryProduct -> CategoryProduct.create(categoryProduct.getCategory().getName(), categoryProduct.getPrice()))
                .collect(Collectors.toList());
        return new LowestPriceSumProductByBrand(brandProductByPriceSum.getBrandName(), productDto);
    }
    @Getter
    public static class CategoryProduct {
        private String categoryName;
        private long price;

        private CategoryProduct(String categoryName, long price) {
            this.categoryName = categoryName;
            this.price = price;
        }

        public static CategoryProduct create(String categoryName, long price) {
            return new CategoryProduct(categoryName, price);
        }

    }
}
