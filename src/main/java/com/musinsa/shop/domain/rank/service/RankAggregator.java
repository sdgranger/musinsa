package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.rank.entity.BrandInfoByPriceSum;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
import com.musinsa.shop.domain.rank.repository.RankProductByCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RankAggregator {
    private final BrandProductExtractor brandProductExtractor;
    private final RankProductByCategoryRepository rankProductByCategoryRepository;
    private final BrandProductByPriceSumRepository brandProductByPriceSumRepository;

    @Transactional
    public void execute() {
        aggregatePriceProductByCategory();
        lowestPriceProductByBrandAggregation();
    }

    private void aggregatePriceProductByCategory() {
        Iterable<Product> all = brandProductExtractor.findAllProducts();

        rankProductByCategoryRepository.saveAllSorted(all);
    }

    private void lowestPriceProductByBrandAggregation() {
        Iterable<Brand> brands = brandProductExtractor.findAllBrand();
        for (Brand brand : brands) {
            long sum = brand.getProducts().stream().mapToLong(Product::getPrice).sum();
            brandProductByPriceSumRepository.save(new BrandInfoByPriceSum(brand.getId(), brand.getName(), sum));
        }
    }

}
