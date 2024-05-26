package com.musinsa.shop.event;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class BrandProductChangedEvent {
    private final Brand brand;
    private final List<Product> products;

    public BrandProductChangedEvent(Brand brand, List<Product> savedProducts) {
        this.brand = brand;
        this.products = savedProducts;
    }
}
