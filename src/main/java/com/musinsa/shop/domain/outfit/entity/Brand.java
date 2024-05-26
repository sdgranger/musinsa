package com.musinsa.shop.domain.outfit.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "brand", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Product> products;
    private Brand(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    private Brand(String name, List<Product> products) {
        this.name = name;
        this.products = products;
        for (Product product : products) {
            product.setBrand(this);
        }
    }

    protected Brand() {

    }

    public static Brand create(String name) {
        return new Brand(name);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public void removeAllProduct() {
        products.clear();
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }
}
