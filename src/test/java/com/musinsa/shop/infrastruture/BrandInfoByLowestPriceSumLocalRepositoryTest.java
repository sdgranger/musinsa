package com.musinsa.shop.infrastruture;

import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandInfoByLowestPriceSumLocalRepositoryTest {
    private final BrandProductByPriceSumRepository brandOrderByPriceSumLocalRepository = new BrandProductByPriceSumLocalRepository();

    @Test
    @DisplayName("브랜드의 카테고리별 가격합계를 저장한다.")
    void save() {
        //given
        BrandInfoByLowestPriceSum brandInfoByLowestPriceSum = new BrandInfoByLowestPriceSum(1L, "A", 314000);

        //when
        brandOrderByPriceSumLocalRepository.save(brandInfoByLowestPriceSum);

        //then
        assertTrue(brandOrderByPriceSumLocalRepository.contains(brandInfoByLowestPriceSum));
    }

    @Test
    @DisplayName("이미 저장된 브랜드의 카테고리별 가격합계를 저장한경우 이전정보(가격)를 갱신한다.")
    void duplicatedSave() {
        //given
        BrandInfoByLowestPriceSum brandInfoByLowestPriceSum = new BrandInfoByLowestPriceSum(1L, "A", 314000);
        brandOrderByPriceSumLocalRepository.save(brandInfoByLowestPriceSum);

        BrandInfoByLowestPriceSum changedBrandInfo = new BrandInfoByLowestPriceSum(1L, "A", 20000);

        //when
        brandOrderByPriceSumLocalRepository.save(changedBrandInfo);

        //then
        assertTrue(brandOrderByPriceSumLocalRepository.contains(changedBrandInfo));
        BrandInfoByLowestPriceSum loadBrandInfo = brandOrderByPriceSumLocalRepository.findBrandByLowestPriceSum();
        assertEquals(changedBrandInfo.getSumPrice(), loadBrandInfo.getSumPrice());
        assertEquals(changedBrandInfo.getBrandId(), loadBrandInfo.getBrandId());
        assertEquals(changedBrandInfo.getBrandName(), loadBrandInfo.getBrandName());
    }

    @Test
    @DisplayName("가장 낮은 가격합계를 가진 브랜드정보를 가져온다.")
    void findLowestBrand() {
        //given
        BrandInfoByLowestPriceSum higherBrandInfoByPriceSum = new BrandInfoByLowestPriceSum(1L, "A", 314000);
        BrandInfoByLowestPriceSum lowerBrandInfoByPriceSum = new BrandInfoByLowestPriceSum(2L, "ㅠ", 112000);
        brandOrderByPriceSumLocalRepository.save(higherBrandInfoByPriceSum);
        brandOrderByPriceSumLocalRepository.save(lowerBrandInfoByPriceSum);

        //when
        BrandInfoByLowestPriceSum loadBrandInfo = brandOrderByPriceSumLocalRepository.findBrandByLowestPriceSum();

        //then
        assertEquals(lowerBrandInfoByPriceSum.getSumPrice(), loadBrandInfo.getSumPrice());
        assertEquals(lowerBrandInfoByPriceSum.getBrandId(), loadBrandInfo.getBrandId());
        assertEquals(lowerBrandInfoByPriceSum.getBrandName(), loadBrandInfo.getBrandName());
        assertEquals(lowerBrandInfoByPriceSum.getId(), loadBrandInfo.getId());
    }

    @Test
    @DisplayName("저장된 합계 브랜드정보를 삭제한다.")
    void delete() {
        //given
        BrandInfoByLowestPriceSum brandInfoByLowestPriceSum = new BrandInfoByLowestPriceSum(1L, "A", 314000);
        brandOrderByPriceSumLocalRepository.save(brandInfoByLowestPriceSum);

        //when
        brandOrderByPriceSumLocalRepository.delete(brandInfoByLowestPriceSum);

        //then
        assertFalse(brandOrderByPriceSumLocalRepository.contains(brandInfoByLowestPriceSum));
        assertNull(brandOrderByPriceSumLocalRepository.findBrandByLowestPriceSum());
    }
}