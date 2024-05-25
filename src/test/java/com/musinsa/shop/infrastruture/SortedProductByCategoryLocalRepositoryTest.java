package com.musinsa.shop.infrastruture;

import com.musinsa.shop.domain.outfit.entity.*;
import com.musinsa.shop.domain.outfit.entity.ProductMock;
import com.musinsa.shop.domain.outfit.entity.Product;
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
        List<Product> products = List.of(
                ProductMock.create(1L, CategoryMock.create(1L, "상의"), Brand.create("A"), 100000),
                ProductMock.create(2L, CategoryMock.create(2L, "아우터"), Brand.create("A"), 110000),
                ProductMock.create(3L, CategoryMock.create(3L, "바지"), Brand.create("A"), 120000),
                ProductMock.create(4L, CategoryMock.create(4L, "스니커즈"), Brand.create("A"), 130000),
                ProductMock.create(5L, CategoryMock.create(5L, "가방"), Brand.create("A"), 140000),
                ProductMock.create(6L, CategoryMock.create(6L, "모자"), Brand.create("A"), 150000),
                ProductMock.create(7L, CategoryMock.create(7L, "양말"), Brand.create("A"), 160000),
                ProductMock.create(8L, CategoryMock.create(8L, "액새서리"), Brand.create("A"), 170000),

                ProductMock.create(9L, CategoryMock.create(1L, "상의"), Brand.create("B"), 140000),
                ProductMock.create(10L, CategoryMock.create(2L, "아우터"), Brand.create("B"), 100000),
                ProductMock.create(11L, CategoryMock.create(3L, "바지"), Brand.create("B"), 130000),
                ProductMock.create(12L, CategoryMock.create(4L, "스니커즈"), Brand.create("B"), 110000),
                ProductMock.create(13L, CategoryMock.create(5L, "가방"), Brand.create("B"), 140000),
                ProductMock.create(14L, CategoryMock.create(6L, "모자"), Brand.create("B"), 110000),
                ProductMock.create(15L, CategoryMock.create(7L, "양말"), Brand.create("B"), 190000),
                ProductMock.create(16L, CategoryMock.create(8L, "액새서리"), Brand.create("B"), 100000)
        );

        //when
        sortedProductByCategoryLocalRepository.saveAllSorted(products);

        //then
        List<Product> byCategoryIdIsOne = sortedProductByCategoryLocalRepository.findByCategoryId(1L);
        assertEquals(100000, byCategoryIdIsOne.get(0).getPrice());
        assertEquals(140000, byCategoryIdIsOne.get(1).getPrice());
        List<Product> byCategoryIdIsTwo = sortedProductByCategoryLocalRepository.findByCategoryId(2L);
        assertEquals(100000, byCategoryIdIsTwo.get(0).getPrice());
        assertEquals(110000, byCategoryIdIsTwo.get(1).getPrice());
        List<Product> byCategoryIdIsThree = sortedProductByCategoryLocalRepository.findByCategoryId(3L);
        assertEquals(120000, byCategoryIdIsThree.get(0).getPrice());
        assertEquals(130000, byCategoryIdIsThree.get(1).getPrice());
        List<Product> byCategoryIdIsFour = sortedProductByCategoryLocalRepository.findByCategoryId(4L);
        assertEquals(110000, byCategoryIdIsFour.get(0).getPrice());
        assertEquals(130000, byCategoryIdIsFour.get(1).getPrice());
        List<Product> byCategoryIdIsFive = sortedProductByCategoryLocalRepository.findByCategoryId(5L);
        assertEquals(140000, byCategoryIdIsFive.get(1).getPrice());
        assertEquals(140000, byCategoryIdIsFive.get(0).getPrice());
        assertEquals(13L, byCategoryIdIsFive.get(0).getId());
        assertEquals(5L, byCategoryIdIsFive.get(1).getId());

    }

    @Test
    void findLowPriceProductByCategory() {
        //given
        List<Product> products = List.of(
                ProductMock.create(1L, CategoryMock.create(1L, "상의"), Brand.create("A"), 100000),
                ProductMock.create(2L, CategoryMock.create(2L, "아우터"), Brand.create("A"), 110000),
                ProductMock.create(3L, CategoryMock.create(3L, "바지"), Brand.create("A"), 120000),
                ProductMock.create(4L, CategoryMock.create(4L, "스니커즈"), Brand.create("A"), 130000),
                ProductMock.create(5L, CategoryMock.create(5L, "가방"), Brand.create("A"), 140000),
                ProductMock.create(6L, CategoryMock.create(6L, "모자"), Brand.create("A"), 150000),
                ProductMock.create(7L, CategoryMock.create(7L, "양말"), Brand.create("A"), 160000),
                ProductMock.create(8L, CategoryMock.create(8L, "액새서리"), Brand.create("A"), 170000),

                ProductMock.create(9L, CategoryMock.create(1L, "상의"), Brand.create("B"), 140000),
                ProductMock.create(10L, CategoryMock.create(2L, "아우터"), Brand.create("B"), 100000),
                ProductMock.create(11L, CategoryMock.create(3L, "바지"), Brand.create("B"), 130000),
                ProductMock.create(12L, CategoryMock.create(4L, "스니커즈"), Brand.create("B"), 110000),
                ProductMock.create(13L, CategoryMock.create(5L, "가방"), Brand.create("B"), 140000),
                ProductMock.create(14L, CategoryMock.create(6L, "모자"), Brand.create("B"), 110000),
                ProductMock.create(15L, CategoryMock.create(7L, "양말"), Brand.create("B"), 190000),
                ProductMock.create(16L, CategoryMock.create(8L, "액새서리"), Brand.create("B"), 100000)
        );
        sortedProductByCategoryLocalRepository.saveAllSorted(products);

        //when
        List<Product> lowPriceProductByCategory = sortedProductByCategoryLocalRepository.findLowestPriceProductByCategory();

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
        List<Product> products = List.of(
                ProductMock.create(1L, CategoryMock.create(1L, "상의"), Brand.create("A"), 100000),
                ProductMock.create(2L, CategoryMock.create(2L, "아우터"), Brand.create("A"), 110000),
                ProductMock.create(3L, CategoryMock.create(3L, "바지"), Brand.create("A"), 120000),
                ProductMock.create(4L, CategoryMock.create(4L, "스니커즈"), Brand.create("A"), 130000),
                ProductMock.create(5L, CategoryMock.create(5L, "가방"), Brand.create("A"), 140000),
                ProductMock.create(6L, CategoryMock.create(6L, "모자"), Brand.create("A"), 150000),
                ProductMock.create(7L, CategoryMock.create(7L, "양말"), Brand.create("A"), 160000),
                ProductMock.create(8L, CategoryMock.create(8L, "액새서리"), Brand.create("A"), 170000),

                ProductMock.create(9L, CategoryMock.create(1L, "상의"), Brand.create("B"), 140000),
                ProductMock.create(10L, CategoryMock.create(2L, "아우터"), Brand.create("B"), 100000),
                ProductMock.create(11L, CategoryMock.create(3L, "바지"), Brand.create("B"), 130000),
                ProductMock.create(12L, CategoryMock.create(4L, "스니커즈"), Brand.create("B"), 110000),
                ProductMock.create(13L, CategoryMock.create(5L, "가방"), Brand.create("B"), 140000),
                ProductMock.create(14L, CategoryMock.create(6L, "모자"), Brand.create("B"), 110000),
                ProductMock.create(15L, CategoryMock.create(7L, "양말"), Brand.create("B"), 190000),
                ProductMock.create(16L, CategoryMock.create(8L, "액새서리"), Brand.create("B"), 100000)
        );
        sortedProductByCategoryLocalRepository.saveAllSorted(products);

        List<Product> deleteProducts = List.of(
                ProductMock.create(1L, CategoryMock.create(1L, "상의"), Brand.create("A"), 100000),
                ProductMock.create(2L, CategoryMock.create(2L, "아우터"), Brand.create("A"), 110000),
                ProductMock.create(3L, CategoryMock.create(3L, "바지"), Brand.create("A"), 120000),
                ProductMock.create(4L, CategoryMock.create(4L, "스니커즈"), Brand.create("A"), 130000),
                ProductMock.create(5L, CategoryMock.create(5L, "가방"), Brand.create("A"), 140000),
                ProductMock.create(6L, CategoryMock.create(6L, "모자"), Brand.create("A"), 150000),
                ProductMock.create(7L, CategoryMock.create(7L, "양말"), Brand.create("A"), 160000),
                ProductMock.create(8L, CategoryMock.create(8L, "액새서리"), Brand.create("A"), 170000)
        );

        //when
        sortedProductByCategoryLocalRepository.delete(deleteProducts);

        //then
        List<Product> byCategoryIdIsOne = sortedProductByCategoryLocalRepository.findByCategoryId(1L);
        assertEquals(140000, byCategoryIdIsOne.get(0).getPrice());
        assertEquals(9L, byCategoryIdIsOne.get(0).getId());
        assertEquals(1, byCategoryIdIsOne.size());
        List<Product> byCategoryIdIsTwo = sortedProductByCategoryLocalRepository.findByCategoryId(2L);
        assertEquals(100000, byCategoryIdIsTwo.get(0).getPrice());
        assertEquals(10L, byCategoryIdIsTwo.get(0).getId());
        assertEquals(1, byCategoryIdIsTwo.size());
        List<Product> byCategoryIdIsFive = sortedProductByCategoryLocalRepository.findByCategoryId(5L);
        assertEquals(140000, byCategoryIdIsFive.get(0).getPrice());
        assertEquals(13L, byCategoryIdIsFive.get(0).getId());
        assertEquals(1, byCategoryIdIsFive.size());
    }
}