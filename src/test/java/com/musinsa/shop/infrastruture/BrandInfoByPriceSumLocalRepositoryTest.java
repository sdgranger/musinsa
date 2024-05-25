package com.musinsa.shop.infrastruture;

import com.musinsa.shop.domain.rank.entity.BrandInfoByPriceSum;
import com.musinsa.shop.domain.rank.repository.BrandProductByPriceSumRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandInfoByPriceSumLocalRepositoryTest {
    private final BrandProductByPriceSumRepository brandOrderByPriceSumLocalRepository = new BrandProductByPriceSumLocalRepository();

    @Test
    @DisplayName("브랜드의 카테고리별 가격합계를 저장한다.")
    void save() {
        //given
        BrandInfoByPriceSum brandInfoByPriceSum = new BrandInfoByPriceSum(1L, "A", 314000);

        //when
        brandOrderByPriceSumLocalRepository.save(brandInfoByPriceSum);

        //then
        assertTrue(brandOrderByPriceSumLocalRepository.contains(brandInfoByPriceSum));
    }

    @Test
    @DisplayName("이미 저장된 브랜드의 카테고리별 가격합계를 저장한경우 이전정보(가격)를 갱신한다.")
    void duplicatedSave() {
        //given
        BrandInfoByPriceSum brandInfoByPriceSum = new BrandInfoByPriceSum(1L, "A", 314000);
        brandOrderByPriceSumLocalRepository.save(brandInfoByPriceSum);

        BrandInfoByPriceSum changedBrandInfo = new BrandInfoByPriceSum(1L, "A", 20000);

        //when
        brandOrderByPriceSumLocalRepository.save(changedBrandInfo);

        //then
        assertTrue(brandOrderByPriceSumLocalRepository.contains(changedBrandInfo));
        BrandInfoByPriceSum loadBrandInfo = brandOrderByPriceSumLocalRepository.findLowestBrand();
        assertEquals(changedBrandInfo.getSumPrice(), loadBrandInfo.getSumPrice());
        assertEquals(changedBrandInfo.getBrandId(), loadBrandInfo.getBrandId());
        assertEquals(changedBrandInfo.getBrandName(), loadBrandInfo.getBrandName());
    }

    @Test
    @DisplayName("가장 낮은 가격합계를 가진 브랜드정보를 가져온다.")
    void findLowestBrand() {
        //given
        BrandInfoByPriceSum higherBrandInfoByPriceSum = new BrandInfoByPriceSum(1L, "A", 314000);
        BrandInfoByPriceSum lowerBrandInfoByPriceSum = new BrandInfoByPriceSum(2L, "ㅠ", 112000);
        brandOrderByPriceSumLocalRepository.save(higherBrandInfoByPriceSum);
        brandOrderByPriceSumLocalRepository.save(lowerBrandInfoByPriceSum);

        //when
        BrandInfoByPriceSum loadBrandInfo = brandOrderByPriceSumLocalRepository.findLowestBrand();

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
        BrandInfoByPriceSum brandInfoByPriceSum = new BrandInfoByPriceSum(1L, "A", 314000);
        brandOrderByPriceSumLocalRepository.save(brandInfoByPriceSum);

        //when
        brandOrderByPriceSumLocalRepository.delete(brandInfoByPriceSum);

        //then
        assertFalse(brandOrderByPriceSumLocalRepository.contains(brandInfoByPriceSum));
        assertNull(brandOrderByPriceSumLocalRepository.findLowestBrand());
    }
}