package com.musinsa.shop.service.dto;

import com.musinsa.shop.domain.rank.entity.RankProduct;
import com.musinsa.shop.error.NotFoundException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class CategoryStatistics {
    private String categoryName;
    private List<BrandProduct> lowestProduct;
    private List<BrandProduct> highestProduct;

    public CategoryStatistics(String categoryName, List<BrandProduct> lowestPriceProducts, List<BrandProduct> highestPriceProducts) {
        this.categoryName = categoryName;
        this.lowestProduct = lowestPriceProducts;
        this.highestProduct = highestPriceProducts;
    }

    public static CategoryStatistics from(String categoryName, List<RankProduct> sortedProducts) {
        Long lowestPrice = sortedProducts.stream().findFirst().orElseThrow(NotFoundException::new).getPrice();
        Long highestPrice = sortedProducts.get(sortedProducts.size()-1).getPrice();
        List<BrandProduct> lowestProducts = new ArrayList<>();
        List<BrandProduct> highestProducts = new ArrayList<>();

        for (RankProduct sortedProduct : sortedProducts) {
            if (Objects.equals(sortedProduct.getPrice(), lowestPrice)) {
                lowestProducts.add(new BrandProduct(sortedProduct.getBrand().getName(), sortedProduct.getPrice()));
            }
            if (Objects.equals(sortedProduct.getPrice(), highestPrice)) {
                highestProducts.add(new BrandProduct(sortedProduct.getBrand().getName(), sortedProduct.getPrice()));
            }
        }


        return new CategoryStatistics(categoryName, lowestProducts, highestProducts);
    }

    @Getter
    public static class BrandProduct {
        private final String brandName;
        private final Long price;

        public BrandProduct(String brandName, Long price) {
            this.brandName = brandName;
            this.price = price;
        }
    }
}
