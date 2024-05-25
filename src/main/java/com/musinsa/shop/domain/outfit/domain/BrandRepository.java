package com.musinsa.shop.domain.outfit.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
    Optional<Brand> findByName(String brandName);
}
