package com.musinsa.shop.domain.outfit.repository;

import com.musinsa.shop.domain.outfit.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByName(String brandName);
}
