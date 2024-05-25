package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;

import java.util.List;

public interface BrandProductExtractor {
    List<Product> findAllProducts();

    List<Brand> findAllBrand();
}
