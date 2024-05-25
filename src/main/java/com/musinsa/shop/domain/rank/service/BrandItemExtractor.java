package com.musinsa.shop.domain.rank.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Item;

public interface BrandItemExtractor {
    Iterable<Item> findAllItems();

    Iterable<Brand> findAllBrand();
}
