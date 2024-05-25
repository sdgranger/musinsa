package com.musinsa.shop.domain.outfit.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import jakarta.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Brand brand;
    @Column(nullable = false)
    private Long price;

    protected Item() {}

    private Item(Category category, Brand brand, long price) {
        this.category = category;
        this.brand = brand;
        this.price = price;

        this.brand.addItem(this);
    }

    public static Item create(Category category, Brand brand, long price) {
        return new Item(category, brand, price);
    }

    public void changePrice(Long price) {
        if (price == null) {
            throw new IllegalArgumentException();
        }
        this.price = price;
    }


    public String getCategoryName() {
        return category.getName();
    }

    public Long getId() {
        return id;
    }
}
