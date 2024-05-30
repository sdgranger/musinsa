package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Category;
import com.musinsa.shop.domain.outfit.repository.BrandRepository;
import com.musinsa.shop.domain.outfit.repository.CategoryRepository;
import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.domain.rank.entity.RankProduct;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
import com.musinsa.shop.domain.rank.service.BrandProductExtractor;
import com.musinsa.shop.domain.rank.service.CategoryNamingCenter;
import com.musinsa.shop.domain.rank.service.CategoryTag;
import com.musinsa.shop.error.NotFoundException;
import com.musinsa.shop.infrastruture.SortedProductByCategoryLocalRepository;
import com.musinsa.shop.service.dto.CategoryStatistics;
import com.musinsa.shop.service.dto.LowestPriceProductByCategory;
import com.musinsa.shop.service.dto.LowestPriceSumProductByBrand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankService {
    private final CategoryNamingCenter categoryNamingCenter;
    private final BrandProductExtractor brandProductExtractor;
    private final SortedProductByCategoryLocalRepository sortedProductByCategoryLocalRepository;
    private final BrandProductByPriceSumRepository brandProductByPriceSumRepository;

    public CategoryStatistics getByCategoryName(String categoryName) {
        CategoryTag category = categoryNamingCenter.findByName(categoryName).orElseThrow(NotFoundException::new);

        List<RankProduct> sortedProducts = sortedProductByCategoryLocalRepository.findByCategoryOrderByPriceIsAsc(category.getId());

        return CategoryStatistics.from(categoryName, sortedProducts);
    }

    public List<LowestPriceProductByCategory> getProductOfLowestPriceByCategory() {
        List<RankProduct> productByCategory = sortedProductByCategoryLocalRepository.findLowestPriceProductByCategory();
        return productByCategory.stream().map(LowestPriceProductByCategory::from).collect(Collectors.toList());
    }

    public LowestPriceSumProductByBrand getByBrand() {
        BrandInfoByLowestPriceSum brandProduct = brandProductByPriceSumRepository.findBrandByLowestPriceSum();
        if (brandProduct == null) {
            throw new NotFoundException();
        }

        List<RankProduct> products = brandProductExtractor.findByBrandId(brandProduct.getBrandId());

        return LowestPriceSumProductByBrand.from(brandProduct, products);
    }

    public void save(Long brandId, String brandName, List<RankProduct> products) {
        long totalPrice = products.stream().mapToLong(RankProduct::getPrice).sum();
        sortedProductByCategoryLocalRepository.saveAllSorted(products);
        brandProductByPriceSumRepository.save(
                new BrandInfoByLowestPriceSum(brandId, brandName, totalPrice)
        );
    }

    public void remove(Long brandId, String brandName, List<RankProduct> products) {
        long totalPrice = products.stream().mapToLong(RankProduct::getPrice).sum();
        sortedProductByCategoryLocalRepository.delete(products);
        brandProductByPriceSumRepository.delete(
                new BrandInfoByLowestPriceSum(brandId, brandName, totalPrice)
        );
    }
}
