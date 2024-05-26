package com.musinsa.shop.event;

import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.rank.entity.RankBrand;
import com.musinsa.shop.domain.rank.entity.RankCategory;
import com.musinsa.shop.domain.rank.entity.RankProduct;
import com.musinsa.shop.service.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductEventListener {
    private final RankService rankService;

    @EventListener
    public void created(BrandProductCreatedEvent event) {
        rankService.save(event.getBrand().getId(), event.getBrand().getName(), convert(event.getProducts()));
    }

    @EventListener
    public void changed(BrandProductChangedEvent event) {
        rankService.save(event.getBrand().getId(), event.getBrand().getName(), convert(event.getProducts()));
    }

    @EventListener
    public void removed(BrandProductRemoveEvent event) {
        rankService.remove(event.getBrand().getId(), event.getBrand().getName(), convert(event.getProducts()));
    }

    private List<RankProduct> convert(List<Product> products) {

        return products.stream().map(this::from).collect(Collectors.toList());
    }

    private RankProduct from(Product product) {
        return RankProduct.create(product.getId(),
                RankCategory.create(product.getCategory().getId(), product.getCategoryName()),
                RankBrand.create(product.getBrand().getId(), product.getBrand().getName()), product.getPrice()
        );
    }

}
