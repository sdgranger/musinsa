package com.musinsa.shop.event;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;
import lombok.Getter;

import java.util.List;

@Getter
public class BrandProductRemoveEvent {
    private final Brand brand;
    private final List<Product> products;
    public BrandProductRemoveEvent(Brand brand, List<Product> products) {
        this.brand = brand;
        this.products = products;
    }
}
