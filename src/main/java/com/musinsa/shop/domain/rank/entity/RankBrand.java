package com.musinsa.shop.domain.rank.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode(of = "id")
public class RankBrand {

    private Long id;
    private String name;
    private List<RankProduct> products;
    private RankBrand(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    protected RankBrand(Long id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    protected RankBrand() {

    }

    public static RankBrand create(Long id, String name) {
        return new RankBrand(id, name);
    }

    public void addProduct(RankProduct product) {
        this.products.add(product);
    }

    public void removeAllProduct() {
        products.clear();
    }

    public void addProducts(List<RankProduct> products) {
        this.products.addAll(products);
    }
}
