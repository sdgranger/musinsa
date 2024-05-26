package com.musinsa.shop.config;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.outfit.service.BrandProductService;
import com.musinsa.shop.domain.rank.service.BrandProductExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigurationBrandProductExtractor implements BrandProductExtractor {
    private final BrandProductService brandProductService;

    @Override
    public List<Product> findAllProducts() {
        return brandProductService.findAllProducts();
    }

    @Override
    public List<Brand> findAllBrand() {
        return brandProductService.findAllBrand();
    }
}
