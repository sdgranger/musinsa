package com.musinsa.shop.infrastruture;

import com.musinsa.shop.domain.outfit.entity.RankProductMock;
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
                RankProductMock.create(1L, 1L, "상의", "A", 100000),
                RankProductMock.create(2L, 2L, "아우터", "A", 110000),
                RankProductMock.create(3L, 3L, "바지", "A", 120000),
                RankProductMock.create(4L, 4L, "스니커즈", "A", 130000),
                RankProductMock.create(5L, 5L, "가방", "A", 140000),
                RankProductMock.create(6L, 6L, "모자", "A", 150000),
                RankProductMock.create(7L, 7L, "양말", "A", 160000),
                RankProductMock.create(8L, 8L, "액새서리", "A", 170000),

                RankProductMock.create(9L,1L, "상의", "B", 140000),
                RankProductMock.create(10L, 2L, "아우터", "B", 100000),
                RankProductMock.create(11L, 3L, "바지", "B", 130000),
                RankProductMock.create(12L, 4L, "스니커즈", "B", 110000),
                RankProductMock.create(13L, 5L, "가방", "B", 140000),
                RankProductMock.create(14L, 6L, "모자", "B", 110000),
                RankProductMock.create(15L, 7L, "양말", "B", 190000),
                RankProductMock.create(16L, 8L, "액새서리", "B", 100000)
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
                RankProductMock.create(1L, 1L, "상의", "A", 100000),
                RankProductMock.create(2L, 2L, "아우터", "A", 110000),
                RankProductMock.create(3L, 3L, "바지", "A", 120000),
                RankProductMock.create(4L, 4L, "스니커즈", "A", 130000),
                RankProductMock.create(5L, 5L, "가방", "A", 140000),
                RankProductMock.create(6L, 6L, "모자", "A", 150000),
                RankProductMock.create(7L, 7L, "양말", "A", 160000),
                RankProductMock.create(8L, 8L, "액새서리", "A", 170000),

                RankProductMock.create(9L, 1L, "상의", "B", 140000),
                RankProductMock.create(10L, 2L, "아우터", "B", 100000),
                RankProductMock.create(11L, 3L, "바지", "B", 130000),
                RankProductMock.create(12L, 4L, "스니커즈", "B", 110000),
                RankProductMock.create(13L, 5L, "가방", "B", 140000),
                RankProductMock.create(14L, 6L, "모자", "B", 110000),
                RankProductMock.create(15L, 7L, "양말", "B", 190000),
                RankProductMock.create(16L, 8L, "액새서리", "B", 100000)
        );
        sortedProductByCategoryLocalRepository.saveAllSorted(products);

        //when
        List<RankProduct> lowPriceProductByCategory = sortedProductByCategoryLocalRepository.findLowestPriceProductByCategory();

        //then
        assertEquals(100000, lowPriceProductByCategory.get(0).getPrice());
        assertEquals(1L, lowPriceProductByCategory.get(0).getCategoryId());
        assertEquals("상의", lowPriceProductByCategory.get(0).getCategoryName());
        assertEquals("A", lowPriceProductByCategory.get(0).getBrandName());

        assertEquals(100000, lowPriceProductByCategory.get(1).getPrice());
        assertEquals(2L, lowPriceProductByCategory.get(1).getCategoryId());
        assertEquals("아우터", lowPriceProductByCategory.get(1).getCategoryName());
        assertEquals("B", lowPriceProductByCategory.get(1).getBrandName());

        assertEquals(120000, lowPriceProductByCategory.get(2).getPrice());
        assertEquals(3L, lowPriceProductByCategory.get(2).getCategoryId());
        assertEquals("바지", lowPriceProductByCategory.get(2).getCategoryName());
        assertEquals("A", lowPriceProductByCategory.get(2).getBrandName());

        assertEquals(110000, lowPriceProductByCategory.get(3).getPrice());
        assertEquals(4L, lowPriceProductByCategory.get(3).getCategoryId());
        assertEquals("스니커즈", lowPriceProductByCategory.get(3).getCategoryName());
        assertEquals("B", lowPriceProductByCategory.get(3).getBrandName());

        assertEquals(140000, lowPriceProductByCategory.get(4).getPrice());
        assertEquals(5L, lowPriceProductByCategory.get(4).getCategoryId());
        assertEquals("가방", lowPriceProductByCategory.get(4).getCategoryName());
        assertEquals("B", lowPriceProductByCategory.get(4).getBrandName());

        assertEquals(110000, lowPriceProductByCategory.get(5).getPrice());
        assertEquals(6L, lowPriceProductByCategory.get(5).getCategoryId());
        assertEquals("모자", lowPriceProductByCategory.get(5).getCategoryName());
        assertEquals("B", lowPriceProductByCategory.get(5).getBrandName());

        assertEquals(160000, lowPriceProductByCategory.get(6).getPrice());
        assertEquals(7L, lowPriceProductByCategory.get(6).getCategoryId());
        assertEquals("양말", lowPriceProductByCategory.get(6).getCategoryName());
        assertEquals("A", lowPriceProductByCategory.get(6).getBrandName());

        assertEquals(100000, lowPriceProductByCategory.get(7).getPrice());
        assertEquals(8L, lowPriceProductByCategory.get(7).getCategoryId());
        assertEquals("액새서리", lowPriceProductByCategory.get(7).getCategoryName());
        assertEquals("B", lowPriceProductByCategory.get(7).getBrandName());

    }

    @Test
    void delete() {
        //given
        List<RankProduct> products = List.of(
                RankProductMock.create(1L, 1L, "상의", "A", 100000),
                RankProductMock.create(2L, 2L, "아우터", "A", 110000),
                RankProductMock.create(3L, 3L, "바지", "A", 120000),
                RankProductMock.create(4L, 4L, "스니커즈", "A", 130000),
                RankProductMock.create(5L, 5L, "가방", "A", 140000),
                RankProductMock.create(6L, 6L, "모자", "A", 150000),
                RankProductMock.create(7L, 7L, "양말", "A", 160000),
                RankProductMock.create(8L, 8L, "액새서리", "A", 170000),

                RankProductMock.create(9L, 1L, "상의", "B", 140000),
                RankProductMock.create(10L, 2L, "아우터", "B", 100000),
                RankProductMock.create(11L, 3L, "바지", "B", 130000),
                RankProductMock.create(12L, 4L, "스니커즈", "B", 110000),
                RankProductMock.create(13L, 5L, "가방", "B", 140000),
                RankProductMock.create(14L, 6L, "모자", "B", 110000),
                RankProductMock.create(15L, 7L, "양말", "B", 190000),
                RankProductMock.create(16L, 8L, "액새서리", "B", 100000)
        );
        sortedProductByCategoryLocalRepository.saveAllSorted(products);

        List<RankProduct> deleteProducts = List.of(
                RankProductMock.create(1L, 1L, "상의", "A", 100000),
                RankProductMock.create(2L, 2L, "아우터", "A", 110000),
                RankProductMock.create(3L, 3L, "바지", "A", 120000),
                RankProductMock.create(4L, 4L, "스니커즈", "A", 130000),
                RankProductMock.create(5L, 5L, "가방", "A", 140000),
                RankProductMock.create(6L, 6L, "모자", "A", 150000),
                RankProductMock.create(7L, 7L, "양말", "A", 160000),
                RankProductMock.create(8L, 8L, "액새서리", "A", 170000)
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