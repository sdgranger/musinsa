package com.musinsa.shop.config;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.outfit.service.BrandProductFindService;
import com.musinsa.shop.domain.rank.entity.BrandStatus;
import com.musinsa.shop.domain.rank.entity.RankProduct;
import com.musinsa.shop.domain.rank.service.BrandProductExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConfigurationBrandProduct implements BrandProductExtractor {
    private final BrandProductFindService brandProductService;

    @Override
    public List<RankProduct> findAllProducts() {
        return convert(brandProductService.findAllProducts());
    }

    private List<RankProduct> convert(List<Product> products) {
        return products.stream().map(this::from).collect(Collectors.toList());
    }

    private RankProduct from(Product product) {
        return RankProduct.create(product.getId(), product.getCategory().getId(), product.getCategoryName(), product.getBrand().getName(), product.getPrice());
    }

    @Override
    public List<BrandStatus> findAllBrand() {
        return convertBrand(brandProductService.findAllBrand());
    }

    @Override
    public List<RankProduct> findByBrandId(Long brandId) {
        List<Product> products = brandProductService.findByBrandId(brandId);
        return convert(products);
    }

    private List<BrandStatus> convertBrand(List<Brand> allBrand) {
        return allBrand.stream().map(this::fromBrand).collect(Collectors.toList());
    }

    private BrandStatus fromBrand(Brand brand) {
        long sum = brand.getProducts().stream().mapToLong(Product::getPrice).sum();

        return BrandStatus.create(brand.getId(), brand.getName(), sum);
    }
}
