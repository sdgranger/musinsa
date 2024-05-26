package com.musinsa.shop.domain.rank.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class RankCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, unique = true, nullable = false)
    private String name;

    protected RankCategory() {

    }

    private RankCategory(String name) {
        this.name = name;
    }

    protected RankCategory(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static RankCategory create(Long id, String name) {
        return new RankCategory(id, name);
    }

}
