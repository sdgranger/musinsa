package com.musinsa.shop.controller;

import com.musinsa.shop.controller.response.LowestPriceProductByCategoryResponse;
import com.musinsa.shop.domain.outfit.entity.Category;
import com.musinsa.shop.service.dto.LowestPriceProductByCategory;
import com.musinsa.shop.domain.rank.service.RankService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class RankControllerTest extends Category {
    @InjectMocks
    private RankController rankController;
    @Mock
    private RankService rankService;

    @Test
    void getLowestPriceByCategory() {
        //given
        given(rankService.getProductOfLowestPriceByCategory()).willReturn(
                List.of(
                        new LowestPriceProductByCategory(1L, "C", "상의", 10000L),
                        new LowestPriceProductByCategory(22L, "E", "아우터", 5000L),
                        new LowestPriceProductByCategory(33L, "D", "바지", 3000L),
                        new LowestPriceProductByCategory(44L, "G", "스니커즈", 9000L),
                        new LowestPriceProductByCategory(55L, "A", "가방", 2000L),
                        new LowestPriceProductByCategory(66L, "D", "모자", 1500L),
                        new LowestPriceProductByCategory(77L, "I", "양말", 1700L),
                        new LowestPriceProductByCategory(88L, "F", "액새서리", 1900L)
                )
        );

        //when
        LowestPriceProductByCategoryResponse lowestPriceByCategory = rankController.getLowestPriceByCategory();

        //then
        assertEquals("34,100", lowestPriceByCategory.getTotalPrice());
        Assertions.assertThat(lowestPriceByCategory).usingRecursiveComparison().isEqualTo(
                new LowestPriceProductByCategoryResponse(
                        List.of(
                                List.of("상의", "C", "10,000"),
                                List.of("아우터", "E", "5,000"),
                                List.of("바지", "D", "3,000"),
                                List.of("스니커즈", "G", "9,000"),
                                List.of("가방", "A", "2,000"),
                                List.of("모자", "D", "1,500"),
                                List.of("양말", "I", "1,700"),
                                List.of("액새서리", "F", "1,900")
                        ),
                        "34,100"
                )
        );
    }
}