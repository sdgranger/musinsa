package com.musinsa.shop.service;

import com.musinsa.shop.domain.outfit.entity.*;
import com.musinsa.shop.domain.outfit.repository.BrandRepository;
import com.musinsa.shop.domain.outfit.service.CategoryService;
import com.musinsa.shop.event.BrandProductChangedEvent;
import com.musinsa.shop.event.BrandProductCreatedEvent;
import com.musinsa.shop.event.BrandProductRemoveEvent;
import com.musinsa.shop.service.dto.BrandProductAdd;
import com.musinsa.shop.service.dto.BrandProductChange;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandProductCrudServiceTest {
    @InjectMocks
    private BrandProductCrudService brandProductCrudService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher;
    @Mock
    private ProductChangeValidator productChangeValidator;

    @Test
    void addTest() {
        //given
        given(categoryService.find()).willReturn(new CategoryStorage(Map.of("상의", new CategoryMock(1L, "상의"))));

        //when
        brandProductCrudService.add(
                new BrandProductAdd(
                        "A", List.of(
                                new BrandProductAdd.CategoryProductAdd("상의", 10000L)
                ))
        );

        //then
        verify(brandRepository, times(1)).save(any(Brand.class));
        verify(applicationEventPublisher, times(1)).publishEvent(any(BrandProductCreatedEvent.class));

    }

    @Test
    void updateTest() {
        //given
        given(categoryService.find()).willReturn(new CategoryStorage(Map.of("상의", new CategoryMock(1L, "상의"))));

        Brand brand = Brand.create("A");
        brand.addProduct(Product.create(Category.create("상의"), brand, 10000L));
        brand.addProduct(Product.create(Category.create("아우터"), brand, 2000L));
        brand.addProduct(Product.create(Category.create("바지"), brand, 3000L));
        brand.addProduct(Product.create(Category.create("스니커즈"), brand, 4000L));
        brand.addProduct(Product.create(Category.create("가방"), brand, 5000L));
        brand.addProduct(Product.create(Category.create("양말"), brand, 6000L));
        brand.addProduct(Product.create(Category.create("액세서리"), brand, 7000L));
        given(brandRepository.findByName("A")).willReturn(Optional.of(brand));

        ///when
        List<Product> changedProducts = brandProductCrudService.update(
                new BrandProductChange(
                        "A", List.of(
                                new BrandProductChange.CategoryProductChange("상의", 10L),
                                new BrandProductChange.CategoryProductChange("아우터", 11L),
                                new BrandProductChange.CategoryProductChange("바지", 12L),
                                new BrandProductChange.CategoryProductChange("스니커즈", 13L),
                                new BrandProductChange.CategoryProductChange("가방", 14L),
                                new BrandProductChange.CategoryProductChange("양말", 15L),
                                new BrandProductChange.CategoryProductChange("액세서리", 16L)
                        )
                )
        );

        //then
        verify(productChangeValidator, times(1)).validateCategorySize(any(CategoryStorage.class), any(BrandProductChange.class));
        Assertions.assertThat(changedProducts).usingRecursiveComparison().isEqualTo(
                List.of(
                        Product.create(Category.create("상의"), brand, 10L),
                        Product.create(Category.create("아우터"), brand, 11L),
                        Product.create(Category.create("바지"), brand, 12L),
                        Product.create(Category.create("스니커즈"), brand, 13L),
                        Product.create(Category.create("가방"), brand, 14L),
                        Product.create(Category.create("양말"), brand, 15L),
                        Product.create(Category.create("액세서리"), brand, 16L)
                )
        );
        verify(applicationEventPublisher, times(1)).publishEvent(any(BrandProductChangedEvent.class));
    }

    @Test
    void deleteTest() {
        Brand brand = Brand.create("A");
        brand.addProduct(Product.create(Category.create("상의"), brand, 10000L));
        brand.addProduct(Product.create(Category.create("아우터"), brand, 2000L));
        brand.addProduct(Product.create(Category.create("바지"), brand, 3000L));
        brand.addProduct(Product.create(Category.create("스니커즈"), brand, 4000L));
        brand.addProduct(Product.create(Category.create("가방"), brand, 5000L));
        brand.addProduct(Product.create(Category.create("양말"), brand, 6000L));
        brand.addProduct(Product.create(Category.create("액세서리"), brand, 7000L));
        given(brandRepository.findByName("A")).willReturn(Optional.of(brand));

        //when
        brandProductCrudService.delete("A");

        verify(brandRepository, times(1)).delete(brand);
        verify(applicationEventPublisher, times(1)).publishEvent(any(BrandProductRemoveEvent.class));
    }
}