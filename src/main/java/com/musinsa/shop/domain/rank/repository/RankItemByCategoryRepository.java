package com.musinsa.shop.domain.rank.repository;

import com.musinsa.shop.domain.outfit.entity.Item;

import java.util.List;

public interface RankItemByCategoryRepository {
    void saveAllSorted(Iterable<Item> items);

    List<Item> findLowPriceItemByCategory();
}
