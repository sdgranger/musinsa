package com.musinsa.shop.event;

import com.musinsa.shop.domain.outfit.entity.Brand;
import com.musinsa.shop.domain.outfit.entity.Product;
import com.musinsa.shop.domain.rank.entity.BrandInfoByLowestPriceSum;
import com.musinsa.shop.infrastruture.BrandProductByPriceSumLocalRepository;
import com.musinsa.shop.infrastruture.SortedProductByCategoryLocalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductEventListener {
    private final BrandProductByPriceSumLocalRepository lowestPriceProductByBrandInMemoryRepository;
    private final SortedProductByCategoryLocalRepository sortedProductByCategoryLocalRepository;

    @EventListener
    public void created(BrandProductCreatedEvent event) {
        save(event.getBrand(), event.getProducts());
    }

    @EventListener
    public void changed(BrandProductChangedEvent event) {
        save(event.getBrand(), event.getProducts());
    }

    @EventListener
    public void removed(BrandProductRemoveEvent event) {
        long totalPrice = event.getProducts().stream().mapToLong(Product::getPrice).sum();
        sortedProductByCategoryLocalRepository.delete(event.getProducts());
        lowestPriceProductByBrandInMemoryRepository.delete(
                new BrandInfoByLowestPriceSum(event.getBrand().getId(), event.getBrand().getName(), totalPrice)
        );
    }

    private void save(Brand brand, List<Product> event1) {
        long totalPrice = event1.stream().mapToLong(Product::getPrice).sum();
        sortedProductByCategoryLocalRepository.saveAllSorted(event1);
        lowestPriceProductByBrandInMemoryRepository.save(
                new BrandInfoByLowestPriceSum(brand.getId(), brand.getName(), totalPrice)
        );
    }


}
