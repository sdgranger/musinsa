package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Item;
import com.musinsa.shop.domain.rank.entity.BrandOrderByPriceSum;
import com.musinsa.shop.domain.rank.repository.BrandOrderByPriceSumRepository;
import com.musinsa.shop.domain.rank.repository.RankItemByCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class RankAggregator {
    private final BrandItemExtractor brandItemExtractor;
    private final RankItemByCategoryRepository rankItemByCategoryRepository;
    private final BrandOrderByPriceSumRepository brandOrderByPriceSumRepository;

    @Transactional
    public void execute() {
        aggregatePriceItemByCategory();
        lowestPriceItemByBrandAggregation();
    }

    private void aggregatePriceItemByCategory() {
        Iterable<Item> all = brandItemExtractor.findAllItems();

        rankItemByCategoryRepository.saveAllSorted(all);
    }

    private void lowestPriceItemByBrandAggregation() {
        Iterable<Brand> brands = brandItemExtractor.findAllBrand();
        for (Brand brand : brands) {
            long sum = brand.getItems().stream().mapToLong(Item::getPrice).sum();
            brandOrderByPriceSumRepository.save(new BrandOrderByPriceSum(brand.getId(), brand.getName(), sum));
        }
    }

}
