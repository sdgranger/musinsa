package com.musinsa.shop.domain.outfit.repository;

import com.musinsa.shop.domain.outfit.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByBrandId(Long brandId);
}