package com.musinsa.shop.domain.outfit.repository;

import com.musinsa.shop.domain.outfit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
