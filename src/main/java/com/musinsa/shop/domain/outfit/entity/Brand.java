package com.musinsa.shop.domain.outfit.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Item> items;
    private Brand(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    protected Brand() {

    }

    public static Brand create(String name) {
        return new Brand(name);
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
