package com.musinsa.shop.infrastruture;

import com.musinsa.shop.domain.outfit.entity.*;
import com.musinsa.shop.domain.rank.entity.RankBrand;
import com.musinsa.shop.domain.rank.entity.RankProduct;
import com.musinsa.shop.domain.rank.repository.RankProductByCategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortedProductByCategoryLocalRepositoryTest {
    private final RankProductByCategoryRepository sortedProductByCategoryLocalRepository = new SortedProductByCategoryLocalRepository();

    @Test
    @DisplayName("상품을 저장할때 ")
    void saveAllSorted() {
        //given
        List<RankProduct> products = List.of(
                RankProductMock.create(1L, RankCategoryMock.create(1L, "상의"), RankBrand.create(1L, "A"), 100000),
                RankProductMock.create(2L, RankCategoryMock.create(2L, "아우터"), RankBrand.create(1L, "A"), 110000),
                RankProductMock.create(3L, RankCategoryMock.create(3L, "바지"), RankBrand.create(1L, "A"), 120000),
                RankProductMock.create(4L, RankCategoryMock.create(4L, "스니커즈"), RankBrand.create(1L, "A"), 130000),
                RankProductMock.create(5L, RankCategoryMock.create(5L, "가방"), RankBrand.create(1L, "A"), 140000),
                RankProductMock.create(6L, RankCategoryMock.create(6L, "모자"), RankBrand.create(1L, "A"), 150000),
                RankProductMock.create(7L, RankCategoryMock.create(7L, "양말"), RankBrand.create(1L, "A"), 160000),
                RankProductMock.create(8L, RankCategoryMock.create(8L, "액새서리"), RankBrand.create(1L, "A"), 170000),

                RankProductMock.create(9L, RankCategoryMock.create(1L, "상의"), RankBrand.create(2L, "B"), 140000),
                RankProductMock.create(10L, RankCategoryMock.create(2L, "아우터"), RankBrand.create(2L, "B"), 100000),
                RankProductMock.create(11L, RankCategoryMock.create(3L, "바지"), RankBrand.create(2L, "B"), 130000),
                RankProductMock.create(12L, RankCategoryMock.create(4L, "스니커즈"), RankBrand.create(2L, "B"), 110000),
                RankProductMock.create(13L, RankCategoryMock.create(5L, "가방"), RankBrand.create(2L, "B"), 140000),
                RankProductMock.create(14L, RankCategoryMock.create(6L, "모자"), RankBrand.create(2L, "B"), 110000),
                RankProductMock.create(15L, RankCategoryMock.create(7L, "양말"), RankBrand.create(2L, "B"), 190000),
                RankProductMock.create(16L, RankCategoryMock.create(8L, "액새서리"), RankBrand.create(2L, "B"), 100000)
        );

        //when
        sortedProductByCategoryLocalRepository.saveAllSorted(products);

        //then
        List<RankProduct> byCategoryIdIsOne = sortedProductByCategoryLocalRepository.findByCategoryId(1L);
        assertEquals(100000, byCategoryIdIsOne.get(0).getPrice());
        assertEquals(140000, byCategoryIdIsOne.get(1).getPrice());
        List<RankProduct> byCategoryIdIsTwo = sortedProductByCategoryLocalRepository.findByCategoryId(2L);
        assertEquals(100000, byCategoryIdIsTwo.get(0).getPrice());
        assertEquals(110000, byCategoryIdIsTwo.get(1).getPrice());
        List<RankProduct> byCategoryIdIsThree = sortedProductByCategoryLocalRepository.findByCategoryId(3L);
        assertEquals(120000, byCategoryIdIsThree.get(0).getPrice());
        assertEquals(130000, byCategoryIdIsThree.get(1).getPrice());
        List<RankProduct> byCategoryIdIsFour = sortedProductByCategoryLocalRepository.findByCategoryId(4L);
        assertEquals(110000, byCategoryIdIsFour.get(0).getPrice());
        assertEquals(130000, byCategoryIdIsFour.get(1).getPrice());
        List<RankProduct> byCategoryIdIsFive = sortedProductByCategoryLocalRepository.findByCategoryId(5L);
        assertEquals(140000, byCategoryIdIsFive.get(1).getPrice());
        assertEquals(140000, byCategoryIdIsFive.get(0).getPrice());
        assertEquals(13L, byCategoryIdIsFive.get(0).getId());
        assertEquals(5L, byCategoryIdIsFive.get(1).getId());

    }

    @Test
    void findLowPriceProductByCategory() {
        //given
        List<RankProduct> products = List.of(
                RankProductMock.create(1L, RankCategoryMock.create(1L, "상의"), RankBrand.create(1L, "A"), 100000),
                RankProductMock.create(2L, RankCategoryMock.create(2L, "아우터"), RankBrand.create(1L, "A"), 110000),
                RankProductMock.create(3L, RankCategoryMock.create(3L, "바지"), RankBrand.create(1L, "A"), 120000),
                RankProductMock.create(4L, RankCategoryMock.create(4L, "스니커즈"), RankBrand.create(1L, "A"), 130000),
                RankProductMock.create(5L, RankCategoryMock.create(5L, "가방"), RankBrand.create(1L, "A"), 140000),
                RankProductMock.create(6L, RankCategoryMock.create(6L, "모자"), RankBrand.create(1L, "A"), 150000),
                RankProductMock.create(7L, RankCategoryMock.create(7L, "양말"), RankBrand.create(1L, "A"), 160000),
                RankProductMock.create(8L, RankCategoryMock.create(8L, "액새서리"), RankBrand.create(1L, "A"), 170000),

                RankProductMock.create(9L, RankCategoryMock.create(1L, "상의"), RankBrand.create(1L, "B"), 140000),
                RankProductMock.create(10L, RankCategoryMock.create(2L, "아우터"), RankBrand.create(1L, "B"), 100000),
                RankProductMock.create(11L, RankCategoryMock.create(3L, "바지"), RankBrand.create(1L, "B"), 130000),
                RankProductMock.create(12L, RankCategoryMock.create(4L, "스니커즈"), RankBrand.create(1L, "B"), 110000),
                RankProductMock.create(13L, RankCategoryMock.create(5L, "가방"), RankBrand.create(1L, "B"), 140000),
                RankProductMock.create(14L, RankCategoryMock.create(6L, "모자"), RankBrand.create(1L, "B"), 110000),
                RankProductMock.create(15L, RankCategoryMock.create(7L, "양말"), RankBrand.create(1L, "B"), 190000),
                RankProductMock.create(16L, RankCategoryMock.create(8L, "액새서리"), RankBrand.create(1L, "B"), 100000)
        );
        sortedProductByCategoryLocalRepository.saveAllSorted(products);

        //when
        List<RankProduct> lowPriceProductByCategory = sortedProductByCategoryLocalRepository.findLowestPriceProductByCategory();

        //then
        assertEquals(100000, lowPriceProductByCategory.get(0).getPrice());
        assertEquals(1L, lowPriceProductByCategory.get(0).getCategory().getId());
        assertEquals("상의", lowPriceProductByCategory.get(0).getCategory().getName());
        assertEquals("A", lowPriceProductByCategory.get(0).getBrand().getName());

        assertEquals(100000, lowPriceProductByCategory.get(1).getPrice());
        assertEquals(2L, lowPriceProductByCategory.get(1).getCategory().getId());
        assertEquals("아우터", lowPriceProductByCategory.get(1).getCategory().getName());
        assertEquals("B", lowPriceProductByCategory.get(1).getBrand().getName());

        assertEquals(120000, lowPriceProductByCategory.get(2).getPrice());
        assertEquals(3L, lowPriceProductByCategory.get(2).getCategory().getId());
        assertEquals("바지", lowPriceProductByCategory.get(2).getCategory().getName());
        assertEquals("A", lowPriceProductByCategory.get(2).getBrand().getName());

        assertEquals(110000, lowPriceProductByCategory.get(3).getPrice());
        assertEquals(4L, lowPriceProductByCategory.get(3).getCategory().getId());
        assertEquals("스니커즈", lowPriceProductByCategory.get(3).getCategory().getName());
        assertEquals("B", lowPriceProductByCategory.get(3).getBrand().getName());

        assertEquals(140000, lowPriceProductByCategory.get(4).getPrice());
        assertEquals(5L, lowPriceProductByCategory.get(4).getCategory().getId());
        assertEquals("가방", lowPriceProductByCategory.get(4).getCategory().getName());
        assertEquals("B", lowPriceProductByCategory.get(4).getBrand().getName());

        assertEquals(110000, lowPriceProductByCategory.get(5).getPrice());
        assertEquals(6L, lowPriceProductByCategory.get(5).getCategory().getId());
        assertEquals("모자", lowPriceProductByCategory.get(5).getCategory().getName());
        assertEquals("B", lowPriceProductByCategory.get(5).getBrand().getName());

        assertEquals(160000, lowPriceProductByCategory.get(6).getPrice());
        assertEquals(7L, lowPriceProductByCategory.get(6).getCategory().getId());
        assertEquals("양말", lowPriceProductByCategory.get(6).getCategory().getName());
        assertEquals("A", lowPriceProductByCategory.get(6).getBrand().getName());

        assertEquals(100000, lowPriceProductByCategory.get(7).getPrice());
        assertEquals(8L, lowPriceProductByCategory.get(7).getCategory().getId());
        assertEquals("액새서리", lowPriceProductByCategory.get(7).getCategory().getName());
        assertEquals("B", lowPriceProductByCategory.get(7).getBrand().getName());

    }

    @Test
    void delete() {
        //given
        List<RankProduct> products = List.of(
                RankProductMock.create(1L, RankCategoryMock.create(1L, "상의"), RankBrand.create(1L, "A"), 100000),
                RankProductMock.create(2L, RankCategoryMock.create(2L, "아우터"), RankBrand.create(1L, "A"), 110000),
                RankProductMock.create(3L, RankCategoryMock.create(3L, "바지"), RankBrand.create(1L, "A"), 120000),
                RankProductMock.create(4L, RankCategoryMock.create(4L, "스니커즈"), RankBrand.create(1L, "A"), 130000),
                RankProductMock.create(5L, RankCategoryMock.create(5L, "가방"), RankBrand.create(1L, "A"), 140000),
                RankProductMock.create(6L, RankCategoryMock.create(6L, "모자"), RankBrand.create(1L, "A"), 150000),
                RankProductMock.create(7L, RankCategoryMock.create(7L, "양말"), RankBrand.create(1L, "A"), 160000),
                RankProductMock.create(8L, RankCategoryMock.create(8L, "액새서리"), RankBrand.create(1L, "A"), 170000),

                RankProductMock.create(9L, RankCategoryMock.create(1L, "상의"), RankBrand.create(1L, "B"), 140000),
                RankProductMock.create(10L, RankCategoryMock.create(2L, "아우터"), RankBrand.create(1L, "B"), 100000),
                RankProductMock.create(11L, RankCategoryMock.create(3L, "바지"), RankBrand.create(1L, "B"), 130000),
                RankProductMock.create(12L, RankCategoryMock.create(4L, "스니커즈"), RankBrand.create(1L, "B"), 110000),
                RankProductMock.create(13L, RankCategoryMock.create(5L, "가방"), RankBrand.create(1L, "B"), 140000),
                RankProductMock.create(14L, RankCategoryMock.create(6L, "모자"), RankBrand.create(1L, "B"), 110000),
                RankProductMock.create(15L, RankCategoryMock.create(7L, "양말"), RankBrand.create(1L, "B"), 190000),
                RankProductMock.create(16L, RankCategoryMock.create(8L, "액새서리"), RankBrand.create(1L, "B"), 100000)
        );
        sortedProductByCategoryLocalRepository.saveAllSorted(products);

        List<RankProduct> deleteProducts = List.of(
                RankProductMock.create(1L, RankCategoryMock.create(1L, "상의"), RankBrand.create(1L, "A"), 100000),
                RankProductMock.create(2L, RankCategoryMock.create(2L, "아우터"), RankBrand.create(1L, "A"), 110000),
                RankProductMock.create(3L, RankCategoryMock.create(3L, "바지"), RankBrand.create(1L, "A"), 120000),
                RankProductMock.create(4L, RankCategoryMock.create(4L, "스니커즈"), RankBrand.create(1L, "A"), 130000),
                RankProductMock.create(5L, RankCategoryMock.create(5L, "가방"), RankBrand.create(1L, "A"), 140000),
                RankProductMock.create(6L, RankCategoryMock.create(6L, "모자"), RankBrand.create(1L, "A"), 150000),
                RankProductMock.create(7L, RankCategoryMock.create(7L, "양말"), RankBrand.create(1L, "A"), 160000),
                RankProductMock.create(8L, RankCategoryMock.create(8L, "액새서리"), RankBrand.create(1L, "A"), 170000)
        );

        //when
        sortedProductByCategoryLocalRepository.delete(deleteProducts);

        //then
        List<RankProduct> byCategoryIdIsOne = sortedProductByCategoryLocalRepository.findByCategoryId(1L);
        assertEquals(140000, byCategoryIdIsOne.get(0).getPrice());
        assertEquals(9L, byCategoryIdIsOne.get(0).getId());
        assertEquals(1, byCategoryIdIsOne.size());
        List<RankProduct> byCategoryIdIsTwo = sortedProductByCategoryLocalRepository.findByCategoryId(2L);
        assertEquals(100000, byCategoryIdIsTwo.get(0).getPrice());
        assertEquals(10L, byCategoryIdIsTwo.get(0).getId());
        assertEquals(1, byCategoryIdIsTwo.size());
        List<RankProduct> byCategoryIdIsFive = sortedProductByCategoryLocalRepository.findByCategoryId(5L);
        assertEquals(140000, byCategoryIdIsFive.get(0).getPrice());
        assertEquals(13L, byCategoryIdIsFive.get(0).getId());
        assertEquals(1, byCategoryIdIsFive.size());
    }
}