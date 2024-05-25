package com.musinsa.shop.infrastruture;

import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
import org.springframework.stereotype.Repository;

import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

@Repository
public class BrandProductByPriceSumLocalRepository implements BrandProductByPriceSumRepository {
    private final SortedSet<BrandInfoByLowestPriceSum> lowestSumPriceProductByBrandId = new ConcurrentSkipListSet<>((o1, o2) -> {
        if (o1.equals(o2)) {
            return 0;
        }

        return o1.getSumPrice() > o2.getSumPrice() ? 1 : -1;
    });

    @Override
    public void save(BrandInfoByLowestPriceSum brandInfoByPriceSum) {
        lowestSumPriceProductByBrandId.remove(brandInfoByPriceSum);
        lowestSumPriceProductByBrandId.add(brandInfoByPriceSum);
    }

    @Override
    public BrandInfoByLowestPriceSum findBrandByLowestPriceSum() {
        if (lowestSumPriceProductByBrandId.isEmpty()) {
            return null;
        }
        return lowestSumPriceProductByBrandId.first();
    }

    @Override
    public void delete(BrandInfoByLowestPriceSum brandInfoByPriceSum) {
        lowestSumPriceProductByBrandId.remove(brandInfoByPriceSum);
    }

    @Override
    public boolean contains(BrandInfoByLowestPriceSum brandId) {
        return lowestSumPriceProductByBrandId.contains(brandId);
    }
}
