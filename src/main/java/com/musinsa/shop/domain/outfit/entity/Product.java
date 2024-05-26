package com.musinsa.shop.domain.outfit.entity;

import com.musinsa.shop.error.InvalidArgumentException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import jakarta.persistence.*;

@Entity
@Getter
@EqualsAndHashCode(of = "id")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Brand brand;
    @Column(nullable = false)
    private Long price;

    protected Product() {}

    private Product(Category category, Brand brand, long price) {
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    protected Product(Long id, Category category, Brand brand, long price) {
        this.id = id;
        this.category = category;
        this.brand = brand;
        this.price = price;
    }

    public static Product create(Category category, Brand brand, long price) {
        return new Product(category, brand, price);
    }

    public void changePrice(Long price) {
        if (price == null) {
            throw new InvalidArgumentException();
        }
        this.price = price;
    }


    public String getCategoryName() {
        return category.getName();
    }

    public Long getId() {
        return id;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
