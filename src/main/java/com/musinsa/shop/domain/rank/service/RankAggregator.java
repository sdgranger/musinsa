package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.domain.rank.entity.BrandStatus;
import com.musinsa.shop.domain.rank.entity.RankProduct;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
import com.musinsa.shop.domain.rank.repository.RankProductByCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        List<RankProduct> all = brandProductExtractor.findAllProducts();

        rankProductByCategoryRepository.saveAllSorted(all);
    }

    private void aggregateLowestPriceProductByBrand() {
        List<BrandStatus> brands = brandProductExtractor.findAllBrand();
        for (BrandStatus brand : brands) {
            brandProductByPriceSumRepository.save(new BrandInfoByLowestPriceSum(brand.getId(), brand.getName(), brand.getPriceSum()));
        }
    }

}
