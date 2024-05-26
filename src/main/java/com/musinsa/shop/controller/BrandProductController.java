package com.musinsa.shop.controller;

import com.musinsa.shop.controller.request.BrandProductAddRequest;
import com.musinsa.shop.controller.request.BrandProductChangeRequest;
import com.musinsa.shop.controller.request.BrandProductRemoveRequest;
import com.musinsa.shop.service.BrandProductCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/brand/products")
@Tag(name = "브랜드, 상품을 추가, 삭제, 수정 API")
public class BrandProductController {

    private final BrandProductCrudService brandProductCrudService;

    @PostMapping
    @Operation(summary = "브랜드, 상품을 추가", description = "")
    public ResponseEntity<Void> addProduct(@RequestBody @Valid BrandProductAddRequest productAddRequest) {
        brandProductCrudService.add(productAddRequest.toBrandProductAdd());

        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "브랜드 상품 정보를 수정", description = "")
    public ResponseEntity<Void> updateProduct(@RequestBody @Valid BrandProductChangeRequest productUpdateRequest) {
        brandProductCrudService.update(productUpdateRequest.toBrandProductChange());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "브랜드, 상품 삭제", description = "")
    public ResponseEntity<Void> deleteProduct(@RequestBody @Valid BrandProductRemoveRequest productDeleteRequest) {
        brandProductCrudService.delete(productDeleteRequest.getBrandName());

        return ResponseEntity.ok().build();
    }

}
