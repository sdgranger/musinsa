package com.musinsa.shop.infrastruture;

import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.rank.repository.RankProductByCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListMap;

@Repository
public class SortedProductByCategoryLocalRepository implements RankProductByCategoryRepository {
    private final Map<Long, SortedSet<Product>> sortedProductByCategoryId = new ConcurrentSkipListMap<>();


    @Override
    public void saveAllSorted(Iterable<Product> products) {
        for (Product product : products) {
            if (sortedProductByCategoryId.get(product.getCategory().getId()) == null) {
                TreeSet<Product> treeSet = new TreeSet<>((o1, o2) -> {
                    if (o1.equals(o2)) {
                        return 0;
                    }

                    return o1.getPrice() > o2.getPrice() ? 1 : -1;
                });
                treeSet.add(product);
                sortedProductByCategoryId.put(product.getCategory().getId(), treeSet);
            } else {
                SortedSet<Product> treeSet = sortedProductByCategoryId.get(product.getCategory().getId());
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
    public List<Product> findLowPriceProductByCategory() {
        List<Product> list = new ArrayList<>();
        for (Long key : sortedProductByCategoryId.keySet()) {
            list.add(sortedProductByCategoryId.get(key).first());
        }
        return list;
    }

    @Override
    public List<Product> findByCategoryId(Long categoryId) {
        return sortedProductByCategoryId.get(categoryId).stream().toList();
    }

    public List<Product> findByCategoryOrderByPriceIsAsc(Long categoryId) {
        return sortedProductByCategoryId.get(categoryId).stream().toList();
    }

    public void delete(List<Product> products) {
        for (Product product : products) {
            SortedSet<Product> treeSet = sortedProductByCategoryId.get(product.getCategory().getId());
            if (treeSet != null) {
                treeSet.remove(product);
            }
        }
    }
}
