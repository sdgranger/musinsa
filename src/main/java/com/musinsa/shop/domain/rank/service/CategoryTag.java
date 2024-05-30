package com.musinsa.shop.domain.rank.service;

public class CategoryTag {
    private Long id;

    private String name;

    public CategoryTag(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
