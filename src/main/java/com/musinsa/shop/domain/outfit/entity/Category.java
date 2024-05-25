package com.musinsa.shop.domain.outfit.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, unique = true, nullable = false)
    private String name;

    protected Category() {

    }

    private Category(String name) {
        this.name = name;
    }

    protected Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
