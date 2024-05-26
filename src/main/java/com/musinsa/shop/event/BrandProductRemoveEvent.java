package com.musinsa.shop.event;

import com.musinsa.shop.domain.outfit.entity.Brand;
import lombok.Getter;

public record BrandProductRemoveEvent(Brand brand) {
}
