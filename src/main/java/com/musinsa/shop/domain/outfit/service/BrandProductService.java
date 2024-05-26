package com.musinsa.shop.domain.outfit.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.outfit.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BrandProductService {
    private final BrandRepository brandRepository;

    public List<Product> findAllProducts() {
        List<Brand> allBrands = brandRepository.findAll();
        return allBrands.stream().flatMap(allBrand -> allBrand.getProducts().stream()).collect(Collectors.toList());
    }

    public List<Brand> findAllBrand() {
        return brandRepository.findAll();
    }
}
