package com.musinsa.shop.config;

import com.musinsa.shop.domain.rank.service.RankAggregator;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MusinsaShopApplicationRunner implements ApplicationRunner {
    private final RankAggregator rankAggregator;

    @Override
    public void run(ApplicationArguments args) {
        rankAggregator.execute();
    }
}
