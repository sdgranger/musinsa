package com.musinsa.shop.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class BrandProductRemoveRequest {
    @Schema(example = "D")
    private String brandName;

}
