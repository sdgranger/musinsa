package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;

public interface BrandProductExtractor {
    Iterable<Product> findAllProducts();

    Iterable<Brand> findAllBrand();
}
