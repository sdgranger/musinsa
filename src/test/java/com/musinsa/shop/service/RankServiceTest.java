package com.musinsa.shop.service;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.CategoryMock;
import com.musinsa.shop.domain.outfit.entity.ProductMock;
import com.musinsa.shop.domain.outfit.entity.RankProductMock;
import com.musinsa.shop.domain.outfit.repository.BrandRepository;
import com.musinsa.shop.domain.outfit.repository.CategoryRepository;
import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
import com.musinsa.shop.infrastruture.SortedProductByCategoryLocalRepository;
import com.musinsa.shop.service.dto.CategoryStatistics;
import com.musinsa.shop.service.dto.LowestPriceProductByCategory;
import com.musinsa.shop.service.dto.LowestPriceSumProductByBrand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RankServiceTest {
    @InjectMocks
    private RankService rankService;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private SortedProductByCategoryLocalRepository sortedProductByCategoryLocalRepository;
    @Mock
    private BrandProductByPriceSumRepository brandProductByPriceSumRepository;

    @Test
    @DisplayName("카테고리 이름에 해당되는 최소가격과 최대가격의 브랜드 상품을 조회합니다.")
    void getByCategory() {
        //given
        given(categoryRepository.findByName("상의")).willReturn(
                Optional.of(new CategoryMock(1L, "상의"))
        );

        given(sortedProductByCategoryLocalRepository.findByCategoryOrderByPriceIsAsc(1L))
                .willReturn(
                        List.of(
                                RankProductMock.create(1L, 1L, "상의", "B", 100000),
                                RankProductMock.create(2L, 1L, "상의", "A", 110000),
                                RankProductMock.create(3L, 1L, "상의", "C", 120000),
                                RankProductMock.create(4L, 1L, "상의", "D", 130000),
                                RankProductMock.create(5L, 1L, "상의", "E", 140000),
                                RankProductMock.create(6L, 1L, "상의", "F", 140000)
                        )

                );

        //when
        CategoryStatistics categoryStatistics = rankService.getByCategoryName("상의");

        //then
        Assertions.assertThat(categoryStatistics).usingRecursiveComparison().isEqualTo(
                new CategoryStatistics(
                        "상의",
                        List.of(
                                new CategoryStatistics.BrandProduct("B", 100000L)
                        ),
                        List.of(
                                new CategoryStatistics.BrandProduct("E", 140000L),
                                new CategoryStatistics.BrandProduct("F", 140000L)
                        )

                )
        );
    }

    @Test
    @DisplayName("카테고리 별로 가장 최저가격의 브랜드 상품을 조회합니다.")
    void getProductOfLowestPriceByCategory() {
        given(sortedProductByCategoryLocalRepository.findLowestPriceProductByCategory())
                .willReturn(List.of(
                        RankProductMock.create(1L, 1L, "상의", "A", 100000),
                        RankProductMock.create(12L, 2L, "아우터", "C", 110000),
                        RankProductMock.create(23L, 3L, "바지", "E", 120000),
                        RankProductMock.create(34L, 4L, "스니커즈","G", 130000),
                        RankProductMock.create(45L, 5L, "가방", "B", 140000),
                        RankProductMock.create(56L, 6L, "모자", "D", 150000),
                        RankProductMock.create(67L, 7L, "양말", "F", 160000),
                        RankProductMock.create(78L, 8L, "액새서리", "H", 170000)
                        )
                );

        //when
        List<LowestPriceProductByCategory> lowestPriceByCategory = rankService.getProductOfLowestPriceByCategory();

        //then
        assertEquals(1L, lowestPriceByCategory.get(0).getProductId());
        assertEquals(100000, lowestPriceByCategory.get(0).getPrice());
        assertEquals("A", lowestPriceByCategory.get(0).getBrandName());
        assertEquals("상의", lowestPriceByCategory.get(0).getCategoryName());

        assertEquals(12L, lowestPriceByCategory.get(1).getProductId());
        assertEquals(110000, lowestPriceByCategory.get(1).getPrice());
        assertEquals("C", lowestPriceByCategory.get(1).getBrandName());
        assertEquals("아우터", lowestPriceByCategory.get(1).getCategoryName());

        assertEquals(23L, lowestPriceByCategory.get(2).getProductId());
        assertEquals(120000, lowestPriceByCategory.get(2).getPrice());
        assertEquals("E", lowestPriceByCategory.get(2).getBrandName());
        assertEquals("바지", lowestPriceByCategory.get(2).getCategoryName());

        assertEquals(34L, lowestPriceByCategory.get(3).getProductId());
        assertEquals(130000, lowestPriceByCategory.get(3).getPrice());
        assertEquals("G", lowestPriceByCategory.get(3).getBrandName());
        assertEquals("스니커즈", lowestPriceByCategory.get(3).getCategoryName());

        assertEquals(45L, lowestPriceByCategory.get(4).getProductId());
        assertEquals(140000, lowestPriceByCategory.get(4).getPrice());
        assertEquals("B", lowestPriceByCategory.get(4).getBrandName());
        assertEquals("가방", lowestPriceByCategory.get(4).getCategoryName());

        assertEquals(56L, lowestPriceByCategory.get(5).getProductId());
        assertEquals(150000, lowestPriceByCategory.get(5).getPrice());
        assertEquals("D", lowestPriceByCategory.get(5).getBrandName());
        assertEquals("모자", lowestPriceByCategory.get(5).getCategoryName());

        assertEquals(67L, lowestPriceByCategory.get(6).getProductId());
        assertEquals(160000, lowestPriceByCategory.get(6).getPrice());
        assertEquals("F", lowestPriceByCategory.get(6).getBrandName());
        assertEquals("양말", lowestPriceByCategory.get(6).getCategoryName());

        assertEquals(78L, lowestPriceByCategory.get(7).getProductId());
        assertEquals(170000, lowestPriceByCategory.get(7).getPrice());
        assertEquals("H", lowestPriceByCategory.get(7).getBrandName());
        assertEquals("액새서리", lowestPriceByCategory.get(7).getCategoryName());
    }

    @Test
    @DisplayName("합계금액이 가장 낮은 브랜드의 카테고리별 상품목록을 조회합니다.")
    void getByBrand() {
        given(brandProductByPriceSumRepository.findBrandByLowestPriceSum())
                .willReturn(new BrandInfoByLowestPriceSum(2L, "B", 3140000));

        Brand brand = Brand.create("B");
        brand.addProducts(List.of(
                ProductMock.create(1L, CategoryMock.create(1L, "상의"), Brand.create("B"), 100000),
                ProductMock.create(12L, CategoryMock.create(2L, "아우터"), Brand.create("B"), 110000),
                ProductMock.create(23L, CategoryMock.create(3L, "바지"), Brand.create("B"), 120000),
                ProductMock.create(34L, CategoryMock.create(4L, "스니커즈"), Brand.create("B"), 130000),
                ProductMock.create(45L, CategoryMock.create(5L, "가방"), Brand.create("B"), 140000),
                ProductMock.create(56L, CategoryMock.create(6L, "모자"), Brand.create("B"), 150000),
                ProductMock.create(67L, CategoryMock.create(7L, "양말"), Brand.create("B"), 160000),
                ProductMock.create(78L, CategoryMock.create(8L, "액새서리"), Brand.create("B"), 170000)));
        given(brandRepository.findById(2L)).willReturn(Optional.of(brand));

        //when
        LowestPriceSumProductByBrand lowestPriceProduct = rankService.getByBrand();

        //then
        assertEquals("B", lowestPriceProduct.getBrandName());
        Assertions.assertThat(lowestPriceProduct.getCategoryProduct()).usingRecursiveComparison().isEqualTo(
               List.of(
                       LowestPriceSumProductByBrand.CategoryProduct.create("상의", 100000),
                       LowestPriceSumProductByBrand.CategoryProduct.create("아우터", 110000),
                       LowestPriceSumProductByBrand.CategoryProduct.create("바지", 120000),
                       LowestPriceSumProductByBrand.CategoryProduct.create("스니커즈", 130000),
                       LowestPriceSumProductByBrand.CategoryProduct.create("가방", 140000),
                       LowestPriceSumProductByBrand.CategoryProduct.create("모자", 150000),
                       LowestPriceSumProductByBrand.CategoryProduct.create("양말", 160000),
                       LowestPriceSumProductByBrand.CategoryProduct.create("액새서리", 170000)
               )
        );
        assertEquals(1080000, lowestPriceProduct.getTotalPrice());
    }
}