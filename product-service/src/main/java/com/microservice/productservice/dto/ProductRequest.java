package com.microservice.productservice.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String skuCode;
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> categories;
    private List<String> tags;
    private List<String> imageLinks;
}
