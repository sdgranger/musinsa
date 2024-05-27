package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.domain.rank.entity.RankProduct;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
import com.musinsa.shop.domain.rank.repository.RankProductByCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RankAggregator {
    private final BrandProductExtractor brandProductExtractor;
    private final RankProductByCategoryRepository rankProductByCategoryRepository;
    private final BrandProductByPriceSumRepository brandProductByPriceSumRepository;

    @Transactional
    public void execute() {
        aggregatePriceProductByCategory();
        aggregateLowestPriceProductByBrand();
    }

    private void aggregatePriceProductByCategory() {
        List<Product> all = brandProductExtractor.findAllProducts();

        rankProductByCategoryRepository.saveAllSorted(convert(all));
    }

    private List<RankProduct> convert(List<Product> products) {
        return products.stream().map(this::from).collect(Collectors.toList());
    }

    private RankProduct from(Product product) {
        return RankProduct.create(product.getId(), product.getCategory().getId(), product.getCategoryName(), product.getBrand().getName(), product.getPrice());
    }

    private void aggregateLowestPriceProductByBrand() {
        List<Brand> brands = brandProductExtractor.findAllBrand();
        for (Brand brand : brands) {
            long sum = brand.getProducts().stream().mapToLong(Product::getPrice).sum();
            brandProductByPriceSumRepository.save(new BrandInfoByLowestPriceSum(brand.getId(), brand.getName(), sum));
        }
    }

}
