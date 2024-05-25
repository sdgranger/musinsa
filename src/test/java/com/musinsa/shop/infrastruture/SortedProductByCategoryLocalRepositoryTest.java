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
                ProductMock.create(7L, CategoryMock.create(7L, "양발"), Brand.create("A"), 160000),
                ProductMock.create(8L, CategoryMock.create(8L, "액새서리"), Brand.create("A"), 170000),

                ProductMock.create(9L, CategoryMock.create(1L, "상의"), Brand.create("B"), 140000),
                ProductMock.create(10L, CategoryMock.create(2L, "아우터"), Brand.create("B"), 100000),
                ProductMock.create(11L, CategoryMock.create(3L, "바지"), Brand.create("B"), 130000),
                ProductMock.create(12L, CategoryMock.create(4L, "스니커즈"), Brand.create("B"), 110000),
                ProductMock.create(13L, CategoryMock.create(5L, "가방"), Brand.create("B"), 140000),
                ProductMock.create(14L, CategoryMock.create(6L, "모자"), Brand.create("B"), 110000),
                ProductMock.create(15L, CategoryMock.create(7L, "양발"), Brand.create("B"), 190000),
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
    }

    @Test
    void findByCategoryOrderByPriceIsAsc() {
    }

    @Test
    void delete() {
    }
}