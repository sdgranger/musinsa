package com.musinsa.shop.controller.response;

import com.musinsa.shop.service.dto.LowestPriceProductByCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LowestPriceProductByCategoryResponse {

    @Schema(example = "[ \"카테고리\", \"브랜드\", \"가격\"]")
    private List<String> headers = List.of(
            "카테고리",
            "브랜드",
            "가격"
    );
    @Schema(example = "[\n" +
            "        [\n" +
            "            \"상의\",\n" +
            "            \"J\",\n" +
            "            \"1,100\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"아우터\",\n" +
            "            \"J\",\n" +
            "            \"2,200\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"바지\",\n" +
            "            \"J\",\n" +
            "            \"3,300\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"스니커즈\",\n" +
            "            \"J\",\n" +
            "            \"3,400\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"가방\",\n" +
            "            \"J\",\n" +
            "            \"4,500\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"모자\",\n" +
            "            \"J\",\n" +
            "            \"5,600\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"양말\",\n" +
            "            \"J\",\n" +
            "            \"6,700\"\n" +
            "        ],\n" +
            "        [\n" +
            "            \"액세서리\",\n" +
            "            \"J\",\n" +
            "            \"7,800\"\n" +
            "        ]\n" +
            "    ]")
    private List<List<String>> data;
    @Schema(example ="250,000")
    private String totalPrice;
    @Schema(example ="총액")
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
