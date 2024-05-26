package com.musinsa.shop.controller.response;

import com.musinsa.shop.service.dto.LowestPriceProductByCategory;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LowestPriceProductByCategoryResponse {

    private List<String> headers = List.of(
            "카테고리",
            "브랜드",
            "가격"
    );
    private List<List<String>> data;
    private String totalPrice;
    private String totalPriceFieldName = "총액";

    public LowestPriceProductByCategoryResponse(List<List<String>> data, String totalPrice) {
        this.data = data;
        this.totalPrice = totalPrice;
    }

    public static LowestPriceProductByCategoryResponse from(List<LowestPriceProductByCategory> allProduct) {
        long sum = allProduct.stream().mapToLong(LowestPriceProductByCategory::getPrice).sum();

        List<List<String>> data = new ArrayList<>();
        for (LowestPriceProductByCategory product : allProduct) {
            List<String> dataList = new ArrayList<>();
            dataList.add(product.getCategoryName());
            dataList.add(product.getBrandName());
            dataList.add(new DecimalFormat("###,###").format(product.getPrice()));
            data.add(dataList);
        }

        return new LowestPriceProductByCategoryResponse(data, new DecimalFormat("###,###").format(sum));
    }
}
