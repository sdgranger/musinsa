package com.musinsa.shop.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Category;
import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.outfit.repository.BrandRepository;
import com.musinsa.shop.domain.outfit.repository.CategoryRepository;
import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
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
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final SortedProductByCategoryLocalRepository sortedProductByCategoryLocalRepository;
    private final BrandProductByPriceSumRepository brandProductByPriceSumRepository;

    public CategoryStatistics getByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow(NotFoundException::new);

        List<Product> sortedProducts = sortedProductByCategoryLocalRepository.findByCategoryOrderByPriceIsAsc(category.getId());

        return CategoryStatistics.from(categoryName, sortedProducts);
    }

    public List<LowestPriceProductByCategory> getProductOfLowestPriceByCategory() {
        List<Product> productByCategory = sortedProductByCategoryLocalRepository.findLowestPriceProductByCategory();
        return productByCategory.stream().map(LowestPriceProductByCategory::from).collect(Collectors.toList());
    }

    public LowestPriceSumProductByBrand getByBrand() {
        BrandInfoByLowestPriceSum brandProduct = brandProductByPriceSumRepository.findBrandByLowestPriceSum();
        if (brandProduct == null) {
            throw new NotFoundException();
        }

        Brand brand = brandRepository.findById(brandProduct.getBrandId()).orElseThrow(NotFoundException::new);

        return LowestPriceSumProductByBrand.from(brandProduct, brand.getProducts());
    }
}
