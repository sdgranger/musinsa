package com.musinsa.shop.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Category;
import com.musinsa.shop.domain.outfit.entity.CategoryStorage;
import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.outfit.repository.BrandRepository;
import com.musinsa.shop.domain.outfit.service.CategoryService;
import com.musinsa.shop.error.NotFoundException;
import com.musinsa.shop.event.BrandProductChangedEvent;
import com.musinsa.shop.event.BrandProductCreatedEvent;
import com.musinsa.shop.event.BrandProductRemoveEvent;
import com.musinsa.shop.service.dto.BrandProductAdd;
import com.musinsa.shop.service.dto.BrandProductChange;
import com.musinsa.shop.service.dto.ProductChangeContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BrandProductCrudService {
    private final CategoryService categoryService;
    private final BrandRepository brandRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ProductChangeValidator productChangeValidator;
    @Transactional
    public void add(BrandProductAdd productAdd) {

        CategoryStorage categoryStorage = categoryService.find();

        Brand brand = Brand.create(productAdd.getBrandName());
        List<Product> savedProducts = new ArrayList<>();
        for (BrandProductAdd.CategoryProductAdd categoryProduct : productAdd.getCategoryProductAdd()) {
            Category category = categoryStorage.find(categoryProduct.getCategoryName());
            Product newProduct = Product.create(category, brand, categoryProduct.getPrice());
            brand.addProduct(newProduct);
        }
        brandRepository.save(brand);

        applicationEventPublisher.publishEvent(new BrandProductCreatedEvent(brand, savedProducts));
    }

    @Transactional
    public List<Product> update(BrandProductChange brandProductChange) {
        CategoryStorage categoryStorage = categoryService.find();
        productChangeValidator.validateCategorySize(categoryStorage, brandProductChange);

        String brandName = brandProductChange.getBrandName();
        Brand brand = brandRepository.findByName(brandName).orElseThrow(NotFoundException::new);
        ProductChangeContext productChangeContext = toConvert(brandProductChange);
        List<Product> products = brand.getProducts();
        for (Product product : products) {
            product.changePrice(productChangeContext.getByCategoryName(product.getCategoryName()));
        }

        applicationEventPublisher.publishEvent(new BrandProductChangedEvent(brand, products));

        return products;
    }

    private ProductChangeContext toConvert(BrandProductChange brandProductChange) {
        Map<String, Long> toBeChangeablePriceByCategoryName = brandProductChange.getCategoryProducts().stream().collect(
                Collectors.toMap(BrandProductChange.CategoryProductChange::getCategoryName, BrandProductChange.CategoryProductChange::getPrice)
        );
        return new ProductChangeContext(toBeChangeablePriceByCategoryName);
    }

    @Transactional
    public void delete(String brandName) {
        Brand brand = brandRepository.findByName(brandName).orElseThrow(NotFoundException::new);
        List<Product> products = brand.getProducts();

        brandRepository.delete(brand);

        applicationEventPublisher.publishEvent(new BrandProductRemoveEvent(brand, products));
    }
}
