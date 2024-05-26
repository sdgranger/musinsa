package com.musinsa.shop.controller;

import com.musinsa.shop.controller.request.BrandProductAddRequest;
import com.musinsa.shop.controller.request.BrandProductChangeRequest;
import com.musinsa.shop.controller.request.BrandProductRemoveRequest;
import com.musinsa.shop.service.BrandProductCrudService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand/products")
public class BrandProductController {

    private final BrandProductCrudService brandProductCrudService;

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody @Valid BrandProductAddRequest productAddRequest) {
        brandProductCrudService.add(productAddRequest.toBrandProductAdd());

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid BrandProductChangeRequest productUpdateRequest) {
        brandProductCrudService.update(productUpdateRequest.toBrandProductChange());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteProduct(@RequestBody @Valid BrandProductRemoveRequest productDeleteRequest) {
        brandProductCrudService.delete(productDeleteRequest.getBrandName());

        return ResponseEntity.ok().build();
    }

}
