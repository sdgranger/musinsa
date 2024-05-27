package com.musinsa.shop.infrastruture;

import com.musinsa.shop.domain.rank.entity.RankProduct;
import com.musinsa.shop.domain.rank.repository.RankProductByCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

@Repository
public class SortedProductByCategoryLocalRepository implements RankProductByCategoryRepository {
    private final Map<Long, SortedSet<RankProduct>> sortedProductByCategoryId = new ConcurrentSkipListMap<>();


    @Override
    public void saveAllSorted(Iterable<RankProduct> products) {
        for (RankProduct product : products) {
            if (sortedProductByCategoryId.get(product.getCategoryId()) == null) {
                TreeSet<RankProduct> treeSet = new TreeSet<>((o1, o2) -> {
                    if (o1.equals(o2)) {
                        return 0;
                    }

                    return o1.getPrice() > o2.getPrice() ? 1 : -1;
                });
                treeSet.add(product);
                sortedProductByCategoryId.put(product.getCategoryId(), treeSet);
            } else {
                SortedSet<RankProduct> treeSet = sortedProductByCategoryId.get(product.getCategoryId());
                boolean contains = treeSet.contains(product);
                if (contains) {
                    treeSet.remove(product);
                    treeSet.add(product);
                } else {
                    treeSet.add(product);
                }
            }

        }
    }

    @Override
    public List<RankProduct> findLowestPriceProductByCategory() {
        List<RankProduct> list = new ArrayList<>();
        for (Long key : sortedProductByCategoryId.keySet()) {
            list.add(sortedProductByCategoryId.get(key).first());
        }
        return list;
    }

    @Override
    public List<RankProduct> findByCategoryId(Long categoryId) {
        return sortedProductByCategoryId.get(categoryId).stream().toList();
    }

    public List<RankProduct> findByCategoryOrderByPriceIsAsc(Long categoryId) {
        return sortedProductByCategoryId.get(categoryId).stream().toList();
    }

    @Override
    public void delete(List<RankProduct> products) {
        for (RankProduct product : products) {
            SortedSet<RankProduct> treeSet = sortedProductByCategoryId.get(product.getCategoryId());
            if (treeSet != null) {
                treeSet.remove(product);
            }
        }
    }
}
